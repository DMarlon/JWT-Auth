package br.com.phbit.firstproject.security.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.phbit.firstproject.model.system.Session;
import br.com.phbit.firstproject.service.system.SessionService;
import br.com.phbit.firstproject.utils.HTTPUtils;

public class JWTUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private SessionService sessionService;
	private AuthenticationManager authenticationManager;
	
    public JWTUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager, SessionService sessionService) {
        this.authenticationManager = authenticationManager;
        this.sessionService = sessionService;
    }
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		try {
            br.com.phbit.firstproject.model.system.User user = new ObjectMapper().readValue(request.getInputStream(), br.com.phbit.firstproject.model.system.User.class);
            return authenticationManager.authenticate(createAuthenticate(user.getName(), user.getPassword()));                  

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
	}
	
	private Authentication createAuthenticate(String username, String userpassword) {
		return new UsernamePasswordAuthenticationToken(username, userpassword, new ArrayList<>());
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {
		User user = (User) auth.getPrincipal();
		Session session = sessionService.register(user.getUsername(), request. getRemoteAddr(), request.getHeader("User-Agent"));
        response.addHeader(HTTPUtils.AUTHORIZATION, HTTPUtils.BEARER + session.getToken());
	}
	
	
}
