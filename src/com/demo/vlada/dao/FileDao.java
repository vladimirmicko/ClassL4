package com.demo.vlada.dao;

import java.util.List;

import com.demo.vlada.dto.PersistedFileDto;
import com.demo.vlada.entities.PersistedFile;

public interface FileDao {
	public void saveOrUpdate(PersistedFile file);
	public List<PersistedFile> getFiles();
	public PersistedFile getPersistedFileById(Integer id);
	public List<PersistedFileDto> getDtoFiles();
}
