package com.snsite.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "refresh_tokens")
public class RefreshTokenEntity extends BaseEntity {
	@Column
	private Long userId;
	@Column
	private String refreshToken;

	public RefreshTokenEntity() {
		super();
	}

	public RefreshTokenEntity(Long userId, String refreshToken) {
		super();
		this.userId = userId;
		this.refreshToken = refreshToken;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

}
