package next.user.web;

import lombok.extern.slf4j.Slf4j;
import next.user.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by juhyung0818@naver.com on 2019. 10. 6.
 */
@Slf4j
@WebServlet("/user/logout")
public class UserLogoutServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("user");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/user/login.html");
        requestDispatcher.forward(request, response);
    }
}
