package com.snsite.service;

import com.snsite.dto.UserDto;
import com.snsite.type.request.ChangePasswordRequest;
import com.snsite.type.request.ForgotPasswordRequest;
import com.snsite.type.request.LoginRequest;
import com.snsite.type.request.RefreshTokenRequest;
import com.snsite.type.request.ReqForgotPasswordRequest;
import com.snsite.type.respone.UserWithToken;

public interface IAuthService {
	public UserWithToken login(LoginRequest loginRequest);

	public UserWithToken signup(UserDto userDto);

	public UserWithToken logout();

	public boolean sendVerifyEmail();

	public boolean verifyEmail(String token);

	public UserWithToken refreshToken(RefreshTokenRequest refreshTokenRequest);

	public boolean requestForgotPassword(ReqForgotPasswordRequest request);

	public UserWithToken forgotPassword(ForgotPasswordRequest forgotPasswordRequest);

	public UserWithToken changePassword(ChangePasswordRequest changePasswordRequest);
}
