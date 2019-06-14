package filters;

import dao.UserDao;
import pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

/**
 * Created by barryfan on 6/10/19.
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {
    private final UserDao userDao = new UserDao();

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        if (req != null && req instanceof HttpServletRequest
                && resp != null && resp instanceof HttpServletResponse) {
            request = (HttpServletRequest) req;
            response = (HttpServletResponse) resp;
        } else {
            throw new RuntimeException("仅支持http协议");
        }

        User user = (User) request.getSession().getAttribute("user");
        boolean alreadyLogin = false;

        if (user == null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("logininfo")) {
                    String[] loginInfo = cookie.getValue().split("-");
                    User result;
                    if ((result = userDao.getUser(new String(Base64.getDecoder().decode(loginInfo[0])))) != null &&
                            result.getPassword().equals(loginInfo[1])) {
                        System.out.println("自动登录--------------------");
                        request.getSession().setAttribute("user", result);
                        alreadyLogin = true;
                        break;
                    }
                }
            }
        } else {
            alreadyLogin = true;
        }

        if (!alreadyLogin
                && !request.getRequestURI().contains("login")) {
            response.sendRedirect("/login.jsp");
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
