package com.snsite.service;

import java.util.List;

import com.snsite.dto.RoomChatDto;

public interface IRoomChatService {
	public List<RoomChatDto> getListRoomChat();

	public RoomChatDto getRoomChatDetail(Long id);

	public RoomChatDto saveRoomChat(RoomChatDto roomChatDto);
}
