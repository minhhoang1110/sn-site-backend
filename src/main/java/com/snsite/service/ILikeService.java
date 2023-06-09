package com.snsite.service;

import java.util.List;

import com.snsite.dto.LikeDto;

public interface ILikeService {
	public List<LikeDto> getListLike(Long postId);

	public LikeDto saveLike(LikeDto likeDto);

	public boolean deleteLike(Long id);
}
