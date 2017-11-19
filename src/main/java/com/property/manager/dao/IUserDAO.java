package com.property.manager.dao;

import java.util.List;

import com.property.manager.models.User;

public interface IUserDAO {

	List<User> getAllUsers();

	User getUserByUsername(String username);

	void addUser(User user);

	void approveUserOffer(int userId, int offerId);
}
