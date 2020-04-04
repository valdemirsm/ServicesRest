package com.valdemir.ServicesRest.dommain.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.valdemir.ServicesRest.dommain.model.Carro;
import com.valdemir.ServicesRest.dommain.repository.CarroRepository;

@Component
public class CarroRepositoryImpl implements CarroRepository {

	@PersistenceContext
	private EntityManager manager;

	public List<Carro> Listar() {
		return manager.createQuery("from Carro", Carro.class).getResultList();
	}

	@Transactional
	public Carro salvar(Carro carro) {
		return manager.merge(carro);
	}

}
