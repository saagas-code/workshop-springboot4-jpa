package estudo.course.security;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import estudo.course.entities.User;

@Service
public class JwtService {
	
	@Value("${security.jwt.expiration}")
	private String expiration;
	
	@Value("${security.jwt.key}")
	private String key;
	
	public String getToken(User user) {
		long expString = Long.valueOf(expiration);
		LocalDateTime dateHourExpiration = LocalDateTime.now().plusHours(expString);
		Instant instant = dateHourExpiration.atZone(ZoneId.systemDefault()).toInstant();
		Date data = Date.from(instant);
		
		return JWT.create()
				.withIssuer("Auth")
				.withSubject(user.getEmail())
				.withClaim("id", user.getId())
				.withExpiresAt(data)
				.sign(Algorithm.HMAC256(key));
	}
	
	public String getSubject(String token) {
		return JWT.require(Algorithm.HMAC256(key))
				.withIssuer("Auth")
				.build().verify(token).getSubject();
	}
	/*
	public String getToken(User user) {
		long expString = Long.valueOf(expiration);
		LocalDateTime dateHourExpiration = LocalDateTime.now().plusMinutes(expString);
		Instant instant = dateHourExpiration.atZone(ZoneId.systemDefault()).toInstant();
		Date data = Date.from(instant);
		
		HashMap<String, Object> claims = new HashMap<>();
		claims.put("id", user.getId());
		
		return Jwts
					.builder()
					.setSubject(user.getEmail())
					.setExpiration(data)
					.setClaims(claims)
					.signWith(SignatureAlgorithm.HS512, key)
					.compact();
					
	}
	*/
	/*
	private Claims getClaims(String token) throws ExpiredJwtException {
		return Jwts
				.parser()
				.setSigningKey(key)
				.parseClaimsJws(token)
				.getBody();
	}
	*/
	/*
	public boolean tokenIsValid(String token) throws ExpiredJwtException {
		try {
			Claims claims = getClaims(token);
			Date dataExpiration = claims.getExpiration();
			LocalDateTime data = dataExpiration.toInstant()
								.atZone(ZoneId.systemDefault()).toLocalDateTime();
			return LocalDateTime.now().isAfter(data);
		} catch (RuntimeException e) {
			return false;
		}
	}
	*/
	/*
	public String getSubject(String token) throws ExpiredJwtException {
		return (String) getClaims(token).getSubject();
	}
	*/
	
}
