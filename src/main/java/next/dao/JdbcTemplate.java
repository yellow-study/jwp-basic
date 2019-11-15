package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.h2.result.Row;

import core.jdbc.ConnectionManager;
import next.model.User;

public class JdbcTemplate {

	void update(String sql, PreparedStatementSetter preparedStatementSetter) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionManager.getConnection();
			pstmt = preparedStatementSetter.values();
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}

			if (con != null) {
				con.close();
			}
		}
	}

	List<User> query(String sql, PreparedStatementSetter preparedStatementSetter, RowMapper rowMapper) throws SQLException {
		List<User> userList ;
		try (ResultSet rs = preparedStatementSetter.values().executeQuery()) {
			userList = (List<User>) rowMapper.mapRow(rs);
		}
		return userList;
	}

	Object queryForObject(String sql, PreparedStatementSetter preparedStatementSetter, RowMapper rowMapper) throws SQLException {
		User user;

		try(ResultSet rs = preparedStatementSetter.values().executeQuery();) {
			user = (User) rowMapper.mapRow(rs);
		}

		return user;
	}
}
