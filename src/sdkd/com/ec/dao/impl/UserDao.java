package sdkd.com.ec.dao.impl;

import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.model.EbUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */
public class UserDao extends BaseDao{
   public List<EbUser> getEbusers(){
       List<EbUser> alluser = new ArrayList<EbUser>();
       String sql = "select * from easybuy_user";
       ResultSet rs = this.excuteSearch(sql, null);
       try {
           while(rs.next()){
               EbUser eu = new EbUser();
               eu.setEuUsername(rs.getString("eu_user_name"));
               eu.setEuPassword(rs.getString("eu_password"));
               alluser.add(eu);
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
        return alluser;
   }

   public boolean checkUser(String euUsername, String euPassword){
        String sql = "select * from easybuy_user where eu_user_name = ? and eu_password = ?";
        List<String> params = new ArrayList<String>();
         params.add(euUsername);
         params.add(euPassword);
        ResultSet rs = this.excuteSearch(sql, params);
       try {
           if (rs.next()){
               return true;
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
      return false;
   }

    public boolean insertUser(String euUsername, String euPassword){
        String sql = "insert into easybuy_user(eu_user_name, eu_password) values(?, ?)";
        List<String> params = new ArrayList<String>();

        params.add(euUsername);
        params.add(euPassword);
        int res = this.excuteModify(sql, params);
        return (res > 0);
    }
}
