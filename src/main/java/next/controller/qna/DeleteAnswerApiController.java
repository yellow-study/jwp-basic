package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.mvc.Controller;
import next.dao.AnswerDao;
import next.model.Answer;
import next.model.Result;

public class DeleteAnswerApiController implements Controller {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Long answerId = Long.parseLong(req.getParameter("answerId"));

		AnswerDao answerDao = new AnswerDao();
		answerDao.delete(answerId);

		ObjectMapper mapper = new ObjectMapper();
		String jsonResult = mapper.writeValueAsString(Result.ok());

		resp.setContentType("application/json;charset=UTF-8");
		resp.getWriter().write(jsonResult);

		return null;
	}
}
