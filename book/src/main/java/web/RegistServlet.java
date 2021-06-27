package web;

import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Rookie
 * @Date 2021/6/20
 */
public class RegistServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String codes = req.getParameter("codes");
        if(codes.equalsIgnoreCase("abcde")){
            if(userService.existsUserName(username)){
                System.out.println("用户名已存在");
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
