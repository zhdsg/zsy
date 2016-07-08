package sdkd.com.ec.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;

/**
 * Created by Administrator on 2016/7/6.
 */
public class BaseDao {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    /**
     * 获取数据库连接
     */
   public Connection getConnections(){
       try {
           //加载驱动
           Class.forName(getPro("driver"));
           //桥梁
           con = DriverManager.getConnection(getPro("url"), getPro("username"), getPro("password"));
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return con;
   }

    /**
     * 释放资源
     */

    public void close(){
       try {
           if(rs != null) rs.close();
           if(con != null) con.close();
           if(ps != null) ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取配置信息
     */

    public String getPro(String key){
        Properties pro = new Properties();
        InputStream is = this.getClass().getResourceAsStream("/jdbc.properties");
        try {
            pro.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String value = pro.get(key).toString();
        return value;
    }


    public int excuteModify(String sql, List<String> params){
       int result = 0;
        try {
          con = this.getConnections();
            //CRUD
            System.out.println(sql);
            PreparedStatement ps = con.prepareStatement(sql);
            if(params != null && params.size() > 0){
                for(int i = 0; i <params.size(); i++){
                    ps.setString((i + 1), params.get(i));
                }
            }
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close();
        }
        return result;
    }

    public ResultSet excuteSearch(String sql, List<String> params){
        try {
            con = this.getConnections();
            //CRUD
            PreparedStatement ps = con.prepareStatement(sql);
            if(params != null && params.size() > 0){
                for(int i = 0; i <params.size(); i++){
                    ps.setString((i + 1), params.get(i));
                }
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
