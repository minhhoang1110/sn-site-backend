package com.snsite.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.snsite.dto.RoomChatDto;
import com.snsite.entity.RoomChatEntity;
import com.snsite.service.IMessageService;

@Component
public class RoomChatConverter {
	@Autowired
	private IMessageService messageService;

	public RoomChatEntity toEntity(RoomChatDto roomChatDto) {
		RoomChatEntity roomChatEntity = new RoomChatEntity();
		roomChatEntity.setUserIds(roomChatDto.getUserIds());
		roomChatEntity.setRoomType(RoomChatDto.RoomTypeToId.get(roomChatDto.getRoomType()));
		roomChatEntity.setThumbnailUrl(roomChatDto.getThumbnailUrl());
		return roomChatEntity;
	}

	public RoomChatEntity toEntity(RoomChatDto roomChatDto, RoomChatEntity roomChatEntity) {
		roomChatEntity.setThumbnailUrl(roomChatDto.getThumbnailUrl());
		return roomChatEntity;
	}

	public RoomChatDto toDto(RoomChatEntity roomChatEntity) {
		RoomChatDto roomChatDto = new RoomChatDto();
		roomChatDto.setId(roomChatEntity.getId());
		roomChatDto.setCreatedAt(roomChatEntity.getCreatedAt());
		roomChatDto.setCreatedBy(roomChatEntity.getCreatedBy());
		roomChatDto.setUpdatedAt(roomChatEntity.getUpdatedAt());
		roomChatDto.setUpdatedBy(roomChatEntity.getUpdatedBy());
		roomChatDto.setIsActive(roomChatEntity.getIsActive());
		roomChatDto.setUserIds(roomChatEntity.getUserIds());
		roomChatDto.setRoomType(RoomChatDto.RoomTypeToString.get(roomChatEntity.getRoomType()));
		roomChatDto.setThumbnailUrl(roomChatEntity.getThumbnailUrl());
		roomChatDto.setHasUnreadMessage(messageService.checkUnreadMessageByRoomChat(roomChatEntity));
		return roomChatDto;
	}

	public List<RoomChatDto> toListDto(List<RoomChatEntity> roomChatEntities) {
		if (roomChatEntities.size() == 0)
			return null;
		List<RoomChatDto> roomChatDtos = new ArrayList<>();
		for (RoomChatEntity roomChatEntity : roomChatEntities) {
			roomChatDtos.add(this.toDto(roomChatEntity));
		}
		return roomChatDtos;
	}
}
