package core.jdbc;

import core.exception.DataAccessException;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class JdbcTemplate {
    public <T> void insert(T data, String sql, SqlValueBinder<PreparedStatement, T> valueBinder) {
        insertOrUpdate(data, sql, valueBinder);
    }

    public <T> void update(T data, String sql, SqlValueBinder<PreparedStatement, T> valueBinder) {
        insertOrUpdate(data, sql, valueBinder);
    }

    private <T> void insertOrUpdate(T data, String sql, SqlValueBinder<PreparedStatement, T> valueBinder) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            log.debug("data : {}", data);

            valueBinder.accept(preparedStatement, data);

            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new DataAccessException();
        }
    }

    public <R> R query(String sql, RowMapper<ResultSet, R> rowMapper) {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            return rowMapper.apply(resultSet);
        } catch (SQLException e) {
            throw new DataAccessException();
        }
    }

    public <T, R> R queryForObject(T data, String sql, SqlValueBinder<PreparedStatement, T> valueBinder, RowMapper<ResultSet, R> rowMapper) {
        ResultSet resultSet = null;
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {

            valueBinder.accept(preparedStatement, data);
            resultSet = preparedStatement.executeQuery();

            R returnValue = rowMapper.apply(resultSet);

            if (resultSet != null) {
                resultSet.close();
            }
            return returnValue;
        } catch (SQLException e) {
            throw new DataAccessException();
        }
    }
}
