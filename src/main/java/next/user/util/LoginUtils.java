package next.user.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by juhyung0818@naver.com on 2019. 10. 6.
 */
@Slf4j
public final class LoginUtils {
    public static boolean isLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            log.error("only show login user");
            response.sendRedirect("/user/login.html");
            return false;
        }

        return true;
    }
}
