package com.snsite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snsite.entity.MessageEntity;
import com.snsite.entity.RoomChatEntity;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
	List<MessageEntity> findAllByRoomMessage(RoomChatEntity roomChatEntity);
}
