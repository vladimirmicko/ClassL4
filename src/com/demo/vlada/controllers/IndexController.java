package com.demo.vlada.controllers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

import com.demo.vlada.classloading.GenericFactory;
import com.demo.vlada.dto.FileEDto;
import com.demo.vlada.dto.PersistedFileDto;
import com.demo.vlada.dto.TextAreaDto;
import com.demo.vlada.entities.PersistedFile;
import com.demo.vlada.interfaces.LocalModule;
import com.demo.vlada.network.Response;
import com.demo.vlada.services.FileService;
import com.demo.vlada.util.UtilHelper;

@RestController
@RequestMapping(value="/rest")
public class IndexController {
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private GenericFactory genericFactory;
	
	@Resource
	private Environment env;
	
	@RequestMapping(value="/download", method=RequestMethod.POST, produces="application/json; charset=UTF-8")
	public ResponseEntity<Response> addFile(@RequestBody List<PersistedFileDto> filesDto) throws IOException {
		try {
			List<PersistedFile> files = new ArrayList<PersistedFile>();
			for(PersistedFileDto file : filesDto) {
				files.add((PersistedFile)fileService.getPersistedFileById(file.getId()));
			}
			for(PersistedFile pf : files) {
				InputStream myInputStream = new ByteArrayInputStream(pf.getFileBytes());
				System.out.println("********************************");
				String className = pf.getName().split("\\.")[0];
				System.out.println("ClassName:"+className);
				LocalModule lm =(LocalModule) genericFactory.create("com.demo.vlada.interfaces."+className, myInputStream, LocalModule.class.getClassLoader());
//				LocalModule lm =(LocalModule) genericFactory.create("com.demo.vlada.interfaces.Micko", myInputStream, LocalModule.class.getClassLoader());
				lm.testAdd();
//				File fileS = new File("C:\\MyDocuments\\Misc\\"+pf.getName());
//				FileOutputStream fos = new FileOutputStream(fileS);
//				fos.write(pf.getFileBytes());
//				fos.close();
//				System.out.println("DONE");
			}
		} catch (Exception e) {
            return new ResponseEntity<Response>(new Response("You failed to upload a file: " + e.getMessage()), HttpStatus.EXPECTATION_FAILED);
        }
		return new ResponseEntity<Response>(new Response("Successfully downloaded all files."), HttpStatus.OK);
	}
	
	@RequestMapping(value="/files", method=RequestMethod.GET, produces="application/json; charset=UTF-8")
	public ResponseEntity<List<PersistedFileDto>> allFiles() {
		return new ResponseEntity<List<PersistedFileDto>>(fileService.getDtoFiles(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/calculate", method=RequestMethod.POST, produces="application/json; charset=UTF-8")
	public ResponseEntity<Response> calculateResult(@RequestBody FileEDto fileDto) {
		PersistedFile file = fileService.getPersistedFileById(fileDto.getFileId());
		return new ResponseEntity<Response>(new Response(UtilHelper.calculateNetSalary(fileDto.getGrossSalary(), file.getName())), HttpStatus.OK);
	}
	
	@RequestMapping(value="/executeTextArea", method=RequestMethod.POST, produces="application/json; charset=UTF-8")
	public ResponseEntity<Response> executeTextArea(@RequestBody TextAreaDto text) {
		return new ResponseEntity<Response>(new Response(UtilHelper.executeTextArea(text)), HttpStatus.OK);
	}
	
	@RequestMapping(value="/executeFile/{id}", method=RequestMethod.POST, produces="application/json; charset=UTF-8")
	public ResponseEntity<Response> executeFiles(@PathVariable(value="id") Integer fileId) {
		PersistedFile file = fileService.getPersistedFileById(fileId);
		return new ResponseEntity<Response>(new Response(UtilHelper.executeFiles(file)), HttpStatus.OK);
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public ResponseEntity<Response> uploadFile(@RequestParam(name="file") MultipartFile file) {
		if (!file.isEmpty())
            return this.upload(file);
        return new ResponseEntity<Response>(new Response("Unable to upload. File is empty."), HttpStatus.EXPECTATION_FAILED);
	}
	
	@RequestMapping(value="/addFiles", method=RequestMethod.POST, produces="application/json; charset=UTF-8")
	public ResponseEntity<Response> addFile(@RequestBody PersistedFile file) {
//		if(!fileService.isFile(file)) {
//			fileService.saveOrUpdate(file);
//			return new ResponseEntity<Response>(new Response("File successfully added."), HttpStatus.OK);
//		}
//		return new ResponseEntity<Response>(new Response("File already exists with that name!"), HttpStatus.IM_USED);
		return new ResponseEntity<Response>(new Response("Adding files not supported yet!"), HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/editFile", method=RequestMethod.POST, produces="application/json; charset=UTF-8")
	public ResponseEntity<Response> editFile(@RequestBody PersistedFileDto file) {
		PersistedFile loaded = fileService.getPersistedFileById(file.getId());
		loaded.setName(file.getFileName());
		fileService.saveOrUpdate(loaded);
		return new ResponseEntity<Response>(new Response("File successfully updated."), HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.POST, produces="application/json; charset=UTF-8")
	public ResponseEntity<Response> deleteFile(@PathVariable(value="id") Integer fileId) {
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
            PersistedFile loaded = (PersistedFile)fileService.isFile(pf.getName());
            if(loaded!=null)
            	pf.setId(loaded.getId());
            fileService.saveOrUpdate(pf);
            return new ResponseEntity<Response>(new Response("You have successfully uploaded " + fileName, "C:/Work/Projects/VladaDemo/"+fileName), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Response>(new Response("You failed to upload a file: " + e.getMessage()), HttpStatus.EXPECTATION_FAILED);
        }
	}
}
