package core.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

interface PreparedStatementSetter {
	void values(PreparedStatement pstmt) throws SQLException;
}
