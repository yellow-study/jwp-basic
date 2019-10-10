package next.web;

import core.db.DataBase;
import next.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/update")
public class UpdateUserServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse rep) throws IOException {
        String userId = req.getParameter("userId");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        User user = DataBase.findUserById(userId);
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);

        rep.sendRedirect("/user/list");
    }
}
