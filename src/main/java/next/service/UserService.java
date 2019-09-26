package next.service;

import core.db.DataBase;
import next.model.User;

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
}
