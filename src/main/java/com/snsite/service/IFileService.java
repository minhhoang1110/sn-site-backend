package com.snsite.service;

import java.util.List;

import com.snsite.dto.FileDto;
import com.snsite.type.request.UploadFileRequest;

public interface IFileService {
	public FileDto uploadFile(UploadFileRequest uploadFileRequest);

	public List<FileDto> getListFile(Long userId);
}
