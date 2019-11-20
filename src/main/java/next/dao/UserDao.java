package next.dao;

import next.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao {

	private JdbcTemplate jdbcTemplate;

	private UserDao() {
		jdbcTemplate = new JdbcTemplate();
	};

	private static class UserDaoHolder {
		public static final UserDao dao = new UserDao();
	}

	public static UserDao getInstance() {
		return UserDaoHolder.dao;
	}

	public void insert(User user) {
		String sql = "INSERT INTO USERS VALUES (?,?,?,?)";

		jdbcTemplate.update(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
	}

	public void update(User user) {
		String sql = "UPDATE USERS SET password=?, name=?, email=? WHERE userId=?";

		jdbcTemplate.update(sql, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
	}

	public List<User> findAll() {
		String sql = "SELECT userId, password, name, email FROM USERS";

		return jdbcTemplate.query(sql, (ResultSet rs) -> {
			return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
				rs.getString("email"));
		});
	}

	public User findByUserId(String userId) {
		String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";

		return jdbcTemplate.queryForObject(sql, (ResultSet rs) -> {
			return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
				rs.getString("email"));
		}, userId);
	}
}
