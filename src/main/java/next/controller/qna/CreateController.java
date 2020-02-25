package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.web.filter.Controller;
import next.controller.UserSessionUtils;
import next.dao.QuestionDao;
import next.model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("/qna/create")
public class CreateController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(CreateController.class);

    private QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {
        if (UserSessionUtils.isLogined(req.getSession())) {
            return jspView("redirect:/users/loginForm");
        }

        Question question = new Question(req.getParameter("writer"), req.getParameter("title"),
                req.getParameter("contents"), req.getParameter("writerId"));
        log.debug("question : {}", question);

        Question savedQuestion = questionDao.insert(question);
        return jspView("redirect:/");
    }
}
