package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.Pizza;


public interface PizzaDao extends JpaRepository<Pizza, Long> {
	
	@Query("SELECT p FROM Pizza p WHERE p.pizzaCategory.id = ?1")
	List<Pizza> findByCategories(long cat_id);
	
}
