package br.com.phbit.firstproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.phbit.firstproject.model.system.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByName(String name);
	
	@Query(nativeQuery = true, value = "SELECT count(per_id) > 0 FROM sysusers JOIN sysprofiles ON use_profile_id=pro_id JOIN syspermissions ON per_profile_id=pro_id JOIN sysauthorizations ON per_authorization_id=aut_id WHERE use_name=:userName and aut_description=:authorization and pro_status=1 and aut_status=1")
    boolean hasAuthorization(@Param("userName") String userName, @Param("authorization") String authorization);
}