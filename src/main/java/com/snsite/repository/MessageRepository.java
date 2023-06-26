package com.snsite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.snsite.entity.MessageEntity;
import com.snsite.entity.RoomChatEntity;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
	List<MessageEntity> findAllByRoomMessage(RoomChatEntity roomChatEntity);
}
