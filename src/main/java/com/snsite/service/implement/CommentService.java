package com.snsite.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snsite.converter.CommentConverter;
import com.snsite.dto.CommentDto;
import com.snsite.entity.CommentEntity;
import com.snsite.entity.PostEntity;
import com.snsite.entity.UserEntity;
import com.snsite.helper.AuthenticationHelper;
import com.snsite.repository.CommentRepository;
import com.snsite.repository.PostRepository;
import com.snsite.service.ICommentService;

@Service
public class CommentService implements ICommentService {
	@Autowired
	private CommentConverter commentConverter;
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private AuthenticationHelper authenticationHelper;
	@Autowired
	private PostRepository postRepository;

	@Override
	public List<CommentDto> getListComment(Long postId) {
		Optional<PostEntity> postEntity = postRepository.findById(postId);
		if (!postEntity.isPresent())
			return null;
		List<CommentEntity> commentEntities = commentRepository.findAllByPostComment(postEntity.get());
		return commentConverter.toListDto(commentEntities);
	}

	@Override
	public CommentDto saveComment(CommentDto commentDto) {
		CommentEntity commentEntity = new CommentEntity();
		UserEntity contextUser = authenticationHelper.getUserFromContext();
		if (commentDto.getId() != null) {
			Optional<CommentEntity> oldData = commentRepository.findById(commentDto.getId());
			if (!oldData.isPresent())
				return null;
			if (oldData.get().getUserComment().getId() != contextUser.getId())
				return null;
			commentEntity = commentConverter.toEntity(commentDto, oldData.get());
		} else {
			if (commentDto.getUserId() != contextUser.getId())
				return null;
			commentEntity = commentConverter.toEntity(commentDto);
		}
		commentEntity = commentRepository.save(commentEntity);
		return commentConverter.toDto(commentEntity);
	}

	@Override
	public boolean deleteComment(Long id) {
		Optional<CommentEntity> commentEntity = commentRepository.findById(id);
		if (!commentEntity.isPresent())
			return false;
		try {
			commentRepository.delete(commentEntity.get());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
