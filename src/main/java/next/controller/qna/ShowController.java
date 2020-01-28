package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.JspView;
import core.mvc.ModelAndView;
import next.dao.AnswerDao;
import next.dao.QuestionDao;

public class ShowController extends AbstractController {
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) {
		long questionId = Long.parseLong(req.getParameter("questionId"));

		QuestionDao questionDao = new QuestionDao();
		AnswerDao answerDao = new AnswerDao();

		return new ModelAndView(new JspView("/qna/show.jsp"))
			.addModel("question", questionDao.findById(questionId))
			.addModel("answers", answerDao.findAllByQuestionId(questionId));
	}
}
