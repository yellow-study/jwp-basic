package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.web.filter.Controller;
import next.dao.QuestionDao;
import next.model.Question;

@Controller("/qna/modify")
public class ModifyController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(ModifyController.class);

    private QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {
        Question question = new Question(req.getParameter("writer"), req.getParameter("title"),
                req.getParameter("contents"), req.getParameter("writerId"));
        log.debug("question : {}", question);

        Question savedQuestion = questionDao.modify(question);
        return jspView("redirect:/");
    }
}
