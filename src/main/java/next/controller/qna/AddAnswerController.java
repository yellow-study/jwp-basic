package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddAnswerController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(AddAnswerController.class);

    private AnswerDao answerDao = new AnswerDao();
    private QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User loginUser = UserSessionUtils.getUserFromSession(request.getSession());
        if (loginUser == null) {
            return jspView("redirect:/");
        }

        Answer answer = new Answer(loginUser.getUserId()
                , request.getParameter("writer")
                , request.getParameter("contents")
                , Long.parseLong(request.getParameter("questionId"))
        );
        log.debug("answer : {}", answer);

        Answer savedAnswer = answerDao.insert(answer);
        questionDao.updateCountOfComment(answer.getQuestionId(), 1);

        return jsonView().addObject("answer", savedAnswer);
    }
}
