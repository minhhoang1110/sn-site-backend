package com.snsite.service;

import java.util.List;

import com.snsite.dto.PostDto;

public interface IPostService {
	public List<PostDto> getListPost(Long userId);

	public PostDto getPostDetail(Long id);

	public PostDto savePost(PostDto postDto);
}
