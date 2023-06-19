package com.snsite.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snsite.converter.FriendShipConverter;
import com.snsite.dto.FriendShipDto;
import com.snsite.entity.FriendShipEntity;
import com.snsite.entity.UserEntity;
import com.snsite.helper.AuthenticationHelper;
import com.snsite.repository.FriendShipRepository;
import com.snsite.repository.UserRepository;
import com.snsite.service.IFriendShipService;

@Service
public class FriendShipService implements IFriendShipService {
	@Autowired
	private FriendShipRepository friendShipRepository;
	@Autowired
	private FriendShipConverter friendShipConverter;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthenticationHelper authenticationHelper;

	@Override
	public List<FriendShipDto> getListFriendShip(Long userId) {
		List<FriendShipEntity> friendShipEntities = new ArrayList<>();
		if (userId != null) {
			Optional<UserEntity> userEntity = userRepository.findById(userId);
			if (!userEntity.isPresent())
				return null;

			friendShipEntities = friendShipRepository.findAllByUserFriendShipOrSecondUserId(userEntity.get(),
					userEntity.get().getId());
		} else {
			UserEntity userEntity = authenticationHelper.getUserFromContext();
			friendShipEntities = friendShipRepository.findAllByUserFriendShipOrSecondUserId(userEntity,
					userEntity.getId());
		}
		List<FriendShipEntity> result = new ArrayList<>();
		for (FriendShipEntity friendShipEntity : friendShipEntities) {
			if (friendShipEntity.getState() == FriendShipDto.StateFriend)
				result.add(friendShipEntity);
		}
		return friendShipConverter.toListDto(result);
	}

	@Override
	public FriendShipDto saveFriendShip(FriendShipDto friendShipDto) {
		UserEntity userEntity = authenticationHelper.getUserFromContext();
		FriendShipEntity friendShipEntity = new FriendShipEntity();
		if (friendShipDto.getId() != null) {
			Optional<FriendShipEntity> oldData = friendShipRepository.findById(friendShipDto.getId());
			if (!oldData.isPresent())
				return null;
			if (userEntity.getId() != oldData.get().getUserFriendShip().getId()
					&& userEntity.getId() != oldData.get().getSecondUserId())
				return null;
			friendShipEntity = friendShipConverter.toEntity(friendShipDto, oldData.get());
		} else {

			if (userEntity.getId() != friendShipDto.getFirstUserId())
				return null;
			if (friendShipDto.getState().compareTo(FriendShipDto.StateToString.get(FriendShipDto.StateRequested)) != 0)
				return null;
			friendShipEntity = friendShipConverter.toEntity(friendShipDto);
		}
		friendShipEntity = friendShipRepository.save(friendShipEntity);
		return friendShipConverter.toDto(friendShipEntity);
	}

	@Override
	public FriendShipDto getFriendShipDetail(Long id) {
		Optional<UserEntity> userEntity = userRepository.findById(id);
		if (!userEntity.isPresent()) {
			return null;
		}
		List<FriendShipEntity> friendShipEntities = friendShipRepository
				.findAllByUserFriendShipOrSecondUserId(userEntity.get(), id);
		UserEntity userContext = authenticationHelper.getUserFromContext();
		FriendShipEntity result = null;
		for (FriendShipEntity friendShipEntity : friendShipEntities) {
			if (userContext.getId() == friendShipEntity.getUserFriendShip().getId()
					|| userContext.getId() == friendShipEntity.getSecondUserId()) {
				result = friendShipEntity;
			}
		}
		if (result == null)
			return null;
		return friendShipConverter.toDto(result);
	}

	@Override
	public boolean deleteFriendShip(Long id) {
		Optional<FriendShipEntity> friendShipEntity = friendShipRepository.findById(id);
		if (!friendShipEntity.isPresent())
			return false;
		UserEntity userEntity = authenticationHelper.getUserFromContext();
		if (userEntity.getId() != friendShipEntity.get().getUserFriendShip().getId()
				&& userEntity.getId() != friendShipEntity.get().getSecondUserId())
			return false;
		try {
			friendShipRepository.delete(friendShipEntity.get());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<FriendShipDto> getListRequestedFriendShip() {
		UserEntity userEntity = authenticationHelper.getUserFromContext();
		List<FriendShipEntity> friendShipEntities = friendShipRepository
				.findAllBySecondUserIdAndState(userEntity.getId(), FriendShipDto.StateRequested);
		return friendShipConverter.toListDto(friendShipEntities);
	}

}
