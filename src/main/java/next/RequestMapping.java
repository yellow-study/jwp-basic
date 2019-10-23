package next;

import com.google.common.collect.Maps;
import next.controller.*;

import java.util.Map;

public class RequestMapping {
    private static Map<String, Controller> requestMapping = Maps.newHashMap();

    static {
        requestMapping.put("", new HomeController());
        requestMapping.put("/users", new ListUserController());
        requestMapping.put("/users/form", new ForwardController("/user/form.jsp"));
        requestMapping.put("/users/create", new CreateUserController());
        requestMapping.put("/users/loginForm", new ForwardController("/user/login.jsp"));
        requestMapping.put("/users/login" , new LoginController());
        requestMapping.put("/users/logout", new LogoutController());
        requestMapping.put("/users/profile", new ProfileController());
        requestMapping.put("/users/update", new UpdateUserController());
        requestMapping.put("/users/updateForm", new UpdateUserFormController());
    }

    public static Controller mapping(String url) {
        return requestMapping.get(url);
    }
}
