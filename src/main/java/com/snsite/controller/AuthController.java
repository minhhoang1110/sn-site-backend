package com.snsite.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.snsite.dto.UserDto;
import com.snsite.service.IAuthService;
import com.snsite.type.request.ChangePasswordRequest;
import com.snsite.type.request.ForgotPasswordRequest;
import com.snsite.type.request.LoginRequest;
import com.snsite.type.request.RefreshTokenRequest;
import com.snsite.type.respone.AuthRespone;
import com.snsite.type.respone.UserWithToken;

@RestController
public class AuthController {
	@Autowired
	private IAuthService authService;

	@PostMapping(value = "/api/login")
	@ResponseBody
	public AuthRespone login(@RequestBody LoginRequest loginRequest) {
		UserWithToken userWithToken = authService.login(loginRequest);
		int code = HttpServletResponse.SC_OK;
		String message = "Log in Successfully User " + userWithToken.getUser().getUsername();
		boolean success = true;
		return new AuthRespone(code, success, message, userWithToken);
	}

	@SuppressWarnings("unused")
	@PostMapping(value = "/api/signup")
	@ResponseBody
	public AuthRespone signup(@RequestBody UserDto userDto) {
		UserWithToken userWithToken = authService.signup(userDto);
		int code = HttpServletResponse.SC_OK;
		String message = "Signup Successfully User " + userWithToken.getUser().getUsername();
		boolean success = true;
		if (userWithToken == null) {
			code = HttpServletResponse.SC_BAD_REQUEST;
			message = "Signup Error User " + userDto.getUsername();
			success = false;
		}
		return new AuthRespone(code, success, message, userWithToken);
	}

	@PostMapping(value = "/api/logout")
	@ResponseBody
	public AuthRespone logout() {
		return null;
	}

	@PostMapping(value = "/api/send_verify_email")
	@ResponseBody
	public AuthRespone sendVerifyEmail() {
		boolean result = authService.sendVerifyEmail();
		int code = HttpServletResponse.SC_OK;
		String message = "Send Verify Email Successfully";
		boolean success = true;
		if (!result) {
			code = HttpServletResponse.SC_BAD_REQUEST;
			message = "Send Verify Email Error";
			success = false;
		}
		return new AuthRespone(code, success, message, null);
	}

	@PostMapping(value = "/api/verify_email")
	@ResponseBody
	public AuthRespone verifyEmail(@RequestParam("token") String token) {
		boolean result = authService.verifyEmail(token);
		int code = HttpServletResponse.SC_OK;
		String message = "Verify Email Successfully";
		boolean success = true;
		if (!result) {
			code = HttpServletResponse.SC_BAD_REQUEST;
			message = "Verify Email Error";
			success = false;
		}
		return new AuthRespone(code, success, message, null);
	}

	@PostMapping(value = "/api/refresh_token")
	@ResponseBody
	public AuthRespone refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
		UserWithToken userWithToken = authService.refreshToken(refreshTokenRequest);
		int code = HttpServletResponse.SC_OK;
		String message = "Refresh Token Successfully";
		boolean success = true;
		if (userWithToken == null) {
			code = HttpServletResponse.SC_BAD_REQUEST;
			message = "Refresh Token Error";
			success = false;
		}
		return new AuthRespone(code, success, message, userWithToken);
	}

	@PostMapping(value = "/api/request_forgot_password")
	@ResponseBody
	public AuthRespone requestForgotPassword() {
		return null;
	}

	@PostMapping(value = "/api/forgot_password")
	@ResponseBody
	public AuthRespone forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
		return null;
	}

	@PostMapping(value = "/api/change_password")
	@ResponseBody
	public AuthRespone changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
		return null;
	}
}
