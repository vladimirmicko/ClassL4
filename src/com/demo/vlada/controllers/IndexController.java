package com.demo.vlada.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.vlada.classes.baseobject.interfaces.Model1;
import com.demo.vlada.classloading.BaseObjectFactory;
import com.demo.vlada.classloading.ObjectFactory;
import com.demo.vlada.classloading.PropertyFactory;
import com.demo.vlada.dto.FileEDto;
import com.demo.vlada.dto.PersistedFileDto;
import com.demo.vlada.dto.Response;
import com.demo.vlada.dto.TextAreaDto;
import com.demo.vlada.entities.PersistedFile;
import com.demo.vlada.services.FileService;

/**
 * 
 * @author randjelovicv
 *
 */

@RestController
@RequestMapping(value = "/rest")
public class IndexController {

	@Autowired
	private FileService fileService;

	@Autowired
	private BaseObjectFactory baseObjectFactory;
	
	@Autowired
	private PropertyFactory propertyFactory;

	@Resource
	private Environment env;

	private Model1 m1;

	@RequestMapping(value = "/download", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ResponseEntity<Response> addFile(@RequestBody List<PersistedFileDto> filesDto) throws IOException {
		Long startTime = System.nanoTime();
		try {
			for (PersistedFileDto file : filesDto) {
				String className = file.getFileName().split("\\.")[0];
				m1 = (Model1) baseObjectFactory.create(className);
				System.out.println("Class: " + className + "       calculate(2, 3): " + m1.calculate(2, 3));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Response>(new Response("You failed to download a file: " + e.getMessage()),
					HttpStatus.EXPECTATION_FAILED);
		}
		Long endTime = System.nanoTime();
		Long execTime = (endTime - startTime);
		System.out.println("---------------------------------------- Class loading time measurement:");
		System.out.println("Execution time: " + execTime / 1000000 + "ms");
		return new ResponseEntity<Response>(new Response("Successfully downloaded all files."), HttpStatus.OK);
	}

	@RequestMapping(value = "/files", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public ResponseEntity<List<PersistedFileDto>> allFiles() {
		return new ResponseEntity<List<PersistedFileDto>>(fileService.getDtoFiles(), HttpStatus.OK);
	}

	@RequestMapping(value = "/calculate", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ResponseEntity<Response> calculateResult(@RequestBody FileEDto fileDto) {
		String className = fileDto.getFileName().split("\\.")[0];
		try {
			m1 = (Model1) baseObjectFactory.create(className);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Integer responseInt = m1.calculate(fileDto.getGrossSalary().intValue(), 1);
		System.out.println("ResponseInt: " + responseInt);
		System.out.println("-----------------------------------------------------------------");
		return new ResponseEntity<Response>(new Response(responseInt), HttpStatus.OK);
	}

	@RequestMapping(value = "/executeTextArea", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ResponseEntity<Response> executeTextArea(@RequestBody TextAreaDto text) {
		return new ResponseEntity<Response>(new Response("Response"), HttpStatus.OK);
	}

	@RequestMapping(value = "/executeFile/{id}", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ResponseEntity<Response> executeFiles(@PathVariable(value = "id") Integer fileId) {
		ResourceBundle bundle = null;
		try {
			bundle = propertyFactory.create(fileId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Property parameter testParam: " + bundle.getString("testParam"));
		return new ResponseEntity<Response>(new Response(bundle.getString("testParam")), HttpStatus.OK);
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ResponseEntity<Response> uploadFile(@RequestParam(name = "file") MultipartFile file) {
		if (!file.isEmpty())
			return this.upload(file);
		return new ResponseEntity<Response>(new Response("Unable to upload. File is empty."),
				HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(value = "/addFiles", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ResponseEntity<Response> addFile(@RequestBody PersistedFile file) {
		// if(!fileService.isFile(file)) {
		// fileService.saveOrUpdate(file);
		// return new ResponseEntity<Response>(new Response("File successfully
		// added."), HttpStatus.OK);
		// }
		// return new ResponseEntity<Response>(new Response("File already exists
		// with that name!"), HttpStatus.IM_USED);
		return new ResponseEntity<Response>(new Response("Adding files not supported yet!"), HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/editFile", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ResponseEntity<Response> editFile(@RequestBody PersistedFileDto file) {
		PersistedFile loaded = fileService.getPersistedFileById(file.getId());
		loaded.setName(file.getFileName());
		fileService.saveOrUpdate(loaded);
		return new ResponseEntity<Response>(new Response("File successfully updated."), HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ResponseEntity<Response> deleteFile(@PathVariable(value = "id") Integer fileId) {
		PersistedFile file = fileService.getPersistedFileById(fileId);
		fileService.remove(file);
		return new ResponseEntity<Response>(new Response("File successfully removed."), HttpStatus.OK);
	}

	private ResponseEntity<Response> upload(MultipartFile file) {
		try {
			String fileName = file.getOriginalFilename();
			byte[] bytes = file.getBytes();
			PersistedFile pf = new PersistedFile();
			pf.setFileBytes(bytes);
			pf.setName(fileName);
			PersistedFile loaded = (PersistedFile) fileService.getFileByName(pf.getName());
			if (loaded != null)
				pf.setId(loaded.getId());
			fileService.saveOrUpdate(pf);
			return new ResponseEntity<Response>(new Response("You have successfully uploaded " + fileName,
					"C:/Work/Projects/VladaDemo/" + fileName), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response("You failed to upload a file: " + e.getMessage()),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
}
