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

import com.snsite.dto.CommentDto;
import com.snsite.logger.ILoggerService;
import com.snsite.service.ICommentService;
import com.snsite.type.respone.CommonRespone;

@RestController
public class CommentController {
	@Autowired
	private ICommentService commentService;
	@Autowired
	private ILoggerService loggerService;

	@GetMapping(value = "/api/comment")
	@ResponseBody
	public CommonRespone<List<CommentDto>> getListComment(@RequestParam("postId") Long postId) {
		return null;
	}

	@PostMapping(value = "/api/comment")
	@ResponseBody
	public CommonRespone<CommentDto> creatComment(@RequestBody CommentDto commentDto, HttpServletRequest request) {
		loggerService.infoCallEndpoint(CommentController.class, request);
		CommentDto data = commentService.saveComment(commentDto);
		int code = HttpServletResponse.SC_OK;
		String message = "Create Successfully Comment";
		boolean success = true;
		if (data == null) {
			code = HttpServletResponse.SC_BAD_REQUEST;
			message = "Create Comment Error";
			success = false;
		}
		loggerService.infoCompleteEndpoint(CommentController.class, request);
		return new CommonRespone<CommentDto>(code, success, message, data);
	}

	@PutMapping(value = "/api/comment/{id}")
	@ResponseBody
	public CommonRespone<CommentDto> updateComment(@PathVariable Long id, @RequestBody CommentDto commentDto,
			HttpServletRequest request) {
		loggerService.infoCallEndpoint(CommentController.class, request);
		commentDto.setId(id);
		CommentDto data = commentService.saveComment(commentDto);
		int code = HttpServletResponse.SC_OK;
		String message = "Update Successfully Comment";
		boolean success = true;
		if (data == null) {
			code = HttpServletResponse.SC_BAD_REQUEST;
			message = "Update Comment Error";
			success = false;
		}
		loggerService.infoCompleteEndpoint(CommentController.class, request);
		return new CommonRespone<CommentDto>(code, success, message, data);
	}

	@DeleteMapping(value = "/api/comment/{id}")
	@ResponseBody
	public CommonRespone<CommentDto> deleteComment(@PathVariable Long id, HttpServletRequest request) {
		loggerService.infoCallEndpoint(CommentController.class, request);
		boolean data = commentService.deleteComment(id);
		int code = HttpServletResponse.SC_OK;
		String message = "Delete Successfully Comment";
		boolean success = true;
		if (!data) {
			code = HttpServletResponse.SC_BAD_REQUEST;
			message = "Delete Comment Error";
			success = false;
		}
		loggerService.infoCompleteEndpoint(CommentController.class, request);
		return new CommonRespone<CommentDto>(code, success, message, null);
	}
}
