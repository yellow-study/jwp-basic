package next.user.service;

import core.db.DataBase;
import next.user.model.User;

/**
 * Created by juhyung0818@naver.com on 2019. 10. 6.
 */
public class UserService {
    public User getUser(String userId) {
        return DataBase.findUserById(userId);
    }
}
