package next.controller.qna;

import core.mvc.Controller;
import core.mvc.view.JsonView;
import core.mvc.view.View;
import next.dao.AnswerDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAnswerController implements Controller {
    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        long answerId = Long.parseLong(req.getParameter("answerId"));
        AnswerDao answerDao = new AnswerDao();
        answerDao.delete(answerId);
        return new JsonView();
    }
}
