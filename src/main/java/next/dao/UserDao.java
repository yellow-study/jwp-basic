package next.dao;

import core.jdbc.ConnectionManager;
import next.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDao {

	public void insert(User user) throws SQLException {
		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
		PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
			@Override
			public PreparedStatement values() throws SQLException {
				Connection con = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = con.prepareStatement(sql);
				preparedStatement.setString(1, user.getUserId());
				preparedStatement.setString(2, user.getPassword());
				preparedStatement.setString(3, user.getName());
				preparedStatement.setString(4, user.getEmail());

				return preparedStatement;
			}
		};

		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.update(sql, preparedStatementSetter);
	}

	public void update(User user) throws SQLException {
		String sql = "UPDATE USERS SET password=?, name=?, email=? WHERE userId=?";
		PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
			@Override
			public PreparedStatement values() throws SQLException {
				Connection con = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = con.prepareStatement(sql);
				preparedStatement.setString(1, user.getPassword());
				preparedStatement.setString(2, user.getName());
				preparedStatement.setString(3, user.getEmail());
				preparedStatement.setString(4, user.getUserId());

				return preparedStatement;
			}
		};
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.update(sql, preparedStatementSetter);
	}


	public List<User> findAll() throws SQLException {
		String sql = "SELECT userId, password, name, email FROM USERS";

		RowMapper rowMapper = new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				List<User> userList = new ArrayList<>();
				User user = null;
				while (rs.next()) {
					user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
							rs.getString("email"));
					userList.add(user);
				}
				return userList;
			}
		};

		List<User> resultUsers;
		try(Connection con = ConnectionManager.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(sql);) {
			PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
				@Override
				public PreparedStatement values() throws SQLException {
					return preparedStatement;
				}
			};

			JdbcTemplate jdbcTemplate = new JdbcTemplate();
			resultUsers = jdbcTemplate.query(sql, preparedStatementSetter, rowMapper);
		}

		return resultUsers;
	}

	public User findByUserId(String userId) throws SQLException {
		String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";

		RowMapper rowMapper = new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				User user = null;
				if (rs.next()) {
					user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
							rs.getString("email"));
				}
				return user;
			}
		};

		User resultUser = null;
		try(Connection con = ConnectionManager.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(sql);) {
			PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
				@Override
				public PreparedStatement values() throws SQLException {
					preparedStatement.setString(1, userId);
					return preparedStatement;
				}
			};

			JdbcTemplate jdbcTemplate = new JdbcTemplate();
			resultUser = (User) jdbcTemplate.queryForObject(sql, preparedStatementSetter, rowMapper);
		}

		return resultUser;
	}
}
