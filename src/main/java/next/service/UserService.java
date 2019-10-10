package next.service;

import core.db.DataBase;
import next.model.User;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * Created by juhyung0818@naver.com on 2019. 9. 26.
 */
public class UserService {
    public User getUser(String userId) {
        return DataBase.findUserById(userId);
    }

    public void updateUser(User user) {
        if (DataBase.findUserById(user.getUserId()) == null) {
            throw new IllegalArgumentException(user.toString());
        }

        DataBase.updateUser(user);
    }

    public Optional<User> login(String userId, String password) {
        return Optional.ofNullable(getUser(userId))
                       .filter(user -> StringUtils.equals(password, user.getPassword()));
    }
}
