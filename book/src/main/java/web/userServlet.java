package web;

import org.apache.commons.beanutils.BeanUtils;
import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @Author Rookie
 * @Date 2021/6/23
 */
public class userServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
        User loginUser = userService.login(user);
        if(loginUser!=null){
            req.getSession().setAttribute("loginUser",loginUser);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }else{
            req.setAttribute("msg","用户名或密码错误");
            req.setAttribute("username",req.getParameter("username"));
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }
    }
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        if(code.equalsIgnoreCase(token)){
            if(userService.existsUserName(username)){
                System.out.println("用户名已存在");
                req.setAttribute("msg","用户名已注册");
                req.setAttribute("username",req.getParameter("username"));
                req.setAttribute("email",req.getParameter("email"));
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else{
                userService.regist(new User(null,username,password,email));
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        }else{
            System.out.println("注册失败");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }
}
