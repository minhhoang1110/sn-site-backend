package com.snsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snsite.entity.MessageEntity;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

}
