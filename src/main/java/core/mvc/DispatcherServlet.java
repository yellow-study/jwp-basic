package core.mvc;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.nmvc.AnnotationHandlerMapping;
import core.nmvc.HandlerExecution;
import core.nmvc.HandlerMapping;

@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

	private LegacyHandlerMapping rm;

	private AnnotationHandlerMapping am;

	private List<HandlerMapping> mappers;

	@Override
	public void init() throws ServletException {
		rm = new LegacyHandlerMapping();
		rm.initMapping();

		am = new AnnotationHandlerMapping();
		am.initialize();

		mappers = Arrays.asList(rm, am);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestUri = req.getRequestURI();
		logger.debug("Method : {}, Request URI : {}", req.getMethod(), requestUri);

		Object handler = getHandler(req);
        ModelAndView mav = null;

		if(handler instanceof Controller) {
            Controller controller = (Controller)handler;
            try {
                mav = controller.execute(req, resp);
            } catch (Exception e) {
                logger.error("Exception : {}", e);
                throw new ServletException(e.getMessage());
            }
        } else if(handler instanceof HandlerExecution) {
            try {
                mav = ((HandlerExecution)handler).handle(req, resp);
            } catch (Exception e) {
                logger.error("Exception : {}", e);
                throw new ServletException(e.getMessage());
            }
        } else {
		    throw new ServletException("Sorry");
        }


		try {

			View view = mav.getView();
			view.render(mav.getModel(), req, resp);
		} catch (Throwable e) {
			logger.error("Exception : {}", e);
			throw new ServletException(e.getMessage());
		}
	}

	private Object getHandler(HttpServletRequest request) {
		for (HandlerMapping handlerMapping : mappers) {
			Object handler = handlerMapping.getHandler(request);

			if (Objects.nonNull(handler)) {
				return handler;
			}
		}

		return null;
	}
}
