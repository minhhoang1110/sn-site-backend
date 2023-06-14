package com.snsite.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.snsite.dto.UserDto;
import com.snsite.entity.UserEntity;

@Component
public class UserConverter {
	public UserEntity toEntity(UserDto userDto) {
		UserEntity userEntity = new UserEntity();
		userEntity.setUsername(userDto.getUsername());
		userEntity.setPassword(userDto.getPassword());
		userEntity.setEmail(userDto.getEmail());
		userEntity.setPhone(userDto.getPhone());
		userEntity.setAddress(userDto.getAddress());
		userEntity.setBio(userDto.getBio());
		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());
		userEntity.setDateOfBirth(userDto.getDateOfBirth());
		userEntity.setAvatarUrl(userDto.getAvatarUrl());
		userEntity.setCoverUrl(userDto.getCoverUrl());
		return userEntity;
	}

	public UserEntity toEntity(UserDto userDto, UserEntity userEntity) {
		userEntity.setUsername(userDto.getUsername());
		userEntity.setPassword(userDto.getPassword());
		userEntity.setEmail(userDto.getEmail());
		userEntity.setPhone(userDto.getPhone());
		userEntity.setAddress(userDto.getAddress());
		userEntity.setBio(userDto.getBio());
		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());
		userEntity.setDateOfBirth(userDto.getDateOfBirth());
		userEntity.setAvatarUrl(userDto.getAvatarUrl());
		userEntity.setCoverUrl(userDto.getCoverUrl());
		return userEntity;
	}

	public UserDto toDto(UserEntity userEntity) {
		UserDto userDto = new UserDto();
		userDto.setId(userEntity.getId());
		userDto.setUsername(userEntity.getUsername());
		userDto.setPassword(userEntity.getPassword());
		userDto.setEmail(userEntity.getEmail());
		userDto.setPhone(userEntity.getPhone());
		userDto.setAddress(userEntity.getAddress());
		userDto.setBio(userEntity.getBio());
		userDto.setFirstName(userEntity.getFirstName());
		userDto.setLastName(userEntity.getLastName());
		userDto.setDateOfBirth(userEntity.getDateOfBirth());
		userDto.setAvatarUrl(userEntity.getAvatarUrl());
		userDto.setCoverUrl(userEntity.getCoverUrl());
		userDto.setCreatedAt(userEntity.getCreatedAt());
		userDto.setCreatedBy(userEntity.getCreatedBy());
		userDto.setUpdatedAt(userEntity.getUpdatedAt());
		userDto.setUpdatedBy(userEntity.getUpdatedBy());
		userDto.setIsActive(userEntity.getIsActive());
		userDto.setSendVerifyEmailAt(userEntity.getSendVerifyEmailAt());
		userDto.setVerifyEmailAt(userEntity.getVerifyEmailAt());
		return userDto;
	}

	public List<UserDto> toListDto(List<UserEntity> userEntities) {
		if (userEntities.size() == 0)
			return null;
		List<UserDto> userDtos = new ArrayList<>();
		for (UserEntity userEntity : userEntities) {
			userDtos.add(this.toDto(userEntity));
		}
		return userDtos;
	}
}
