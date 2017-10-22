package com.property.manager.dao.impl;

import java.util.List;

import com.property.manager.dao.IUserDAO;
import com.property.manager.models.User;
import com.property.manager.rowmappers.UserRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class UserDAO implements IUserDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDAO.class);

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public UserDAO(JdbcTemplate jdbcTemplate) {

		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<User> getAllUsers() {

		String getUsersQuery = "SELECT * FROM user";

		RowMapper<User> rowMapper = new UserRowMapper();

		LOGGER.info("Retrieved all users.");

		return this.jdbcTemplate.query(getUsersQuery, rowMapper);
	}

	@Override
	public User getUserById(int userId) {

		String getUserIdQuery = "SELECT id FROM user";

		RowMapper<User> rowMapper = new UserRowMapper();

		User user = this.jdbcTemplate.queryForObject(getUserIdQuery, rowMapper);

		LOGGER.info("Retrieved user by ID.");

		return user;
	}

	@Override
	public void addUser(User user) {

		String addUserQuery = "INSERT INTO user (id,full_name,address,username,password,role) values (?,?,?,?,?,?)";
		jdbcTemplate.update(addUserQuery, user.getId(), user.getFullName(), user.getAddress(), user.getUsername(),
				user.getPassword(), user.getRole());

		LOGGER.info("Added user to DB.");
	}
}
