package next.dao;

import next.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao {

	public void insert(User user) throws SQLException {

		PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {

			@Override
			public void values(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getUserId());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getEmail());
				
			}};

		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "INSERT INTO USERS VALUES (?,?,?,?)";

		jdbcTemplate.update(sql, preparedStatementSetter);
	}

	public void update(User user) throws SQLException {	

		PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {

			@Override
			public void values(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getPassword());
				pstmt.setString(2, user.getName());
				pstmt.setString(3, user.getEmail());
				pstmt.setString(4, user.getUserId());
				
			}};

		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "UPDATE USERS SET password=?, name=?, email=? WHERE userId=?";

		jdbcTemplate.update(sql, preparedStatementSetter);
	}

	public List<User> findAll() throws SQLException {
		
		RowMapper rowMapper = new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email"));
			}
		};

		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "SELECT userId, password, name, email FROM USERS";

		return (List<User>)jdbcTemplate.query(sql, rowMapper);
	}

	public User findByUserId(String userId) throws SQLException {
		PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {

			@Override
			public void values(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, userId);
				
			}};

		RowMapper rowMapper = new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
						rs.getString("email"));
			}};

		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";

		return (User)jdbcTemplate.queryForObject(sql, preparedStatementSetter, rowMapper);
	
	}
}
