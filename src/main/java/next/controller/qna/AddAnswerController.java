package next.controller.qna;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.mvc.Controller;
import next.dao.AnswerDao;
import next.model.Answer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class AddAnswerController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Answer answer = new Answer(req.getParameter("writer")
                , req.getParameter("contents")
                , Long.parseLong(req.getParameter("questionId")));

        AnswerDao answerDao = new AnswerDao();
        Answer savedAnswer = answerDao.insert(answer);
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println(mapper.writeValueAsString(savedAnswer));
        return null;
    }
}
