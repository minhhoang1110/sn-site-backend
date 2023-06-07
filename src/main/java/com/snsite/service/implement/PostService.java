package com.snsite.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snsite.converter.PostConverter;
import com.snsite.dto.PostDto;
import com.snsite.entity.PostEntity;
import com.snsite.entity.UserEntity;
import com.snsite.helper.AuthenticationHelper;
import com.snsite.repository.PostRepository;
import com.snsite.repository.UserRepository;
import com.snsite.repository.customize.ICustomPostRepository;
import com.snsite.service.IPostService;

@Service
public class PostService implements IPostService {
	@Autowired
	private AuthenticationHelper authenticationHelper;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private ICustomPostRepository customPostRepository;
	@Autowired
	private PostConverter postConverter;
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<PostDto> getListPost(Long userId) {
		List<PostEntity> postEntities;
		if (userId == null) {
			postEntities = customPostRepository.findAllWithFriendShip(userId);
		} else {
			Optional<UserEntity> userEntity = userRepository.findById(userId);
			if (!userEntity.isPresent()) {
				return null;
			}
			postEntities = postRepository.findAllByUserPostOrderByUpdatedAtDesc(userEntity.get());
		}
		return postConverter.toListDto(postEntities);
	}

	@Override
	public PostDto getPostDetail(Long id) {
		Optional<PostEntity> postEntity = postRepository.findById(id);
		if (!postEntity.isPresent()) {
			return null;
		}
		return postConverter.toDto(postEntity.get());
	}

	@Override
	public PostDto savePost(PostDto postDto) {
		PostEntity postEntity = new PostEntity();
		if (postDto.getId() != null) {
			UserEntity contextUser = authenticationHelper.getUserFromContext();
			if (postDto.getUserId() != contextUser.getId()) {
				return null;
			}
			Optional<PostEntity> oldData = postRepository.findById(postDto.getId());
			if (!oldData.isPresent()) {
				return null;
			}
			postEntity = postConverter.toEntity(postDto, oldData.get());
		} else {
			postEntity = postConverter.toEntity(postDto);
		}
		Optional<UserEntity> userEntity = userRepository.findById(postDto.getUserId());
		if (!userEntity.isPresent()) {
			return null;
		}
		postEntity.setUserPost(userEntity.get());
		postEntity = postRepository.save(postEntity);
		return postConverter.toDto(postEntity);
	}

}
