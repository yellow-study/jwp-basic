package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import core.mvc.View;
import next.dao.AnswerDao;
import next.model.Answer;
import next.view.JsonView;

public class AddAnswerController implements Controller {
	@Override
	public View execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Long questionId = Long.parseLong(req.getParameter("questionId"));
		String writer = req.getParameter("writer");
		String contents = req.getParameter("contents");

		Answer answer = new Answer(writer, contents, questionId);

		AnswerDao answerDao = new AnswerDao();
		answer = answerDao.insert(answer);

		req.setAttribute("answer", answer);

		return new JsonView();
	}
}
