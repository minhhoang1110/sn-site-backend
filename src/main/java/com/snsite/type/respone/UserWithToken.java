package com.snsite.type.respone;

import com.snsite.dto.UserDto;

public class UserWithToken {
	private String accessToken;
	private String refreshToken;
	private UserDto user;

	public UserWithToken() {
		super();
	}

	public UserWithToken(String accessToken, String refreshToken, UserDto user) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.user = user;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

}
