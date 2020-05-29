package br.com.phbit.firstproject.security.filter;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import br.com.phbit.firstproject.adapter.JWTAdapter;
import br.com.phbit.firstproject.exception.JWTTokenInvalid;
import br.com.phbit.firstproject.model.system.Session;
import br.com.phbit.firstproject.service.system.SessionService;
import br.com.phbit.firstproject.utils.HTTPUtils;

public class JWTAuthenticationFilter extends BasicAuthenticationFilter {
	
	private JWTAdapter jWTAdapter;
	private SessionService sessionService;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, SessionService sessionService, JWTAdapter jWTAdapter)	{
		super(authenticationManager);
		this.jWTAdapter = jWTAdapter;
		this.sessionService = sessionService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(HTTPUtils.AUTHORIZATION);
        if (header == null || !header.startsWith(HTTPUtils.BEARER)) {
            chain.doFilter(request, response);
        } else {
            try {
            	SecurityContextHolder.getContext().setAuthentication(getAuthentication(header.replace(HTTPUtils.BEARER, "")));
			} catch (JWTTokenInvalid e) {
				response.sendError(1, e.getMessage());
				SecurityContextHolder.getContext().setAuthentication(null);
			}

            chain.doFilter(request, response);	
        }
	}
	
	private Authentication getAuthentication(String token) throws JWTTokenInvalid {
		
		Session session = sessionService.getByToken(token);
		if (session != null) {			
			String username = jWTAdapter.getUserName(token);
			if (username!= null)
				return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
		}
                    
		return null;
	}
}
