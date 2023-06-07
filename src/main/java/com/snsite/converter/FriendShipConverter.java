package com.snsite.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.snsite.dto.FriendShipDto;
import com.snsite.entity.FriendShipEntity;
import com.snsite.entity.UserEntity;
import com.snsite.repository.UserRepository;

@Component
public class FriendShipConverter {
	@Autowired
	private UserConverter userConverter;
	@Autowired
	private UserRepository userRepository;

	public FriendShipEntity toEntity(FriendShipDto friendShipDto) {
		FriendShipEntity friendShipEntity = new FriendShipEntity();
		friendShipEntity.setSecondUserId(friendShipDto.getSecondUserId());
		friendShipEntity.setState(FriendShipDto.StateToId.get(friendShipDto.getState()));
		UserEntity firstUser = userRepository.findById(friendShipDto.getFirstUserId()).get();
		friendShipEntity.setUserFriendShip(firstUser);
		return friendShipEntity;
	}

	public FriendShipEntity toEntity(FriendShipDto friendShipDto, FriendShipEntity friendShipEntity) {
		friendShipEntity.setState(FriendShipDto.StateToId.get(friendShipDto.getState()));
		return friendShipEntity;
	}

	public FriendShipDto toDto(FriendShipEntity friendShipEntity) {
		FriendShipDto friendShipDto = new FriendShipDto();
		friendShipDto.setId(friendShipEntity.getId());
		friendShipDto.setFirstUserId(friendShipEntity.getUserFriendShip().getId());
		friendShipDto.setFirstUser(userConverter.toDto(friendShipEntity.getUserFriendShip()));
		friendShipDto.setSecondUserId(friendShipEntity.getSecondUserId());
		UserEntity secondUser = userRepository.findById(friendShipEntity.getSecondUserId()).get();
		friendShipDto.setSecondUser(userConverter.toDto(secondUser));
		friendShipDto.setState(FriendShipDto.StateToString.get(friendShipEntity.getState()));
		friendShipDto.setCreatedAt(friendShipEntity.getCreatedAt());
		friendShipDto.setCreatedBy(friendShipEntity.getCreatedBy());
		friendShipDto.setUpdatedAt(friendShipEntity.getUpdatedAt());
		friendShipDto.setUpdatedBy(friendShipEntity.getUpdatedBy());
		friendShipDto.setIsActive(friendShipEntity.getIsActive());
		return friendShipDto;
	}

	public List<FriendShipDto> toListDto(List<FriendShipEntity> friendShipEntities) {
		if (friendShipEntities.size() == 0)
			return null;
		List<FriendShipDto> friendShipDtos = new ArrayList<>();
		for (FriendShipEntity friendShipEntity : friendShipEntities) {
			friendShipDtos.add(this.toDto(friendShipEntity));
		}
		return friendShipDtos;
	}
}
