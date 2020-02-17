package next.controller.qna;

import core.annotation.Controller;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.service.QuestionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("/api/qna/deleteQuestion")
public class DeleteQuestionAPIController extends AbstractController {
    private QuestionService questionService = new QuestionService();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        long questionId = Long.parseLong(request.getParameter("questionId"));

        if (questionService.removeQuestion(questionId)) {
            return jsonView().addObject("result", true);
        }
        return jsonView().addObject("result", false);
    }
}
