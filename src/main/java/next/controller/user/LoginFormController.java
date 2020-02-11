package next.controller.user;

import core.annotation.RequestMapping;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(path = "/users/loginForm")
public class LoginFormController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return jspView("/user/login.jsp");
    }
}
