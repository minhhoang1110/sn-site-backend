package com.snsite.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.snsite.dto.LikeDto;
import com.snsite.entity.LikeEntity;
import com.snsite.entity.PostEntity;
import com.snsite.entity.UserEntity;
import com.snsite.repository.PostRepository;
import com.snsite.repository.UserRepository;

@Component
public class LikeConverter {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private UserConverter userConverter;

	public LikeEntity toEntity(LikeDto likeDto) {
		LikeEntity likeEntity = new LikeEntity();
		UserEntity userEntity = userRepository.findById(likeDto.getUserId()).get();
		PostEntity postEntity = postRepository.findById(likeDto.getPostId()).get();
		likeEntity.setUserLike(userEntity);
		likeEntity.setLikeOfPost(postEntity);
		return likeEntity;
	}

	public LikeEntity toEntity(LikeDto likeDto, LikeEntity likeEntity) {
		UserEntity userEntity = userRepository.findById(likeDto.getUserId()).get();
		PostEntity postEntity = postRepository.findById(likeDto.getPostId()).get();
		likeEntity.setUserLike(userEntity);
		likeEntity.setLikeOfPost(postEntity);
		return likeEntity;
	}

	public LikeDto toDto(LikeEntity likeEntity) {
		LikeDto likeDto = new LikeDto();
		likeDto.setId(likeEntity.getId());
		likeDto.setCreatedAt(likeEntity.getCreatedAt());
		likeDto.setCreatedBy(likeEntity.getCreatedBy());
		likeDto.setUpdatedAt(likeEntity.getUpdatedAt());
		likeDto.setUpdatedBy(likeEntity.getUpdatedBy());
		likeDto.setIsActive(likeEntity.getIsActive());
		likeDto.setUserId(likeEntity.getUserLike().getId());
		likeDto.setUser(userConverter.toDto(likeEntity.getUserLike()));
		likeDto.setPostId(likeEntity.getLikeOfPost().getId());
		return likeDto;
	}

	public List<LikeDto> toListDto(List<LikeEntity> likeEntities) {
		if (likeEntities.size() == 0)
			return null;
		List<LikeDto> likeDtos = new ArrayList<>();
		for (LikeEntity likeEntity : likeEntities) {
			likeDtos.add(this.toDto(likeEntity));
		}
		return likeDtos;
	}
}
