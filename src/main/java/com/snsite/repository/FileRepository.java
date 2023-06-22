package com.snsite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snsite.entity.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
	List<FileEntity> findAllByUserId(Long userId);
}
