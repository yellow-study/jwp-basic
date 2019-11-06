package core.mvc;

import next.controller.*;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
	public static Map<String, Controller> controllerMap = new HashMap();

	static {
		controllerMap.put("/", new HomeController());
		controllerMap.put("/users/create", new CreateUserController());
		controllerMap.put("/users", new ListUserController());
		controllerMap.put("/users/login", new LoginController());
		controllerMap.put("/users/logout", new LogoutController());
		controllerMap.put("/users/profile", new ProfileController());
		controllerMap.put("/users/update", new UpdateUserController());
		controllerMap.put("/users/updateForm", new UpdateFormUserController());
		controllerMap.put("/users/loginForm", new ForwardController("/user/login.jsp"));
		controllerMap.put("/users/form", new ForwardController("/user/form.jsp"));
	}

//	public Controller findController(String url) {
//		return controllerMap.get(url);
//	}
//
//	void put(String url, Controller controller) {
//		controllerMap.put(url, controller);
//	}
}
