package com.app.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.AddressDao;
import com.app.dao.CartItemDao;
import com.app.dao.OrderDao;
import com.app.dao.OrderItemDao;
import com.app.dao.ShoppingCartDao;
import com.app.entities.Address;
import com.app.entities.CartItem;
import com.app.entities.DeliveryStatus;
import com.app.entities.Order;
import com.app.entities.OrderItem;
import com.app.entities.Pizza;
import com.app.entities.ShoppingCart;
import com.app.entities.User;


@Service
@Transactional
public class ShoppingCartServiceImpl {
	@Autowired
	ShoppingCartDao shoppingCartDao;
	
	@Autowired
	CartItemDao cartItemDao;
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired
	PizzaServiceImpl pizzaServiceImpl;
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	OrderItemDao orderItemDao;
	
	@Autowired
	AddressDao addressDao;
	
	 
	public ShoppingCart addToCart(long user_id, long pizza_id){
		Pizza pizza = pizzaServiceImpl.getByID(pizza_id);
		System.out.println("Got pizza: "+pizza);
		ShoppingCart cart = shoppingCartDao.findByCartOwnerId(user_id);
		System.out.println("Got cart: "+cart);
		CartItem cartItem = cartItemDao.findByPizzaIdAndCartId(pizza_id, cart.getId());
		System.out.println("GotCartItem: "+cartItem);
		
		if(cartItem == null) {
			cartItem = new CartItem();
			cartItem.setPizza(pizza);
			cartItem.setCart(cart);
			System.out.println("New cartItem created: "+cartItem);
			cartItemDao.save(cartItem);
		}
		
		int newQuantity = cartItem.getQuantity();
		newQuantity++;
		System.out.println("Quantity: "+newQuantity);
		System.out.println("Pizza Price: "+pizza.getPrice());
		double newPrice = (newQuantity * pizza.getPrice());
		System.out.println("Total Price: "+newPrice);
		cartItem.setTotalPrice(newPrice);
		cartItem.setQuantity(newQuantity);
		
		System.out.println("new cartItem is ready" +cartItem);
		System.out.println("new cartItem is saved" +cartItem);

		System.out.println("new cart saved: "+shoppingCartDao.save(cart));
		
		ShoppingCart updatedCart = this.updateCart(cart);
		
		return shoppingCartDao.save(updatedCart);
	}
	
	public ShoppingCart removeFromCart(long user_id, long pizza_id){
		Pizza pizza = pizzaServiceImpl.getByID(pizza_id);
		System.out.println("Got pizza: "+pizza);
		ShoppingCart cart = shoppingCartDao.findByCartOwnerId(user_id);
		System.out.println("Got cart: "+cart);
		CartItem cartItem = cartItemDao.findByPizzaIdAndCartId(pizza_id, cart.getId());
		System.out.println("GotCartItem: "+cartItem);
		
		if(cartItem == null) {
			System.out.println("Quantity is zero");
			return cart;
		}
		
		int newQuantity = cartItem.getQuantity();
		
		if(newQuantity==1) {
			System.out.println("Quantity is Zero!");
			cartItemDao.delete(cartItem);
			System.out.println("deleted cart item: "+cart);
			ShoppingCart updatedCart = this.updateCart(cart);
			System.out.println("Updated cart: "+updatedCart);
			return shoppingCartDao.save(updatedCart);
		}else if(newQuantity<=0){
			throw new ResourceNotFoundException("Quantity less than zero");
		}else{
		newQuantity--;
		} 
		
		System.out.println("Quantity: "+newQuantity);
		System.out.println("Pizza Price: "+pizza.getPrice());
		double newPrice = (newQuantity * pizza.getPrice());
		System.out.println("Total Price: "+newPrice);
		cartItem.setTotalPrice(newPrice);
		cartItem.setQuantity(newQuantity);
		
		System.out.println("new cartItem is ready" +cartItem);
		cartItemDao.save(cartItem);
		
		System.out.println("new cartItem is saved" +cartItem);
		System.out.println("new cart saved: "+shoppingCartDao.save(cart));
		
		ShoppingCart updatedCart = this.updateCart(cart);
		return shoppingCartDao.save(updatedCart);
	}	
	
	public ShoppingCart addCartToUser(User user) {
		ShoppingCart cart = new ShoppingCart();
	    cart.setCartOwner(user);
	    shoppingCartDao.save(cart);
	    return cart;
	}
	
