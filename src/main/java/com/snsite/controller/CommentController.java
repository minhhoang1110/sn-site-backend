package com.snsite.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.snsite.dto.CommentDto;
import com.snsite.type.respone.CommonRespone;

@RestController
public class CommentController {
	@GetMapping(value = "/api/comment")
	@ResponseBody
	public CommonRespone<List<CommentDto>> getListComment(@RequestParam("postId") Long postId) {
		return null;
	}

	@PostMapping(value = "/api/comment")
	@ResponseBody
	public CommonRespone<CommentDto> creatComment(@RequestBody CommentDto commentDto) {
		return null;
	}

	@PutMapping(value = "/api/comment/{id}")
	@ResponseBody
	public CommonRespone<CommentDto> updateComment(@PathVariable Long id, @RequestBody CommentDto commentDto) {
		return null;
	}
}
