package next.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.db.DataBase;
import core.mvc.Controller;

public class ListUserController implements Controller {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		if (!UserSessionUtils.isLogined(req.getSession())) {
			resp.sendRedirect("/users/loginForm");
			return "redirect:/users/loginForm";
		}

		req.setAttribute("users", DataBase.findAll());

		return "/user/list.jsp";
	}
}
