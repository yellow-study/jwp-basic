package core.jdbc;

import java.sql.SQLException;

@FunctionalInterface
public interface RowMapper<T, R> {
    R apply(T t) throws SQLException;
}
