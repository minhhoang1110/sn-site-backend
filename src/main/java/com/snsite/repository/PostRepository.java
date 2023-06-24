package com.snsite.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.snsite.entity.PostEntity;
import com.snsite.entity.UserEntity;

public interface PostRepository extends PagingAndSortingRepository<PostEntity, Long> {
	List<PostEntity> findAllByUserPost(UserEntity userEntity, Pageable pageable);
}
