package next.controller.qna;

import core.annotation.RequestMapping;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.QuestionDao;
import next.dao.UserDao;
import next.model.Question;
import next.model.Result;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(path = "/qna/updateForm")
public class UpdateFormQuestionController extends AbstractController {
    private QuestionDao questionDao = new QuestionDao();
    private UserDao userDao = new UserDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!UserSessionUtils.isLogined(request.getSession())) {
            return jspView("redirect:/users/loginForm");
        }

        User writer = userDao.findByUserName(request.getParameter("writer"));

        if (!UserSessionUtils.isSameUser(request.getSession(), writer)) {
            return jspView("/qna/updateForm.jsp").addObject("auth", Result.fail("auth fail"));
        }

        String questionId = request.getParameter("questionId");
        Question question = questionDao.findById(Long.parseLong(questionId));

        return jspView("/qna/updateForm.jsp")
                .addObject("question", question)
                .addObject("auth", Result.ok());
    }
}
