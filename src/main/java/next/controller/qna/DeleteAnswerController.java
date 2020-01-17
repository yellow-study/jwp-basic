package next.controller.qna;

import core.mvc.Controller;
import next.dao.AnswerDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAnswerController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        long answerId = Long.parseLong(req.getParameter("answerId"));
        AnswerDao answerDao = new AnswerDao();
        answerDao.delete(answerId);
        return null;
    }
}
