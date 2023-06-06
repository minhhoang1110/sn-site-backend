package com.snsite.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.snsite.converter.UserConverter;
import com.snsite.dto.UserDto;
import com.snsite.entity.RefreshTokenEntity;
import com.snsite.entity.UserEntity;
import com.snsite.helper.AuthenticationHelper;
import com.snsite.middleware.CustomUserDetails;
import com.snsite.middleware.JwtTokenProvider;
import com.snsite.middleware.UserMiddlewareService;
import com.snsite.repository.UserRepository;
import com.snsite.service.IAuthService;
import com.snsite.service.IRefreshTokenService;
import com.snsite.service.IUserService;
import com.snsite.type.request.ChangePasswordRequest;
import com.snsite.type.request.ForgotPasswordRequest;
import com.snsite.type.request.LoginRequest;
import com.snsite.type.request.RefreshTokenRequest;
import com.snsite.type.respone.UserWithToken;

@Service
public class AuthService implements IAuthService {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	private IUserService userService;
	@Autowired
	private IRefreshTokenService refreshTokenService;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private UserConverter userConverter;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthenticationHelper authenticationHelper;
	@Autowired
	private UserMiddlewareService userMiddlewareService;

	@Override
	public UserWithToken login(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
		String accessToken = jwtTokenProvider.generateAccessToken(customUserDetails);
		String refreshToken = jwtTokenProvider.generateRefreshToken(customUserDetails);
		RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity(customUserDetails.getUserEntity().getId(),
				refreshToken);
		refreshTokenEntity = refreshTokenService.saveRefreshToken(refreshTokenEntity);
		return new UserWithToken(accessToken, refreshToken, userConverter.toDto(customUserDetails.getUserEntity()));
	}

	@Override
	public UserWithToken logout() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserWithToken sendVerifyEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserWithToken verifyEmail(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserWithToken refreshToken(RefreshTokenRequest refreshTokenRequest) {
		if (!jwtTokenProvider.validateToken(refreshTokenRequest.getRefreshToken(), true))
			return null;
		Long userId = jwtTokenProvider.getUserIdFromRefreshToken(refreshTokenRequest.getRefreshToken());
		UserEntity userEntity = userService.getEntityDetail(userId);
		if (userEntity == null)
			return null;
		RefreshTokenEntity refreshTokenEntity = refreshTokenService
				.getByTokenAndUserId(refreshTokenRequest.getRefreshToken(), userEntity.getId());
		if (refreshTokenEntity == null)
			return null;
		if (!refreshTokenService.deleteRefreshToken(refreshTokenEntity))
			return null;
		CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);
		String accessToken = jwtTokenProvider.generateAccessToken(customUserDetails);
		return new UserWithToken(accessToken, "", userConverter.toDto(userEntity));
	}

	@Override
	public UserWithToken requestForgotPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserWithToken forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserWithToken changePassword(ChangePasswordRequest changePasswordRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean validateSignup(UserDto userDto) {
		if (userRepository.findOneByEmail(userDto.getEmail()) != null)
			return false;
		if (userRepository.findOneByUsername(userDto.getUsername()) != null)
			return false;
		if (userRepository.findOneByPhone(userDto.getPhone()) != null)
			return false;
		return true;
	}

	@Override
	public UserWithToken signup(UserDto userDto) {
		if (!validateSignup(userDto))
			return null;
		UserDto data = userService.saveUser(userDto);
		UserEntity userEntity = userConverter.toEntity(data);
		if (userEntity == null)
			return null;
		CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);
		String accessToken = jwtTokenProvider.generateAccessToken(customUserDetails);
		String refreshToken = jwtTokenProvider.generateRefreshToken(customUserDetails);
		RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity(userEntity.getId(), refreshToken);
		refreshTokenEntity = refreshTokenService.saveRefreshToken(refreshTokenEntity);
		return new UserWithToken(accessToken, refreshToken, userConverter.toDto(userEntity));
	}

}
