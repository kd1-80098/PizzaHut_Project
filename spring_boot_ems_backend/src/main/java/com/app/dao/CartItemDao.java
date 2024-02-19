package com.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.CartItem;

public interface CartItemDao extends JpaRepository<CartItem, Long> {
	List<CartItem> findByCartId(long id);
	CartItem findByPizzaIdAndCartId(long pizzaId, long CartId);
}
