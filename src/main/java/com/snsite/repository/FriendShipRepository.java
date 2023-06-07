package com.snsite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snsite.entity.FriendShipEntity;
import com.snsite.entity.UserEntity;

public interface FriendShipRepository extends JpaRepository<FriendShipEntity, Long> {
	List<FriendShipEntity> findAllByUserFriendShipOrSecondUserId(UserEntity userFriendShip, Long secondUserId);

	List<FriendShipEntity> findAllBySecondUserIdAndState(Long secondUserId, Integer state);
}
