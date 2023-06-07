package com.snsite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snsite.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findOneByUsername(String username);

	UserEntity findOneByEmail(String email);

	UserEntity findOneByPhone(String phone);

	List<UserEntity> findAllByLastNameOrFirstNameAllIgnoreCase(String lastName, String firstName);
}
