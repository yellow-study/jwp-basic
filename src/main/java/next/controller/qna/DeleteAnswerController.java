/**
 * Copyright 2020 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.JsonView;
import core.mvc.ModelAndView;
import next.dao.AnswerDao;
import next.model.Result;

public class DeleteAnswerController extends AbstractController {
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) {
		ModelAndView modelAndView = new ModelAndView(new JsonView());
		long answerId = Long.parseLong(req.getParameter("answerId"));

		try {
			AnswerDao answerDao = new AnswerDao();
			answerDao.delete(answerId);

			modelAndView.addModel("result", Result.ok());
		} catch (Exception exception) {
			modelAndView.addModel("result", Result.fail("fail to delete"));
		}

		return modelAndView;
	}
}