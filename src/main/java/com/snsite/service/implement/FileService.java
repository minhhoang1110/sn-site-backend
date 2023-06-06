package com.snsite.service.implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.snsite.dto.FileDto;
import com.snsite.service.IFileService;
import com.snsite.type.request.UploadFileRequest;

@Service
public class FileService implements IFileService {

	@Override
	public FileDto uploadFile(UploadFileRequest uploadFileRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FileDto> getListFile(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
