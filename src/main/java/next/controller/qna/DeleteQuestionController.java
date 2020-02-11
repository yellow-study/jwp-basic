package next.controller.qna;

import core.annotation.RequestMapping;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.bo.QuestionBo;
import next.dao.AnswerDao;
import next.dao.QuestionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(path = "/api/qna/delete")
public class DeleteQuestionController extends AbstractController {
    private QuestionDao questionDao = new QuestionDao();
    private AnswerDao answerDao = new AnswerDao();
    private QuestionBo questionBo = new QuestionBo(questionDao, answerDao);

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        long questionId = Long.parseLong(request.getParameter("questionId"));
        boolean removed = questionBo.delete(questionId);

        return jsonView().addObject("result", removed);
    }
}
