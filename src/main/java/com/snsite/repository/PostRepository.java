package com.snsite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snsite.entity.PostEntity;
import com.snsite.entity.UserEntity;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
	List<PostEntity> findAllByUserPostOrderByUpdatedAtDesc(UserEntity userEntity);
}
