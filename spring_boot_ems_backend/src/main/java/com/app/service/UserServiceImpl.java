package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.CartItemDao;
import com.app.dao.UserDao;
import com.app.entities.CartItem;
import com.app.entities.ShoppingCart;
import com.app.entities.User;


@Service // mandatory cls level anno to tell SC following is spring bean , containing B.L
@Transactional // mandatory cls level anno to tell SC --to auto supply tx management
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;
	
	@Autowired
	CartItemDao cartItemDao;
	
	@Override
	public User addUser(User user) {
		userDao.save(user);
		return user;
	}
	
	@Override
	public User updateCreds(User updateCust) {
		User updCus = this.userDao.findById(updateCust.getId()).orElse(null);
		updCus.setEmail(updateCust.getEmail());
		updCus.setFirst_name(updateCust.getFirst_name());
		updCus.setLast_name(updateCust.getLast_name());
		updCus.setMobile_no(updateCust.getMobile_no());
		updCus.setPassword(updateCust.getPassword());
		updCus.setUserRole(updateCust.getUserRole());
		this.userDao.save(updCus);
		return updCus;
	}
	
	@Override
	public User validate(String email, String password) {
		System.out.println("Email"+email+", Password: "+password);
		User user = userDao.findByEmailAndPassword(email, password)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid credentials !! , User not found!!!!"));
		return user;
	}
	
	public List<User> allUsers(){
		return userDao.findAll();
	}
	
	
	public User getByID(long id) {
		return userDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("user not found"));
	}
	
	ShoppingCart addToCart(long user_id, long pizza_id){
		
		CartItem cartItem = cartItemDao.findById(user_id).get(); 
		
		
		return cartItem.getCart();
	}

	public User getUserById(long id) {
		return userDao.findById(id).orElse(null);
	}
	
}
