package sdkd.com.ec.dao.impl;

import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.model.EbPCategory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/7.
 */
public class EbPCategoryDao extends BaseDao{

    public  List<EbPCategory> getCategroies(){
        List<EbPCategory> pcalist = new ArrayList<EbPCategory>();
        String sql = "select * from easybuy_product_category";
        try {
            ResultSet rs = this.excuteSearch(sql, null);
            while(rs.next()){
                EbPCategory pcate = new EbPCategory();
                pcate.setEpcId(rs.getInt("epc_id"));
                pcate.setEpcName(rs.getString("epc_name"));
                pcate.setEpcParentId(rs.getInt("epc_parent_id"));
                pcalist.add(pcate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pcalist;
    }
}
