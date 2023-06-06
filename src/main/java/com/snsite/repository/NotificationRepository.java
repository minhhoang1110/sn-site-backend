package com.snsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snsite.entity.NotificationEntity;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

}
