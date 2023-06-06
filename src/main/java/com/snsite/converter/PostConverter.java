package com.snsite.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.snsite.dto.PostDto;
import com.snsite.entity.PostEntity;

@Component
public class PostConverter {
	@Autowired
	private UserConverter userConverter;

	public PostEntity toEntity(PostDto postDto) {
		PostEntity postEntity = new PostEntity();
		postEntity.setSharedType(PostDto.SharedTypeToId.get(postDto.getSharedType()));
		postEntity.setContent(postDto.getContent());
		postEntity.setImageUrls(postDto.getImageUrls());
		return postEntity;
	}

	public PostEntity toEntity(PostDto postDto, PostEntity postEntity) {
		postEntity.setSharedType(PostDto.SharedTypeToId.get(postDto.getSharedType()));
		postEntity.setContent(postDto.getContent());
		postEntity.setImageUrls(postDto.getImageUrls());
		return postEntity;
	}

	public PostDto toDto(PostEntity postEntity) {
		PostDto postDto = new PostDto();
		postDto.setId(postEntity.getId());
		postDto.setUserId(postEntity.getUserPost().getId());
		postDto.setUser(userConverter.toDto(postEntity.getUserPost()));
		postDto.setSharedType(PostDto.SharedTypeToString.get(postEntity.getSharedType()));
		postDto.setContent(postEntity.getContent());
		postDto.setImageUrls(postEntity.getImageUrls());
		postDto.setCreatedAt(postEntity.getCreatedAt());
		postDto.setCreatedBy(postEntity.getCreatedBy());
		postDto.setUpdatedAt(postEntity.getUpdatedAt());
		postDto.setUpdatedBy(postEntity.getUpdatedBy());
		postDto.setIsActive(postEntity.getIsActive());
		return postDto;
	}

	public List<PostDto> toListDto(List<PostEntity> postEntities) {
		if (postEntities.size() == 0)
			return null;
		List<PostDto> postDtos = new ArrayList<>();
		for (PostEntity postEntity : postEntities) {
			postDtos.add(this.toDto(postEntity));
		}
		return postDtos;
	}
}
