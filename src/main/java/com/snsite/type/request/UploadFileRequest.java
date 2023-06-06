package com.snsite.type.request;

import org.springframework.web.multipart.MultipartFile;

public class UploadFileRequest {
	private MultipartFile file;
	private String type;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
