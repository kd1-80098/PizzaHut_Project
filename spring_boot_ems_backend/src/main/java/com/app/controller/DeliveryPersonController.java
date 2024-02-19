package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.AddressServiceImpl;
import com.app.service.OfferServiceImpl;
import com.app.service.PizzaServiceImpl;
import com.app.service.ReviewsServiceImpl;
import com.app.service.ShoppingCartServiceImpl;
import com.app.service.UserServiceImpl;


@RestController
@RequestMapping("/pizzadelivery")
@CrossOrigin
public class DeliveryPersonController {

	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired
	AddressServiceImpl addressServiceImpl;
	
	@Autowired
	OfferServiceImpl offerServiceImpl;
	
	@Autowired
	PizzaServiceImpl pizzaServiceImpl;
	
	@Autowired
	ReviewsServiceImpl reviewsServiceImpl;
	
	@Autowired
	ShoppingCartServiceImpl shoppingCartServiceImpl;
	
	@GetMapping("/test")
	public void getCartTest() {
		shoppingCartServiceImpl.addToCart((long)10, (long)4);
	}

}
