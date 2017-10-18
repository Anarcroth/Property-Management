package com.property.manager.dao;

import java.util.List;

import com.property.manager.models.User;

public interface IUserDAO {

	List<User> getAllUsers();

	User getUserById(int userId);

	void addUser(User user);
}
