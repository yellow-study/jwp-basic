package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.ConnectionManager;

class JdbcTemplate {

	void update(String sql, PreparedStatementSetter preparedStatementSetter) throws RuntimeException {
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
				preparedStatementSetter.values(pstmt);
				pstmt.executeUpdate();
		} catch(SQLException exception) {
			//TODO Exception 정의 
			throw new RuntimeException("DataAccessException : {} ", exception);
		}
	}

	List query(String sql, RowMapper rowMapper) throws SQLException {
		ResultSet rs = null;

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){

			rs = pstmt.executeQuery();

			List<Object> result = new ArrayList<Object>();
			while (rs.next()) {
				result.add(rowMapper.mapRow(rs));
			}

			return result;

		} catch(SQLException exception) {
			//TODO Exception 정의 
			throw new RuntimeException("DataAccessException : {} ", exception);
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
	}

	Object queryForObject(String sql, PreparedStatementSetter preparedStatementSetter, RowMapper rowMapper) throws SQLException {
		ResultSet rs = null;

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			preparedStatementSetter.values(pstmt);

			rs = pstmt.executeQuery();

			Object result = null;

			if (rs.next()) {
				result = rowMapper.mapRow(rs);
			}

			return result;

		} catch(SQLException exception) {
			//TODO Exception 정의 
			throw new RuntimeException("DataAccessException : {} ", exception);
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
	}
}
