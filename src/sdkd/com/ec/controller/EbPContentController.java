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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/7/7.
 */
@WebServlet(name = "EbPContentController")
public class EbPContentController extends HttpServlet {
    EbProductDao pro = new EbProductDao();
    EbPCategoryDao pcaDao = new EbPCategoryDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");

        List<EbPCategory> pcalist = pcaDao.getCategroies();
        request.setAttribute("pcaList", pcalist);


        if("detail_product".equals(action)){
             list(request, response);
        }else {
            content(request, response);
        }

    }

    //product-view
    public void content(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<EbProduct> proList = pro.getProduct();
        request.setAttribute("proList", proList);
        String paramsId = request.getParameter("id_pro");
        int Id = 0;

        if(paramsId != null && !"".equals(paramsId)) {
            Id = Integer.parseInt(paramsId);
        }

        List<EbPCategory> pcalist = pcaDao.getCategroies();
        for(int i = 0; i < pcalist.size(); i++){
            if(Id == pcalist.get(i).getEpcId()){
                request.setAttribute("epcName", pcalist.get(i).getEpcName());
                request.setAttribute("epcParentId", pcalist.get(i).getEpcParentId());
            }
        }

         for(int i = 0; i < proList.size(); i++){
         if(Id == proList.get(i).getEpId()){
         request.setAttribute("proName", proList.get(i).getEpName());
         request.setAttribute("proDescription", proList.get(i).getEpDescription());
         request.setAttribute("proPrice", proList.get(i).getEpPrice());
         request.setAttribute("epId", proList.get(i).getEpId());
         request.setAttribute("proStock", proList.get(i).getEpStock());
           }
         }

        EbProduct product = pro.findProductById(Id);
        recent(request, response, product, Id);

        request.getRequestDispatcher("/product-view.jsp").forward(request, response);
    }


    //product-list
      public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
          String pageIndexParam = request.getParameter("pageIndex");
          String pageSizeParam = request.getParameter("pageSize");
          int pageIndex = 1;
          int pageSize = 4;

          if(pageIndexParam != null && !"".equals(pageIndexParam)){
              pageIndex = Integer.parseInt(pageIndexParam);
          }

          if(pageSizeParam != null && !"".equals(pageSizeParam)){
              pageSize = Integer.parseInt(pageSizeParam);
          }

          List<EbProduct> productList = pro.getProductPager(pageIndex,pageSize);
          int count = pro.getProductCount();
          int totalPage  = count % pageSize == 0 ?(count/pageSize):((count/pageSize)+1);

          String paramsId = request.getParameter("id_ep");
          int Id = 0;

          List<EbPCategory> pcalist = pcaDao.getCategroies();

          if(paramsId != null && !"".equals(paramsId)) {
              Id = Integer.parseInt(paramsId);
          }

          request.setAttribute("productList",productList);
          request.setAttribute("totalPage",totalPage);  //总记录数
          request.setAttribute("pageIndex",pageIndex);


          for(int i = 0; i < pcalist.size(); i++){
              if(Id == pcalist.get(i).getEpcId()){
                  request.setAttribute("epcId", pcalist.get(i).getEpcId());
                  request.setAttribute("epcName", pcalist.get(i).getEpcName());
                  request.setAttribute("epcParentId", pcalist.get(i).getEpcParentId());
              }
          }

          List<EbProduct> proList = pro.findProductByepcId(Id);
          request.setAttribute("proList", proList);

          request.getRequestDispatcher("/product-list.jsp").forward(request, response);
      }


   //最近浏览
    public void recent(HttpServletRequest request, HttpServletResponse response, EbProduct product, int id) throws ServletException, IOException{
        Iterator<EbProduct> it = null;
        List<EbProduct> recentList = (List<EbProduct>)request.getSession().getAttribute("recent");

        if(recentList == null){
            recentList = new ArrayList<>();
        }else{
            it = recentList.iterator();
            while(it.hasNext()){
                EbProduct pro = it.next();
                if(pro.getEpId() == id){
                    it.remove();
                }
            }
        }
        recentList.add(product);
        if(recentList.size() > 5){
                      recentList.remove(0);
            /**
             *       if(it.hasNext()){
             it.next();
             it.remove();
             }
             */
        }
        request.getSession().setAttribute("recent", recentList);
    }
}