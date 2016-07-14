package sdkd.com.ec.controller;

import sdkd.com.ec.dao.impl.UserDao;
import sdkd.com.ec.model.EbUser;

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

        String action = request.getParameter("action");
        if("login".equals(action)){
                        Login(request, response);
                     }else if("logout".equals(action)){
                         Logout(request, response);
                     }



    }

    public void Login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String username = request.getParameter("userName");
        String password = request.getParameter("passWord");

        UserDao  ud = new UserDao();
        if(ud.checkUser(username, password)){
            EbUser user =new EbUser();
            user.setEuUsername(username);
            user.setEuPassword(password);
            request.getSession().setAttribute("user", user);
            request.getRequestDispatcher("/index.servlet").forward(request, response);
        }else{

            request.getRequestDispatcher("login.jsp").forward(request,response);
        }

    }

    public void Logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        EbUser user = (EbUser) request.getSession().getAttribute("currUser");
                 if(user!=null){
                         request.getSession().invalidate();
                     }
                 response.sendRedirect("/login.jsp");
             }


}
