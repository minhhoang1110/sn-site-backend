package com.snsite.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.snsite.converter.UserConverter;
import com.snsite.dto.UserDto;
import com.snsite.entity.UserEntity;
import com.snsite.helper.AuthenticationHelper;
import com.snsite.repository.UserRepository;
import com.snsite.service.IUserService;

@Service
public class UserService implements IUserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserConverter userConverter;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationHelper authenticationHelper;

	@Override
	public UserDto getCurrentProfile() {
		UserEntity userEntity = authenticationHelper.getUserFromContext();
		return userConverter.toDto(userEntity);
	}

	@Override
	public UserDto getUserDetail(Long id) {
		Optional<UserEntity> userEntity = userRepository.findById(id);
		if (!userEntity.isPresent())
			return null;
		return userConverter.toDto(userEntity.get());
	}

	@Override
	public UserDto saveUser(UserDto userDto) {
		UserEntity userEntity = new UserEntity();
		if (userDto.getId() != null) {
			UserEntity contextUser = authenticationHelper.getUserFromContext();
			if (userDto.getId() != contextUser.getId()) {
				return null;
			}
			Optional<UserEntity> oldData = userRepository.findById(userDto.getId());
			if (!oldData.isPresent())
				return null;
			userEntity = userConverter.toEntity(userDto, oldData.get());
		} else {
			userEntity = userConverter.toEntity(userDto);
			String encodedPassword = passwordEncoder.encode(userEntity.getPassword());
			userEntity.setPassword(encodedPassword);
		}
		userEntity = userRepository.save(userEntity);
		return userConverter.toDto(userEntity);
	}

	@Override
	public List<UserDto> getListUser() {
		List<UserEntity> userEntities = userRepository.findAll();
		return userConverter.toListDto(userEntities);
	}

	@Override
	public UserEntity getEntityDetail(Long id) {
		Optional<UserEntity> userEntity = userRepository.findById(id);
		if (!userEntity.isPresent())
			return null;
		return userEntity.get();
	}

}
