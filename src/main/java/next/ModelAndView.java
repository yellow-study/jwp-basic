package next;

import core.mvc.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ModelAndView implements View {
    private View view;
    private Map<String, Object> model;


    public void addModel(String name, Object model) {
        this.model.put(name, model);
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.view.render(this.model, request, response);
    }
}
