package br.com.phbit.firstproject.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${url.test.path}")
public class Test {
	
	@Value("${url.test.path}")
	private String teste;
		
	@GetMapping
	@PreAuthorize("hasAuthority('teste:1')")
	public String test() {
		return teste;
	}
	
	@GetMapping("/2")
	@PreAuthorize("@securityService.hasAuthorization('teste:2')")
	public String test2() {
		return teste;
	}
	
	@GetMapping("/3")
	@PreAuthorize("@securityService.hasAuthorization('teste:marlon')")
	public String test3() {
		return teste;
	}
	
	@GetMapping("/{id}")
	public String test2(@PathVariable("id") String id) {
		return id;
	}
}
