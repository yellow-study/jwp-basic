/**
 * Copyright 2020 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import core.mvc.View;
import next.dao.AnswerDao;
import next.model.Result;
import next.view.JsonView;

public class DeleteAnswerController implements Controller {
	@Override
	public View execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		long answerId = Long.parseLong(req.getParameter("answerId"));

		try {
			AnswerDao answerDao = new AnswerDao();
			answerDao.delete(answerId);

			req.setAttribute("result", Result.ok());
		} catch (Exception exception) {
			req.setAttribute("result", Result.fail("fail to delete"));
		}

		return new JsonView();
	}
}