package com.snsite.service;

import com.snsite.entity.RefreshTokenEntity;

public interface IRefreshTokenService {
	public RefreshTokenEntity getByTokenAndUserId(String token, Long userId);

	public RefreshTokenEntity saveRefreshToken(RefreshTokenEntity refreshTokenEntity);

	public boolean deleteRefreshToken(RefreshTokenEntity refreshTokenEntity);
}
