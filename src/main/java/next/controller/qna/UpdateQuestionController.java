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

@RequestMapping(path = "/api/qna/updateQuestion")
public class UpdateQuestionController extends AbstractController {
    private QuestionDao questionDao = new QuestionDao();
    private UserDao userDao = new UserDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!UserSessionUtils.isLogined(request.getSession())) {
            return jsonView().addObject("result", Result.fail("login needed"));
        }

        User writer = userDao.findByUserName(request.getParameter("writer"));

        if (!UserSessionUtils.isSameUser(request.getSession(), writer)) {
            return jsonView().addObject("result", Result.fail("writer auth fail"));
        }

        questionDao.update(new Question(
                Long.parseLong(request.getParameter("questionId")),
                request.getParameter("name"),
                request.getParameter("title"),
                request.getParameter("contents")
        ));

        return jsonView().addObject("result", Result.ok());
    }
}
