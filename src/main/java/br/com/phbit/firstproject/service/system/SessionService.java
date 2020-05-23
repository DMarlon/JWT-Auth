package br.com.phbit.firstproject.service.system;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.phbit.firstproject.adapter.JWTAdapter;
import br.com.phbit.firstproject.model.system.Session;
import br.com.phbit.firstproject.model.system.User;
import br.com.phbit.firstproject.repository.SessionRepository;
import br.com.phbit.firstproject.repository.UserRepository;

@Service
public class SessionService {

	@Autowired
	private JWTAdapter jWTAdapter; 
	private UserRepository userRepository;
	private SessionRepository sessionRepository;

	public SessionService(SessionRepository sessionRepository, UserRepository userRepository) {
		this.sessionRepository = sessionRepository;
		this.userRepository = userRepository;
	}
	
	public Session register(String username, String addressIP, String device) {
		User user = userRepository.findByName(username);
		Optional<Session> optionalSession = sessionRepository.getExisting(user.getId(), addressIP);
		Session session = optionalSession.orElse(create(user, addressIP, device));
		session.setToken(jWTAdapter.createToken(user.getName()));
		session.setLastaccess(new Date());
		sessionRepository.save(session);
		
		return session;
	}
	
	private Session create(User user, String addressIP, String device) {
		Session session = new Session();
		session.setUser(user);		
		session.setRemoteaddress(addressIP);
		session.setDevicename(device);
		
		return session;
	}
	
	public Session getByToken(String token) {
		return sessionRepository.findByToken(token);
	}
		
	public void logout(String token) {
		sessionRepository.deleteByToken(token);
	}
}
