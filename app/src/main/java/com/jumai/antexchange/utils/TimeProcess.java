package com.jumai.antexchange.utils;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author yf
 * @date 2019/9/19/019
 * 描述：时间处理
 */

public class TimeProcess {

    private static final SimpleDateFormat YEAR_FORMAT1 = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
    private static final SimpleDateFormat YEAR_FORMAT2 = new SimpleDateFormat("yyyy.MM.dd", Locale.CHINA);
    private static final SimpleDateFormat YEAR_FORMAT3 = new SimpleDateFormat("yyyy-MM-dd-HH-mm", Locale.CHINA);
    private static final SimpleDateFormat YEAR_FORMAT4 = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
    private static final SimpleDateFormat YEAR_FORMAT5 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
    private static final SimpleDateFormat YEAR_FORMAT6 = new SimpleDateFormat("yyyy.MM", Locale.CHINA);
    private static final SimpleDateFormat YEAR_FORMAT7 = new SimpleDateFormat("yyyy", Locale.CHINA);
    private static final SimpleDateFormat YEAR_FORMAT8 = new SimpleDateFormat("yyyy年 MM月 dd日", Locale.CHINA);
    private static final SimpleDateFormat YEAR_FORMAT9 = new SimpleDateFormat("yyyy年 MM月", Locale.CHINA);
    private static final SimpleDateFormat YEAR_FORMAT10 = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
    private static final SimpleDateFormat YEAR_FORMAT11 = new SimpleDateFormat("yyyy年MM月dd日a HH:mm", Locale.CHINA);

    private static final SimpleDateFormat MONTH_FORMAT1 = new SimpleDateFormat("MM月dd日", Locale.CHINA);
    private static final SimpleDateFormat MONTH_FORMAT2 = new SimpleDateFormat("MM-dd", Locale.CHINA);
    private static final SimpleDateFormat MONTH_FORMAT3 = new SimpleDateFormat("MM月  yyyy", Locale.CHINA);
    private static final SimpleDateFormat MONTH_FORMAT4 = new SimpleDateFormat("MM-dd  HH:mm", Locale.CHINA);

    private static final SimpleDateFormat DAY_FORMAT1 = new SimpleDateFormat("dd", Locale.CHINA);

    private static final SimpleDateFormat HOUR_FORMAT1 = new SimpleDateFormat("HH:mm", Locale.CHINA);
    private static final SimpleDateFormat HOUR_FORMAT2 = new SimpleDateFormat(" HH:mm", Locale.CHINA);
    private static final SimpleDateFormat HOUR_FORMAT3 = new SimpleDateFormat("hh:mm", Locale.CHINA);

    private static final SimpleDateFormat MINUTE_FORMAT = new SimpleDateFormat("mm:ss", Locale.CHINA);

    private final static long second = 1000;            //1秒
    private final static long minute = 60000;           // 1分钟
    private final static long hour = 3600000;           // 1小时
    private final static long day = 86400000;           // 1天
    private final static long ThreeDay = 259200000;     // 3天


    public static String getMonthFormat3(long time) {
        return MONTH_FORMAT3.format(new Date(time));
    }

    public static String getYearFormat7() {
        return YEAR_FORMAT7.format(new Date());
    }

    public static String getYearMonthFormater(long time) {
        return YEAR_FORMAT9.format(new Date(time));
    }

    public static String getYearMonthDayFormater(long time) {
        return YEAR_FORMAT8.format(new Date(time));
    }


    public static String getHourMinuteFormate(long time) {
        return HOUR_FORMAT1.format(new Date(time));
    }

    public static String getHourMinuteFormate12(long time) {
        String timeStr = HOUR_FORMAT3.format(new Date(time));
        if (timeStr.startsWith("0")) {
            timeStr = timeStr.substring(1);
        }
        return timeStr;
    }

    /**
     * dd
     */
    public static int getDayFormater(long time) {
        return Integer.valueOf(DAY_FORMAT1.format(new Date(time)));
    }

