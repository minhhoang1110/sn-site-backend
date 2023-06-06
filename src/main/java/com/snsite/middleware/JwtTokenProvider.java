package com.snsite.middleware;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
	private final String ACCESS_TOKEN_SECRET = "access-secret-tmh";
	private final String REFRESH_TOKEN_SECRET = "refresh-secret-tmh-ezio";
	private final long ACCESS_TOKEN_EXPIRATION = 259200000L;
	private final long REFRESH_TOKEN_EXPIRATION = 604800000L;

	public String generateAccessToken(CustomUserDetails customUserDetails) {
		Date currentTime = new Date();
		Date expiryDate = new Date(currentTime.getTime() + ACCESS_TOKEN_EXPIRATION);
		return Jwts.builder().setSubject(Long.toString(customUserDetails.getUserEntity().getId()))
				.setIssuedAt(currentTime).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, ACCESS_TOKEN_SECRET).compact();
	}

	public String generateRefreshToken(CustomUserDetails customUserDetails) {
		Date currentTime = new Date();
		Date expiryDate = new Date(currentTime.getTime() + REFRESH_TOKEN_EXPIRATION);
		return Jwts.builder().setSubject(Long.toString(customUserDetails.getUserEntity().getId()))
				.setIssuedAt(currentTime).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, REFRESH_TOKEN_SECRET).compact();
	}

	public Long getUserIdFromAccessToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(ACCESS_TOKEN_SECRET).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

	public Long getUserIdFromRefreshToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(REFRESH_TOKEN_SECRET).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

	public boolean validateToken(String authToken, boolean isRefresh) {
		try {
			String secret = ACCESS_TOKEN_SECRET;
			if (isRefresh)
				secret = REFRESH_TOKEN_SECRET;
			Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
			return true;
		} catch (MalformedJwtException ex) {
			System.out.println("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			System.out.println("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			System.out.println("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			System.out.println("JWT claims string is empty.");
		}
		return false;
	}
}
