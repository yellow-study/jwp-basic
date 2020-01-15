package next.controller.qna;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.mvc.Controller;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddAnswerController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Long questionId = Long.parseLong(req.getParameter("questionId"));
        String writer = req.getParameter("writer");
        String contents = req.getParameter("contents");

        Answer answer = new Answer(writer, contents, questionId);

        AnswerDao answerDao = new AnswerDao();
        answer = answerDao.insert(answer);

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writeValueAsString(answer);

        resp.setContentType("application/json");
        resp.getWriter().write(jsonResult);

        return null;
    }
}
