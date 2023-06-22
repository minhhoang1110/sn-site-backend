package com.snsite.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.snsite.dto.FileDto;
import com.snsite.service.IFileService;
import com.snsite.type.request.UploadFileRequest;
import com.snsite.type.respone.CommonRespone;

@RestController
public class FileController {
	@Autowired
	private IFileService fileService;

	@PostMapping(value = "/api/file")
	@ResponseBody
	public CommonRespone<FileDto> uploadFile(@ModelAttribute UploadFileRequest uploadFileRequest) {
		FileDto fileDto = fileService.uploadFile(uploadFileRequest);
		int code = HttpServletResponse.SC_OK;
		String message = "Upload Successfully File";
		boolean success = true;
		if (fileDto == null) {
			code = HttpServletResponse.SC_BAD_REQUEST;
			message = "Can not Upload File";
			success = false;
		}
		return new CommonRespone<FileDto>(code, success, message, fileDto);
	}

	@GetMapping(value = "/api/file")
	@ResponseBody
	public CommonRespone<List<FileDto>> getListFile(@RequestParam("userId") Long userId) {
		List<FileDto> fileDtos = fileService.getListFile(userId);
		int code = HttpServletResponse.SC_OK;
		String message = "Fetch Successfully File";
		boolean success = true;
		if (fileDtos == null) {
			code = HttpServletResponse.SC_BAD_REQUEST;
			message = "Can not Fetch File";
			success = false;
		}
		return new CommonRespone<List<FileDto>>(code, success, message, fileDtos);
	}
}
