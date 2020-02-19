package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.web.filter.Controller;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import next.model.Result;
import core.jdbc.DataAccessException;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;

@Controller("/api/qna/deleteAnswer")
public class DeleteAnswerController extends AbstractController {
    private AnswerDao answerDao = new AnswerDao();
    private QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long answerId = Long.parseLong(request.getParameter("answerId"));
        Long questionId = Long.parseLong(request.getParameter("questionId"));

        ModelAndView mav = jsonView();
        try {
            answerDao.delete(answerId);
            questionDao.updateCountOfAnswer(true, questionId);
            mav.addObject("result", Result.ok());
        } catch (DataAccessException e) {
            mav.addObject("result", Result.fail(e.getMessage()));
        }

        return jsonView().addObject("answerId", answerId);
    }
}
