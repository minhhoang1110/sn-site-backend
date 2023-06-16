package com.snsite.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.snsite.dto.MessageDto;
import com.snsite.entity.MessageEntity;
import com.snsite.entity.RoomChatEntity;
import com.snsite.entity.UserEntity;
import com.snsite.repository.RoomChatRepository;
import com.snsite.repository.UserRepository;

@Component
public class MessageConverter {
	@Autowired
	private RoomChatRepository roomChatRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoomChatConverter roomChatConverter;
	@Autowired
	private UserConverter userConverter;

	public MessageEntity toEntity(MessageDto messageDto) {
		MessageEntity messageEntity = new MessageEntity();
		RoomChatEntity roomChatEntity = roomChatRepository.findById(messageDto.getRoomId()).get();
		UserEntity userEntity = userRepository.findById(messageDto.getUserId()).get();
		messageEntity.setRoomMessage(roomChatEntity);
		messageEntity.setUserMessage(userEntity);
		messageEntity.setMessage(messageDto.getMessage());
		messageEntity.setImageUrl(messageDto.getImageUrl());
		return messageEntity;
	}

	public MessageEntity toEntity(MessageDto messageDto, MessageEntity messageEntity) {
		messageEntity.setMessage(messageDto.getMessage());
		return messageEntity;
	}

	public MessageDto toDto(MessageEntity messageEntity) {
		MessageDto messageDto = new MessageDto();
		messageDto.setId(messageEntity.getId());
		messageDto.setCreatedAt(messageEntity.getCreatedAt());
		messageDto.setCreatedBy(messageEntity.getCreatedBy());
		messageDto.setUpdatedAt(messageEntity.getUpdatedAt());
		messageDto.setUpdatedBy(messageEntity.getUpdatedBy());
		messageDto.setIsActive(messageEntity.getIsActive());
		messageDto.setRoomId(messageEntity.getRoomMessage().getId());
		messageDto.setRoom(roomChatConverter.toDto(messageEntity.getRoomMessage()));
		messageDto.setUserId(messageEntity.getUserMessage().getId());
		messageDto.setUser(userConverter.toDto(messageEntity.getUserMessage()));
		messageDto.setIsRead(messageEntity.getIsRead());
		messageDto.setMessage(messageEntity.getMessage());
		messageDto.setImageUrl(messageEntity.getImageUrl());
		return messageDto;
	}

	public List<MessageDto> toListDto(List<MessageEntity> messageEntities) {
		if (messageEntities.size() == 0)
			return null;
		List<MessageDto> messageDtos = new ArrayList<>();
		for (MessageEntity messageEntity : messageEntities) {
			messageDtos.add(this.toDto(messageEntity));
		}
		return messageDtos;
	}
}
