package next.user.web;

import core.db.DataBase;
import lombok.extern.slf4j.Slf4j;
import next.user.util.LoginUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@WebServlet("/user/list")
public class ListUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!LoginUtils.isLogin(request, response)) {
            return;
        }

        request.setAttribute("users", DataBase.findAll());
        RequestDispatcher rd = request.getRequestDispatcher("/user/list.jsp");
        rd.forward(request, response);
    }
}
