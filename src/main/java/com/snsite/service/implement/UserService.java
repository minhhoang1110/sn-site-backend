package com.snsite.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.snsite.converter.UserConverter;
import com.snsite.dto.FriendShipDto;
import com.snsite.dto.RoomChatDto;
import com.snsite.dto.UserDto;
import com.snsite.entity.UserEntity;
import com.snsite.helper.AuthenticationHelper;
import com.snsite.repository.UserRepository;
import com.snsite.service.IFriendShipService;
import com.snsite.service.IRoomChatService;
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
	@Autowired
	private IFriendShipService friendShipService;
	@Autowired
	private IRoomChatService roomChatService;

	@Cacheable(value = "cachedUser")
	@Override
	public UserDto getCurrentProfile() {
		UserEntity userEntity = authenticationHelper.getUserFromContext();
		List<FriendShipDto> friendShipDtos = friendShipService.getListFriendShip(null);
		UserDto userDto = userConverter.toDto(userEntity);
		int countOfFriends = 0;
		if (friendShipDtos != null && friendShipDtos.size() > 0) {
			for (FriendShipDto friendShipDto : friendShipDtos) {
				if (friendShipDto.getState() == FriendShipDto.StateToString.get(FriendShipDto.StateFriend))
					countOfFriends++;
			}
		}
		userDto.setCountOfFriends(countOfFriends);
		return userDto;
	}

	@Cacheable(value = "cachedUser", key = "#id")
	@Override
	public UserDto getUserDetail(Long id) {
		UserEntity contextUser = authenticationHelper.getUserFromContext();
		Optional<UserEntity> userEntity = userRepository.findById(id);
		if (!userEntity.isPresent())
			return null;
		UserDto userDto = userConverter.toDto(userEntity.get());
		List<FriendShipDto> friendShipDtos = friendShipService.getListFriendShip(id);
		int countOfFriends = 0;
		if (friendShipDtos != null && friendShipDtos.size() > 0) {
			for (FriendShipDto friendShipDto : friendShipDtos) {
				if (friendShipDto.getState() == FriendShipDto.StateToString.get(FriendShipDto.StateFriend))
					countOfFriends++;
			}
		}
		userDto.setCountOfFriends(countOfFriends);
		RoomChatDto roomChatDto = roomChatService.getRoomChatByChatUser(userDto.getId());
		if (roomChatDto != null && contextUser.getId() != userDto.getId()) {
			userDto.setChatWithSessionUser(true);
			userDto.setRoomChatId(roomChatDto.getId());
		}
		FriendShipDto friendShipDto = friendShipService.getFriendShipDetail(userDto.getId());
		if (friendShipDto != null && friendShipDto.getState()
				.compareTo(FriendShipDto.StateToString.get(FriendShipDto.StateFriend)) == 0) {
			userDto.setBeFriendWidthSessionUser(true);
		}
		return userDto;
	}

	@CacheEvict(value = "cachedUser", allEntries = true)
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

	@Cacheable(value = "cachedUser", key = "#keyword")
	@Override
	public List<UserDto> getListUser(String keyword) {
		List<UserEntity> userEntities;
		if (keyword != null && keyword != "") {
			userEntities = userRepository.findAllByLastNameOrFirstNameAllIgnoreCase(keyword, keyword);
		} else {
			userEntities = userRepository.findAll();
		}
		List<UserDto> userDtos = userConverter.toListDto(userEntities);
		List<UserDto> result = new ArrayList<>();
		if (userDtos != null && userDtos.size() > 0) {
			for (UserDto userDto : userDtos) {
				RoomChatDto roomChatDto = roomChatService.getRoomChatByChatUser(userDto.getId());
				if (roomChatDto != null) {
					userDto.setChatWithSessionUser(true);
					userDto.setRoomChatId(roomChatDto.getId());
				}
				FriendShipDto friendShipDto = friendShipService.getFriendShipDetail(userDto.getId());
				if (friendShipDto != null && friendShipDto.getState()
						.compareTo(FriendShipDto.StateToString.get(FriendShipDto.StateFriend)) == 0) {
					userDto.setBeFriendWidthSessionUser(true);
				}
				result.add(userDto);
			}
		}
		return result;
	}

	@Override
	public UserEntity getEntityDetail(Long id) {
		Optional<UserEntity> userEntity = userRepository.findById(id);
		if (!userEntity.isPresent())
			return null;
		return userEntity.get();
	}

}
