package sdkd.com.ec.dao.impl;

import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.model.EbUser;
import sdkd.com.ec.util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户DAO
 * Created by SDUST-132 on 2016/7/5.
 */
public class EbUserDao extends BaseDao{
    public boolean hasRegisterUser(String user){
        //查重
        String sql=null;
        sql ="select eu_user_name FROM easybuy_user ORDER BY eu_user_id";

        ResultSet rs=this.excuteSearch(sql,null);
        try {
            while(rs.next()){
                if(rs.getString("eu_user_name").trim().equals(user)){
                    return true;
                }
            }
            //用户添加
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close();
        }


        return false;

    }
    public int deleteUser(int id){
        int result=0;
        String sql ="DELETE from easybuy_user WHERE eu_user_id =?";
        List<String> param =new ArrayList<String>();
        param.add(id+"");
        result=this.excuteModify(sql,param);
        return result;

    }
    public void registerUser(String user,String password){
        String sql ="insert into easybuy_user(eu_user_name,eu_password) values(?,?)";
        List<String> params =new ArrayList<String>();
        params.add(user);
        params.add(password);
        int result =this.excuteModify(sql,params);


}
    public int  getUser(String user,String password){
        int key=0;

        String sql ="select eu_user_name ,eu_password,eu_status  FROM easybuy_user";

        ResultSet rs=this.excuteSearch(sql,null);
        try {
            while(rs.next()){
                if(rs.getString("eu_user_name").trim().equals(user)&&rs.getString("eu_password").equals(password)){
                  key =rs.getInt("eu_status");
            }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close();
        }

        return key;
    }
    public List<EbUser>  getUser(){
        List<EbUser>  userList =new ArrayList<EbUser>();
        String sql ="select * from easybuy_user";
        ResultSet rs =this.excuteSearch(sql,null);

        try {
            while(rs.next()){
            EbUser user =new EbUser();
                user.setEuUserId(rs.getInt("eu_user_id"));
                user.setEuUserName(rs.getString("eu_user_name"));
                user.setEuSex(rs.getString("eu_sex"));
                user.setEuEmail(rs.getString("eu_email"));
                user.setEuMobile(rs.getString("eu_mobile"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close();
        }
        return userList;

    }
    public int  insertUser(EbUser user){
        int result =0;
        String sql ="insert into easybuy_user(eu_user_name,eu_password,eu_sex,eu_birthday,eu_mobile,eu_address,eu_status) values(?,?,?,?,?,?,?)";
        List<String> param =new ArrayList<String>();
        param.add(user.getEuUserName());
        param.add(user.getEuPassword());
        param.add(user.getEuSex());
        param.add(DateUtil.parseDateToStr(user.getEuBirthday(),"yyyy-MM-dd"));
        param.add(user.getEuMobile());
        param.add(user.getEuAddress());
        param.add(user.getEuStatus()+"");
         result =this.excuteModify(sql,param);
        return result;
    }
    public int updateUser(EbUser user){
        int result=0;
        String sql ="UPDATE easybuy_user SET eu_password=?,eu_sex=?,eu_birthday=?,eu_mobile=?,eu_address=?WHERE eu_user_id=?";
        List<String> param =new ArrayList<String>();

        param.add(user.getEuPassword());
        param.add(user.getEuSex());
        param.add(DateUtil.parseDateToStr(user.getEuBirthday(),"yyyy-MM-dd"));
        param.add(user.getEuMobile());
        param.add(user.getEuAddress());
        param.add(user.getEuUserId()+"");
        result =this.excuteModify(sql,param);



        return result;

    }

    public EbUser pushUser(int id){
        String sql ="select * from easybuy_user where eu_user_id =?";
        List<String> param =new ArrayList<String>();
        param.add(id+"");
        ResultSet rs =this.excuteSearch(sql,param);
        EbUser user =new EbUser();
        try {
            while(rs.next()){
                user.setEuUserId(rs.getInt("eu_user_id"));
                user.setEuUserName(rs.getString("eu_user_name"));
                user.setEuPassword(rs.getString("eu_password"));
                user.setEuSex(rs.getString("eu_sex"));
                user.setEuBirthday(rs.getDate("eu_birthday"));
                user.setEuMobile(rs.getString("eu_mobile"));
                user.setEuAddress(rs.getString("eu_address"));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close();
        }
        return user;
    }



}
