package com.snsite.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.snsite.dto.PostDto;
import com.snsite.logger.ILoggerService;
import com.snsite.service.IPostService;
import com.snsite.type.respone.CommonRespone;

@RestController
public class PostController {
	@Autowired
	private IPostService postService;
	@Autowired
	private ILoggerService loggerService;

	@GetMapping(value = "/api/post")
	@ResponseBody
	public CommonRespone<List<PostDto>> getListPost(@RequestParam(value = "userId", required = false) Long userId,
			@RequestParam("limit") Integer limit, @RequestParam("offset") Integer offset,
			@RequestParam("page") Integer page, HttpServletRequest request) {
		loggerService.infoCallEndpoint(PostController.class, request);
		List<PostDto> postDtos = postService.getListPost(userId, limit, offset, page);
		int code = HttpServletResponse.SC_OK;
		String message = "Fetch Successfully Posts";
		boolean success = true;
		if (postDtos == null) {
			code = HttpServletResponse.SC_BAD_REQUEST;
			message = "Can not find Posts";
			success = false;
		}
		loggerService.infoCompleteEndpoint(PostController.class, request);
		return new CommonRespone<List<PostDto>>(code, success, message, postDtos);
	}

	@GetMapping(value = "/api/post/{id}")
	@ResponseBody
	public CommonRespone<PostDto> getPostDetail(@PathVariable("id") Long id, HttpServletRequest request) {
		loggerService.infoCallEndpoint(PostController.class, request);
		PostDto postDto = postService.getPostDetail(id);
		int code = HttpServletResponse.SC_OK;
		String message = "Fetch Successfully Post " + id;
		boolean success = true;
		if (postDto == null) {
			code = HttpServletResponse.SC_BAD_REQUEST;
			message = "Can not find Post " + id;
			success = false;
		}
		loggerService.infoCompleteEndpoint(PostController.class, request);
		return new CommonRespone<PostDto>(code, success, message, postDto);
	}

	@SuppressWarnings("unused")
	@PostMapping(value = "/api/post")
	@ResponseBody
	public CommonRespone<PostDto> createPost(@RequestBody PostDto postDto, HttpServletRequest request) {
		loggerService.infoCallEndpoint(PostController.class, request);
		PostDto data = postService.savePost(postDto);
		int code = HttpServletResponse.SC_OK;
		String message = "Create Successfully Post " + data.getId();
		boolean success = true;
		if (data == null) {
			code = HttpServletResponse.SC_BAD_REQUEST;
			message = "Create Error Post ";
			success = false;
		}
		loggerService.infoCompleteEndpoint(PostController.class, request);
		return new CommonRespone<PostDto>(code, success, message, data);
	}

	@SuppressWarnings("unused")
	@PutMapping(value = "/api/post/{id}")
	@ResponseBody
	public CommonRespone<PostDto> updatePost(@PathVariable("id") Long id, @RequestBody PostDto postDto,
			HttpServletRequest request) {
		loggerService.infoCallEndpoint(PostController.class, request);
		postDto.setId(id);
		PostDto data = postService.savePost(postDto);
		int code = HttpServletResponse.SC_OK;
		String message = "Update Successfully Post " + data.getId();
		boolean success = true;
		if (data == null) {
			code = HttpServletResponse.SC_BAD_REQUEST;
			message = "Update Error Post ";
			success = false;
		}
		loggerService.infoCompleteEndpoint(PostController.class, request);
		return new CommonRespone<PostDto>(code, success, message, data);
	}
}
