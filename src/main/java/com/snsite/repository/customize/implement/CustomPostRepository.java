package com.snsite.repository.customize.implement;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.snsite.entity.PostEntity;
import com.snsite.repository.customize.ICustomPostRepository;

@Repository
public class CustomPostRepository implements ICustomPostRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<PostEntity> findAllAvailablePost(Integer limit, Integer offset) {
		List<PostEntity> postEntities = null;
		try {
			Query query = entityManager.createNativeQuery(
					"select * from posts where shared_type=?0 or shared_type=?1 order by updated_at DESC limit ?2 offset ?3",
					PostEntity.class).unwrap(org.hibernate.query.Query.class);
			query.setParameter(0, 0);
			query.setParameter(1, 1);
			query.setParameter(2, limit);
			query.setParameter(3, offset);
			postEntities = query.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return postEntities;
	}
}
