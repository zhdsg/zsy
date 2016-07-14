package sdkd.com.ec.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/7/13.
 */
public class DateUtil {
    /**
     * 将Date转换为String
     * @param date
     * @param format
     * @return
     */
    public static String parseDateToStr(Date date, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateStr = sdf.format(date);
        return dateStr;
    }
    public static Date parseStrToDate(String date, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date dateStr = sdf.parse(date);
        return dateStr;
    }

    public static Date getSystemDate(){
        Date date = new Date();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        return date;
    }
}
