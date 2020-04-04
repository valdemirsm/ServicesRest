package com.valdemir.servicesrest.dommain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valdemir.servicesrest.dommain.model.Carro;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long>{
	
}
