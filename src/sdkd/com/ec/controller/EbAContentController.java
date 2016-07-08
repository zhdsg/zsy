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
@WebServlet(name = "EbAContentController")
public class EbAContentController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          doPost(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int Id_ann = Integer.parseInt(request.getParameter("id_ann"));
        EbAnnouncementDao annDao = new EbAnnouncementDao();
        List<EbAnnouncement> annList = annDao.getAnnouncement();
        request.setAttribute("noticeList", annList);

        EbNewsDao newsDao = new EbNewsDao();
        List<EbNews> newList = newsDao.getNews();
        request.setAttribute("newList",newList);


            for (int i = 0; i < annList.size(); i++) {
                if (Id_ann == annList.get(i).getNoId()) {
                    request.setAttribute("annTitle", annList.get(i).getNoTitle());
                    request.setAttribute("annContent", annList.get(i).getNoContent());
                }
            }

        request.getRequestDispatcher("news-view.jsp").forward(request, response);
    }
}
