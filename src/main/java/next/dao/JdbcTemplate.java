package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.ConnectionManager;
import next.exception.DataAccessException;

public class JdbcTemplate {

	public void update(String sql, Object... parameters) throws DataAccessException {
		try (Connection con = ConnectionManager.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);) {

			int parameterIndex = 0;
			for (Object parameter : parameters) {
				pstmt.setObject(parameterIndex + 1, parameter);
				parameterIndex++;
			}
			pstmt.executeUpdate();
		} catch (SQLException exception) {
			throw new DataAccessException("JdbcTemplate.update error : {} ", exception);
		}
	}

	public <T> List<T> query(String sql, RowMapper<T> rowMapper) throws DataAccessException {
		ResultSet rs = null;

		try (Connection con = ConnectionManager.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)) {

			rs = pstmt.executeQuery();

			List<T> result = new ArrayList<T>();
			while (rs.next()) {
				result.add(rowMapper.mapRow(rs));
			}

			return result;

		} catch (SQLException exception) {
			throw new DataAccessException("JdbcTemplate.query error : {} ", exception);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException exception) {
					throw new DataAccessException("JdbcTemplate.query ResultSet close error : {} ", exception);
				}
			}
		}
	}

	public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... parameters)
		throws DataAccessException {
		ResultSet rs = null;

		try (Connection con = ConnectionManager.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);) {

			int parameterIndex = 0;
			for (Object parameter : parameters) {
				pstmt.setObject(parameterIndex + 1, parameter);
				parameterIndex++;
			}

			rs = pstmt.executeQuery();

			T result = null;

			if (rs.next()) {
				result = rowMapper.mapRow(rs);
			}

			return result;

		} catch (SQLException exception) {
			throw new DataAccessException("JdbcTemplate.queryForObject error : {} ", exception);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException exception) {
					throw new DataAccessException("JdbcTemplate.queryForObject ResultSet close error : {} ", exception);
				}
			}
		}
	}
}
