package sdkd.com.ec.dao.impl;

import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.model.EbNews;
import sdkd.com.ec.util.DateUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by SDUST-132 on 2016/7/6.
 */
public class EbNewsDao extends BaseDao {

    public List<EbNews> buildList(String sql){
        List<EbNews> newsList = new ArrayList<>();
        try {
            ResultSet rs = this.excuteSearch(sql,null);
            while (rs.next()){
                EbNews news = new EbNews();
                news.setEnId(rs.getInt("en_id"));
                news.setEnTitle(rs.getString("en_title"));
                news.setEnContent(rs.getString("en_content"));
                //添加到集合中
                newsList.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newsList;
    }

    public List<EbNews> getNews(){
        List<EbNews> newsList = new ArrayList<EbNews>();
        String sql = "select * from easybuy_news order by en_create_time desc limit 0,7";
        newsList = this.buildList(sql);
        return newsList;
    }



    public EbNews findNewsById(int id){
        EbNews news = new EbNews();
        List<EbNews> newsList = new ArrayList<EbNews>();
        String sql = "select * from easybuy_news while en_id = ?";
        try {
            List<String> params = new ArrayList<String>();
            params.add(id+"");
            ResultSet rs = this.excuteSearch(sql,params);
            while (rs.next()){
                    news.setEnId(rs.getInt("en_id"));
                    news.setEnTitle(rs.getString("en_title"));
                    news.setEnContent(rs.getString("en_content"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return news;
    }

    public List<EbNews> getallNews(){
        List<EbNews> newsList = new ArrayList<>();
        String sql = "select * from easybuy_news";
        newsList = this.buildList(sql);
       return newsList;
    }

    public boolean addNews(String newsTitle, String newsContent, Date createTime){
        String sql = "insert into easybuy_news(en_title, en_content, en_create_time) values(?, ?, ?)";
        List<String> params = new ArrayList<>();
        params.add(newsTitle);
        params.add(newsContent);
        params.add(DateUtil.parseDateToStr(createTime, "yyyy-MM-dd HH:mm:ss"));
        int res = this.excuteModify(sql, params);
        return (res > 0);
    }

    public boolean deleteNews(int id){
         String sql = "delete from easybuy_news where en_id = ?";
        List<String> params = new ArrayList<>();
        params.add(id+"");
        int res = this.excuteModify(sql, params);
        return (res > 0);
    }

    public boolean modifyNews(String newTitle, String newContent, Date createTime, int id){
        String sql = "update easybuy_news " +
                "set en_title = ?, en_content = ?, en_create_time = ? where en_id = ?";
        List<String> params = new ArrayList<>();
        params.add(newTitle);
        params.add(newContent);
        params.add(DateUtil.parseDateToStr(createTime, "yyyy-MM-dd HH:mm:ss"));
        params.add(id+"");
        int res = this.excuteModify(sql, params);
        return (res > 0);
    }

}
