package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.QuestionDao;
import next.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author sungryul-yook on 2020-01-29.
 */
public class CreateQuestionController extends AbstractController {
	private QuestionDao questionDao = new QuestionDao();

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (UserSessionUtils.isLogined(request.getSession())) {
			String userId = request.getParameter("userId");
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");

			questionDao.insert(new Question(userId, writer, title, contents));
		}

		response.sendRedirect("/");
		return jsonView();
	}
}