	public ShoppingCart updateCart(ShoppingCart cart) {
		List<CartItem> cartItems = cartItemDao.findByCartId(cart.getId());
		Double cartPrice=0.0;
		int count = 0;
		for (CartItem item : cartItems) {
			cartPrice += item.getTotalPrice();
			count++;
        }
		cart.setTotalCartPrice(cartPrice);
		cart.setTotalItems(count);
		
		return cart;
	}
	
//	public Order checkout(ShoppingCart sentCart) {
//		/* Step 1. Get list of cart items
//		 * step 2. create new order
//		 * step 3. create new order item for each order - in loop
//		 * step 4. copy each cart item to order items - in loop
//		 * Step 5. delete cart item - in loop
//		 * step 5. copy additional info (total price etc) to orders
//		 * step 6. update the cart (total price, count etc)
//		 * step 7. save updated cart to DB
//		 * step 8. save & return order
//		 */
//		Order order = new Order();
//		ShoppingCart cart = shoppingCartRepo.findById(sentCart.getId()).orElse(null) ; 
//		List<CartItem>cartItems = cartItemRepo.findByCartId(cart.getId());
//		System.out.println("cartItems in order "+cartItems);
//		if (cartItems.isEmpty()) {
//			System.out.println("order cannot be placed with empty cart");
//			return null;
//		}
//		
//		System.out.println("Before loop");
//		System.out.println("Got cart items: "+cartItems);
//		for (CartItem cartItem : cartItems) {
//			OrderItem orderItem = new OrderItem();
//			orderItem.setOrder(order);
//			orderItem.setPizza(cartItem.getPizza());
//			orderItem.setQuantity(cartItem.getQuantity());
//			orderItem.setTotalPrice(cartItem.getTotalPrice());
//			order.getOrderItems().add(orderItem);
//			System.out.println("Order Item Saved");
//			cartItemRepo.delete(cartItem);
//        }
//		System.out.println("After loop" +cart);
//		
//		order.setCartOwner(cart.getCartOwner());
//		order.setTotalItems(cart.getTotalItems());
//		order.setTotalOrderPrice(cart.getTotalCartPrice());
//		order.setStatus(DeliveryStatus.PLACED);
//		
//		ShoppingCart updatedCart = this.updateCart(cart);
//		shoppingCartRepo.save(updatedCart);
//		
//		return orderRepo.save(order);
//	}
	
	public Order checkout(long user_id, String paymentType, double deliveryPrice, int totalItems, double totalOrderPrice) {
		/* Step 1. Get list of cart items
		 * step 2. create new order
		 * step 3. create new order item for each order - in loop
		 * step 4. copy each cart item to order items - in loop
		 * Step 5. delete cart item - in loop
		 * step 5. copy additional info (total price etc) to orders
		 * step 6. update the cart (total price, count etc)
		 * step 7. save updated cart to DB
		 * step 8. save & return order
		 */
		User user = userServiceImpl.getByID(user_id);
		List<Address> addlist = addressDao.findByUserId(user_id);
		Address add = addlist.get(0);
//		Address address = addressDao.findById(address_id).orElse(null);
		
		Order order = new Order();
		ShoppingCart cart = shoppingCartDao.findByCartOwnerId(user.getId());
		order.setCartOwner(cart.getCartOwner());
		order.setTotalItems(cart.getTotalItems());
		order.setCartPrice(cart.getTotalCartPrice());
		order.setStatus(DeliveryStatus.PLACED);
		order.setAddress(add);
		order.setDeliveryPrice(deliveryPrice);
		order.setPaymentType(paymentType);
		order.setTotalOrderPrice(cart.getTotalCartPrice());
		order.setOrderOreatedOn(LocalDate.now());
		order.setOrderItems(null);
		order.setPlacedOn(LocalDate.now());
		
		orderDao.save(order);
		
		
		
		List<CartItem>cartItems = cartItemDao.findByCartId(cart.getId());
		System.out.println("cartItems in order "+cartItems);
		
		if (cartItems.isEmpty()) {
			System.out.println("order cannot be placed with empty cart");
			return null;
		}
		
		System.out.println("Before loop");
		System.out.println("Got cart items: "+cartItems);
		for (CartItem cartItem : cartItems) {
			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(order);
			orderItem.setPizza(cartItem.getPizza());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setTotalPrice(cartItem.getTotalPrice());
			orderItemDao.save(orderItem);
			System.out.println("Order Item Saved");
			cartItemDao.delete(cartItem);
        }
		System.out.println("After loop" +cart);
		ShoppingCart updatedCart = this.updateCart(cart);
		shoppingCartDao.save(updatedCart);
		
		return orderDao.save(order);
	}
	
	
	public ShoppingCart getCartByUserID(long user_id) {
		return shoppingCartDao.findByCartOwnerId(user_id);
	}

	public List<CartItem> getCartItemsByCartID(long cart_id) {
		return cartItemDao.findByCartId(cart_id);
	}
	
//	public List<CartItem> getCartItemsByUserId(long user_id) {
//		return shoppingCartRepo.findByCartOwnerId(user_id);
//	}
	
	public void deleteCartItemById(long id) {
		CartItem item = cartItemDao.findById(id).orElse(null);
		ShoppingCart cart = item.getCart();
		cartItemDao.deleteById(id);
		this.updateCart(cart);
	}

}
