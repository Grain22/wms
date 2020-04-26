package tools.bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author laowu
 * @version 4/12/2019 11:26 AM
 */
public class DateUtils {

    static final String DATA_PATTERN = "yyyyMMdd";

    public static int getYear(Date date) {
        return getCalender(date).get(Calendar.YEAR);
    }

    public static int getMonth(Date date) {
        return getCalender(date).get(Calendar.MONTH) + 1;
    }

    public static int getDay(Date date) {
        return getCalender(date).get(Calendar.DAY_OF_MONTH);
    }

    public static int getCurrentYear() {
        return new GregorianCalendar().get(Calendar.YEAR);
    }

    public static int getCurrentMonth() {
        return new GregorianCalendar().get(Calendar.MONTH) + 1;
    }

    public static int getCurrentDay() {
        return new GregorianCalendar().get(Calendar.DAY_OF_MONTH);
    }

    public static int getCurrentHour() {
        return new GregorianCalendar().get(Calendar.HOUR_OF_DAY);
    }

    public static int getCurrentMinute() {
        return new GregorianCalendar().get(Calendar.MINUTE);
    }

    public static int getCurrentSecond() {
        return new GregorianCalendar().get(Calendar.SECOND);
    }

    public static int getCurrentMillisecond() {
        return new GregorianCalendar().get(Calendar.MILLISECOND);
    }

    /**
     * get year month string length 6
     *
     * @param year  *100
     * @param month *1
     * @return year+month
     */
    public static int getYearMonth6(Integer year, Integer month) {
        return year * 100 + month;
    }

    /**
     * get NOW time string
     *
     * @return yyyyMMdd
     */
    public static String getTimeString() {
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(new GregorianCalendar().getTime());
    }

    /**
     * get NOW time string
     *
     * @return yyyyMMddHHmmss
     */
    public static String getTimeStringFull() {
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(new GregorianCalendar().getTime());
    }

    /**
     * get NOW time string
     *
     * @return yyyyMMddHHmmssSSS
     */
    public static String getTimeStringLong() {
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return format.format(new GregorianCalendar().getTime());
    }

    /**
     * get gregorian calender
     *
     * @param date
     * @return GregorianCalendar
     */
    public static Calendar getCalender(Date date) {
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        return c;
    }

    /**
     * if before NOW
     * check min limit : all
     *
     * @param date
     * @return true/false
     */
    public static boolean ifBeforeNow(Date date) {
        return getCalender(date).before(new GregorianCalendar());
    }

    /**
     * if before NOW
     * check min limit : month
     *
     * @param year  compare year
     * @param month compare month
     * @return true false
     */
    public static boolean ifBeforeNow(Integer year, Integer month) {
        if (year < getCurrentYear()) {
            /*passed years*/
            return true;
        }
        if (year == getCurrentYear()) {
            /*this year*/
            return month < getCurrentMonth();
        }
        return false;
    }

    /**
     * delete elements after hour
     *
     * @param calendar date
     * @return hour = 0 ...
     */
    public static Calendar getSimpleDate(Calendar calendar) {
        return new GregorianCalendar(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * is same day
     *
     * @param date1 date limit : day
     * @param date2 date limit : day
     * @return true false
     */
    public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = getSimpleDate(getCalender(date1));
        Calendar cal2 = getSimpleDate(getCalender(date2));
        return cal1.compareTo(cal2) == 0;
    }

    /**
     * string to date
     *
     * @param dateStr date string
     * @param pattern format string
     * @return date can be null
     */
    public static Date parseDate(String dateStr, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * string to date
     *
     * @param dateStr yyyyMMdd
     * @return date
     */
    public static Date parseDate(String dateStr) {
        return parseDate(dateStr, DATA_PATTERN);
    }

    /**
     * days between
     *
     * @param d1 date limit day
     * @param d2 date limit day
     * @return int
     */
    public static Integer daysBetween(Date d1, Date d2) {
        Calendar c1 = new GregorianCalendar(getYear(d1), getMonth(d1) - 1, getDay(d1));
        Calendar c2 = new GregorianCalendar(getYear(d2), getMonth(d2) - 1, getDay(d2));
        int days = ((int) (c2.getTime().getTime() / 1000) - (int) (c1.getTime().getTime() / 1000)) / 3600 / 24;
        return Math.abs(days);

    }

    /**
     * max days this month
     *
     * @return int
     */
    public static int getMaxDaysThisMonth() {
        return getMaxDaysThisMonth(getCurrentYear(), getCurrentMonth());
    }

    /**
     * max days
     *
     * @param year  year
     * @param month month
     * @return int
     */
    public static int getMaxDaysThisMonth(int year, int month) {
        Calendar c = new GregorianCalendar(year, month - 1, 1);
        c.roll(Calendar.DATE, -1);
        int maxDate = c.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * is this year
     *
     * @param year year
     * @return true false
     */
    public static boolean isThisYear(int year) {
        return isThisMonth(year, getCurrentMonth());
    }

    /**
     * is this month
     *
     * @param year  year
     * @param month month
     * @return true false
     */
    public static boolean isThisMonth(int year, int month) {
        return isThisDay(year, month, getCurrentDay());
    }

    /**
     * is this day
     *
     * @param year  year
     * @param month month
     * @param day   day
     * @return true false
     */
    public static boolean isThisDay(int year, int month, int day) {
        return year == getCurrentYear() && month == getCurrentMonth() && day == getCurrentDay();
    }

    /**
     * month name lower
     *
     * @param month month 1-12
     * @return january ...
     */
    public static String getMonthNameLower(int month) {
        return Month.of(month).name().toLowerCase();
    }

    /**
     * month name
     *
     * @param month month 1-12
     * @return JANUARY
     */
    public static String getMonthName(int month) {
        return Month.of(month).name();
    }

    /**
     * format date
     *
     * @param date date
     * @return yyyyMMdd HHmmss
     */
    public static String formateDate(Date date) {
        return new SimpleDateFormat("yyyyMMdd HHmmss").format(date).toString();
    }

    /**
     * format date
     *
     * @param date   date
     * @param format like "yyyyMMdd HHmmss"
     * @return string
     */
    public static String formatDate(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }
}
