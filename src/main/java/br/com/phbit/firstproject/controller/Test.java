package br.com.phbit.firstproject.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class Test {
		
	public String test() {
		return "Hello World";
	}
}
