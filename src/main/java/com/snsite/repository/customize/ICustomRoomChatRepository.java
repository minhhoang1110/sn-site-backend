package com.snsite.repository.customize;

import java.util.List;

import com.snsite.entity.RoomChatEntity;

public interface ICustomRoomChatRepository {
	public List<RoomChatEntity> findAllByUserId(Long userId);
}
