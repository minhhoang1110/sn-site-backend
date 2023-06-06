package com.snsite.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.snsite.dto.FileDto;
import com.snsite.type.request.UploadFileRequest;
import com.snsite.type.respone.CommonRespone;

@RestController
public class FileController {
	@PostMapping(value = "/api/file")
	@ResponseBody
	public CommonRespone<FileDto> uploadFile(@RequestBody UploadFileRequest uploadFileRequest) {
		return null;
	}

	@GetMapping(value = "/api/file")
	@ResponseBody
	public CommonRespone<List<FileDto>> getListFile(@RequestParam("userId") Long userId) {
		return null;
	}
}
