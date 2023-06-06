package com.snsite.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.snsite.entity.UserEntity;
import com.snsite.middleware.CustomUserDetails;

@Component
public class AuthenticationHelper {
	public UserEntity getUserFromContext() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
		UserEntity userEntity = customUserDetails.getUserEntity();
		return userEntity;
	}
}
