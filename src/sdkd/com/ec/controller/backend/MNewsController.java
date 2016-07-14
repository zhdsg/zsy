package sdkd.com.ec.controller.backend;

import sdkd.com.ec.dao.impl.EbNewsDao;
import sdkd.com.ec.model.EbNews;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/7/14.
 */
@WebServlet(name = "MNewsController")
public class MNewsController extends HttpServlet {
    EbNewsDao newsDao = new EbNewsDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if("search".equals(action)){
            getNews(request, response);
        }else if("add".equals(action)){
            addNews(request, response);
        }else if("delete".equals(action)){
            delete(request, response);
        }else if("modify".equals(action)){
            modify(request, response);
        }
    }

    public void getNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<EbNews> newList = newsDao.getallNews();
        request.setAttribute("newList", newList);

        request.getRequestDispatcher("/manage/news.jsp").forward(request, response);
    }

    public void addNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        String newTitle = new String(request.getParameter("title").getBytes("ISO-8859-1"), "UTF-8");
        String newContent = new String(request.getParameter("content").getBytes("ISO-8859-1"), "UTF-8");

        if((newsDao.addNews(newTitle, newContent, date)) == true){
            request.getRequestDispatcher("/manage/manage-result.jsp").forward(request, response);
        }
        else{
            request.setAttribute("hint", "新闻已存在, 添加失败");
            request.getRequestDispatcher("/manage/news-add.jsp").forward(request, response);
        }
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String paramId = request.getParameter("id");
        int Id = 0;
        if(paramId != null && !"".equals(paramId)){
            Id = Integer.parseInt(paramId);
        }

        newsDao.deleteNews(Id);
        request.getRequestDispatcher("/mnews.servlet?action=search").forward(request, response);
    }

    public void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        String newTitle = new String(request.getParameter("title").getBytes("ISO-8859-1"), "UTF-8");
        String newContent = new String(request.getParameter("content").getBytes("ISO-8859-1"), "UTF-8");


        String paramId = request.getParameter("id");
        int Id = 0;
        if(paramId != null && !"".equals(paramId)){
            Id = Integer.valueOf(paramId);
        }

        if((newsDao.modifyNews(newTitle, newContent, date, Id)) == true){
            request.getRequestDispatcher("/manage/manage-result.jsp").forward(request, response);
        }
        else{
            request.setAttribute("hint", "修改失败");
            request.getRequestDispatcher("/manage/news-modify.jsp").forward(request, response);
        }
    }


}
