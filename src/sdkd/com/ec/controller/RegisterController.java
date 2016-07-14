package sdkd.com.ec.controller;

import sdkd.com.ec.dao.impl.EbUserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2016/7/6.
 */
@WebServlet(name = "RegisterController")
public class RegisterController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
             doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
             String username = request.getParameter("userName");
             String password = request.getParameter("passWord");

             EbUserDao ud = new EbUserDao();
             if(!ud.hasRegisterUser(username)){    //遍历数据库查询用户是否已存在
                 ud.registerUser(username,password);//不存在，添加
                 request.getRequestDispatcher("reg-result.jsp").forward(request, response);
        }else{
                 request.setAttribute("hint","用户名已存在!!!1");
                 request.getRequestDispatcher("register.jsp").forward(request, response);
             }
    }
}
