package br.com.phbit.firstproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.phbit.firstproject.model.system.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByName(String name);
}