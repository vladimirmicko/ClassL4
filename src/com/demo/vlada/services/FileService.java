package com.demo.vlada.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.vlada.dao.FileDao;
import com.demo.vlada.dto.PersistedFileDto;
import com.demo.vlada.entities.PersistedFile;

@Service
public class FileService {
	@Autowired
	private FileDao fileDao;
	
	public void saveOrUpdate(PersistedFile file) {
		fileDao.saveOrUpdate(file);
	}
	
	public List<PersistedFile> getFiles() {
		return fileDao.getFiles();
	}
	
	public PersistedFile getPersistedFileById(Integer id) {
		return fileDao.getPersistedFileById(id);
	}
	
	public List<PersistedFileDto> getDtoFiles() {
		return fileDao.getDtoFiles();
	}
	
	public PersistedFile getFileByName(String fileName) {
		return fileDao.getFileByName(fileName);
	}
	
	public void remove(PersistedFile file) {
		fileDao.remove(file);
	}
}