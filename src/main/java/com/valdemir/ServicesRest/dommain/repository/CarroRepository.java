package com.valdemir.ServicesRest.dommain.repository;

import java.util.List;

import com.valdemir.ServicesRest.dommain.model.Carro;

public interface CarroRepository {
	public List<Carro> Listar();
	public Carro salvar(Carro carro);
}
