package next.dao;

import next.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao {

	private UserDao() {}

	private static class UserDaoHolder {
		public static final UserDao dao = new UserDao();
	}

	public static UserDao getInstance() {
		return UserDaoHolder.dao;
	}

	public void insert(User user) {

		PreparedStatementSetter preparedStatementSetter = (PreparedStatement pstmt) -> {
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getEmail());
		};

		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "INSERT INTO USERS VALUES (?,?,?,?)";

		jdbcTemplate.update(sql, preparedStatementSetter);
	}

	public void update(User user) {

		PreparedStatementSetter preparedStatementSetter = (PreparedStatement pstmt) -> {
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getUserId());
		};

		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "UPDATE USERS SET password=?, name=?, email=? WHERE userId=?";

		jdbcTemplate.update(sql, preparedStatementSetter);
	}

	public List<User> findAll() {

		RowMapper<User> rowMapper = (ResultSet rs) -> {
			return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
				rs.getString("email"));
		};

		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "SELECT userId, password, name, email FROM USERS";

		return jdbcTemplate.query(sql, rowMapper);
	}

	public User findByUserId(String userId) {
		PreparedStatementSetter preparedStatementSetter = (PreparedStatement pstmt) -> {
			pstmt.setString(1, userId);
		};

		RowMapper<User> rowMapper = (ResultSet rs) -> {
			return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
				rs.getString("email"));
		};

		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";

		return jdbcTemplate.queryForObject(sql, preparedStatementSetter, rowMapper);
	}
}
