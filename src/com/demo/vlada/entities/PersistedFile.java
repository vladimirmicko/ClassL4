package com.demo.vlada.entities;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="pfiles")
public class PersistedFile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="pfiles_gen", sequenceName="pfiles_seq", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pfiles_gen")
	@Column(name="id")
	private Integer id;
	
	@Column(name="fileName")
	private String name;
	
	@Lob
	@Column(name="fileBytes")
	private byte[] fileBytes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getFileBytes() {
		return fileBytes;
	}

	public void setFileBytes(byte[] fileBytes) {
		this.fileBytes = fileBytes;
	}

	@Override
	public String toString() {
		return "PersistedFile [name=" + name + ", fileBytes=" + Arrays.toString(fileBytes) + "]";
	}
}
