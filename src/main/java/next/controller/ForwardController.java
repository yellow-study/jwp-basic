package next.controller;

import core.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
