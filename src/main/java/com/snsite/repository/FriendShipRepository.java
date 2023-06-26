package com.snsite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.snsite.entity.FriendShipEntity;
import com.snsite.entity.UserEntity;

@Repository
public interface FriendShipRepository extends JpaRepository<FriendShipEntity, Long> {
	List<FriendShipEntity> findAllByUserFriendShipOrSecondUserId(UserEntity userFriendShip, Long secondUserId);

	List<FriendShipEntity> findAllBySecondUserIdAndState(Long secondUserId, Integer state);
}
