package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.entities.Order;


public interface OrderService {

	public List<Order> findAllOrders();

	public Optional<Order> findOrderByID(Long id);

//	public Order addOrder(Order order);

	public void deleteOrderByID(Long id);

	Order updateOrder(Order order);


}
