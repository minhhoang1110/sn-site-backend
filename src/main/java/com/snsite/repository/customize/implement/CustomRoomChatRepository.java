package com.snsite.repository.customize.implement;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.snsite.entity.RoomChatEntity;
import com.snsite.repository.customize.ICustomRoomChatRepository;

@Repository
public class CustomRoomChatRepository implements ICustomRoomChatRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<RoomChatEntity> findAllByUserId(Long userId) {
		List<RoomChatEntity> roomChatEntities = null;
		try {
			Query query = entityManager
					.createNativeQuery("select * from room_chats where user_ids like '%" + userId + "%'",
							RoomChatEntity.class)
					.unwrap(org.hibernate.query.Query.class);
			roomChatEntities = query.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return roomChatEntities;
	}
}
