package next.dao;

import core.jdbc.ConnectionManager;
import next.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
	public void insert(User user) throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate() {

			@Override
			void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getUserId());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getEmail());
			}

			@Override
			String createQuery() {
				return "INSERT INTO USERS VALUES (?,?,?,?)";
			}

			@Override
			Object mapRow(ResultSet rs) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		jdbcTemplate.update(user);
	}

	public void update(User user) throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate() {
			@Override
			void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getPassword());
				pstmt.setString(2, user.getName());
				pstmt.setString(3, user.getEmail());
				pstmt.setString(4, user.getUserId());
			}

			@Override
			String createQuery() {
				return "UPDATE USERS SET password=?, name=?, email=? WHERE userId=?";
			}

			@Override
			Object mapRow(ResultSet rs) {
				return null;
			}

		};
		jdbcTemplate.update(user);
	}

	public List<User> findAll() throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate() {

			@Override
			void setValues(PreparedStatement pstmt) throws SQLException {}

			@Override
			String createQuery() {
				return null;
			}

			@Override
			Object mapRow(ResultSet rs) throws SQLException {
				return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email"));
			}

		};
		String sql = "SELECT userId, password, name, email FROM USERS";

		return (List<User>)jdbcTemplate.query(sql);
	}

	public User findByUserId(String userId) throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate() {

			@Override
			void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, userId);
			}

			@Override
			String createQuery() {
				return null;
			}

			@Override
			Object mapRow(ResultSet rs) throws SQLException {
				return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
						rs.getString("email"));
			}

		};
		String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";

		return (User)jdbcTemplate.queryForObject(sql);
	}
}
