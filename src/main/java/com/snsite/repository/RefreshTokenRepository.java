package com.snsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.snsite.entity.RefreshTokenEntity;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
	public RefreshTokenEntity findOneByUserIdAndRefreshToken(Long userId, String refreshToken);

	public RefreshTokenEntity findOneByUserId(Long userId);
}
