package next.user.web;

import core.db.DataBase;
import lombok.extern.slf4j.Slf4j;
import next.user.model.User;
import next.user.service.UserService;
import next.user.util.LoginUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@WebServlet("/user/update")
public class ModifyUserServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String userId = request.getParameter("userId");

        if (!LoginUtils.isLogin(request, response)) {
            return;
        }

        if (!StringUtils.equals(user.getUserId(), userId)) {
            log.error("you have not modified authority");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/user/list.jsp");
            requestDispatcher.forward(request, response);
            return;
        }

        log.debug("userId : {}", userId);
        request.setAttribute("user", userService.getUser(userId));

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/user/update.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = User.builder().userId(request.getParameter("userId"))
            .password(request.getParameter("password"))
            .name(request.getParameter("name"))
            .email(request.getParameter("email"))
            .build();

        log.debug("user : {}", user);
        userService.modifyUser(user);
        response.sendRedirect("/user/list");
    }
}
