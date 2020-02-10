package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.web.filter.Controller;
import next.controller.UserSessionUtils;
import next.dao.QuestionDao;
import next.model.Question;
import next.model.User;

@Controller("/qna/create")
public class AddQuestionController extends AbstractController {
	private static final Logger log = LoggerFactory.getLogger(AddQuestionController.class);

	private QuestionDao questionDao = new QuestionDao();

	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {

		if (!UserSessionUtils.isLogined(req.getSession())) {
			return jspView("redirect:/users/loginForm");
		}

		User user = UserSessionUtils.getUserFromSession(req.getSession());
		Question question = new Question(user.getName(), req.getParameter("title")
			, req.getParameter("contents"));
		log.debug("question : {}", question);

		questionDao.insert(question);

		return jspView("redirect:/");
	}
}
