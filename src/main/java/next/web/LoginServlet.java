package next.web;

/**
 * Created by juhyung0818@naver.com on 2019. 10. 10.
 */

import next.model.User;
import next.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        String result;
        Optional<User> optionalUser = userService.login(userId, password);
        if(optionalUser.isPresent()) {
            session.setAttribute("user", optionalUser.get());
            result = "/index.html";
        } else {
            session.removeAttribute("user");
            result = "/user/login_failed.html";
        }

        RequestDispatcher rd = req.getRequestDispatcher(result);
        rd.forward(req, resp);
    }
}
