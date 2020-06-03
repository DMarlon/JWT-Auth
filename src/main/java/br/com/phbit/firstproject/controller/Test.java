package br.com.phbit.firstproject.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Test {
	
    private Logger logger = LoggerFactory.getLogger(Test.class);
		
	@GetMapping("/2")
	@PreAuthorize("@security.hasAuthorization('teste:2')")
	public String test2() {
		logger.info("Acessando rota /2");
		return "Acessando rota /2";
	}
	
	@GetMapping("/3")
	@PreAuthorize("@security.hasAuthorization('teste:marlon')")
	public String test3() {
		logger.info("Acessando rota /3");
		return "Acessando rota /3";
	}
		
	@GetMapping("/{id}")
	public String test2(@PathVariable("id") String id) {
		return id;
	}
}
