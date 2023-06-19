package com.snsite.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snsite.converter.RoomChatConverter;
import com.snsite.dto.MessageDto;
import com.snsite.dto.RoomChatDto;
import com.snsite.entity.RoomChatEntity;
import com.snsite.entity.UserEntity;
import com.snsite.helper.AuthenticationHelper;
import com.snsite.repository.RoomChatRepository;
import com.snsite.repository.customize.ICustomRoomChatRepository;
import com.snsite.service.IMessageService;
import com.snsite.service.IRoomChatService;

@Service
public class RoomChatService implements IRoomChatService {
	@Autowired
	private RoomChatRepository roomChatRepository;
	@Autowired
	private RoomChatConverter roomChatConverter;
	@Autowired
	private AuthenticationHelper authenticationHelper;
	@Autowired
	private ICustomRoomChatRepository customRoomChatRepository;
	@Autowired
	private IMessageService messageService;

	@Override
	public boolean checkUserJoinRoomChat(String[] userIds, String userId) {
		boolean checkUser = false;
		for (String id : userIds) {
			if (id.compareTo(userId) == 0) {
				checkUser = true;
				break;
			}
		}
		return checkUser;
	}

	@Override
	public List<RoomChatDto> getListRoomChat() {
		UserEntity contextUser = authenticationHelper.getUserFromContext();
		List<RoomChatEntity> roomChatEntities = customRoomChatRepository.findAllByUserId(contextUser.getId());
		if (roomChatEntities == null)
			return null;
		return roomChatConverter.toListDto(roomChatEntities);
	}

	@Override
	public RoomChatDto getRoomChatDetail(Long id) {
		Optional<RoomChatEntity> data = roomChatRepository.findById(id);
		if (!data.isPresent())
			return null;
		RoomChatEntity roomChatEntity = data.get();
		UserEntity contextUser = authenticationHelper.getUserFromContext();
		if (!checkUserJoinRoomChat(roomChatEntity.getUserIds().split(","), contextUser.getId().toString()))
			return null;
		messageService.readAllMessageByRoomChat(roomChatEntity);
		return roomChatConverter.toDto(roomChatEntity);
	}

	@Override
	public RoomChatDto saveRoomChat(RoomChatDto roomChatDto) {
		RoomChatEntity roomChatEntity = new RoomChatEntity();
		if (roomChatDto.getId() != null) {
			Optional<RoomChatEntity> oldData = roomChatRepository.findById(roomChatDto.getId());
			if (!oldData.isPresent())
				return null;
			roomChatEntity = roomChatConverter.toEntity(roomChatDto, oldData.get());
		} else {
			UserEntity contextUser = authenticationHelper.getUserFromContext();
			if (!checkUserJoinRoomChat(roomChatDto.getUserIds().split(","), contextUser.getId().toString()))
				return null;
			roomChatEntity = roomChatConverter.toEntity(roomChatDto);
		}
		roomChatEntity = roomChatRepository.save(roomChatEntity);
		return roomChatConverter.toDto(roomChatEntity);
	}

	@Override
	public boolean deleteRoomChat(Long id) {
		try {
			Optional<RoomChatEntity> data = roomChatRepository.findById(id);
			if (!data.isPresent())
				return false;
			RoomChatEntity roomChatEntity = data.get();
			UserEntity contextUser = authenticationHelper.getUserFromContext();
			if (!checkUserJoinRoomChat(roomChatEntity.getUserIds().split(","), contextUser.getId().toString()))
				return false;
			List<MessageDto> messageDtos = messageService.getListMessage(roomChatEntity.getId());
			if (messageDtos != null && messageDtos.size() > 0) {
				for (MessageDto messageDto : messageDtos) {
					messageService.deleteMessage(messageDto.getId());
				}
			}
			roomChatRepository.delete(roomChatEntity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public RoomChatDto getRoomChatByChatUser(Long userId) {
		List<RoomChatDto> roomChatDtos = this.getListRoomChat();
		UserEntity contextUser = authenticationHelper.getUserFromContext();
		RoomChatDto result = null;
		if (roomChatDtos != null && roomChatDtos.size() > 0) {
			for (RoomChatDto roomChatDto : roomChatDtos) {
				boolean isContextUserJoined = checkUserJoinRoomChat(roomChatDto.getUserIds().split(","),
						contextUser.getId().toString());
				boolean isChatUserJoined = checkUserJoinRoomChat(roomChatDto.getUserIds().split(","),
						userId.toString());
				if (isChatUserJoined && isContextUserJoined)
					result = roomChatDto;
			}
		}
		return result;
	}

}
