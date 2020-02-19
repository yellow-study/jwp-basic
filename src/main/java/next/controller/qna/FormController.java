package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.web.filter.Controller;
import next.controller.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("/qna/form")
public class FormController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(!UserSessionUtils.isLogined(request.getSession())){
            return jspView("redirect:/users/loginForm");
        } else {
            ModelAndView mav = jspView("/qna/form.jsp");
            mav.addObject("userId", UserSessionUtils.getUserFromSession(request.getSession()).getUserId());

            return mav;
        }

    }
}
