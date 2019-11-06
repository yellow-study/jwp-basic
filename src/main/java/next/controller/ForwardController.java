package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;

public class ForwardController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String returnJsp = "";

		switch (request.getRequestURI()) {
			case "/users/loginForm":
				returnJsp = "/user/login.jsp";
				break;
			case "/users/form":
				returnJsp = "/user/form.jsp";
		}

		return returnJsp;
	}
}
