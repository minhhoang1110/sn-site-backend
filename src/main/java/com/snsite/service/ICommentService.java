package com.snsite.service;

import java.util.List;

import com.snsite.dto.CommentDto;

public interface ICommentService {
	public List<CommentDto> getListComment(Long postId);

	public CommentDto saveComment(CommentDto commentDto);
}
