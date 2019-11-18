package next.controller;

import core.mvc.Controller;
import next.dao.UserDao;
import next.exception.DataAccessException;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateUserController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(CreateUserController.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		User user = new User(req.getParameter("userId"), req.getParameter("password"), req.getParameter("name"),
			req.getParameter("email"));
		log.debug("User : {}", user);

		UserDao userDao = new UserDao();

		try {
			userDao.insert(user);
		} catch (DataAccessException exception) {
			//TODO error page return
			log.error("CreateUserController join error {}", exception.getMessage());
		}

		return "redirect:/";
	}
}
