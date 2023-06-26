package com.snsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.snsite.entity.RoomChatEntity;

@Repository
public interface RoomChatRepository extends JpaRepository<RoomChatEntity, Long> {

}
