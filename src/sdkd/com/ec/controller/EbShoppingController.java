package sdkd.com.ec.controller;

import sdkd.com.ec.dao.impl.EbProductDao;
import sdkd.com.ec.model.*;
import sdkd.com.ec.service.impl.EbOrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/7/8.
 */
@WebServlet(name = "EbShoppingController")
public class EbShoppingController extends HttpServlet {
    EbProductDao productDao = new EbProductDao();
    EbOrderService orderService = new EbOrderService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if("mod".equals(action)){
            modifyCart(request, response);
        }else if("remove".equals(action)){
            removeCart(request, response);
        }else if("pay".equals(action)){
            parCart(request, response);
            return;
        }
        else if("add".equals(action)){
            addCart(request, response);
        }
            response.sendRedirect("/shopping.jsp");
    }

    public void addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        boolean issame = false;

        //获取商品ID
        String paramsId = request.getParameter("id");
        int Id = 0;
        if(paramsId != null && !"".equals(paramsId)){
            Id = Integer.parseInt(paramsId);
        }
        EbProduct product =  productDao.findProductById(Id);
        //存储至session
        EbShoppingCart cart = (EbShoppingCart) request.getSession().getAttribute("cart");

        if (cart == null) {
            cart = new EbShoppingCart();
            cart.addItem(product, 1);
        } else {
            //若存在。。
            List<EbShoppingCartItem> items = cart.getItems();
            if (items != null && items.size() > 0) {
                for (EbShoppingCartItem item : items) {
                    EbProduct itemPro = new EbProduct();
                    if (itemPro.getEpId() == Id) {
                        item.setQuantity(item.getQuantity() + 1);
                        issame = true;
                    }
                }
            }
            //若不存在
            if (!issame) {
                cart.addItem(product, 1);
            }
        }

        request.getSession().setAttribute("cart", cart);
    }
    public void modifyCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
           EbShoppingCart cart = (EbShoppingCart)request.getSession().getAttribute("cart");
          long quantity = 0;
        int index = 0;
        String quantityParam = request.getParameter("quantity");
        String indexParam = request.getParameter("index");

        if(quantityParam != null && !"".equals(quantityParam)){
            quantity = Integer.parseInt(quantityParam);
        }

        if(indexParam != null && !"".equals(indexParam)){
            index = Integer.parseInt(indexParam);
        }
        cart.modifyQuantity(index, quantity);
    }

    public void removeCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
       EbShoppingCart  cart = (EbShoppingCart) request.getSession().getAttribute("cart");
        int index = 0;
        String indexParam = request.getParameter("index");

        if(indexParam != null && !"".equals(indexParam)){
            index = Integer.valueOf(indexParam);
        }
        cart.removeItem(index);
    }

    public void parCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        EbUser user = (EbUser) request.getSession().getAttribute("user");
        if(user == null){
            response.sendRedirect("login.jsp");
            return;
        }

        EbShoppingCart cart = (EbShoppingCart) request.getSession().getAttribute("cart");

        if(cart != null){
            orderService.payOrder(cart, user);
        }
        response.sendRedirect("/shopping-result.jsp");
    }

}
