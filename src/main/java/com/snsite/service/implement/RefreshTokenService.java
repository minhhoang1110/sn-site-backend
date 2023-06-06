package com.snsite.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snsite.entity.RefreshTokenEntity;
import com.snsite.repository.RefreshTokenRepository;
import com.snsite.service.IRefreshTokenService;

@Service
public class RefreshTokenService implements IRefreshTokenService {
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Override
	public RefreshTokenEntity getByTokenAndUserId(String token, Long userId) {
		return refreshTokenRepository.findOneByUserIdAndRefreshToken(userId, token);
	}

	@Override
	public RefreshTokenEntity saveRefreshToken(RefreshTokenEntity refreshTokenEntity) {
		RefreshTokenEntity oldData = refreshTokenRepository.findOneByUserId(refreshTokenEntity.getUserId());
		if (oldData != null) {
			refreshTokenEntity.setId(oldData.getId());
		}
		return refreshTokenRepository.save(refreshTokenEntity);
	}

	@Override
	public boolean deleteRefreshToken(RefreshTokenEntity refreshTokenEntity) {
		try {
			refreshTokenRepository.delete(refreshTokenEntity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
