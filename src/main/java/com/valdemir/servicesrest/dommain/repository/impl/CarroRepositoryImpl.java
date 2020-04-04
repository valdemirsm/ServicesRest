package com.valdemir.servicesrest.dommain.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

@Component
public class CarroRepositoryImpl {

	@PersistenceContext
	private EntityManager manager;

}
