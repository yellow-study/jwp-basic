package core.mvc;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger("DispatcherServlet");
	private static final String DEFAULT_REDIRECT_PREFIX = "redirect:";

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestUrl = req.getRequestURI();

		Controller controller = RequestMapping.controllerMap.get(requestUrl);

		try {
			String result = controller.execute(req, resp);
			move(result, req, resp);
		} catch (Exception exception) {
			LOGGER.error("DispatcherServlet error : ", exception);
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestUrl = req.getRequestURI();

		Controller controller = RequestMapping.controllerMap.get(requestUrl);

		try {
			String result = controller.execute(req, resp);
			move(result, req, resp);
		} catch (Exception exception) {
			LOGGER.error("DispatcherServlet error : ", exception);
		}
	}

	private void move(String viewName, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
		if (StringUtils.startsWith(viewName, DEFAULT_REDIRECT_PREFIX)) {
			String redirectUrl = StringUtils.remove(viewName, DEFAULT_REDIRECT_PREFIX);
			response.sendRedirect(redirectUrl);

			return;
		}

		RequestDispatcher rd = request.getRequestDispatcher(viewName);
		rd.forward(request, response);
	}
}
