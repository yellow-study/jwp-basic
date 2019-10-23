package next.servlet;

import next.RequestMapping;
import next.controller.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        Controller controller = RequestMapping.mapping(uri);

        String path;
        try {
            path = controller.execute(req, resp);
        } catch (Exception e) {
            path = "/index.html";
            e.printStackTrace();
        }

        if (path.startsWith("redirect:")) {
            resp.sendRedirect(path.replaceFirst("redirect:", ""));
        } else {
            RequestDispatcher rd = req.getRequestDispatcher(path);
            rd.forward(req, resp);
        }
    }
}
