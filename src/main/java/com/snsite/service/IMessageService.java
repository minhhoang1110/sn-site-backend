package com.snsite.service;

import java.util.List;

import com.snsite.dto.MessageDto;

public interface IMessageService {
	public List<MessageDto> getListMessage(Long roomId);

	public MessageDto saveMessage(MessageDto messageDto);

	public boolean deleteMessage(Long messageId);

	public void readAllMessageByRoomChatId(Long roomId);
}
