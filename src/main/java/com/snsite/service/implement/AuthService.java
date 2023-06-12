package com.snsite.service.implement;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.snsite.converter.UserConverter;
import com.snsite.dto.UserDto;
import com.snsite.emailer.service.IEmailService;
import com.snsite.entity.RefreshTokenEntity;
import com.snsite.entity.UserEntity;
import com.snsite.helper.AuthenticationHelper;
import com.snsite.helper.DateTimeHelper;
import com.snsite.middleware.CustomUserDetails;
import com.snsite.middleware.JwtTokenProvider;
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
	private IEmailService emailService;
	@Autowired
	private AuthenticationHelper authenticationHelper;
	@Autowired
	private DateTimeHelper dateTimeHelper;

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
	public boolean sendVerifyEmail() {
		UserEntity contextUser = authenticationHelper.getUserFromContext();
		if (contextUser.getVerifyEmailAt() != null)
			return false;
		if (contextUser.getSendVerifyEmailAt() != null) {
			long durationHours = dateTimeHelper.getDuration(contextUser.getSendVerifyEmailAt(), new Date()).toHours();
			if (durationHours < 24)
				return false;

		}
		boolean result = emailService.sendVerifyEmail(contextUser);
		if (result) {
			contextUser.setSendVerifyEmailAt(new Date());
			userRepository.save(contextUser);
		}
		return result;
	}

	@Override
	public boolean verifyEmail(String token) {
		Long userId = jwtTokenProvider.getUserIdFromEmailToken(token);
		Optional<UserEntity> userEntity = userRepository.findById(userId);
		if (!userEntity.isPresent())
			return false;
		if (userEntity.get().getSendVerifyEmailAt() == null)
			return false;
		long durationHours = dateTimeHelper.getDuration(userEntity.get().getSendVerifyEmailAt(), new Date()).toHours();
		if (durationHours > 24)
			return false;
		UserEntity contextUser = authenticationHelper.getUserFromContext();
		if (contextUser.getId() != userEntity.get().getId())
			return false;
		contextUser.setVerifyEmailAt(new Date());
		userRepository.save(contextUser);
		return true;
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
