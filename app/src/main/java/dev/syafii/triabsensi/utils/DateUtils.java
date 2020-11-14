package dev.syafii.triabsensi.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static final String RAW_DATE_PATTERN = "yyyy-MM-dd";
    public static final String RAW_TIME_DATE_PATTERN = "HH:mm:ss";

    public static String formatDateToString(Date time, String pattern){
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(time);
    }
}
