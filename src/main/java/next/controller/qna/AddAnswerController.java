package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.AnswerDao;
import next.model.Answer;

public class AddAnswerController extends AbstractController {
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) {
		Long questionId = Long.parseLong(req.getParameter("questionId"));
		String writer = req.getParameter("writer");
		String contents = req.getParameter("contents");

		Answer answer = new Answer(writer, contents, questionId);

		AnswerDao answerDao = new AnswerDao();
		answer = answerDao.insert(answer);

		return jsonView().addModel("answer", answer);
	}
}
