package com.FileUplDownSpring.model;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "files")
public class FileEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 
	@Column(name="file_name")
	private String fileName;
	
	@Column(name = "content_type")
	private String contentType;
	
	@Lob
	@Column(name= "file")
	private byte[] data;  //lob represents large object type
	
	

	@Override
	public String toString() {
		return "FileEntity [id=" + id + ", fileName=" + fileName + ", contentType=" + contentType + ", data="
				+ Arrays.toString(data) + "]";
	}

	public FileEntity() {
		super();
	}

	public FileEntity(String fileName, String contentType, byte[] data) {
		super();
		this.fileName = fileName;
		this.contentType = contentType;
		this.data = data;
	}

	public Long getId() {
		return id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	
	
	
}
