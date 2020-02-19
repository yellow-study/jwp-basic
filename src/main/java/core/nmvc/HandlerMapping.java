package core.nmvc;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sungryul-yook on 2020-02-19.
 */
public interface HandlerMapping {
	Object getHandler(HttpServletRequest request);
}
