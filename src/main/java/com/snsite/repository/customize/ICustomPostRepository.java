package com.snsite.repository.customize;

import java.util.List;

import com.snsite.entity.PostEntity;

public interface ICustomPostRepository {
	List<PostEntity> findAllWithFriendShip(Long userId);
}
