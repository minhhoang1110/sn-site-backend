package com.snsite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.snsite.entity.LikeEntity;
import com.snsite.entity.PostEntity;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
	List<LikeEntity> findAllByLikeOfPost(PostEntity postEntity);
}
