package com.snsite.emailer.service;

import com.snsite.entity.UserEntity;

public interface IEmailService {
	boolean sendVerifyEmail(UserEntity userEntity);
}
