package com.snsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snsite.entity.RoomChatEntity;

public interface RoomChatRepository extends JpaRepository<RoomChatEntity, Long> {

}
