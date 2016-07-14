package sdkd.com.ec.dao.impl;

import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.model.EbOrderDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/12.
 */
public class EbOrderDetailDao extends BaseDao {

    public void saveOrderDetail(EbOrderDetail detail){
        String sql = "insert into easybuy_order_detail(eo_id,ep_id,eod_quantity,eod_cost) " +
                "values(?,?,?,?)";
        List<String> params = new ArrayList<String>();
        params.add(detail.getEo_id()+"");
        params.add(detail.getEp_id()+"");
        params.add(detail.getEod_quantity()+"");
        params.add(detail.getEod_cost()+"");
        this.excuteModify(sql, params);
    }
}
