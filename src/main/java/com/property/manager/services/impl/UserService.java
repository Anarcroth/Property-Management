package com.property.manager.services.impl;

import java.util.List;

import com.property.manager.dao.IUserDAO;
import com.property.manager.models.User;
import com.property.manager.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {


	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	private static IUserDAO userDAO = null;

	@Autowired
	public UserService(IUserDAO userDAO) {

		this.userDAO = userDAO;
	}

	@Override
	public List<User> getAllUsers() {

		return userDAO.getAllUsers();
	}

	@Override
	public User getUserByUsername(String username) {

		return userDAO.getUserByUsername(username);
	}

	@Override
	public boolean addUser(User user) {

		try {
			userDAO.addUser(user);

			return true;

		} catch (Exception e) {

			LOGGER.error("Could not add user.", e);
		}

		return false;
	}

	@Override
	public void updateUser(User user) {

	}

	@Override
	public void deleteUser(int userId) {

	}

	@Override
	public void approveUserOffer(int userId, int offerId) {

		userDAO.approveUserOffer(userId, offerId);
	}
}