    /**
     * yyyy-MM-dd HH:mm:ss"
     */
    public static String getToSecondFormater(long time) {
        return YEAR_FORMAT5.format(new Date(time));
    }

    /**
     * "yyyy-MM-dd HH:mm"
     */
    public static String getToMinuteFormater(long time) {
        return YEAR_FORMAT4.format(new Date(time));
    }

    /**
     * "MM-dd HH:mm"
     */
    public static String getMonthFormater(long time) {
        return MONTH_FORMAT4.format(new Date(time));
    }

    /**
     * "MM-dd HH:mm"
     */
    public static String getMonthFormaterAdatper(long time, String currYear) {
        String data = YEAR_FORMAT4.format(new Date(time));
        if (data.startsWith(currYear)) {
            return data.substring(currYear.length() + 1, data.length());
        }
        return data;
    }

    /**
     * "yyyy-MM-dd HH:mm"
     */
    public static long getMinuteFormaterValue(String dataString) {
        try {
            Date date = YEAR_FORMAT4.parse(dataString);
            return date.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * "yyyy-MM-dd"
     */
    public static long getDayFormateValue(String dataString) {
        try {
            Date date = YEAR_FORMAT1.parse(dataString);
            return date.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * "yyyy-MM-dd"
     */
    public static String getExchangeFormateValue(long time) {
        if (System.currentTimeMillis() - time < day) {
            return getHourMinuteFormate(time);
        } else {
            return getFormatTime(time);
        }
    }


    /**
     * is in same day
     */
    public static boolean isTimeSameDay(long time1, long time2) {
        return time1 - time2 < day;
    }

    /**
     * is in same day
     */
    public static boolean isTimeInThreeDay(long time) {
        return System.currentTimeMillis() - time < ThreeDay;
    }

    public static boolean isTimeInThreeDay(long curTime, long time) {
        return curTime - time < ThreeDay;
    }

    /**
     * yyyy-MM-dd
     */
    public static String getFormatTime(long time) {
        if (time > 0) {
            return YEAR_FORMAT1.format(new Date(time));
        }
        return "";
    }

    /**
     * yyyy-MM-dd
     */
    public static String getFormatTimeHor(long time) {
        if (time > 0) {
            return YEAR_FORMAT1.format(new Date(time));
        }
        return "-";
    }

    /**
     * yyyy.MM.dd
     */
    public static String getFormatTimeDot(long time) {
        if (time > 0) {
            return YEAR_FORMAT2.format(new Date(time));
        }
        return "-";
    }

    /**
     * yyyy.MM
     */
    public static String getFormatTimeMonthDot(long time) {
        if (time > 0) {
            return YEAR_FORMAT6.format(new Date(time));
        }
        return "-";
    }

    /**
     * MM月dd日
     */
    public static String getFormatTimeMonth(long time) {
        if (time > 0) {
            return MONTH_FORMAT1.format(new Date(time));
        }
        return "-";
    }

    /**
     * MM-dd
     */
    public static String getNearFormatTimeMonth(long time) {
        if (time > 0) {
            return MONTH_FORMAT2.format(new Date(time));
        }
        return "-";
    }

    /**
     * MM月dd日 或者yyyy年
     */
    public static String getFormatTimeMonthAdapter(long time, String currentYear) {

        String data = "";
        if (time > 0) {
            data = YEAR_FORMAT10.format(new Date(time));
        }
        if (data.startsWith(currentYear)) {
            return data.substring(currentYear.length() + 1, data.length());
        }
        return data;
    }


    /**
     * 文字描述的日期 已发生的时间
     */
    public static String getCommentStyleTime(long time) {
        if (time == 0) {
            return "刚刚";
        }
        long diff = System.currentTimeMillis() - time;
        String timeStr;
        if (diff < 1000) {
            timeStr = "刚刚";
        } else if (diff < minute) {
            timeStr = diff / 1000 + "秒前";
        } else if (diff < hour) {
            timeStr = diff / minute + "分钟前";
        } else if (diff < day) {
            timeStr = diff / hour + "小时前";
        } else {
            timeStr = YEAR_FORMAT1.format(new Date(time));
        }

        return timeStr;
    }

    /**
     * 附近的投行人时间
     */
    public static String getNearbyTime(long time) {
        long diff = System.currentTimeMillis() - time;
        String timeStr;
        if (diff < 1000) {
            timeStr = "刚刚";
        } else if (diff < hour) {
            timeStr = diff / minute + "分钟前";
        } else if (diff < day) {
            timeStr = diff / hour + "小时前";
        } else if (diff < 2 * day) {
            timeStr = "昨天" + getHourMinuteFormate(time);
        } else {
            timeStr = getNearFormatTimeMonth(time);
        }
        return timeStr;
    }

    /**
     *
     */
    public static String getCommentStyleTime(long time, long curr) {
        long diff = curr - time;
        String timeStr;
        if (diff < minute) {
            timeStr = "刚刚";
        } else if (diff < hour) {
            timeStr = diff / minute + "分钟前";
        } else if (diff < day) {
            timeStr = diff / hour + "小时前";
        } else {
            timeStr = YEAR_FORMAT1.format(new Date(time));
        }

        return timeStr;
    }


    public static String getActivityStyleTime(long time) {
        if (time < minute) {
            return "";
        } else if (time < hour) {
            return time / minute + "分钟";
        } else if (time < day) {
            return time / hour + "小时";
        } else {
            return time / day + "天";
        }
    }


    /**
     *
     */
    public static String getDynamicStyleTime(long time) {
        if (time == 0) {
            return "刚刚";
        }
        long diff = (getCurrentDayEndTime() - time) / day;

        String timeStr;
        if (diff == 0) {
            timeStr = "今天";
        } else if (diff < 4) {
            timeStr = diff + "天前";
        } else {
            timeStr = getFormatTime(time);
        }
        return timeStr;
    }

    /**
     * get news time style
     */
    public static String getNewsTimeStyle(long time) {

        long diff = (getCurrentDayEndTime() - time) / day;

        String timeStr;

        if (diff == 0) {
            timeStr = "今天";
        } else if (diff == 1) {
            timeStr = "昨天";
        } else {
            timeStr = getFormatTime(time);
        }
        return timeStr;
    }

    /**
     * get news time style
     */
    public static String getContactTimeStyle(long time) {

        long diff = (getCurrentDayEndTime() - time) / day;

        String timeStr;

        if (diff == 0) {
            timeStr = "今天 " + getHourMinuteFormate(time);
        } else if (diff == 1) {
            timeStr = "昨天 " + getHourMinuteFormate(time);
        } else {
            timeStr = getFormatTime(time);
        }
        return timeStr;
    }

    /**
     * 格式化通话时长
     */
    public static String formatCallDuration(long time) {
        if (time < 60) {
            return time + "秒";
        } else if (time < 3600) {
            return time / 60 + "分钟";
        } else {
            return time / 3600 + "小时" + time % 3600 / 60 + "分钟";
        }
    }

    /**
     * get current day end time
     */
    public static long getCurrentDayEndTime() {
        return getCurrentDayEndTime(System.currentTimeMillis());
    }


    /**
     * get given time's day end time
     */
    public static long getCurrentDayEndTime(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.SECOND, -1);
        return calendar.getTimeInMillis();
    }

    private static DecimalFormat visitorFormat = new DecimalFormat("##.#");

    /**
     * 客户访问时间格式化
     */
    public static String getTimeForVisitor(long time) {
        String timeStr;
        float result;
        if (time >= day) {
            result = time * 1.0f / day;
            timeStr = Math.round(result) + "d";
        } else if (time >= hour) {
            result = time * 1.0f / hour;
            timeStr = Math.round(result) + "h";
        } else if (time >= minute) {
            result = time * 1.0f / minute;
            timeStr = Math.round(result) + "min";
        } else if (time >= second) {
            result = time * 1.0f / second;
            timeStr = Math.round(result) + "s";
        } else {
            if (time >= 500) {
                timeStr = "1s";
            } else {
                timeStr = "0s";
            }
        }
        return timeStr;
    }

    private static String[] dayNames = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};

    public static String getChatTime(long timesamp) {
        String result = "";
        Calendar todayCalendar = Calendar.getInstance();
        Calendar otherCalendar = Calendar.getInstance();
        otherCalendar.setTimeInMillis(timesamp);

        String am_pm = "";
        int hour = otherCalendar.get(Calendar.HOUR_OF_DAY);
        if (hour < 6) {
            am_pm = "凌晨";
        } else if (hour < 12) {
            am_pm = "早上";
        } else if (hour == 12) {
            am_pm = "中午";
        } else if (hour < 18) {
            am_pm = "下午";
        } else {
            am_pm = "晚上";
        }
        String timeFormat = "M月d日 " + am_pm + "HH:mm";
        String yearTimeFormat = "yyyy年M月d日 " + am_pm + "HH:mm";

        boolean yearTemp = todayCalendar.get(Calendar.YEAR) == otherCalendar.get(Calendar.YEAR);
        if (yearTemp) {
            int todayMonth = todayCalendar.get(Calendar.MONTH);
            int otherMonth = otherCalendar.get(Calendar.MONTH);
            if (todayMonth == otherMonth) {//表示是同一个月
                int temp = todayCalendar.get(Calendar.DATE) - otherCalendar.get(Calendar.DATE);
                switch (temp) {
                    case 0:
                        result = am_pm + getHourMinuteFormate12(timesamp);
                        break;
                    case 1:
                        result = "昨天 " + am_pm + getHourMinuteFormate12(timesamp);
                        break;
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        int dayOfMonth = otherCalendar.get(Calendar.WEEK_OF_MONTH);
                        int todayOfMonth = todayCalendar.get(Calendar.WEEK_OF_MONTH);
                        if (dayOfMonth == todayOfMonth) {//表示是同一周
                            int dayOfWeek = otherCalendar.get(Calendar.DAY_OF_WEEK);
                            if (dayOfWeek != 1) {//判断当前是不是星期日     如想显示为：周日 12:09 可去掉此判断
                                result = dayNames[otherCalendar.get(Calendar.DAY_OF_WEEK) - 1] + " " + am_pm + getHourMinuteFormate12(timesamp);
                            } else {
                                result = getTime(timesamp, timeFormat);
                            }
                        } else {
                            result = getTime(timesamp, timeFormat);
                        }
                        break;
                    default:
                        result = getTime(timesamp, timeFormat);
                        break;
                }
            } else {
                result = getTime(timesamp, timeFormat);
            }
        } else {
            result = getYearTime(timesamp, yearTimeFormat);
        }
        return result;
    }

    public static String getTabChatTime(long timesamp) {
        String result = "";
        Calendar todayCalendar = Calendar.getInstance();
        Calendar otherCalendar = Calendar.getInstance();
        otherCalendar.setTimeInMillis(timesamp);

        String am_pm = "";
        int hour = otherCalendar.get(Calendar.HOUR_OF_DAY);
        if (hour < 6) {
            am_pm = "凌晨";
        } else if (hour < 12) {
            am_pm = "早上";
        } else if (hour == 12) {
            am_pm = "中午";
        } else if (hour < 18) {
            am_pm = "下午";
        } else {
            am_pm = "晚上";
        }
        String timeFormat = "M月d日";
        String yearTimeFormat = "yyyy年M月d日 ";

        boolean yearTemp = todayCalendar.get(Calendar.YEAR) == otherCalendar.get(Calendar.YEAR);
        if (yearTemp) {
            int todayMonth = todayCalendar.get(Calendar.MONTH);
            int otherMonth = otherCalendar.get(Calendar.MONTH);
            if (todayMonth == otherMonth) {//表示是同一个月
                int temp = todayCalendar.get(Calendar.DATE) - otherCalendar.get(Calendar.DATE);
                switch (temp) {
                    case 0:
                        result = getHourMinuteFormate(timesamp);
                        break;
                    case 1:
                        result = "昨天 ";
                        break;
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        int dayOfMonth = otherCalendar.get(Calendar.WEEK_OF_MONTH);
                        int todayOfMonth = todayCalendar.get(Calendar.WEEK_OF_MONTH);
                        if (dayOfMonth == todayOfMonth) {//表示是同一周
                            int dayOfWeek = otherCalendar.get(Calendar.DAY_OF_WEEK);
                            if (dayOfWeek != 1) {//判断当前是不是星期日     如想显示为：周日 12:09 可去掉此判断
                                result = dayNames[otherCalendar.get(Calendar.DAY_OF_WEEK) - 1];
                            } else {
                                result = getTime(timesamp, timeFormat);
                            }
                        } else {
                            result = getTime(timesamp, timeFormat);
                        }
                        break;
                    default:
                        result = getTime(timesamp, timeFormat);
                        break;
                }
            } else {
                result = getTime(timesamp, timeFormat);
            }
        } else {
            result = getYearTime(timesamp, yearTimeFormat);
        }
        return result;
    }

    /**
     * 不同一周的显示时间格式
     */
    public static String getTime(long time, String timeFormat) {
        SimpleDateFormat format = new SimpleDateFormat(timeFormat);
        return format.format(new Date(time));
    }

    /**
     * 不同年的显示时间格式
     */
    public static String getYearTime(long time, String yearTimeFormat) {
        SimpleDateFormat format = new SimpleDateFormat(yearTimeFormat);
        return format.format(new Date(time));
    }


    /**
     * 音频时间格式化 03:40:30
     */
    public static String formaterAudioTime(long time) {
        if (time <= 0) {
            return "00:00";
        }
        StringBuilder audioTime = new StringBuilder();
        if (time >= (hour * 10)) {
            audioTime.append(time / hour).append(":");
        } else if (time >= hour) {
            audioTime.append("0").append(time / hour).append(":");
        } else {
            audioTime.append("");
        }
        long min = time % hour;
        if (min >= (minute * 10)) {
            audioTime.append(min / minute).append(":");
        } else if (min >= minute) {
            audioTime.append("0").append(min / minute).append(":");
        } else {
            audioTime.append("00:");
        }
        long sec = min % minute;
        if (sec >= (second * 10)) {
            audioTime.append(sec / second);
        } else if (sec >= second) {
            audioTime.append("0").append(sec / second);
        } else {
            audioTime.append("00");
        }

        return audioTime.toString();
    }

    /**
     * 文件名称中插入的时间格式 yyyy-MM-dd-HH-mm
     */
    public static String getTimeForFile(long time) {
        return YEAR_FORMAT3.format(new Date(time));
    }


    /**
     * 通过一个音频文件获取音频的时长
     */
    public static int getAudioTime(Activity context, File saveFile) {
        MediaPlayer player = MediaPlayer.create(context, Uri.fromFile(saveFile));
        return player.getDuration();
    }

    public static String duration2Time(long time) {
        synchronized (MINUTE_FORMAT) {
            return MINUTE_FORMAT.format(new Date(time));
        }
    }

    /**
     * 将时间戳转换成hh:MM格式
     */
    public static String getTime2DiffContent(Long time) {
        if (System.currentTimeMillis() - time < day) {
            Date date = new Date(time);
            String res = YEAR_FORMAT5.format(date);
            String[] times = res.split(" ");
            String res1 = times[1];
            int index = res1.lastIndexOf(":");
            return res1.substring(0, index);
        } else {
            long diff = (getCurrentDayEndTime() - time) / day;
            return diff + "天前";
        }
    }
}
