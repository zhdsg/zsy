package sdkd.com.ec.controller;

import sdkd.com.ec.dao.impl.EbPCategoryDao;
import sdkd.com.ec.dao.impl.EbProductDao;
import sdkd.com.ec.model.EbPCategory;
import sdkd.com.ec.model.EbProduct;

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
@WebServlet(name = "EbPContentController")
public class EbPContentController extends HttpServlet {
    EbProductDao pro = new EbProductDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");



        EbPCategoryDao pcaDao = new EbPCategoryDao();
        List<EbPCategory> pcalist = pcaDao.getCategroies();
        request.setAttribute("pcaList", pcalist);

        if("detail_product".equals(action)){
             detial(request, response);
        }else {
            content(request, response);
        }

    }

    public void content(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<EbProduct> proList = pro.getProduct();
        request.setAttribute("proList", proList);

         int Id = Integer.parseInt(request.getParameter("id_pro"));
         for(int i = 0; i < proList.size(); i++){
         if(Id == proList.get(i).getEpId()){
         request.setAttribute("proName", proList.get(i).getEpName());
         request.setAttribute("proDescription", proList.get(i).getEpDescription());
         request.setAttribute("proPrice", proList.get(i).getEpPrice());
         request.setAttribute("epId", proList.get(i).getEpId());
           }
         }
        request.getRequestDispatcher("/product-view.jsp").forward(request, response);
    }

      public void detial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
          int Id = Integer.parseInt(request.getParameter("id_ep"));
          List<EbProduct> proList = pro.findProductByepcId(Id);
          request.setAttribute("proList", proList);

          request.getRequestDispatcher("/product-list.jsp").forward(request, response);
      }
}
