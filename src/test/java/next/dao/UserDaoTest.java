package next.dao;

import next.user.dao.UserDao;
import next.user.model.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserDaoTest {

    @Test
    public void crud() throws Exception {
        User expected = new User("userId", "password", "name", "javajigi@email.com");
        UserDao userDao = new UserDao();
        userDao.insert(expected);

        User actual = userDao.findByUserId(expected.getUserId());
        assertEquals(expected, actual);
    }

}
