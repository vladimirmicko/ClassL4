package com.demo.vlada.dto;

public class PersistedFileDto {
	public Integer id;
	public String fileName;
	
	//public PersistedFileDto() {}
	
//	public PersistedFileDto(Integer id, String fileName) {
//		this.id = id;
//		this.fileName = fileName;
//	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
