package com.valdemir.servicesrest.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valdemir.servicesrest.dommain.model.Carro;
import com.valdemir.servicesrest.dommain.service.CarroService;

@RestController
@RequestMapping("/servicesrest")
public class CarroController {
	
	@Autowired
	private CarroService carroService;
	
	@GetMapping
	public ResponseEntity<List<Carro>> listaTodosCarros() {
		List<Carro> listar = carroService.listaTodos();
		return ResponseEntity.ok(listar);
	}
	
	

}
