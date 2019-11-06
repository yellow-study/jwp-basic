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
	private static final Logger LOGGER = LoggerFactory.getLogger("DispatcherServlet");
	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_REDIRECT_PREFIX = "redirect:";

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUrl = req.getRequestURI();
        Controller controller = RequestMapping.controllerMap.get(requestUrl);

        try {
            String result = controller.execute(req, resp);

            if (StringUtils.startsWith(result, DEFAULT_REDIRECT_PREFIX)) {
                String redirectUrl = StringUtils.remove(result, DEFAULT_REDIRECT_PREFIX);

                resp.sendRedirect(redirectUrl);
            } else {
                RequestDispatcher rd = req.getRequestDispatcher(result);
                rd.forward(req, resp);
            }

        } catch (Exception exception) {
            LOGGER.error("DispatcherServlet error : ", exception);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


//	@Override
//	public void init() throws ServletException {
//		rm = new RequestMapping();
//		rm.initMapping();
//	}
//
//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String requestUri = req.getRequestURI();
//
//		Controller controller = rm.findController(requestUri);
//
//        try {
//            String viewName = controller.execute(req, resp);
//            move(viewName, req, resp);
//        } catch (Exception e) {
//            throw new ServletException(e.getMessage());
//        }
//    }
//
//	private void move(String viewName, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		if (viewName.startsWith(DEFAULT_REDIRECT_PREFIX)) {
//            resp.sendRedirect(viewName.substring(DEFAULT_REDIRECT_PREFIX.length()));
//            return;
//		}
//
//		RequestDispatcher rd = req.getRequestDispatcher(viewName);
//		rd.forward(req, resp);
//	}
}
