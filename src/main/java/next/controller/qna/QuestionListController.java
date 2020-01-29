package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;

/**
 * @author sungryul-yook on 2020-01-29.
 */
public class QuestionListController extends AbstractController {

	private QuestionDao questionDao = new QuestionDao();

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return jsonView().addObject("list", questionDao.findAll());
	}
}
