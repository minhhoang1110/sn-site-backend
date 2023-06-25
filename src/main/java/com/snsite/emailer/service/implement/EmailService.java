package com.snsite.emailer.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.snsite.emailer.service.IEmailService;
import com.snsite.entity.UserEntity;
import com.snsite.helper.StringHelper;
import com.snsite.middleware.CustomUserDetails;
import com.snsite.middleware.JwtTokenProvider;
import com.snsite.repository.UserRepository;

@Service
public class EmailService implements IEmailService {
	@Autowired
	private JavaMailSender javaMailSender;
	@Value("${spring.mail.username}")
	private String sender;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private StringHelper stringHelper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean sendVerifyEmail(UserEntity userEntity) {
		try {
			String emailSubject = "Email Verification - SN Site";
			CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);
			String emailToken = jwtTokenProvider.generateEmailToken(customUserDetails);
			String callbackURL = System.getenv("FRONT_END_URL") + "/verify-email?token=" + emailToken;
			String emailBody = "You requested for email verification.\nKindly use this link: " + callbackURL
					+ " to verify your email address.";
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setFrom(sender);
			simpleMailMessage.setTo(userEntity.getEmail());
			simpleMailMessage.setSubject(emailSubject);
			simpleMailMessage.setText(emailBody);
			javaMailSender.send(simpleMailMessage);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean sendForgotPasswordEmail(UserEntity userEntity) {
		try {
			String emailSubject = "Forgot Password - SN Site";
			CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);
			String emailToken = jwtTokenProvider.generateEmailToken(customUserDetails);
			String temporaryPassword = stringHelper.randomAlphanumeric(10);
			String encodedPassword = passwordEncoder.encode(temporaryPassword);
			userEntity.setPassword(encodedPassword);
			userRepository.save(userEntity);
			String callbackURL = System.getenv("FRONT_END_URL") + "/reset-password?token=" + emailToken + "&email="
					+ userEntity.getEmail() + "&temporary_password=" + temporaryPassword;
			String emailBody = "You requested for password resetting.\n";
			emailBody += "Your temporary password: " + temporaryPassword + "\n";
			emailBody += "You should set your new password.";
			emailBody += "Kindly use this link: " + callbackURL + " to reset your password.";
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setFrom(sender);
			simpleMailMessage.setTo(userEntity.getEmail());
			simpleMailMessage.setSubject(emailSubject);
			simpleMailMessage.setText(emailBody);
			javaMailSender.send(simpleMailMessage);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

}
