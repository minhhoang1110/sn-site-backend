package com.snsite.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.snsite.dto.CommentDto;
import com.snsite.entity.CommentEntity;
import com.snsite.entity.PostEntity;
import com.snsite.entity.UserEntity;
import com.snsite.repository.PostRepository;
import com.snsite.repository.UserRepository;

@Component
public class CommentConverter {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private UserConverter userConverter;

	public CommentEntity toEntity(CommentDto commentDto) {
		CommentEntity commentEntity = new CommentEntity();
		UserEntity userEntity = userRepository.findById(commentDto.getUserId()).get();
		PostEntity postEntity = postRepository.findById(commentDto.getPostId()).get();
		commentEntity.setUserComment(userEntity);
		commentEntity.setPostComment(postEntity);
		commentEntity.setComment(commentDto.getComment());
		return commentEntity;
	}

	public CommentEntity toEntity(CommentDto commentDto, CommentEntity commentEntity) {
		commentEntity.setComment(commentDto.getComment());
		return commentEntity;
	}

	public CommentDto toDto(CommentEntity commentEntity) {
		CommentDto commentDto = new CommentDto();
		commentDto.setId(commentEntity.getId());
		commentDto.setCreatedAt(commentEntity.getCreatedAt());
		commentDto.setCreatedBy(commentEntity.getCreatedBy());
		commentDto.setUpdatedAt(commentEntity.getUpdatedAt());
		commentDto.setUpdatedBy(commentEntity.getUpdatedBy());
		commentDto.setIsActive(commentEntity.getIsActive());
		commentDto.setUserId(commentEntity.getUserComment().getId());
		commentDto.setUser(userConverter.toDto(commentEntity.getUserComment()));
		commentDto.setPostId(commentEntity.getPostComment().getId());
		commentDto.setComment(commentEntity.getComment());
		return commentDto;
	}

	public List<CommentDto> toListDto(List<CommentEntity> commentEntities) {
		if (commentEntities.size() == 0)
			return null;
		List<CommentDto> commentDtos = new ArrayList<>();
		for (CommentEntity commentEntity : commentEntities) {
			commentDtos.add(this.toDto(commentEntity));
		}
		return commentDtos;
	}
}
