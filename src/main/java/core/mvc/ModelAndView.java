package core.mvc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModelAndView implements View {
	private View view;
	private Map<String, Object> model = new HashMap<>();

	public ModelAndView(View view) {
		this.view = view;
	}

	public ModelAndView addModel(String name, Object model) {
		this.model.put(name, model);

		return this;
	}

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.view.render(this.model, request, response);
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public Map<String, Object> getModel() {
		return Collections.unmodifiableMap(model);
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}
}