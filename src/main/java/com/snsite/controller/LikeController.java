package com.snsite.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.snsite.dto.LikeDto;
import com.snsite.logger.ILoggerService;
import com.snsite.service.ILikeService;
import com.snsite.type.respone.CommonRespone;

@RestController
public class LikeController {
	@Autowired
	private ILikeService likeService;
	@Autowired
	private ILoggerService loggerService;

	@GetMapping(value = "/api/like")
	@ResponseBody
	public CommonRespone<List<LikeDto>> getListLike(@RequestParam("postId") Long postId) {
		return null;
	}

	@PostMapping(value = "/api/like")
	@ResponseBody
	public CommonRespone<LikeDto> createLike(@RequestBody LikeDto likeDto, HttpServletRequest request) {
		loggerService.infoCallEndpoint(LikeController.class, request);
		LikeDto data = likeService.saveLike(likeDto);
		int code = HttpServletResponse.SC_OK;
		String message = "Create Successfully Like";
		boolean success = true;
		if (data == null) {
			code = HttpServletResponse.SC_BAD_REQUEST;
			message = "Create Like Error";
			success = false;
		}
		loggerService.infoCompleteEndpoint(LikeController.class, request);
		return new CommonRespone<LikeDto>(code, success, message, data);
	}

	@PutMapping(value = "/api/like/{id}")
	@ResponseBody
	public CommonRespone<LikeDto> updateLike(@PathVariable Long id, @RequestBody LikeDto likeDto) {
		return null;
	}

	@DeleteMapping(value = "/api/like/{id}")
	@ResponseBody
	public CommonRespone<LikeDto> deleteLike(@PathVariable Long id, HttpServletRequest request) {
		loggerService.infoCallEndpoint(LikeController.class, request);
		boolean data = likeService.deleteLike(id);
		int code = HttpServletResponse.SC_OK;
		String message = "Delete Successfully Like";
		boolean success = true;
		if (!data) {
			code = HttpServletResponse.SC_BAD_REQUEST;
			message = "Delete Like Error";
			success = false;
		}
		loggerService.infoCompleteEndpoint(LikeController.class, request);
		return new CommonRespone<LikeDto>(code, success, message, null);
	}
}
