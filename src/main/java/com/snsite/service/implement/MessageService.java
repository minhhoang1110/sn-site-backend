package com.snsite.service.implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.snsite.dto.MessageDto;
import com.snsite.service.IMessageService;

@Service
public class MessageService implements IMessageService {

	@Override
	public List<MessageDto> getListMessage(Long roomId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageDto saveMessage(MessageDto messageDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
