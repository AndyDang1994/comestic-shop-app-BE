package com.hacorp.shop.security;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.hacorp.shop.configuration.JwtSecurityProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtTokenUtils implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private JwtSecurityProperties jwtPros;
	
	
		//		//retrieve username from jwt token
//		public String getUsernameFromToken(String token) {
//			return getClaimFromToken(token, Claims.getSubject);
//		}
//
//		//retrieve expiration date from jwt token
//		public Date getExpirationDateFromToken(String token) {
//			return getClaimFromToken(token, Claims::getExpiration);
//		}
//
//		public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//			final Claims claims = getAllClaimsFromToken(token);
//			return claimsResolver.apply(claims);
//		}
//	    //for retrieveing any information from token we will need the secret key
//		private Claims getAllClaimsFromToken(String token) {
//			return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//		}
//
//		//check if the token has expired
//		private Boolean isTokenExpired(String token) {
//			final Date expiration = getExpirationDateFromToken(token);
//			return expiration.before(new Date());
//		}
//
//		//generate token for user
//		public String generateToken(UserDetails userDetails) {
//			Map<String, Object> claims = new HashMap<>();
//			return doGenerateToken(claims, userDetails.getUsername());
//		}
//
//		//while creating the token -
//		//1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
//		//2. Sign the JWT using the HS512 algorithm and secret key.
//		//3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
//		//   compaction of the JWT to a URL-safe string 
//		private String doGenerateToken(Map<String, Object> claims, String subject) {
//
//			return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
//					.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
//					.signWith(SignatureAlgorithm.HS512, secret).compact();
//		}
//
		//validate token
		public Boolean validateToken(String token, UserDetails userDetails) {
			final String username = getUsernameFromToken(token);
			return (username.equals(userDetails.getUsername()));
		}

		public  String createJWT(String userName) {

			// The JWT signature algorithm used to sign the token
			SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

			long nowMillis = System.currentTimeMillis();
			Date now = new Date(nowMillis);

			//  sign JWT with our ApiKey secret
			byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtPros.getSecretKey());
			Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

			//  set the JWT Claims
			JwtBuilder builder = Jwts.builder().setIssuedAt(now).setSubject(userName).setIssuer(jwtPros.getIssuer())
					.signWith(signatureAlgorithm, signingKey);

//			if (ttlMillis >= 0) {
//				long expMillis = nowMillis + ttlMillis;
//				Date exp = new Date(expMillis);
//				builder.setExpiration(exp);
//			}

			// Builds the JWT and serializes it to a compact, URL-safe string
			return builder.compact();
		}

		// Sample method to validate and read the JWT
		public  String getUsernameFromToken(String jwt) {
			// This line will throw an exception if it is not a signed JWS (as
			// expected)
			Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(jwtPros.getSecretKey()))
					.parseClaimsJws(jwt).getBody();
			
			return claims.getSubject();
		}
}
