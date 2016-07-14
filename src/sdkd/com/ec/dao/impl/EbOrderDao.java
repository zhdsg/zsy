package sdkd.com.ec.dao.impl;

import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.model.EbOrder;
import sdkd.com.ec.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/12.
 */
public class EbOrderDao extends BaseDao {

    public int saveOrder(EbOrder order){
        String sql = "insert into easybuy_order(eo_user_id,eo_user_name,eo_user_address,eo_create_time,eo_cost,eo_status,eo_type) " +
                "values(?,?,?,?,?,?,?)";
        List<String> params = new ArrayList<>();
        params.add(order.getEo_user_id());
        params.add(order.getEo_user_name());
        params.add(order.getEo_user_address());
        params.add(DateUtil.parseDateToStr(order.getEo_create_time(),"yyyy-MM-dd HH:mm:ss"));
        params.add(order.getEo_cost()+"");
        params.add(order.getEo_status()+"");
        params.add(order.getEo_type()+"");
        return this.excuteModify(sql, params);
    }
}
