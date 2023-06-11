package com.snsite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snsite.entity.CommentEntity;
import com.snsite.entity.PostEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
	List<CommentEntity> findAllByPostComment(PostEntity postEntity);
}
