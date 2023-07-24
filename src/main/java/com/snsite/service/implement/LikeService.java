package com.snsite.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.snsite.converter.LikeConverter;
import com.snsite.dto.LikeDto;
import com.snsite.dto.NotificationDto;
import com.snsite.entity.LikeEntity;
import com.snsite.entity.PostEntity;
import com.snsite.entity.UserEntity;
import com.snsite.helper.AuthenticationHelper;
import com.snsite.repository.LikeRepository;
import com.snsite.repository.PostRepository;
import com.snsite.service.ILikeService;
import com.snsite.service.INotificationService;

@Service
public class LikeService implements ILikeService {
	@Autowired
	private LikeConverter likeConverter;
	@Autowired
	private LikeRepository likeRepository;
	@Autowired
	private AuthenticationHelper authenticationHelper;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private INotificationService notificationService;

	@Cacheable(value = "cachedLike", key = "#postId", condition = "#postId!=null")
	@Override
	public List<LikeDto> getListLike(Long postId) {
		Optional<PostEntity> postEntity = postRepository.findById(postId);
		if (!postEntity.isPresent())
			return null;
		List<LikeEntity> likeEntities = likeRepository.findAllByLikeOfPost(postEntity.get());
		return likeConverter.toListDto(likeEntities);
	}

	@CacheEvict(value = { "cachedLike", "cachedPosts", "cachedPost" }, allEntries = true)
	@Override
	public LikeDto saveLike(LikeDto likeDto) {
		LikeEntity likeEntity = new LikeEntity();
		UserEntity contextUser = authenticationHelper.getUserFromContext();
		if (likeDto.getUserId() != contextUser.getId())
			return null;
		likeEntity = likeConverter.toEntity(likeDto);
		likeEntity = likeRepository.save(likeEntity);
		notificationService.saveNotificationByPostId(likeEntity.getLikeOfPost().getId(),
				likeEntity.getUserLike().getId(), NotificationDto.TypeLike);
		return likeConverter.toDto(likeEntity);
	}

	@CacheEvict(value = { "cachedLike", "cachedPosts", "cachedPost" }, allEntries = true)
	@Override
	public boolean deleteLike(Long id) {
		Optional<LikeEntity> likeEntity = likeRepository.findById(id);
		if (!likeEntity.isPresent())
			return false;
		try {
			likeRepository.delete(likeEntity.get());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
