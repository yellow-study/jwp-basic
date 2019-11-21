package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import core.jdbc.ConnectionManager;
import next.exception.DataAccessException;
import next.model.User;

public class JdbcTemplate {

	void update(String sql, PreparedStatementSetter preparedStatementSetter) throws SQLException {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatementSetter.setValues(preparedStatement);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	void update(String sql, Object... parameters) throws SQLException {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			for (int i = 0; i < parameters.length; i++) {
				preparedStatement.setObject(i + 1, parameters[i]);
			}

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public <T> List<T> query(String sql, PreparedStatementSetter preparedStatementSetter, RowMapper<List<T>> rowMapper) throws SQLException {
		List<T> userList;
		ResultSet rs = null;
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(sql);
		) {
			preparedStatementSetter.setValues(preparedStatement);
			rs = preparedStatement.executeQuery();
			userList = rowMapper.mapRow(rs);

			return userList;
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			rs.close();
		}
	}

	public <T> T queryForObject(String sql, PreparedStatementSetter preparedStatementSetter, RowMapper<T> rowMapper) throws SQLException {
		T user;
		ResultSet rs = null;
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(sql);
		) {
			preparedStatementSetter.setValues(preparedStatement);
			rs = preparedStatement.executeQuery();
			user = rowMapper.mapRow(rs);
		} finally {
			rs.close();
		}

		return user;
	}
}
