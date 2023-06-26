package com.snsite.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.snsite.entity.PostEntity;
import com.snsite.entity.UserEntity;

@Repository
public interface PostRepository extends PagingAndSortingRepository<PostEntity, Long> {
	List<PostEntity> findAllByUserPost(UserEntity userEntity, Pageable pageable);
}
