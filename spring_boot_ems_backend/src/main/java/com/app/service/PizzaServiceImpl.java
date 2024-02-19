package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.PizzaDao;
import com.app.entities.Pizza;


@Service
@Transactional
public class PizzaServiceImpl implements PizzaService {
	
	@Autowired
	PizzaDao pizzaDao; 

	@Override
	public Pizza addPizza(Pizza pizza) {
		pizzaDao.save(pizza);
		return pizza;
	}
	
	public List<Pizza> getByCategory(Long category_id){
		
		List<Pizza> items = new ArrayList<>();
		List<Pizza> allItems = new ArrayList<>();

		allItems = pizzaDao.findAll();
		for (Pizza pizza : allItems) {
			if(pizza.getPizzaCategory().getId() == category_id)
			{
				items.add(pizza);
			}
		}
		return items;
	}

	public List<Pizza> getAll() {
		List<Pizza> allProducts = pizzaDao.findAll();
		return allProducts;
	}
	
	public Pizza updatePizza(Pizza pizza){
		
		Pizza updPiz = this.pizzaDao.findById(pizza.getId()).orElse(null);
		updPiz.setName(pizza.getName());
		updPiz.setPizzaCategory(pizza.getPizzaCategory());
		updPiz.setPrice(pizza.getPrice());
		updPiz.setSummary(pizza.getSummary());
		updPiz.setVeg(pizza.isVeg());
		updPiz.setInStock(pizza.isInStock());
		this.pizzaDao.save(updPiz);
		
		return updPiz;
	}
	
	@Override
	public List<Pizza> findAllPizza() {
		return pizzaDao.findAll();
	}
	
	@Override
	public void deletePizzaByID(Long id) {
		System.out.println("Finding the pizza with the Id: "+ id);
		pizzaDao.deleteById(id);
	}
	
	public List<Pizza> findByCategoryID(long cat_id) {
		return pizzaDao.findByCategories(cat_id);
		
	}
	
	public Pizza getByID(long id){
		return pizzaDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("Pizza not found"));
	}
	
	@Override
	public Optional<Pizza> PizzaByID(Long id) {
		System.out.println("Finding the pizza with the Id: "+ id);
		
		return pizzaDao.findById(id);
	}

}
