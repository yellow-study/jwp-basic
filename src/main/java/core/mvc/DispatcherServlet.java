package core.mvc;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger("DispatcherServlet");
    private static final long serialVersionUID = 1L;
    private static String REDIRECT_PREFIX = "redirect:";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        String requestUrl = req.getRequestURI();

        Controller controller = RequestMapping.controllerMap.get(requestUrl);

        try {
            String result = controller.execute(req, resp);

            if (StringUtils.startsWith(result, REDIRECT_PREFIX)) {
                String redirectUrl = StringUtils.remove(result, REDIRECT_PREFIX);

                resp.sendRedirect(redirectUrl);
            } else {
                RequestDispatcher rd = req.getRequestDispatcher(result);
                rd.forward(req, resp);
            }

        } catch (Exception exception) {
            LOGGER.error("DispatcherServlet error : ", exception);

        }
    }
}
