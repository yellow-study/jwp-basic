package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardController implements Controller {
    private String direction;

    public ForwardController(String direction) {
        this.direction = direction;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        return direction;
    }
}
