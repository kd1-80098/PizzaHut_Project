package com.app.service;

import com.app.entities.User;

public interface UserService {
	public User addUser(User user);

	public User updateCreds(User updateCust);

	public User validate(String email, String password);
}
