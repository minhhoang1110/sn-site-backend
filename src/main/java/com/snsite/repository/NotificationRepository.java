package com.snsite.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.snsite.entity.NotificationEntity;
import com.snsite.entity.UserEntity;

@Repository
public interface NotificationRepository extends PagingAndSortingRepository<NotificationEntity, Long> {
	List<NotificationEntity> findAllByUserNotification(UserEntity userEntity, Pageable pageable);

	List<NotificationEntity> findAllByUserNotification(UserEntity userEntity);
}
