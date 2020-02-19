package core.nmvc;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import org.springframework.util.CollectionUtils;

import core.annotation.Controller;

/**
 * @author sungryul-yook on 2020-02-19.
 */
public class ControllerScanner {
	private Reflections reflections;

	private Map<Class<?>, Object> controllers;

	public Map<Class<?>, Object> getControllers() throws InstantiationException, IllegalAccessException {
		if (CollectionUtils.isEmpty(controllers)) {
			controllers = new HashMap<>();
			instantiateControllers();
		}

		return controllers;
	}

	private Set<Class<?>> scanControllers() {
		reflections = new Reflections();
		return reflections.getTypesAnnotatedWith(Controller.class);
	}

	private void instantiateControllers() throws IllegalAccessException, InstantiationException {
		Set<Class<?>> controllers = scanControllers();

		for (Class<?> controller : controllers) {
			this.controllers.put(controller, controller.newInstance());
		}
	}

}
