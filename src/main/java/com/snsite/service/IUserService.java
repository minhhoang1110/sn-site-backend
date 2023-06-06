package com.snsite.service;

import java.util.List;

import com.snsite.dto.UserDto;
import com.snsite.entity.UserEntity;

public interface IUserService {
	public UserDto getCurrentProfile();

	public UserDto getUserDetail(Long id);

	public UserEntity getEntityDetail(Long id);

	public UserDto saveUser(UserDto userDto);

	public List<UserDto> getListUser();
}
