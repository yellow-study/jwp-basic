package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.annotation.RequestMapping;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.model.User;

/**
 * @author sungryul-yook on 2020-01-29.
 */
@RequestMapping(path = "/qna/form")
public class QuestionFormController extends AbstractController {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		boolean isLogined = UserSessionUtils.isLogined(session);

		ModelAndView mv = jspView("/qna/form.jsp");
		mv.addObject("isLogined", isLogined);

		if (isLogined) {
			User user = (User)session.getAttribute("user");
			mv.addObject("user", user);
		}

		return mv;
	}
}
