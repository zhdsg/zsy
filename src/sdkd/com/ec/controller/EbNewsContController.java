package sdkd.com.ec.controller;

import sdkd.com.ec.dao.impl.EbAnnouncementDao;
import sdkd.com.ec.dao.impl.EbNewsDao;
import sdkd.com.ec.model.EbAnnouncement;
import sdkd.com.ec.model.EbNews;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/7/7.
 */
@WebServlet(name = "EbNewsContController")
public class EbNewsContController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
               doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");

        EbAnnouncementDao annDao = new EbAnnouncementDao();
        List<EbAnnouncement> annList = annDao.getAnnouncement();
        request.setAttribute("noticeList", annList);

        detail(request, response);

    }

    public void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int Id_news = Integer.parseInt(request.getParameter("id_news"));
        EbNewsDao newsDao = new EbNewsDao();
        List<EbNews> newList = newsDao.getNews();
        request.setAttribute("newList",newList);

        for (int i = 0; i < newList.size(); i++) {
            if (Id_news == newList.get(i).getEnId()) {
                request.setAttribute("newTitle", newList.get(i).getEnTitle());
                request.setAttribute("newContent", newList.get(i).getEnContent());
            }
        }
        request.getRequestDispatcher("news-view.jsp").forward(request, response);
    }
}
