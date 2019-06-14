package contorller;

import pojo.User;
import services.LoginService;
import utils.EncryptionUtils;
import utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Base64;

/**
 * Created by barryfan on 6/10/19.
 */
@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {

    private final LoginService loginService = new LoginService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//         业务逻辑-loginService
        String username = request.getParameter("username");

        String password = request.getParameter("password");

        String message = null;

        if (StringUtils.isNull(username) || StringUtils.isNull(password)) {
            // 跳转login.jsp, 并且要返回错误信息, 用户名密码不能为空
            // application
            // session
            // request
            message = "用户名密码不能为空";
        } else {
            String encryPass = EncryptionUtils.encrypPassword(password);
            User user = new User(username, encryPass);
            if (loginService.login(user)) {
                // todo 返回true, 代表登录成功，跳转到首页index.jsp
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(60);
                if (request.getParameter("remeber") != null) {
                    Cookie cookie = new Cookie("logininfo",
                            Base64.getEncoder().encodeToString(username.getBytes()).concat("-")
                                    .concat(encryPass));
                    cookie.setMaxAge(Integer.MAX_VALUE);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
                response.sendRedirect("/index.jsp");
                return;
            } else {
                // 返回false, 代表登录失败，还是跳转login.jsp，但是要带错误信息
                // 错误信息: 用户名或密码错误
                message = "用户名或密码错误";
            }
        }
        request.setAttribute("loginFailed", message);
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
