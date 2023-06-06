package com.snsite.service;

import java.util.List;

import com.snsite.dto.FriendShipDto;

public interface IFriendShipService {
	public List<FriendShipDto> getListFriendShip(Long userId);

	public FriendShipDto saveFriendShip(FriendShipDto friendShipDto);
}
