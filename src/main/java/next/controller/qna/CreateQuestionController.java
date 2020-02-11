package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.annotation.RequestMapping;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.QuestionDao;
import next.model.Question;

/**
 * @author sungryul-yook on 2020-01-29.
 */
@RequestMapping(path = "/qna/create")
public class CreateQuestionController extends AbstractController {
	private QuestionDao questionDao = new QuestionDao();

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (UserSessionUtils.isLogined(request.getSession())) {
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");

			questionDao.insert(new Question(writer, title, contents));
		}

		response.sendRedirect("/");
		return jsonView();
	}
}
