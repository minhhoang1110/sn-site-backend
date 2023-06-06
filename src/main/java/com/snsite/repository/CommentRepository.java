package com.snsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snsite.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

}
