package core.mvc;

import java.util.HashMap;
import java.util.Map;

import next.controller.CreateUserController;
import next.controller.ForwardController;
import next.controller.HomeController;
import next.controller.ListUserController;
import next.controller.LoginController;
import next.controller.LogoutController;
import next.controller.ProfileController;
import next.controller.UpdateUserController;
import next.controller.UpdateUserFormController;

public class RequestMapping {
	public static Map<String, Controller> controllerMap = new HashMap();

	static {
		controllerMap.put("/users/create", new CreateUserController());
		controllerMap.put("/users", new ListUserController());
		controllerMap.put("/users/login", new LoginController());
		controllerMap.put("/users/logout", new LogoutController());
		controllerMap.put("/users/profile", new ProfileController());
		controllerMap.put("/users/updateForm", new UpdateUserFormController());
		controllerMap.put("/users/update", new UpdateUserController());
		controllerMap.put("", new HomeController());

		controllerMap.put("/users/loginForm", new ForwardController());
		controllerMap.put("/users/form", new ForwardController());
	}
}
