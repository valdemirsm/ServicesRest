package com.valdemir.ServicesRest.testajpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.valdemir.ServicesRest.ServicesRestApplication;
import com.valdemir.ServicesRest.dommain.model.Carro;
import com.valdemir.ServicesRest.dommain.repository.CarroRepository;

public class CadastroCarroMain {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(ServicesRestApplication.class).web(WebApplicationType.NONE).run(args);
		CarroRepository cadastroCarro = applicationContext.getBean(CarroRepository.class);
		
		List<Carro> lista = cadastroCarro.Listar();
		for (Carro carro : lista) {
			System.out.println(carro.getNome());
		}
	}
}
