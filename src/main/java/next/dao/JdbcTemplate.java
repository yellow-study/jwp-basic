package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.ConnectionManager;
import next.model.User;

abstract class JdbcTemplate {

	void update(User user) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ConnectionManager.getConnection();
			String sql = createQuery();
			pstmt = con.prepareStatement(sql);
			setValues(pstmt);

			pstmt.executeUpdate();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}

			if (con != null) {
				con.close();
			}
		}
	}

	List query(String sql) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			List<Object> result = new ArrayList<Object>();
			while (rs.next()) {
				result.add(mapRow(rs));
			}

			return result;

		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	Object queryForObject(String sql) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql);
			setValues(pstmt);

			rs = pstmt.executeQuery();
			Object result = null;
			if (rs.next()) {
				result = mapRow(rs);
			}

			return result;

		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	abstract void setValues(PreparedStatement pstmt) throws SQLException;

	abstract String createQuery();

	abstract Object mapRow(ResultSet rs) throws SQLException;
}
