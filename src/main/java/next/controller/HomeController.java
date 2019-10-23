package next.controller;

import core.db.DataBase;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController extends HttpServlet implements Controller{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        req.setAttribute("users", DataBase.findAll());
        return "index.jsp";
    }
}
