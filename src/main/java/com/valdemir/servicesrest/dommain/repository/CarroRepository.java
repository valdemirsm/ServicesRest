package com.valdemir.servicesrest.dommain.repository;

import java.util.List;

import com.valdemir.servicesrest.dommain.model.Carro;

public interface CarroRepository {
	public List<Carro> Listar();
	public Carro salvar(Carro carro);
}
