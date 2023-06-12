package com.snsite.emailer.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.snsite.emailer.service.IEmailService;
import com.snsite.entity.UserEntity;
import com.snsite.middleware.CustomUserDetails;
import com.snsite.middleware.JwtTokenProvider;

@Service
public class EmailService implements IEmailService {
	@Autowired
	private JavaMailSender javaMailSender;
	@Value("${spring.mail.username}")
	private String sender;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	public boolean sendVerifyEmail(UserEntity userEntity) {
		try {
			String emailSubject = "Email Verification - SN Site";
			CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);
			String emailToken = jwtTokenProvider.generateEmailToken(customUserDetails);
			String callbackURL = "http://localhost:3000/verify-email?token=" + emailToken;
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

}
