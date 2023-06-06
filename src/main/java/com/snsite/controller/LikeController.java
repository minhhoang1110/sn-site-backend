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

import com.snsite.dto.LikeDto;
import com.snsite.type.respone.CommonRespone;

@RestController
public class LikeController {
	@GetMapping(value = "/api/like")
	@ResponseBody
	public CommonRespone<List<LikeDto>> getListLike(@RequestParam("postId") Long postId) {
		return null;
	}

	@PostMapping(value = "/api/like")
	@ResponseBody
	public CommonRespone<LikeDto> createLike(@RequestBody LikeDto likeDto) {
		return null;
	}

	@PutMapping(value = "/api/like/{id}")
	@ResponseBody
	public CommonRespone<LikeDto> updateLike(@PathVariable Long id, @RequestBody LikeDto likeDto) {
		return null;
	}
}
