package com.property.manager.dao;

import java.util.List;

import com.property.manager.models.User;

public interface IUserDAO {

	List<User> getAllUsers();

	User getUserByUsername(String username);

	User getUserById(String id);

	void addUser(User user);

	void approveUserOffer(int userId, int offerId);
}
