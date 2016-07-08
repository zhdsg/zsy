package sdkd.com.ec.controller;

import sdkd.com.ec.dao.impl.EbAnnouncementDao;
import sdkd.com.ec.dao.impl.EbNewsDao;
import sdkd.com.ec.dao.impl.EbPCategoryDao;
import sdkd.com.ec.dao.impl.EbProductDao;
import sdkd.com.ec.model.EbAnnouncement;
import sdkd.com.ec.model.EbNews;
import sdkd.com.ec.model.EbPCategory;
import sdkd.com.ec.model.EbProduct;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */
public class EbIndexController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EbNewsDao newsDao = new EbNewsDao();
        List<EbNews> newList = newsDao.getNews();
        request.setAttribute("newList",newList);

        EbAnnouncementDao annDao = new EbAnnouncementDao();
        List<EbAnnouncement> annList = annDao.getAnnouncement();
        request.setAttribute("noticeList", annList);

        EbProductDao proDao = new EbProductDao();
        List<EbProduct> proList = proDao.getProduct();
        request.setAttribute("productList",proList);

        EbPCategoryDao pcaDao = new EbPCategoryDao();
        List<EbPCategory> pcalist = pcaDao.getCategroies();
        request.setAttribute("pcaList", pcalist);

        //跳转
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }
}
