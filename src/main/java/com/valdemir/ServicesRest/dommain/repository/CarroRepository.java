package com.valdemir.servicesrest.dommain.repository;

import java.util.List;

import com.valdemir.servicesrest.dommain.model.Carro;

public interface CarroRepository {
	List<Carro> Listar();
	Carro salvar(Carro carro);
}
