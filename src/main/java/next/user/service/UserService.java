package next.user.service;

import core.db.DataBase;
import lombok.extern.slf4j.Slf4j;
import next.user.model.User;

import javax.xml.crypto.Data;

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

        DataBase.updateUser(user);
    }
}
