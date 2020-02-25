/**
 * Copyright 2020 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package next.controller.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.dao.UserDao;
import next.model.Answer;
import next.model.Question;

public class ModifyController extends AbstractController {
	private QuestionDao questionDao = new QuestionDao();
	private AnswerDao answerDao = new AnswerDao();
	private UserDao userDao = new UserDao();

	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {
		Long questionId = Long.parseLong(req.getParameter("questionId"));

		Question question = new Question(questionId, req.getParameter("title"), req.getParameter("contents"));
		questionDao.update(question);
		List<Answer> answers = answerDao.findAllByQuestionId(questionId);

		Question updatedQuestion = questionDao.findById(questionId);

		ModelAndView mav = jspView("/qna/show.jsp");
		mav.addObject("question", updatedQuestion);
		mav.addObject("answers", answers);
		mav.addObject("isSameUser", UserSessionUtils.isSameUser(req.getSession(), userDao.findByUserId(updatedQuestion.getWriterId())));

		return mav;
	}
}