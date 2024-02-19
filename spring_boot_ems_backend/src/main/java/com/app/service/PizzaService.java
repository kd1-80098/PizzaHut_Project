package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.entities.Pizza;


public interface PizzaService {

	public Pizza addPizza(Pizza pizza);

	public List<Pizza> findAllPizza();

	public void deletePizzaByID(Long id);

	Optional<Pizza> PizzaByID(Long id);
}
