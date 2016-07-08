package sdkd.com.ec.dao.impl;

import java.sql.*;

/**
 * Created by Administrator on 2016/7/5.
 */
public class EBUserDao {

    public static void test(){
        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //桥梁
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/easybuy?useUnicode=true&characterEncoding = gbk","root", "");
            //CRUD
            String sql = "insert into easybuy_user(eu_user_name) values('啊a a 啊')";
            System.out.println(sql);
            PreparedStatement ps = con.prepareStatement(sql);
            int result = ps.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void testDelete(){
        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //桥梁
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/easybuy?useUnicode=true&characterEncoding = utf8","root", "");
            //CRUD
            String sql = "select * from easybuy_user";
            System.out.println(sql);
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                String name = rs.getString("eu_user_name");
                System.out.println("name: " + name);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    //    test();
        //testDelete();
   //     testDelete();
    }
}
