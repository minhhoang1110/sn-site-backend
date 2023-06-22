package com.snsite.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.snsite.dto.NotificationDto;
import com.snsite.entity.NotificationEntity;
import com.snsite.entity.UserEntity;
import com.snsite.repository.UserRepository;

@Component
public class NotificationConverter {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserConverter userConverter;

	public NotificationEntity toEntity(NotificationDto notificationDto) {
		NotificationEntity notificationEntity = new NotificationEntity();
		Optional<UserEntity> userData = userRepository.findById(notificationDto.getUserId());
		if (userData.isPresent())
			notificationEntity.setUserNotification(userData.get());
		notificationEntity.setFromUserId(notificationDto.getFromUserId());
		notificationEntity.setType(NotificationDto.TypeToId.get(notificationDto.getType()));
		notificationEntity.setObjectId(notificationDto.getObjectId());
		notificationEntity.setAction(NotificationDto.TypeToAction.get(notificationDto.getType()));
		return notificationEntity;
	}

	public NotificationDto toDto(NotificationEntity notificationEntity) {
		NotificationDto notificationDto = new NotificationDto();
		notificationDto.setId(notificationEntity.getId());
		notificationDto.setCreatedAt(notificationEntity.getCreatedAt());
		notificationDto.setCreatedBy(notificationEntity.getCreatedBy());
		notificationDto.setUpdatedAt(notificationEntity.getUpdatedAt());
		notificationDto.setUpdatedBy(notificationEntity.getUpdatedBy());
		notificationDto.setIsActive(notificationEntity.getIsActive());
		notificationDto.setUserId(notificationEntity.getUserNotification().getId());
		notificationDto.setUser(userConverter.toDto(notificationEntity.getUserNotification()));
		notificationDto.setFromUserId(notificationEntity.getFromUserId());
		Optional<UserEntity> userData = userRepository.findById(notificationEntity.getFromUserId());
		if (userData.isPresent())
			notificationDto.setFromUser(userConverter.toDto(userData.get()));
		notificationDto.setRead(notificationEntity.isRead());
		notificationDto.setType(NotificationDto.TypeToString.get(notificationEntity.getType()));
		notificationDto.setObjectId(notificationEntity.getObjectId());
		notificationDto.setAction(notificationEntity.getAction());
		return notificationDto;
	}

	public List<NotificationDto> toListDto(List<NotificationEntity> notificationEntities) {
		if (notificationEntities.size() == 0)
			return null;
		List<NotificationDto> notificationDtos = new ArrayList<>();
		for (NotificationEntity notificationEntity : notificationEntities) {
			notificationDtos.add(this.toDto(notificationEntity));
		}
		return notificationDtos;
	}
}
