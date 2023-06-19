package com.snsite.service;

import java.util.List;

import com.snsite.dto.RoomChatDto;

public interface IRoomChatService {
	public List<RoomChatDto> getListRoomChat();

	public RoomChatDto getRoomChatDetail(Long id);

	public RoomChatDto saveRoomChat(RoomChatDto roomChatDto);

	public boolean deleteRoomChat(Long id);

	public boolean checkUserJoinRoomChat(String[] userIds, String userId);

	public RoomChatDto getRoomChatByChatUser(Long userId);
}
