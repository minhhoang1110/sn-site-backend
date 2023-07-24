package com.snsite.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.snsite.converter.PostConverter;
import com.snsite.dto.CommentDto;
import com.snsite.dto.FriendShipDto;
import com.snsite.dto.LikeDto;
import com.snsite.dto.PostDto;
import com.snsite.entity.PostEntity;
import com.snsite.entity.UserEntity;
import com.snsite.helper.AuthenticationHelper;
import com.snsite.repository.PostRepository;
import com.snsite.repository.UserRepository;
import com.snsite.repository.customize.ICustomPostRepository;
import com.snsite.service.ICommentService;
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
	@Autowired
	private ICommentService commentService;

	private PostDto getLikeAndCommentOfPost(PostDto postDto) {
		List<LikeDto> likeDtos = likeService.getListLike(postDto.getId());
		if (likeDtos != null) {
			postDto.setLikes(likeDtos);
			postDto.setCountOfLikes(likeDtos.size());
		}
		List<CommentDto> commentDtos = commentService.getListComment(postDto.getId());
		if (commentDtos != null) {
			postDto.setComments(commentDtos);
			postDto.setCountOfComments(commentDtos.size());
		}
		return postDto;
	}

	@Cacheable(value = "cachedPosts")
	@Override
	public List<PostDto> getListPost(Long userId, Integer limit, Integer offset, Integer page) {
		UserEntity contextUser = authenticationHelper.getUserFromContext();
		List<PostEntity> result = new ArrayList<>();
		List<PostEntity> postEntities;
		if (userId == null) {
			postEntities = customPostRepository.findAllAvailablePost(limit, offset);
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
			Pageable pageable = PageRequest.of(page, limit, Sort.by("updatedAt").descending());
			postEntities = postRepository.findAllByUserPost(userEntity.get(), pageable);
			result = postEntities;
		}
		List<PostDto> postDtos = new ArrayList<>();
		for (PostEntity postEntity : result) {
			postDtos.add(this.getLikeAndCommentOfPost(postConverter.toDto(postEntity)));
		}
		return postDtos;
	}

	@Cacheable(value = "cachedPost", key = "#id")
	@Override
	public PostDto getPostDetail(Long id) {
		Optional<PostEntity> postEntity = postRepository.findById(id);
		if (!postEntity.isPresent()) {
			return null;
		}
		PostDto postDto = postConverter.toDto(postEntity.get());
		postDto = getLikeAndCommentOfPost(postDto);
		return postDto;
	}

	@CacheEvict(value = "cachedPosts", allEntries = true)
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

	@CacheEvict(value = "cachedPosts", allEntries = true)
	@Override
	public boolean deletePost(Long id) {
		try {
			Optional<PostEntity> postEntity = postRepository.findById(id);
			if (!postEntity.isPresent()) {
				return false;
			}
			List<LikeDto> likeDtos = likeService.getListLike(id);
			if (likeDtos != null && likeDtos.size() > 0) {
				for (LikeDto likeDto : likeDtos) {
					likeService.deleteLike(likeDto.getId());
				}
			}
			List<CommentDto> commentDtos = commentService.getListComment(id);
			if (commentDtos != null && commentDtos.size() > 0) {
				for (CommentDto commentDto : commentDtos) {
					commentService.deleteComment(commentDto.getId());
				}
			}
			postRepository.delete(postEntity.get());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
