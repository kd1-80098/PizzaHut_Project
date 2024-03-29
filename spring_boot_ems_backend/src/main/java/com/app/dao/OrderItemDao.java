package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.OrderItem;


public interface OrderItemDao extends JpaRepository<OrderItem, Long> {
	List<OrderItem> findByOrderId(long id);


}
