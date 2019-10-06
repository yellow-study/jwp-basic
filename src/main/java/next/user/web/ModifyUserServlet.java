package next.user.web;

import next.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/update")
public class ModifyUserServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(ModifyUserServlet.class);

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");

        LOG.debug("userId : {}", userId);
        request.setAttribute("user", userService.getUser(userId));

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/user/update.jsp");
        requestDispatcher.forward(request, response);
    }
}
