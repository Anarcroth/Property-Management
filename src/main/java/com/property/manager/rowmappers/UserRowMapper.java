package com.property.manager.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.property.manager.models.User;
import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {

		User user = new User();

		user.setId(rs.getInt("id"));
		user.setRole(rs.getString("role"));
		user.setAddress(rs.getString("address"));
		user.setPassword(rs.getString("password"));
		user.setUsername(rs.getString("username"));
		user.setFullName(rs.getString("full_name"));

		return user;
	}
}
