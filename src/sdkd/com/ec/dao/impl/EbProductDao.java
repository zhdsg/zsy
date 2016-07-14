package sdkd.com.ec.dao.impl;

import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.model.EbNews;
import sdkd.com.ec.model.EbProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
                ebProduct.setEpStock(rs.getInt("ep_stock"));
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
                ebProduct.setEpFileName(rs.getString("ep_file_name"));
                ebProduct.setEpStock(rs.getInt("ep_stock"));
                proList.add(ebProduct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proList;
    }

    public EbProduct findProductById(int id){
        EbProduct ebProduct = new EbProduct();

        String sql = "select * from easybuy_product where ep_id = ?";
        List<String> params = new ArrayList<>();
        params.add(id + "");

        ResultSet rs = this.excuteSearch(sql, params);
        try {
            while(rs.next()){
                ebProduct.setEpId(rs.getInt("ep_id"));
                ebProduct.setEpName(rs.getString("ep_name"));
                ebProduct.setEpDescription(rs.getString("ep_description"));
                ebProduct.setEpPrice(rs.getDouble("ep_price"));
                ebProduct.setEpDiscount(rs.getInt("ep_discount"));
                ebProduct.setEpHotsale(rs.getInt("ep_hotsale"));
                ebProduct.setEpcId(rs.getInt("epc_id"));
                ebProduct.setEpFileName(rs.getString("ep_file_name"));
                ebProduct.setEpStock(rs.getInt("ep_stock"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ebProduct;
    }

    /**
     * 更新商品库存
     */

    public void updateStock(int proid, int quantity){
        String sql = "update easybuy_product set ep_stock=ep_stock-? where ?ep_id=?";
        List<String> params = new ArrayList<>();
        params.add(proid + "");
        params.add(quantity + "");
        this.excuteModify(sql, params);
    }

    /**
     * 所有商品列表
     * @return
     */
    public List<EbProduct> getProductLists(){
      List<EbProduct> productList = new ArrayList<>();
        String sql = "select * from easybuy_product";
        this.buildList(sql, null);
        return productList;
    }

    /**
     * 独立获取结果集
     * @param sql
     * @param params
     * @return
     */

    private List<EbProduct> buildList(String sql,List<String> params){
        List<EbProduct> productList = new ArrayList<EbProduct>();
        ResultSet rs = this.excuteSearch(sql, params);
        try {
            while(rs.next()){
                EbProduct product = new EbProduct();
                product.setEpcId(rs.getInt("epc_id"));
                product.setEpcChildId(rs.getInt("epc_child_id"));
                product.setEpDescription(rs.getString("ep_description"));

                product.setEpFileName(rs.getString("ep_file_name"));
                product.setEpId(rs.getInt("ep_id"));
                product.setEpName(rs.getString("ep_name"));
                product.setEpPrice(rs.getDouble("ep_price"));
                product.setEpStock(rs.getInt("ep_stock"));
                product.setEpViews(rs.getInt("ep_views"));
                productList.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    /**
     * 所有商品数量
     */

    public int getProductCount(){
                 int count = 0;
                 String sql = "select count(ep_id) from easybuy_product";
                 ResultSet rs = this.excuteSearch(sql,null);
                 try {
                         if(rs.next()){
                                 count = rs.getInt(1);
                             }
                     } catch (SQLException e) {
                         e.printStackTrace();
                     }
                 return count;
             }


    public List<EbProduct> getProductPager(int pageIndex,int pageSize){
                 //pageIndex 1 2 3 4 5 6
                 List<EbProduct> productList = new ArrayList<EbProduct>();
                 String sql = "select * from easybuy_product limit ?,?";
                 ResultSet rs = null;
                 try {
                         Connection con = this.getConnections();
                         PreparedStatement ps = con.prepareStatement(sql);
                         int start = (pageIndex * pageSize)-pageSize;
                         ps.setInt(1,start);
                         ps.setInt(2,pageSize);
                         rs = ps.executeQuery();
                         try {
                                 while(rs.next()){
                                         EbProduct product = new EbProduct();
                                         product.setEpcId(rs.getInt("epc_id"));
                                         product.setEpcChildId(rs.getInt("epc_child_id"));
                                         product.setEpDescription(rs.getString("ep_description"));
                                         product.setEpDiscount(rs.getInt("ep_discount"));
                                         product.setEpFileName(rs.getString("ep_file_name"));
                                         product.setEpId(rs.getInt("ep_id"));
                                         product.setEpName(rs.getString("ep_name"));
                                         product.setEpPrice(rs.getDouble("ep_price"));
                                         product.setEpStock(rs.getInt("ep_stock"));
                                         product.setEpViews(rs.getInt("ep_views"));
                                         productList.add(product);
                                     }


                             } catch (SQLException e) {
                                 e.printStackTrace();
                             }
                     } catch (SQLException e) {
                         e.printStackTrace();
                     }


                 return productList;
             }

}
