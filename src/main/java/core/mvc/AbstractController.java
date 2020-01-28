/**
 * Copyright 2020 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package core.mvc;

public abstract class AbstractController implements Controller {
	protected ModelAndView jspView(String viewName) {
		return new ModelAndView(new JspView(viewName));
	}

	protected ModelAndView jsonView() {
		return new ModelAndView(new JsonView());
	}
}