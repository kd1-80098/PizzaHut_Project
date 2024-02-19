package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.Category;
import com.app.entities.Offer;
import com.app.entities.Order;
import com.app.entities.Pizza;
import com.app.entities.Review;
import com.app.entities.User;
import com.app.service.AddressServiceImpl;
import com.app.service.CategoryServiceImpl;
import com.app.service.OfferServiceImpl;
import com.app.service.OrderServiceImpl;
import com.app.service.PizzaServiceImpl;
import com.app.service.ReviewsServiceImpl;
import com.app.service.UserServiceImpl;

@RestController
@RequestMapping("/pizzadelivery")
@CrossOrigin
public class AdminController {
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired
	AddressServiceImpl addressServiceImpl;
	
	@Autowired
	OfferServiceImpl offerServiceImpl;
	
	@Autowired
	PizzaServiceImpl pizzaServiceImpl;
	
	@Autowired
	CategoryServiceImpl categoryServiceImpl;
	
	@Autowired
	ReviewsServiceImpl reviewsServiceImpl;
	
	@Autowired
	OrderServiceImpl orderDaoImp;
	
//	@GetMapping("/order/{Id}")
//	public Optional<Order> getOrderById(@PathVariable long Id) {
//		System.out.println("in fetch order of user  " + Id);
//		return orderDaoImp.findOrderByID(Id);
//	}
	
	@GetMapping("/orders")
	public List<Order> seeAllOrder() {
		return this.orderDaoImp.findAllOrders();
	}
	
	@DeleteMapping("/pizza/order/{Id}")
	public void deleteOrderById(@PathVariable long Id) {
		System.out.println("in fetch order of user  " + Id);
		orderDaoImp.deleteOrderByID(Id);
	}
	
	
	@PostMapping("/pizza")
	public Pizza addPizza(@RequestBody Pizza pizza) {
		this.pizzaServiceImpl.addPizza(pizza);
//		user.getAddresses().get(0).setUser(user);
//		addressDaoImpl.addAddress(user.getAddresses().get(0));
		return pizza;
	}
	
	@PostMapping("/category")
	public Category addCategory(@RequestBody Category category) {
		this.categoryServiceImpl.addCategory(category);
//		user.getAddresses().get(0).setUser(user);
//		addressDaoImpl.addAddress(user.getAddresses().get(0));
		return category;
	}
	
	@GetMapping("/offers")
	public List<Offer> seeAllOffer() {
		return this.offerServiceImpl.findAllOffer();
	}
	
	@PutMapping("/category")
	public Category updateCategory(@RequestBody Category category) {
		System.out.println(category);
		return this.categoryServiceImpl.updateCategory(category);
	}
	
	
	@GetMapping("/reviews")
	public List<Review> seeAllReviews(){
		return this.reviewsServiceImpl.findAllReview();
	}
	
	
	@GetMapping("/review/{id}")
	public Optional<Review> getReview(@PathVariable long id){
		return reviewsServiceImpl.getReview(id);
	}
	 
	@DeleteMapping("/review/{id}")
	public void deleteReviewsById(@PathVariable long id){
		reviewsServiceImpl.deleteReviewById(id);
	}
	
	
	//Deleting all pizzas
	@DeleteMapping("/pizza/{Id}")
	public void deleteById(@PathVariable long Id) {
		System.out.println("in fetch address of user  " + Id);
	    pizzaServiceImpl.deletePizzaByID(Id);
		//Optional<Pizza> findPizzaByID(Long id);
	}
	
	@GetMapping("/category/{Id}")
	public Optional<Category> getCategoryById(@PathVariable long Id) {
		System.out.println("in fetch Category of user  " + Id);
		return categoryServiceImpl.findCategoryByID(Id);
	}
	
	@GetMapping("/categories")
	public List<Category> seeAllCategory() {
		return categoryServiceImpl.allCategory();
	}
	
	@DeleteMapping("category/{Id}")
	public void deleteCategoryById(@PathVariable long Id) {
		System.out.println("in fetch Category of user  " + Id);
		categoryServiceImpl.deleteCategoryByID(Id);
	}
	
	@PostMapping("/offer")
	public Offer addOffer(@RequestBody Offer offer) {
//		Offer offer1 = new Offer();
//		offer1.setCode("NewUser");
//		offer1.setName("FirstOrder");
//		offer1.setDiscount(25);
//		offer1.setTerms_conditions("Only for new Users!");
		this.offerServiceImpl.addOffer(offer);
		return offer;
	}
	
	@GetMapping("/users")
	public List<User> allUsers(){
		return userServiceImpl.allUsers();
	}
	
	//From ashraf
	
//	@GetMapping("/order/{Id}")
//	public Optional<Order> getOrder(@PathVariable long Id) {
//		System.out.println("in fetch order of user  " + Id);
//		return this.orderDaoImp.findOrderByID(Id);
//	}



	@DeleteMapping("/offer/{Id}")
	public void deleteOfferById(@PathVariable long Id) {
		System.out.println("in fetch offer " + Id);
		offerServiceImpl.deleteofferByID(Id);
	}


//	@PostMapping("/pizza")
//	public Pizza addPizza(@RequestBody Pizza pizza) {
//		this.pizzaDaoImpl.addPizza(pizza);
////		user.getAddresses().get(0).setUser(user);
////		addressDaoImpl.addAddress(user.getAddresses().get(0));
//		return pizza;
//	}
	

	@PutMapping("/pizza")
	public Pizza updatepizza(@RequestBody Pizza pizza) {
		System.out.println(pizza);
		return this.pizzaServiceImpl.updatePizza(pizza);
	}
	
	@PutMapping("/order")
	public Order updateOrder(@RequestBody Order order) {
		System.out.println(order);
		return this.orderDaoImp.updateOrder(order);
	}
	
	@PutMapping("/offer")
	public Offer updateOffer(@RequestBody Offer offer) {
		System.out.println(offer);
		return this.offerServiceImpl.updateOffer(offer);
	}
	
	@GetMapping("/offer/{id}")
	public Optional<Offer> getByOfferId(@PathVariable long id){
		return offerServiceImpl.OfferByID(id);
	}
	
	

}
