package next.dao;

final class UserSql {
    static final String INTO_USERS_VALUES = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
    static final String UPDATE_USER = "UPDATE USERS SET password = ?, name = ?, email = ? WHERE userId = ?";
    static final String SELECT_USERS = "SELECT userId, password, name, email FROM USERS";
    static final String SELECT_USER_BY_ID = "SELECT userId, password, name, email FROM USERS WHERE userid=?";

    private UserSql() {

    }
}
