package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.ShoppingCart;


public interface ShoppingCartDao extends JpaRepository<ShoppingCart, Long> {
	ShoppingCart findByCartOwnerId(long id);
	

}
