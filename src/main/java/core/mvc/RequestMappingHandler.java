package core.mvc;

import com.google.common.collect.Maps;
import core.annotation.RequestMapping;
import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;

public class RequestMappingHandler {
    private static final Map<String, Controller> map = Maps.newHashMap();

    public void init() {
        Reflections reflections = new Reflections("next.controller");
        Set<Class<? extends AbstractController>> controllers = reflections.getSubTypesOf(AbstractController.class);

        for (Class<? extends AbstractController> controller : controllers) {
            RequestMapping requestMapping = controller.getAnnotation(RequestMapping.class);

            if (requestMapping != null) {
                try {
                    map.put(requestMapping.path(), controller.newInstance());
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Controller findController(String url) {
        return map.get(url);
    }
}
