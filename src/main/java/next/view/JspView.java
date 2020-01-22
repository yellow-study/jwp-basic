package next.view;

import core.mvc.View;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class JspView implements View {
    private static final String DEFAULT_REDIRECT_PREFIX = "redirect:";

    private String url;

    public JspView(String url) {
        this.url = url;
    }

    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (url.startsWith(DEFAULT_REDIRECT_PREFIX)) {
            response.sendRedirect(url.substring(DEFAULT_REDIRECT_PREFIX.length()));
            return;
        }

        request.setAttribute("model", model);

        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }
}
