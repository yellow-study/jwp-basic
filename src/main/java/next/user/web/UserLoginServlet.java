package next.user.web;

import core.db.DataBase;
import lombok.extern.slf4j.Slf4j;
import next.user.model.User;
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
@WebServlet("/user/login")
public class UserLoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/user/login.html");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        log.debug("userId : {}, password : {} ", userId, password);

        HttpSession session = request.getSession();

        if (!userService.login(userId, password)) {
            log.debug("login fail");
            response.sendRedirect("/user/login_failed.html");
            return;
        }

        session.setAttribute("user", userService.getUser(userId));
        log.debug("login success");
        response.sendRedirect("/user/list");
    }
}
