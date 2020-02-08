package next.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import next.factory.JdbcTemplateFactory;
import next.model.User;

public class UserDao {
	public void insert(User user) {
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate();
		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
	}

	public User findByUserId(String userId) {
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate();
		String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";

		RowMapper<User> rm = new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs) throws SQLException {
				return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
					rs.getString("email"));
			}
		};

		return jdbcTemplate.queryForObject(sql, rm, userId);
	}

	public List<User> findAll() throws SQLException {
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate();
		String sql = "SELECT userId, password, name, email FROM USERS";

		RowMapper<User> rm = new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs) throws SQLException {
				return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
					rs.getString("email"));
			}
		};

		return jdbcTemplate.query(sql, rm);
	}

	public void update(User user) {
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate();
		String sql = "UPDATE USERS set password = ?, name = ?, email = ? WHERE userId = ?";
		jdbcTemplate.update(sql, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
	}
}
