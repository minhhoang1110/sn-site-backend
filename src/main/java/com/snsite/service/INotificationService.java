package com.snsite.service;

import java.util.List;

import com.snsite.dto.NotificationDto;

public interface INotificationService {
	public List<NotificationDto> getListNotification();

	public NotificationDto readNotification(Long id);

	public List<NotificationDto> readAllListNotification();
}
