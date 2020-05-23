package br.com.phbit.firstproject.filter.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import br.com.phbit.firstproject.service.system.SessionService;
import br.com.phbit.firstproject.utils.HTTPUtils;

public class JWTLogoutFilter extends SimpleUrlLogoutSuccessHandler {
	
	private SessionService sessionService;
	
	public JWTLogoutFilter(SessionService sessionService) {
		super();
		this.sessionService = sessionService;
	}

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		String header = request.getHeader(HTTPUtils.AUTHORIZATION);
        if (header != null && header.startsWith(HTTPUtils.BEARER)) {
        	sessionService.logout(header.replace(HTTPUtils.BEARER, ""));
        }
	}
}
