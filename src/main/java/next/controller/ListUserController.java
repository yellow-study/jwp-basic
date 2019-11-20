package next.controller;

import core.mvc.Controller;
import next.dao.UserDao;
import next.exception.DataAccessException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListUserController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(ListUserController.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if (!UserSessionUtils.isLogined(req.getSession())) {
			return "redirect:/users/loginForm";
		}

		UserDao userDao = UserDao.getInstance();
		req.setAttribute("users", userDao.findAll());

		return "/user/list.jsp";
	}
}
