package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import next.exception.DataAccessException;

public class JdbcTemplate {

	public void update(String sql, PreparedStatementSetter pss) throws DataAccessException {
		try (Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pss.values(pstmt);
			pstmt.executeUpdate();
		} catch (SQLException exception) {
			throw new DataAccessException("JdbcTemplate.update error : {} ", exception);
		}
	}

	public void update(String sql, Object... parameters) throws DataAccessException {
		update(sql, createPreparedStatementSetter(parameters));
	}

	public <T> List<T> query(String sql, RowMapper<T> rowMapper, PreparedStatementSetter pss)
		throws DataAccessException {
		ResultSet rs = null;

		try (Connection con = ConnectionManager.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)) {
			pss.values(pstmt);
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

	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) throws DataAccessException {
		return query(sql, rowMapper, createPreparedStatementSetter(parameters));
	}

	public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... parameters) throws DataAccessException {
		return queryForObject(sql, rowMapper, createPreparedStatementSetter(parameters));
	}

	public <T> T queryForObject(String sql, RowMapper<T> rowMapper, PreparedStatementSetter pss)
		throws DataAccessException {
		ResultSet rs = null;

		try (Connection con = ConnectionManager.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)) {
			pss.values(pstmt);
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

	private PreparedStatementSetter createPreparedStatementSetter(Object... parameters) {
		return new PreparedStatementSetter() {
			@Override
			public void values(PreparedStatement pstmt) throws SQLException {
				int parameterIndex = 0;
				for (Object parameter : parameters) {
					pstmt.setObject(parameterIndex + 1, parameter);
					parameterIndex++;
				}
			}
		};
	}
}
