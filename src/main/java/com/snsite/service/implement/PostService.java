package com.snsite.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snsite.converter.PostConverter;
import com.snsite.dto.FriendShipDto;
import com.snsite.dto.LikeDto;
import com.snsite.dto.PostDto;
import com.snsite.entity.PostEntity;
import com.snsite.entity.UserEntity;
import com.snsite.helper.AuthenticationHelper;
import com.snsite.repository.PostRepository;
import com.snsite.repository.UserRepository;
import com.snsite.repository.customize.ICustomPostRepository;
import com.snsite.service.IFriendShipService;
import com.snsite.service.ILikeService;
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
	@Autowired
	private IFriendShipService friendShipService;
	@Autowired
	private ILikeService likeService;

	private PostDto getLikeOfPost(PostDto postDto) {
		List<LikeDto> likeDtos = likeService.getListLike(postDto.getId());
		if (likeDtos != null) {
			postDto.setLikes(likeDtos);
			postDto.setCountOfLikes(likeDtos.size());
		}
		return postDto;
	}

	@Override
	public List<PostDto> getListPost(Long userId) {
		UserEntity contextUser = authenticationHelper.getUserFromContext();
		List<PostEntity> result = new ArrayList<>();
		List<PostEntity> postEntities;
		if (userId == null) {
			postEntities = customPostRepository.findAllAvailablePost();
			for (PostEntity postEntity : postEntities) {
				if (postEntity.getUserPost().getId() == contextUser.getId()) {
					result.add(postEntity);
					continue;
				}
				if (postEntity.getSharedType() == PostDto.SharedTypePrivate)
					continue;
				if (postEntity.getSharedType() == PostDto.SharedTypePublic) {
					result.add(postEntity);
					continue;
				}
				FriendShipDto friendShipDto = friendShipService.getFriendShipDetail(postEntity.getUserPost().getId());
				if (friendShipDto == null)
					continue;
				result.add(postEntity);
			}
		} else {
			Optional<UserEntity> userEntity = userRepository.findById(userId);
			if (!userEntity.isPresent()) {
				return null;
			}
			postEntities = postRepository.findAllByUserPostOrderByUpdatedAtDesc(userEntity.get());
			result = postEntities;
		}
		List<PostDto> postDtos = new ArrayList<>();
		for (PostEntity postEntity : result) {
			postDtos.add(this.getLikeOfPost(postConverter.toDto(postEntity)));
		}
		return postDtos;
	}

	@Override
	public PostDto getPostDetail(Long id) {
		Optional<PostEntity> postEntity = postRepository.findById(id);
		if (!postEntity.isPresent()) {
			return null;
		}
		PostDto postDto = postConverter.toDto(postEntity.get());
		postDto = getLikeOfPost(postDto);
		return postDto;
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
