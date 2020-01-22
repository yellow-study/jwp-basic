package next.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.mvc.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class JsonView implements View {
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writeValueAsString(model);

        response.setContentType("application/json");
        response.getWriter().write(jsonResult);
    }
}
