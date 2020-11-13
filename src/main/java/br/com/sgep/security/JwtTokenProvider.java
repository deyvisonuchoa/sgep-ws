package br.com.sgep.security;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.sgep.service.MyUserDetailsService;
import br.com.sgep.service.exception.BusinessException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@Service
public class JwtTokenProvider {
	
	public static final long JWT_TOKEN_VALIDITY = 60 * 60 * 1000; // 3600000 miliseconds = 1 hr

	@Value("${jwt.secret}")
	private String secret;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@PostConstruct
	protected void init() {
		secret = Base64.getEncoder().encodeToString(secret.getBytes());
	}
	
	public String createToken(String username, List<String> roles) {
		Claims claims = Jwts.claims().setSubject(username);
		claims.put("roles", roles);
		
		Date now = new Date();
		Date validity = new Date(now.getTime() + JWT_TOKEN_VALIDITY);
		
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(validity)
				.signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, secret)
				.compact();
	}
	
	public Authentication getAuthentication(String token) {
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	private String getUsername(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	}
	
	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}		
		return null;
	}
	
	public boolean validateToken(String token) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			if (claims.getBody().getExpiration().before(new Date())) {
				return false;
			}
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			throw new BusinessException("Expired or invalid JWT token");
		}
	}
}