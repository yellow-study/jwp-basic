package next.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import core.jdbc.SqlValueBinder;
import lombok.extern.slf4j.Slf4j;
import next.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public void insert(User user) {
        SqlValueBinder<PreparedStatement, User> valueBinder = (preparedStatement, data) -> {
            preparedStatement.setString(1, data.getUserId());
            preparedStatement.setString(2, data.getPassword());
            preparedStatement.setString(3, data.getName());
            preparedStatement.setString(4, data.getEmail());
        };

        jdbcTemplate.insert(user, UserSql.INTO_USERS_VALUES, valueBinder);
    }

    public void update(User user) {
        SqlValueBinder<PreparedStatement, User> valueBinder = (preparedStatement, data) -> {
            preparedStatement.setString(1, data.getPassword());
            preparedStatement.setString(2, data.getName());
            preparedStatement.setString(3, data.getEmail());
            preparedStatement.setString(4, data.getUserId());
        };

        jdbcTemplate.update(user, UserSql.UPDATE_USER, valueBinder);
    }

    public List<User> findAll() {
        RowMapper<ResultSet, List<User>> rowMapper = (resultSet) -> {
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(mapUser(resultSet));
            }
            return users;
        };

        return jdbcTemplate.query(UserSql.SELECT_USERS, rowMapper);
    }

    public User findByUserId(String userId) {
        SqlValueBinder<PreparedStatement, String> valueBinder = (preparedStatement, data) ->
                preparedStatement.setString(1, data);

        RowMapper<ResultSet, User> rowMapper = (resultSet) -> {
            User user = null;
            if (resultSet.next()) {
                user = mapUser(resultSet);
            }
            return user;
        };

        return jdbcTemplate.queryForObject(userId, UserSql.SELECT_USER_BY_ID, valueBinder, rowMapper);
    }

    private User mapUser(ResultSet resultSet) throws SQLException {
        return User.builder()
                .userId(resultSet.getString("userId"))
                .password(resultSet.getString("password"))
                .name(resultSet.getString("name"))
                .email(resultSet.getString("email"))
                .build();
    }
}
