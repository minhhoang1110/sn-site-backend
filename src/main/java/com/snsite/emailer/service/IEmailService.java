package com.snsite.emailer.service;

import com.snsite.entity.UserEntity;

public interface IEmailService {
	public boolean sendVerifyEmail(UserEntity userEntity);

	public boolean sendForgotPasswordEmail(UserEntity userEntity);
}
