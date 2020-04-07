package com.valdemir.servicesrest.dommain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valdemir.servicesrest.dommain.model.Carro;
import com.valdemir.servicesrest.dommain.repository.CarroRepository;



@Service
public class CarroService {
	
	@Autowired
	private CarroRepository carroRepository;
	
	public List<Carro> listaTodos(){
		return carroRepository.findAll();
	}

	public void deletar(Long idCarro) {
		carroRepository.deleteById(idCarro);
	}

}
