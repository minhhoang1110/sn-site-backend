package com.snsite.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.snsite.converter.NotificationConverter;
import com.snsite.dto.NotificationDto;
import com.snsite.entity.NotificationEntity;
import com.snsite.entity.PostEntity;
import com.snsite.entity.UserEntity;
import com.snsite.helper.AuthenticationHelper;
import com.snsite.repository.NotificationRepository;
import com.snsite.repository.PostRepository;
import com.snsite.repository.UserRepository;
import com.snsite.service.INotificationService;

@Service
public class NotificationService implements INotificationService {
	private final int page = 0;
	private final int limit = 20;
	@Autowired
	private NotificationConverter notificationConverter;
	@Autowired
	private NotificationRepository notificationRepository;
	@Autowired
	private AuthenticationHelper authenticationHelper;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private UserRepository userRepository;

	@Cacheable(value = "cachedNotification")
	@Override
	public List<NotificationDto> getListNotification() {
		Pageable pageable = PageRequest.of(this.page, this.limit, Sort.by("updatedAt").descending());
		UserEntity contextUser = authenticationHelper.getUserFromContext();
		List<NotificationEntity> notificationEntities = notificationRepository.findAllByUserNotification(contextUser,
				pageable);
		return notificationConverter.toListDto(notificationEntities);
	}

	@Override
	public NotificationDto readNotification(Long id) {
		Optional<NotificationEntity> data = notificationRepository.findById(id);
		if (!data.isPresent())
			return null;
		NotificationEntity notificationEntity = data.get();
		notificationEntity.setRead(true);
		notificationEntity = notificationRepository.save(notificationEntity);
		NotificationDto result = notificationConverter.toDto(notificationEntity);
		if (notificationEntity.getType() == NotificationDto.TypeRequestFriendShip) {
			notificationRepository.delete(notificationEntity);
		}
		return result;
	}

	@Override
	public List<NotificationDto> readAllListNotification() {
		UserEntity contextUser = authenticationHelper.getUserFromContext();
		List<NotificationEntity> notificationEntities = notificationRepository.findAllByUserNotification(contextUser);
		if (notificationEntities.size() == 0)
			return null;
		for (NotificationEntity notificationEntity : notificationEntities) {
			readNotification(notificationEntity.getId());
		}
		return notificationConverter.toListDto(notificationEntities);
	}

	@CacheEvict(value = "cachedNotification", allEntries = true)
	@Override
	public NotificationDto saveNotification(NotificationDto notificationDto) {
		notificationDto.setAction(NotificationDto.TypeToAction.get(notificationDto.getType()));
		NotificationEntity notificationEntity = new NotificationEntity();
		notificationEntity = notificationConverter.toEntity(notificationDto);
		notificationEntity = notificationRepository.save(notificationEntity);
		return notificationConverter.toDto(notificationEntity);
	}

	@Override
	public NotificationDto saveNotificationByPostId(Long postId, Long fromUserId, Integer type) {
		Optional<PostEntity> postData = postRepository.findById(postId);
		if (!postData.isPresent())
			return null;
		Optional<UserEntity> userData = userRepository.findById(postData.get().getUserPost().getId());
		if (!userData.isPresent())
			return null;
		UserEntity contextUser = authenticationHelper.getUserFromContext();
		if (contextUser.getId() == userData.get().getId())
			return null;
		NotificationDto notificationDto = new NotificationDto(userData.get().getId(), fromUserId,
				NotificationDto.TypeToString.get(type), postId);
		notificationDto = this.saveNotification(notificationDto);
		return notificationDto;
	}

}
