package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.OrderItemDao;
import com.app.dto.OrderDTO;
import com.app.entities.Address;
import com.app.entities.CartItem;
import com.app.entities.LoginCred;
import com.app.entities.Order;
import com.app.entities.OrderItem;
import com.app.entities.Pizza;
import com.app.entities.Review;
import com.app.entities.ShoppingCart;
import com.app.entities.User;
import com.app.service.AddressServiceImpl;
import com.app.service.OfferServiceImpl;
import com.app.service.OrderServiceImpl;
import com.app.service.PizzaServiceImpl;
import com.app.service.ReviewsServiceImpl;
import com.app.service.ShoppingCartServiceImpl;
import com.app.service.UserServiceImpl;


@RestController
@RequestMapping("/pizzadelivery")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	
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
	
	@Autowired
	OrderServiceImpl orderServiceImpl;
	
	@Autowired
	OrderItemDao orderItemDao;
	
	@PostMapping("/login")
	public User logigCustomer(@RequestBody LoginCred logincred) {
		System.out.println(logincred);
		return this.userServiceImpl.validate(logincred.getEmail(), logincred.getPassword());
	}
	
	
	@PostMapping("/register")
	public User register(@RequestBody User user) {
		System.out.println(user);
		User newUser = userServiceImpl.addUser(user);
		shoppingCartServiceImpl.addCartToUser(newUser);
		return user;
	}
	
	@PutMapping("/user")
	public User updateUser(@RequestBody User user) {
		return userServiceImpl.updateCreds(user);		
	}
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable long id) {
		return userServiceImpl.getUserById(id);		
	}
	
	@PostMapping("/menu")
	public List<Pizza> catMenu(){
		return pizzaServiceImpl.getByCategory((long) 1);
	}
	
//	@GetMapping("/menu")
//	public List<Pizza> menu(){
//		return pizzaDaoImpl.getAll();
//	}
	
	@PostMapping("/address")
	public Address addAddress(@RequestBody Address address) {
		this.addressServiceImpl.addAddress(address);
		return address;
	}
	
	@PostMapping("/address/{id}")
	public Address addAddressWithUser(@RequestBody Address address, @PathVariable String id) {
		System.out.println(id);
		Long pid = Long.parseLong(id);
		User user = userServiceImpl.getUserById(pid);
		address.setUser(user);
		this.addressServiceImpl.addAddress(address);
		return address;
	}
	
	
//	@PostMapping("/address")
//	public Address addAddress(@RequestBody Address address, @RequestParam("userId") Long userId) {
//	    User user = userDaoImpl.getUserById(userId); // Retrieve the User entity by its ID
//	    address.setUser(user); // Assign the User entity to the Address entity
//	    this.addressDaoImpl.addAddress(address);
//	    return address;
//	}
	
	
	@GetMapping("/addresses")
	public List<Address> seeAllAddress(){
		return this.addressServiceImpl.findAllAddress();
	}
	
	@GetMapping("/addressbyuser/{id}")
	public List<Address> getAddressByUser(@PathVariable long id){
		return this.addressServiceImpl.getAddressByUser(id);
	}
	
	
	
//	@GetMapping("/alladdress/{id}")
//	public Optional<Address> getAddress(@PathVariable long id){
//		return addressdaoImpl.getAddress(id);
//	}
	
	
	

	@DeleteMapping("/address/{id}")
	public void deleteAddressByID(@PathVariable long id){
		addressServiceImpl.deleteAddressById(id);
	}
	 
	 
	@PostMapping("/review")
	public Review addReview(@RequestBody Review review) {
		this.reviewsServiceImpl.addReview(review);
		return review;
	}
	
	@GetMapping("/pizzas")
	public List<Pizza> allPizza() {
		return this.pizzaServiceImpl.findAllPizza();
	}
	
	@GetMapping("/pizzabycat/{id}")
	public List<Pizza> getByCatId(@PathVariable long cat_id){
		return pizzaServiceImpl.findByCategoryID(cat_id);
	}
	
