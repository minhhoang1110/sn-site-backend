package com.snsite.service;

import java.util.List;

import com.snsite.dto.FriendShipDto;

public interface IFriendShipService {
	public List<FriendShipDto> getListFriendShip();

	public List<FriendShipDto> getListRequestedFriendShip();

	public FriendShipDto saveFriendShip(FriendShipDto friendShipDto);

	public FriendShipDto getFriendShipDetail(Long id);

	public boolean deleteFriendShip(Long id);
}
