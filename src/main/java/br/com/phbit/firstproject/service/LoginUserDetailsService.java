package br.com.phbit.firstproject.service;

import static java.util.Collections.emptyList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.phbit.firstproject.enumerators.Status;
import br.com.phbit.firstproject.repository.UserRepository;

@Service
public class LoginUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;

    public LoginUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
        
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        br.com.phbit.firstproject.model.system.User user = userRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        
        return new User(user.getName(), user.getPassword(), user.getStatus().equals(Status.ENABLE), true, true, true, emptyList());
    }

}
