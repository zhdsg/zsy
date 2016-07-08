package sdkd.com.ec.controller;

import sdkd.com.ec.dao.impl.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2016/7/6.
 */
@WebServlet(name = "LoginController")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
               doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                 String username = request.getParameter("userName");
                 String password = request.getParameter("passWord");

        UserDao  ud = new UserDao();
                 if(ud.checkUser(username, password)){
                     request.getSession().setAttribute("user", username);
                     request.getRequestDispatcher("index.jsp").forward(request, response);
                 }else{
                     String res = "Wrong";
                     request.getRequestDispatcher("login.jsp").forward(request,response);
                 }
    }
}
