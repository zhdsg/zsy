package sdkd.com.ec.dao.impl;

import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.model.EbNews;
import sdkd.com.ec.model.EbProduct;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */
public class EbProductDao extends BaseDao{

    public List<EbProduct>  getProduct(){
        List<EbProduct>  ebProductList =new ArrayList<EbProduct>();
        String sql ="select * FROM easybuy_product ORDER BY epc_id DESC LIMIT 0,7";
        ResultSet rs =this.excuteSearch(sql, null);
        try {
            while(rs.next()){
                EbProduct ebProduct =new EbProduct();
                ebProduct.setEpId(rs.getInt("ep_id"));
                ebProduct.setEpName(rs.getString("ep_name"));
                ebProduct.setEpDescription(rs.getString("ep_description"));
                ebProduct.setEpPrice(rs.getDouble("ep_price"));
                ebProduct.setEpDiscount(rs.getInt("ep_discount"));
                ebProduct.setEpHotsale(rs.getInt("ep_hotsale"));
                ebProduct.setEpcId(rs.getInt("epc_id"));
                ebProduct.setEpcChildId(rs.getInt("epc_child_id"));
                ebProductList.add(ebProduct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ebProductList;
    }

    public List<EbProduct> findProductByepcId(int id){
        List<EbProduct> proList = new ArrayList<>();

        String sql ="select * FROM easybuy_product where epc_id = ?";
        List<String> params = new ArrayList<String>();
        params.add(id+"");

        ResultSet rs = this.excuteSearch(sql, params);
        try {
            while(rs.next()){
                EbProduct ebProduct = new EbProduct();
                ebProduct.setEpId(rs.getInt("ep_id"));
                ebProduct.setEpName(rs.getString("ep_name"));
                ebProduct.setEpDescription(rs.getString("ep_description"));
                ebProduct.setEpPrice(rs.getDouble("ep_price"));
                ebProduct.setEpDiscount(rs.getInt("ep_discount"));
                ebProduct.setEpHotsale(rs.getInt("ep_hotsale"));
                ebProduct.setEpcId(rs.getInt("epc_id"));
                proList.add(ebProduct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proList;
    }

}
