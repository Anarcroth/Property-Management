package com.property.manager.services;

import java.util.List;

import com.property.manager.models.User;

public interface IUserService {

	List<User> getAllUsers();

	User getUserByUsername(String username);

	boolean addUser(User user);

	void updateUser(User user);

	void deleteUser(int userId);
}
