package com.prolifics.employee.security;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private static final String SECRET_KEY =
	        "VGhpc0lzQVNlY3JldEtleUZvckpXVFRva2VuR2VuZXJhdGlvbjEyMzQ1Njc4OTA=";

	private SecretKey getSigningKey() {

	    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

	    return Keys.hmacShaKeyFor(keyBytes);
	}

	public String generateToken(String username) {

	    return Jwts.builder()
	            .subject(username)
	            .issuedAt(new Date())
	            .expiration(new Date(System.currentTimeMillis() + 86400000))
	            .signWith(getSigningKey())
	            .compact();
	}
	
	public String extractUsername(String token) {
	    return extractClaim(token, Claims::getSubject);
	}
	
	public <T> T extractClaim(String token,
            Function<Claims, T> claimsResolver) {

		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
	
		return Jwts.parser()
		.verifyWith(getSigningKey())
		.build()
		.parseSignedClaims(token)
		.getPayload();
	}
}
