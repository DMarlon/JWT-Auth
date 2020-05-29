package br.com.phbit.firstproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.phbit.firstproject.adapter.JWTAdapter;
import br.com.phbit.firstproject.security.filter.JWTAuthenticationFilter;
import br.com.phbit.firstproject.security.filter.JWTLogoutFilter;
import br.com.phbit.firstproject.security.filter.JWTUsernamePasswordAuthenticationFilter;
import br.com.phbit.firstproject.security.service.LoginUserDetailsService;
import br.com.phbit.firstproject.service.system.SessionService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private JWTAdapter jWTAdapter;
	
	private LoginUserDetailsService userDetailsService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public WebSecurityConfig(LoginUserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) 
	{
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception 
	{
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		http.cors()
			.and()
				.csrf().disable().authorizeRequests()
				.antMatchers(HttpMethod.POST, "/users/signup").permitAll()
				.anyRequest().authenticated()
			.and()				
				.addFilter(new JWTUsernamePasswordAuthenticationFilter(authenticationManager(), sessionService))
				.addFilter(new JWTAuthenticationFilter(authenticationManager(), sessionService, jWTAdapter))
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessHandler(new JWTLogoutFilter(sessionService));
	}

	@Bean
	protected CorsConfigurationSource corsConfigurationSource() 
	{
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
}
