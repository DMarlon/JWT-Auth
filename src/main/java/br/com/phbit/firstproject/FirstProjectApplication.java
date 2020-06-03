package br.com.phbit.firstproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EntityScan(basePackages = {"br.com.phbit.firstproject.model"})
@EnableJpaRepositories(basePackages = {"br.com.phbit.firstproject.repository"})
public class FirstProjectApplication {
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
		
	public static void main(String[] args) {
		SpringApplication.run(FirstProjectApplication.class, args);
	}
}
