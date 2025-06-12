package br.edu.ifce.meuprimeirospringboot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {
	@GetMapping("/hello")
	public Map<String,String> helloWord(){
		 Map<String, String> response = new HashMap<>();
		 response.put("messagem", "Hello World!");
		 return response;
	}

}
