package sdkd.com.ec.service.impl;

import sdkd.com.ec.dao.impl.EbOrderDao;
import sdkd.com.ec.dao.impl.EbOrderDetailDao;
import sdkd.com.ec.dao.impl.EbProductDao;
import sdkd.com.ec.model.*;
import sdkd.com.ec.util.ConstantUtil;

import java.util.Date;

/**
 * Created by Administrator on 2016/7/12.
 */
public class EbOrderService {
    EbOrderDao orderDao = new EbOrderDao();
    EbOrderDetailDao orderDetailDao = new EbOrderDetailDao();
    EbProductDao productDao = new EbProductDao();

    public void payOrder(EbShoppingCart cart, EbUser user){
        EbOrder order = new EbOrder();
        order.setEo_user_id(user.getEuUserId()+"");
        order.setEo_user_name(user.getEuUserName());
        order.setEo_user_address(user.getEuAddress());
        order.setEo_cost(cart.getTotalCost());
        order.setEo_create_time(new Date());
        order.setEo_status(ConstantUtil.ORDER);
        order.setEo_type(ConstantUtil.ONLINEPAY);
        int genId = orderDao.saveOrder(order);

        if(cart.getItems() != null && cart.getItems().size() > 0){
            for(EbShoppingCartItem item : cart.getItems()){
                EbOrderDetail detail = new EbOrderDetail();
                detail.setEo_id(genId);
                detail.setEod_cost(item.getCost());
                detail.setEod_quantity(Integer.parseInt(item.getQuantity().toString()));
                detail.setEp_id(item.getProduct().getEpId());
                orderDetailDao.saveOrderDetail(detail);   //保存订单详情

                productDao.updateStock(item.getProduct().getEpId(), Integer.parseInt(item.getQuantity().toString()));
            }
        }
    }

}
