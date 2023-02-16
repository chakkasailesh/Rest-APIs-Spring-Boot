package com.springboot.blogapp.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.springboot.blogapp.exception.JWTException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTTokenProvider {
	@Value("${app.jwt-secret}")
	private String jwtSecret;
	@Value("${app-jwt-expiration-milliseconds}")
	private long jwtExpirationData;

	public String generateToken(Authentication authentication) {
		String userName = authentication.getName();
		Date currentDate = new Date();
		Date expiryDate = new Date(currentDate.getTime() + jwtExpirationData);
		String token = Jwts.builder().setSubject(userName).setIssuedAt(new Date()).setExpiration(expiryDate)
				.signWith(key()).compact();
		return token;
	}

	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}

	public String getUserName(String token) {
		String userName = Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody()
				.getSubject();
		return userName;
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key()).build().parse(token);
		} catch (MalformedJwtException ex) {
			throw new JWTException("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			throw new JWTException("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			throw new JWTException("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			throw new JWTException("JWT claims string is empty");
		}
		return true;
	}
}
