package next.controller.qna;

import core.annotation.Controller;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.QuestionDao;
import next.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("/qna/modify")
public class QuestionModifyController extends AbstractController {
    private QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (UserSessionUtils.isLogined(request.getSession())) {
            long questionId = Long.parseLong(request.getParameter("questionId"));
            String title = request.getParameter("title");
            String contents = request.getParameter("contents");

            Question question = new Question();
            question.setQuestionId(questionId);
            question.setTitle(title);
            question.setContents(contents);
            questionDao.update(question);

            return jspView("redirect:/qna/show?questionId=" + questionId);
        }

        return jspView("redirect:/users/loginForm");
    }
}
