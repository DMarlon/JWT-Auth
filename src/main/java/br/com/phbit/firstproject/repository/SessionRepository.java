package br.com.phbit.firstproject.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.phbit.firstproject.model.system.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {

	@Transactional
	long deleteByToken(String token);
	
	Session findByToken(String token);
	
	@Query("FROM Session s where s.user.id = :userId and s.remoteaddress = :remoteAddress")
    Optional<Session> getExisting(@Param("userId") long userId, @Param("remoteAddress") String remoteAddress);
}
