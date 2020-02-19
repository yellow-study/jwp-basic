package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.QuestionDao;
import next.model.Question;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateController extends AbstractController {
    private QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {
        User user = UserSessionUtils.getUserFromSession(req.getSession());

        if (user == null) {
            return jspView("redirect:/users/loginForm");
        } else {
            Question question = new Question(user.getName(), req.getParameter("title"), req.getParameter("contents"), req.getParameter("writerId"));
            questionDao.insert(question);

            return jspView("redirect:/");
        }
    }
}
