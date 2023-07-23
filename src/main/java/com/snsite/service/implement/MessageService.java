package com.snsite.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.snsite.converter.MessageConverter;
import com.snsite.dto.MessageDto;
import com.snsite.dto.UserDto;
import com.snsite.entity.MessageEntity;
import com.snsite.entity.RoomChatEntity;
import com.snsite.entity.UserEntity;
import com.snsite.helper.AuthenticationHelper;
import com.snsite.repository.MessageRepository;
import com.snsite.repository.RoomChatRepository;
import com.snsite.service.IMessageService;
import com.snsite.service.IRoomChatService;
import com.snsite.service.IUserService;

@Service
public class MessageService implements IMessageService {
	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private MessageConverter messageConverter;
	@Autowired
	private AuthenticationHelper authenticationHelper;
	@Autowired
	private IUserService userService;
	@Autowired
	private RoomChatRepository roomChatRepository;
	@Autowired
	private IRoomChatService roomChatService;

	@Cacheable(value = "cachedMessage")
	@Override
	public List<MessageDto> getListMessage(Long roomId) {
		Optional<RoomChatEntity> roomChatEntity = roomChatRepository.findById(roomId);
		if (!roomChatEntity.isPresent())
			return null;
		UserEntity contextUser = authenticationHelper.getUserFromContext();
		boolean checkUserJoinRoomChat = roomChatService
				.checkUserJoinRoomChat(roomChatEntity.get().getUserIds().split(","), contextUser.getId().toString());
		if (!checkUserJoinRoomChat)
			return null;
		List<MessageEntity> messageEntities = messageRepository.findAllByRoomMessage(roomChatEntity.get());
		return messageConverter.toListDto(messageEntities);
	}

	@CacheEvict(value = { "cachedMessage", "cachedNotification", "cachedRoomchat" }, allEntries = true)
	@Override
	public MessageDto saveMessage(MessageDto messageDto) {
		UserEntity contextUser = authenticationHelper.getUserFromContext();
		UserDto userDto = userService.getUserDetail(messageDto.getUserId());
		if (userDto == null || userDto.getId() != contextUser.getId())
			return null;
		Optional<RoomChatEntity> roomChatEntity = roomChatRepository.findById(messageDto.getRoomId());
		if (!roomChatEntity.isPresent())
			return null;
		boolean checkUserJoinRoomChat = roomChatService
				.checkUserJoinRoomChat(roomChatEntity.get().getUserIds().split(","), contextUser.getId().toString());
		if (!checkUserJoinRoomChat)
			return null;
		MessageEntity messageEntity = new MessageEntity();
		if (messageDto.getId() != null) {
			Optional<MessageEntity> oldData = messageRepository.findById(messageDto.getId());
			if (!oldData.isPresent())
				return null;
			messageEntity = messageConverter.toEntity(messageDto, oldData.get());
		} else {
			messageEntity = messageConverter.toEntity(messageDto);
		}
		messageEntity = messageRepository.save(messageEntity);
		return messageConverter.toDto(messageEntity);
	}

	@CacheEvict(value = { "cachedMessage", "cachedNotification", "cachedRoomchat" }, allEntries = true)
	@Override
	public boolean deleteMessage(Long messageId) {
		try {
			Optional<MessageEntity> oldData = messageRepository.findById(messageId);
			if (!oldData.isPresent())
				return false;
			MessageEntity messageEntity = oldData.get();
			UserEntity contextUser = authenticationHelper.getUserFromContext();
			if (contextUser.getId() != messageEntity.getUserMessage().getId())
				return false;
			messageRepository.delete(messageEntity);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public void readAllMessageByRoomChat(RoomChatEntity roomChatEntity) {
		try {
			List<MessageEntity> messageEntities = messageRepository.findAllByRoomMessage(roomChatEntity);
			if (messageEntities.size() > 0) {
				for (MessageEntity messageEntity : messageEntities) {
					messageEntity.setIsRead(true);
					messageRepository.save(messageEntity);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public boolean checkUnreadMessageByRoomChat(RoomChatEntity roomChatEntity) {
		try {
			List<MessageEntity> messageEntities = messageRepository.findAllByRoomMessage(roomChatEntity);
			if (messageEntities.size() > 0) {
				for (MessageEntity messageEntity : messageEntities) {
					if (!messageEntity.getIsRead())
						return true;
				}
			}
			return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

}
