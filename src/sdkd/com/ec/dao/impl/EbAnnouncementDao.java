package sdkd.com.ec.dao.impl;

import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.model.EbAnnouncement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/7.
 */
public class EbAnnouncementDao extends BaseDao {
    public List<EbAnnouncement> getAnnouncement(){
       List<EbAnnouncement> annList = new ArrayList<EbAnnouncement>();
        String sql = "select * from easybuy_notice order by notice_create_time DESC LIMIT 0, 7";
        ResultSet rs = this.excuteSearch(sql, null);
        try {
            while(rs.next()){
                EbAnnouncement ann = new EbAnnouncement();
                ann.setNoId(rs.getInt("notice_id"));
                ann.setNoTitle(rs.getString("notice_title"));
                ann.setNoContent(rs.getString("notice_content"));
                ann.setNoCreateTime(rs.getTime("notice_create_time"));
                annList.add(ann);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return annList;
    }
}
