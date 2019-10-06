package next.user.service;

import core.db.DataBase;
import lombok.extern.slf4j.Slf4j;
import next.user.model.User;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * Created by juhyung0818@naver.com on 2019. 10. 6.
 */
@Slf4j
public class UserService {
    public User getUser(String userId) {
        return DataBase.findUserById(userId);
    }

    public void modifyUser(User user) {
        if (DataBase.findUserById(user.getUserId()) == null) {
            log.error("user is not exist. user : {}", user);
            throw new IllegalArgumentException("user is not exist. user " + user);
        }

        DataBase.addUser(user);
    }

    public boolean login(String userId, String password) {
        return Optional.ofNullable(DataBase.findUserById(userId))
                .filter(user -> StringUtils.equals(password, user.getPassword()))
                .isPresent();
    }

}
