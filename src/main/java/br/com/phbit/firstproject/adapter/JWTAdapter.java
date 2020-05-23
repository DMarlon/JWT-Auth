package br.com.phbit.firstproject.adapter;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

import br.com.phbit.firstproject.exception.JWTTokenInvalid;

@Component
public class JWTAdapter {
	
	@Autowired
	private Environment env;
    private final long EXPIRATION_TIME = 864_000_000; // 10 days
        
	public String createToken(String username) {
		return JWT.create()
				.withSubject(username)
				.withClaim("teste", "testando Claim")
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(env.getProperty("application.security.secrect")));
	}
	
	public String getUserName(String token) throws JWTTokenInvalid {
		try {
			return JWT.require(Algorithm.HMAC256(env.getProperty("application.security.secrect"))).build().verify(token).getSubject();
		} catch (TokenExpiredException e) {
			throw new JWTTokenInvalid("Token expirado!");
		} catch (JWTVerificationException e) {
			throw new JWTTokenInvalid("Token invalido!");
		}		
	}	
}
