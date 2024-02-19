package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.dao.OrderDao;
import com.app.entities.Order;


@Component
public class OrderServiceImpl implements OrderService{
	@Autowired
	OrderDao orderDao;
	
	@Override
	public List<Order> findAllOrders() {
		return orderDao.findAll();
	}
	
//	@Override
//	public Order addOrder(Order order) {
//		orderRepo.save(order);
//		return order;
//	}
	
	@Override
	public Optional<Order> findOrderByID(Long id) {
		System.out.println("Finding the order with the Id: "+ id);
		return orderDao.findById(id);
	}

	@Override
	public void deleteOrderByID(Long id) {
		System.out.println("Finding the order with the Id: "+ id);
		orderDao.deleteById(id);
	}

	public Order findByID(Long id) {
		return orderDao.findById(id).orElse(null);
	}
	
	public List<Order> findByUserId(long id){
		return orderDao.findByCartOwnerId(id);
	}
	
	@Override
	public Order updateOrder(Order order) {
		Order updOder = orderDao.findById(order.getId()).orElse(null);
		updOder.setTotalItems(order.getTotalItems());
		updOder.setTotalOrderPrice(order.getTotalOrderPrice());
		updOder.setOrderOreatedOn(order.getOrderOreatedOn());
		updOder.setCartOwner(order.getCartOwner());
		updOder.setOrderItems(order.getOrderItems());
		updOder.setPlacedOn(order.getPlacedOn());
		updOder.setStatus(order.getStatus());
		updOder.setAddress(order.getAddress());

		return orderDao.save(updOder);
	}


}
