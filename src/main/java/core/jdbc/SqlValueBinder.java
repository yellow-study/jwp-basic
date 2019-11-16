package core.jdbc;

import java.sql.SQLException;

@FunctionalInterface
public interface SqlValueBinder<T, U> {
    void accept(T t, U u) throws SQLException;
}
