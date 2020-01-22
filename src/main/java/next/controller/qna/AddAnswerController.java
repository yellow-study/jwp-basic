package next.controller.qna;

import core.mvc.Controller;
import next.dao.AnswerDao;
import next.model.Answer;
import next.view.JsonView;
import core.mvc.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
