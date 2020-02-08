package next.controller.qna;

import core.annotations.Controller;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("/qna/form")
public class FormController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {
        if (!UserSessionUtils.isLogined(req.getSession())) {
            return jspView("redirect:/users/loginForm");
        } else {
            return jspView("/qna/form.jsp");
        }
    }
}
