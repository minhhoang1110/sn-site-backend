package com.snsite.repository.customize.implement;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.snsite.entity.RoomChatEntity;
import com.snsite.repository.customize.ICustomMessageRepository;

@Repository
public class CustomMessageRepository implements ICustomMessageRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void readAllMessageByRoomId(Long roomId) {
		try {
			Query query = entityManager
					.createNativeQuery("update messages set is_read=true where room_id=" + roomId, RoomChatEntity.class)
					.unwrap(org.hibernate.query.Query.class);
			query.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
