package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import core.mvc.Controller;
import next.dao.AnswerDao;
import next.model.Answer;

public class DeleteAnswerController implements Controller {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		long answerId = Long.parseLong(req.getParameter("answerId"));
		ObjectMapper mapper = new ObjectMapper();
		String jsonResult = null;
		try {
			AnswerDao answerDao = new AnswerDao();
			answerDao.delete(answerId);
			jsonResult = mapper.writeValueAsString(true);
		} catch (Exception exception) {
			mapper.writeValueAsString(false);
		}
		resp.setContentType("application/json");
		resp.getWriter().write(jsonResult);
		return null;
	}
}
