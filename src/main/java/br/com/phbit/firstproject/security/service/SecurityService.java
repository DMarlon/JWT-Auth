package br.com.phbit.firstproject.security.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.phbit.firstproject.repository.UserRepository;

@Service(value = "security")
public class SecurityService {

	private UserRepository userRepository;

	public SecurityService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public boolean hasAuthorization(String authorization) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return !username.isEmpty() && userRepository.hasAuthorization(username, authorization);
	}
}