//	public ShoppingCart addToCart(@PathVariable long user_id, @PathVariable long pizza_id){
////		step 1: create new cartitem & set the cart id as current
////		step 2: add pizza id to cart item
////		step 3: calculate the price
////		step 4: 
//		
//		
//		
//		return null;
//	}
//	
//	@GetMapping("/path")
//	public String handleRequest(@RequestParam("name") String name,
//	                            @RequestParam("age") int age) {
//	    // code to handle the request
//	}
//	In this example, @RequestParam("name") extracts the "name" parameter from the URL and binds it to the name method parameter. Similarly, @RequestParam("age") extracts the "age" parameter and binds it to the age parameter.
//
//	On the other hand, @PathVariable is used to extract path variables from a URL. Path variables are part of the URL path, and are denoted by curly braces. For example, in the URL "/path/{name}/{age}", "name" and "age" are path variables. You can use the @PathVariable annotation to extract these variables in your controller method:
//
//	java
//	Copy code
//	@GetMapping("/path/{name}/{age}")
//	public String handleRequest(@PathVariable("name") String name,
//	                            @PathVariable("age") int age) {
//	    // code to handle the request
//	}
//	In this example, @PathVariable("name") extracts the "name" path variable from the URL and binds it to the name method parameter. Similarly, `@PathVariable
//



	
	@GetMapping("/addtocart")
	public ShoppingCart addToCart(@RequestParam("user_id") long user_id,
            @RequestParam("pizza_id") long pizza_id) {
		return shoppingCartServiceImpl.addToCart(user_id, pizza_id);
	}
	
	@GetMapping("/removefromcart")
	public ShoppingCart removeFromCart(@RequestParam("user_id") long user_id,
            @RequestParam("pizza_id") long pizza_id) {
		return shoppingCartServiceImpl.removeFromCart(user_id, pizza_id);
	}
	
//	@PostMapping("/checkout")
//	public Order checkout(@RequestBody ShoppingCart cart) {
//		return shoppingCartDaoImpl.checkout(cart);
//	}
	
//	@PostMapping("/checkout")
//	public Order checkout(@RequestParam long user_id, @RequestParam long address_id, @RequestParam String paymentType, 
//			@RequestParam double discount, @RequestParam double deliveryPrice, @RequestParam double taxAmount) {
//		return shoppingCartDaoImpl.checkout(user_id, address_id, paymentType, discount, deliveryPrice, taxAmount);
//	}
	
	@PostMapping("/checkout")
	public Order checkout(@RequestBody OrderDTO order) {
		return shoppingCartServiceImpl.checkout(order.getUserId(),order.getPaymentType()
				,order.getDeliveryPrice(), order.getTotalItems(), order.getTotalOrderPrice());
	}
	
	@GetMapping("/cart/{user_id}")
	public ShoppingCart getCartByUserId(@PathVariable long user_id){
		return shoppingCartServiceImpl.getCartByUserID(user_id);
	}
	
	@GetMapping("/cartitems/{cart_id}")
	public List<CartItem> getCartItemsByCartId(@PathVariable long cart_id){
		return shoppingCartServiceImpl.getCartItemsByCartID(cart_id);
	}
	
	@DeleteMapping("/cartitems/{id}")
	public void deleteCartItemById(@PathVariable long id) {
		shoppingCartServiceImpl.deleteCartItemById(id);
	}
	
//	@PostMapping("/setAddress/{id}")
//	public Order setAddressToOrder(@RequestBody Address address, @PathVariable long id) {
//		
//		return shoppingCartDaoImpl.checkout(cart);
//	}
	
	@GetMapping("/order/{id}")
	public Order findOrderById(@PathVariable long id) {
		return orderServiceImpl.findByID(id);
	}
	
	@GetMapping("/orderbyuser/{id}")
	public List<Order> findOrderByUser(@PathVariable long id){
		return orderServiceImpl.findByUserId(id);
	}
	
	@GetMapping("/orderitemsbyorder/{id}")
	public List<OrderItem> findOrderItemsByOrder(@PathVariable long id){
		return orderItemDao.findByOrderId(id);
	}
	
	@GetMapping("/productreviews/{id}")
	public List<Review> getProductReviews(@PathVariable long id){
		return reviewsServiceImpl.findReviewByProduct(id);
	}
	
	@GetMapping("/userreviews/{id}")
	public List<Review> getUserReviews(@PathVariable long id){
		return reviewsServiceImpl.findReviewByUser(id);
	}
	
	@GetMapping("/pizza/{id}")
	public Pizza pizzaById(@PathVariable long id) {
		return pizzaServiceImpl.PizzaByID(id).orElse(null);
	}
	
	@GetMapping("/address/{id}")
	public Address addressById(@PathVariable long id) {
		return addressServiceImpl.addressByID(id).orElse(null);
	}
	
	
	
}
