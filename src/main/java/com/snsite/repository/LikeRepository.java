package com.snsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snsite.entity.LikeEntity;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {

}
