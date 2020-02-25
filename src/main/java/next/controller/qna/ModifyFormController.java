package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.web.filter.Controller;
import next.controller.UserSessionUtils;
import next.dao.QuestionDao;
import next.dao.UserDao;
import next.model.Question;

@Controller("/qna/modify/form")
public class ModifyFormController extends AbstractController {
	private QuestionDao questionDao = new QuestionDao();
	private UserDao userDao = new UserDao();

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long questionId = Long.parseLong(request.getParameter("questionId"));
		Question question = questionDao.findById(questionId);
		ModelAndView modelAndView = jspView("/qna/form.jsp");

		modelAndView.addObject("writerId", UserSessionUtils.getUserFromSession(request.getSession()).getUserId());
		modelAndView.addObject("question", question);
		modelAndView.addObject("isModify", true);

		return modelAndView;
	}
}
