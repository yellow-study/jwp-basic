package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;
import next.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModifyFormController extends AbstractController {
    private QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {
    	Long questionId = Long.parseLong(req.getParameter("questionId"));

	    Question question = questionDao.findById(questionId);

	    ModelAndView mav = jspView("/qna/modifyForm.jsp");
	    mav.addObject("question", question);

        return mav;
    }
}