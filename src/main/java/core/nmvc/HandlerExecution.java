package core.nmvc;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.ModelAndView;

public class HandlerExecution {
    private Method method;
    private Object declaredObject;

    public HandlerExecution(Method method, Object declaredObject) {
        this.method = method;
        this.declaredObject = declaredObject;
    }

    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return (ModelAndView)method.invoke(declaredObject, request, response);
    }
}
