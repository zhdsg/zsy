package sdkd.com.ec.controller.backend;

import sdkd.com.ec.dao.impl.EbUserDao;
import sdkd.com.ec.model.EbUser;
import sdkd.com.ec.util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.datatransfer.DataFlavor;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Administrator on 2016/7/13.
 */
@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action.equals("load")){
            loadUser(request,response);
        }else if (action.equals("insert")){
            insertUser(request,response);
        }else if(action.equals("delete")){
            deleteUser(request,response);
        }else if(action.equals("update")){
            updateUser(request,response);
        }else  if (action.equals("push")){
            pushUser(request,response);
        }

    }
    public void loadUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EbUserDao ebUserDao =new EbUserDao();
        List<EbUser> ebUserList =ebUserDao.getUser();
        request.setAttribute("userList",ebUserList);
        request.getRequestDispatcher("/manage/user.jsp").forward(request,response);
    }
    public void insertUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EbUser ebUser=new EbUser();
        String user =request.getParameter("userName");
        EbUserDao ebUserDao =new EbUserDao();
        if(ebUserDao.hasRegisterUser(user)){
            request.setAttribute("hint","用户名已存在！！！");
            request.getRequestDispatcher("/manage/user-add.jsp").forward(request,response);
        }else {
            ebUser.setEuUserName(user);

            ebUser.setEuPassword(request.getParameter("passWord"));
            String sex =request.getParameter("sex");
            if(sex.equals("1"))
            {
                ebUser.setEuSex("男");
            }else {
                ebUser.setEuSex("女");
            }

            try {
                ebUser.setEuBirthday(DateUtil.parseStrToDate(request.getParameter("birthyear") + "-" + request.getParameter("birthmonth") + "-" + request.getParameter("birthday"), "yyyy-MM-dd"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ebUser.setEuMobile(request.getParameter("mobile"));
            ebUser.setEuAddress(request.getParameter("address"));
            ebUser.setEuStatus(1);

            int result = ebUserDao.insertUser(ebUser);
            request.getRequestDispatcher("/manage/manage-result.jsp").forward(request, response);
        }



    }
    public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id =Integer.parseInt(request.getParameter("id"));
        EbUserDao ebUserDao =new EbUserDao();
        int result =ebUserDao.deleteUser(id);
        if(result==1){
            request.getRequestDispatcher("/manageUser.do?action=load").forward(request,response);
        }else if (result==0){
            request.setAttribute("hint","删除失败！！！！");
            request.getRequestDispatcher("/manageUser.do?action=load").forward(request,response);
        }





    }
    public void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id =Integer.parseInt(request.getParameter("id"));

        EbUser ebUser=new EbUser();
        EbUserDao ebUserDao =new EbUserDao();
        ebUser.setEuUserId(id);

        ebUser.setEuPassword(request.getParameter("passWord"));
        String sex =request.getParameter("sex");
        if(sex.equals("1"))
        {
                ebUser.setEuSex("男");
        }else {
                ebUser.setEuSex("女");
        }

            try {
                ebUser.setEuBirthday(DateUtil.parseStrToDate(request.getParameter("birthyear") + "-" + request.getParameter("birthmonth") + "-" + request.getParameter("birthday"), "yyyy-MM-dd"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ebUser.setEuMobile(request.getParameter("mobile"));
            ebUser.setEuAddress(request.getParameter("address"));


            int result = ebUserDao.updateUser(ebUser);
        if(result==1){
            request.getRequestDispatcher("/manageUser.do?action=load").forward(request,response);
        }else if (result==0){
            request.setAttribute("hint","非法操作错误！！！！");
            request.getRequestDispatcher("/manageUser.do?action=load").forward(request,response);
        }



    }
    public void pushUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id =Integer.parseInt(request.getParameter("id"));
        EbUserDao ebUserDao =new EbUserDao();
        EbUser user =ebUserDao.pushUser(id);
        request.setAttribute("user",user);
        request.getRequestDispatcher("/manage/user-modify.jsp").forward(request,response);

    }

}
