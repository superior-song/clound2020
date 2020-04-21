package com.atguigu.springcloud.support;

import com.alibaba.druid.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>Title: DateUtil.java</p>
 * <p>Description:通用的日历工具类</p>
 * <p>Copyright: Copyright (c) 2019</p>
 * @author songxiaoliang
 * @date 2019-7-12 12:05:22
 **/
@Slf4j
public class DateUtil {

	private static  SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyyMMdd");
	private static  SimpleDateFormat simpleFormat2 = new SimpleDateFormat("yyyyMMddHHmmss");
	private static  SimpleDateFormat simpleFormat3 = new SimpleDateFormat("yyyy-MM-dd");
	private static  SimpleDateFormat simpleFormat4 = new SimpleDateFormat("yyyy-MM");
	private static  SimpleDateFormat dateSimpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static  SimpleDateFormat dateSimpleFormatChange = new SimpleDateFormat("HH:mm:ss");
	private static  SimpleDateFormat dateSFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static  DateFormat dateFormat = new SimpleDateFormat("HHmmss");
	private static  SimpleDateFormat simpleFormaYear = new SimpleDateFormat("yyyy");

	/**
	 * 以指定的格式来格式化日期
	 *
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String formatDateByFormat(Date date, String format) {
		String result = "";
		if (date != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				result = sdf.format(date);
			} catch (Exception ex) {
				// Logger.info("date:" + date);
				ex.printStackTrace();
			}
		}
		return result;
	}

	//时间戳格式转换
	public static Long changeTimeFormat(Long time){
        //如果时间是 59:59
		String forTime = "23:59:59";
		String date = dateSimpleFormatChange.format(time);
	    if(date.equals(forTime)){
			time = time +1;
		}
		return time;
	}

	public static Boolean isCross(Long endTime){
		String forTime = "00:00:00";
		String date = dateSimpleFormatChange.format(endTime);
		if(date.equals(forTime)){
			return true;
		}
		return false;
	}



	//判断当前时间是否是月初
	public static Boolean isMonthFinalDay(Date date){
		//
		Calendar cale = Calendar.getInstance();
		cale.add(Calendar.MONTH, 0);
		cale.set(Calendar.DAY_OF_MONTH, 1);
		String lastday = simpleFormat3.format(cale.getTime());
		System.out.print(lastday);
		System.out.println(formatDate(date));
        if(lastday.equals(formatDate(date))){
        	return  true;
		}
		return false;
	}

	//判断当天时间是否跨天
	public static boolean checkIsCrossDay(String startTime,String endTime){
          try {
		    if(!StringUtils.isEmpty(startTime)&&!StringUtils.isEmpty(endTime)){
		    	Long stTime = dateSimpleFormat.parse(startTime).getTime();
		    	Long edTime = dateSimpleFormat.parse(endTime).getTime();
			   if(!(simpleFormat3.format(stTime)).equals(simpleFormat3.format(edTime))){
				   return true;
			   }
		   }
	   }catch (Exception e){
       	e.printStackTrace();
	   }
     return false;
	}


	//判断两个时间是否跨年
	public static boolean checkIsCrossYear(Long startTime,Long endTime){
		try {
			if(!(simpleFormaYear.format(startTime)).equals(simpleFormaYear.format(endTime))){
				return true;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}

	//判断当天时间是否跨天
	public static boolean checkIsCrossDay(Long startTime,Long endTime){
		try {
			if(null!=startTime&&null!=endTime){
				if(!(simpleFormat3.format(startTime)).equals(simpleFormat3.format(endTime))){
					return true;
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 以指定的格式来格式化日期
	 *
	 * @param format
	 * @return String
	 */
	public static String formatObject(Object object, String format) {
		String result = "";
		if (object != null) {
			try {
				Date date = dateSimpleFormat.parse(object.toString());
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				result = sdf.format(date);
			} catch (Exception ex) {
				// Logger.info("date:" + date);
				ex.printStackTrace();
			}
		}
		return result;
	}

	 //讲da
	 public static String  getDate(Date date) {
	   return dateSimpleFormat.format(date);
		}
	/**
	 * 常用的格式化日期
	 * 
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String formatDate(Date date) {
		return formatDateByFormat(date, "yyyy-MM-dd");
	}

	/**
	 * 格式化日期，传入格式：yyyymmdd，输出格式yyyy年mm月dd日
	 * 
	 * @param indate
	 * @return
	 */
	public static String formatDateSelf(String indate) {
		String year;
		String month;
		String day;
		if ((null == indate) || ("".equals(indate))) {
			return "";
		} else {
			year = indate.substring(0, 4);
			month = indate.substring(4, 6);
			day = indate.substring(6, 8);
			return year + "年" + month + "月" + day + "日";
		}
	}

	/**
	 * 格式化时间，传入格式：hhmmss，输出格式hh点mm分
	 * 
	 * @param intime
	 * @return
	 */
	public static String formatTimeSelf(String intime) {
		String hour;
		String min;
		if ((null == intime) || ("".equals(intime))) {
			return "";
		} else {
			hour = intime.substring(0, 2);
			min = intime.substring(2, 4);
			// String sec = time.substring(4,2);
			return hour + "点" + min + "分";
		}

	}

	/**
	 * 返回小时，输入格式hhmmss，输出格式hh
	 * 
	 * @param time
	 * @return
	 */
	public static String getHour(String time) {
		if ((null == time) || ("".equals(time))) {
			return "00";
		} else {
			return time.substring(0, 2);
		}
	}

	/**
	 * 返回分钟，输入格式hhmmss，输出格式mm
	 * 
	 * @param time
	 * @return
	 */
	public static String getMin(String time) {
		if ((null == time) || ("".equals(time))) {
			return "00";
		} else {
			return time.substring(2, 4);
		}
	}

	/**
	 * 格式化日期，输入格式yyyy-mm-dd,输出格式yyyymmdd
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate2(String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date d = sdf.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(d.getTime());
			sdf = new SimpleDateFormat("yyyyMMdd");
			return sdf.format(calendar.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return date;
		}
	}

	/**
	 * 格式化日期，输入格式yyyymmdd,输出格式yyyy-mm-dd
	 *
	 * @param date
	 * @return
	 */
	public static String formatDate3(String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date d = sdf.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(d.getTime());
			sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(calendar.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return date;
		}
	}

	public static String formatDate3(Date date) {
		return formatDateByFormat(date, "yyyy-MM-dd");
	}

	public static String formatDate4(Date date) {
		return formatDateByFormat(date, "yyyy-MM-dd HH:mm");
	}

	/**
	 * 返回年份
	 *
	 * @param date 日期
	 * @return 返回年份
	 */
	public static int getYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	/**
	 * 返回月份
	 *
	 * @param date 日期
	 * @param diff 偏移量
	 * @return 返回月份
	 */
	public static int getMonth(Date date,int diff) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH,diff);
		return c.get(Calendar.MONTH) + 1;
	}
	public static String getYearMonth(Date date,int diff) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH,diff);
		return  new SimpleDateFormat("yyyy-MM").format(c.getTime());
	}
	/**
	 * 返回年月
	 *
	 * @param date 日期 yyyy-MM-dd
	 * @return 返回年月 201912
	 */
	public static String getYearMonth(String date) {
		return date.replace("-","").substring(0,6);
	}

	/**
	 * 返回年月
	 *
	 * @param date 日期 yyyy-MM-dd
	 * @return 返回年月 2019-12
	 */
	public static String getYearMonthAnd(String date) {
		String dateTime = date.replace("-","").substring(0,6);
		dateTime = dateTime .substring(0,4)+"-"+dateTime .substring(4,6);
		return dateTime;
	}

	/**
	 * 返回日份
	 *
	 * @param date 日期
	 * @return 返回日份
	 */
	public static int getDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	//获取当年的年初时间
	public static Long getYearStartTime(Long timeStamp) {
		Calendar calendar = Calendar.getInstance();// 获取当前日期
		calendar.setTimeInMillis(timeStamp);
		calendar.add(Calendar.YEAR, 0);
		calendar.add(Calendar.DATE, 0);
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_YEAR, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	/**
	 * 获取当年的最后时间戳
	 *
	 * @param timeStamp 毫秒级时间戳
	 * @return
	 */
	public static Long getYearEndTime(Long timeStamp) {
		Calendar calendar = Calendar.getInstance();// 获取当前日期
		calendar.setTimeInMillis(timeStamp);
		int year = calendar.get(Calendar.YEAR);
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		return calendar.getTimeInMillis();
	}

	/**
	 * 返回小时
	 *
	 * @param date 日期
	 * @return 返回小时
	 */
	public static int getHour(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 根据输入的字符串格式化时间，返回格式hh:mm:ss 传入格式：hhmmss
	 *
	 * @param time
	 * @return
	 */
	public static String reFormatTime(String time) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
			Date d = sdf.parse(time);
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(d.getTime());
			sdf = new SimpleDateFormat("HH:mm:ss");
			return sdf.format(calendar.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return time;
		}

	}

	/**
	 * 返回分钟
	 * 
	 * @param date 日期
	 * @return 返回分钟
	 */
	public static int getMinute(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MINUTE);
	}

	/**
	 * 返回秒钟
	 * 
	 * @param date 日期
	 * @return 返回秒钟
	 */
	public static int getSecond(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.SECOND);
	}

	/**
	 * 返回毫秒
	 * 
	 * @param date 日期
	 * @return 返回毫秒
	 */
	public static long getMillis(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	/**
	 * 日期相加
	 * 
	 * @param date 日期
	 * @param day 天数
	 * @return 返回相加后的日期
	 */
	public static Date addDate(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}

	public static String addDate2(String date,int type, int day) {
		Calendar c = Calendar.getInstance();
		c.setTime(getStrToDate(date));
		c.add(type, +day);
		return simpleFormat3.format(c.getTime());
	}

	/*
	 * 分钟相加，输入格式hhmmss,输出格式hhmmss
	 */
	public static String addTime(String time, int min) {
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		Date begin = null;
		String outTime = null;
		try {
			begin = sdf.parse(time);
			Calendar c = Calendar.getInstance();
			c.setTime(begin);
			c.add(Calendar.MINUTE, min);
			outTime = sdf.format(c.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		// String nowTime = sdf.format(new Date());
		return outTime;
	}

	/**
	 * 日期相减
	 * 
	 * @param date 日期
	 * @param date1 日期
	 * @return 返回相减后的日期
	 */
	public static int diffDate(Date date, Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
	}

	/**
	 * 返回当前日期，格式为2005-01-01
	 * 
	 * @return
	 */
	public static String getNowDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowTime = sdf.format(new Date());
		return nowTime;
	}
	/**
	 * 返回当前日期，格式为2005-01-01
	 *
	 * @return
	 */
	public static String getNowDate1() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String nowTime = sdf.format(new Date());
		return nowTime;
	}

	/**
	 * 返回当前日期，格式为20050101
	 * 
	 * @return
	 */
	public static String getNowDate2() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String nowTime = sdf.format(new Date());
		return nowTime;
	}

	/**
	 * 返回当前时间，格式：hhmmss
	 * 
	 * @return
	 */
	public static String getNowTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		String nowTime = sdf.format(new Date());
		return nowTime;
	}

	/**
	 * 返回当前时间，格式：hhmm
	 * 
	 * @return
	 */
	public static String getNowTime2() {
		SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
		String nowTime = sdf.format(new Date());
		return nowTime;
	}

	/**
	 * 返回当前时间，格式：yyyyMMddHH
	 * 
	 * @return
	 */
	public static String getNowTime3() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
		String nowTime = sdf.format(new Date());
		return nowTime;
	}

	/**
	 * 得到两个日期的日期差
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static long diffDate(String beginDate, String endDate) {
		Date bDate = null;
		Date eDate = null;
		try {
			bDate = simpleFormat.parse(beginDate);
			eDate = simpleFormat.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar t1 = Calendar.getInstance();
		Calendar t2 = Calendar.getInstance();
		t1.setTime(bDate);
		t2.setTime(eDate);
		long diff = (t2.getTimeInMillis() - t1.getTimeInMillis()) / (1000 * 60 * 60 * 24);

		return diff;
	}

	/**
	 * 得到时间差，时间传入格式：20060101101010（年月日时分秒）yyyymmddhhmmss
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static long diffDate2(String beginDate, String endDate) {
		Date bDate = null;
		Date eDate = null;
		long time = 000000000;
		try {
			bDate = simpleFormat2.parse(beginDate);
			eDate = simpleFormat2.parse(endDate);
			time = eDate.getTime() - bDate.getTime();
			return time;
		} catch (ParseException e) {
			e.printStackTrace();
			return time;
		}
	}

	/**
	 * 传入参数格式为：hhmmss 比较两个时间的差，返回值为分钟
	 * 
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static long timeDiff1(String beginTime, String endTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		Date begin = null;
		Date end = null;
		try {
			begin = sdf.parse(beginTime);
			end = sdf.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long times = (end.getTime() - begin.getTime()) / (1000 * 60);
		return times;
	}

	/**
	 * 返回两个时间差，返回值为分钟 传入时间格式为hhmm
	 * 
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static long timeDiff2(String beginTime, String endTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		Date begin = null;
		Date end = null;
		try {
			begin = sdf.parse(beginTime + "00");
			end = sdf.parse(endTime + "00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long times = (end.getTime() - begin.getTime()) / (1000 * 60);
		return times;
	}

	public static Date getStrToDate(String strDate) {
		Date outDate = null;
		try {
			outDate = simpleFormat3.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return outDate;
	}

	/**
	 * 返回当前日期的后一个月的日期，格式：2005-01-01
	 * 
	 * @return
	 */
	public static String getDateLaterMonth() {
		Date nowDate;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(new Date()) - ((long) 30) * 24 * 3600 * 1000);
		nowDate = c.getTime();
		String nowTime = sdf.format(nowDate);
		return nowTime;

	}

	/**
	 * 根据某一个日期得到该日期属于该年的第几周 传入参数格式：yyyy-mm-dd
	 * 输出年份+周次  201929
	 * 
	 * @param date
	 * @return
	 */
	public static String getWeek(String date) {
		String weekstr = "";
		Date myDate = null;
		try {
			myDate = simpleFormat3.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		// 设置一周的第一天是星期一（默认是周末）
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(myDate);
		int week = c.get(Calendar.WEEK_OF_YEAR);
		if(week < 10){
			weekstr = "0" + week;
		}else{
			weekstr = "" + week;
		}
		c.add(Calendar.DAY_OF_MONTH, -7);
		int year = c.get(Calendar.YEAR);
		if(week<c.get(Calendar.WEEK_OF_YEAR)){
			year+=1;
		}
		return year + weekstr;
	}

	/**
	 * 将date类型数据转为String
	 * @param date
	 * @return
	 */
	public static String getStringDate(Date date) {
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		  String dateString = formatter.format(date);
	     return dateString;
		 }
	/**
	 * 根据年月判断当前月的日期天数
	 * 
	 * @param s
	 *            String 格式为 yyyy-MM的日期字符串
	 * @return int 当月天数
	 * @throws ParseException
	 */
	public static int getDaysByMonth(String s) {
		int dayNum = 0;
		switch (Integer.parseInt(s.substring(5, 7))) {
		case 2:
			int year = Integer.parseInt(s.substring(0, 4));
			if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
				dayNum = 29;
			} else {
				dayNum = 28;
			}
			break;
		case 4:
			dayNum = 30;
			break;
		case 6:
			dayNum = 30;
			break;
		case 9:
			dayNum = 30;
			break;
		case 11:
			dayNum = 30;
			break;
		default:
			dayNum = 31;
			break;
		}
		return dayNum;
	}

	/**
	 * 根据日期字符串取得当前为星期几 传入日期格式为yyyymmdd
	 * 
	 * @param strdate
	 * @return
	 */
	public static int getWeekByDate2(String strdate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = dateFormat.parse(strdate);
		} catch (ParseException e) {

		}
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		int mydate = cd.get(Calendar.DAY_OF_WEEK);
		return mydate;
	}

	/**
	 * 根据日期字符串取得当前为星期几，1为星期一，...7为星期日 传入日期格式为yyyymmdd
	 * 
	 * @param strdate
	 * @return
	 */
	public static String getWeekByDate(String strdate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = dateFormat.parse(strdate);
		} catch (ParseException e) {

		}
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		int mydate = cd.get(Calendar.DAY_OF_WEEK);
		String showDate = "";
		switch (mydate) {
		case 1:
			showDate = "日";
			break;
		case 2:
			showDate = "一";
			break;
		case 3:
			showDate = "二";
			break;
		case 4:
			showDate = "三";
			break;
		case 5:
			showDate = "四";
			break;
		case 6:
			showDate = "五";
			break;
		default:
			showDate = "六";
			break;
		}

		return showDate;
	}

	public static String getCheckSign(int i) {
		String sign = "";
		switch (i) {
		case 0:
			sign = "早";
			break;
		case 1:
			sign = "上";
			break;
		case 2:
			sign = "下";
			break;
		case 3:
			sign = "晚";
			break;
		default:
			break;
		}
		return sign;
	}

	public static String getCheckSign2(int i) {
		String sign = "";
		switch (i) {
		case 0:
			sign = "mo-";
			break;
		case 1:
			sign = "am-";
			break;
		case 2:
			sign = "pm-";
			break;
		case 3:
			sign = "ni-";
			break;
		default:
			break;
		}
		return sign;
	}

	// 判断两个日期是否是同一周，根据一个给定的日期获得所属周的周一和周五的日期。
	public class ManageWeek {

		// 判断两个日期是否在同一周
		boolean isSameWeekDates(Date date1, Date date2) {
			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			cal1.setTime(date1);
			cal2.setTime(date2);
			int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
			if (0 == subYear) {
				if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
					return true;
				}
			} else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
				// 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
				if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
					return true;
				}
			} else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
				if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
					return true;
				}
			}
			return false;
		}

		// 产生周序列
		public String getSeqWeek() {
			Calendar c = Calendar.getInstance(Locale.CHINA);
			String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
			if (week.length() == 1) {
				week = "0" + week;
			}
			String year = Integer.toString(c.get(Calendar.YEAR));
			return year + week;

		}

		// 获得周一的日期
		public String getMonday(Date date) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		}

		// 获得周五的日期
		public String getFriday(Date date) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
			return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());

		}

	}

	// 根据周得到该周的开始日期和结束日期
	static public class WeekBeginEndDate {

		private static DateFormat formatter = new SimpleDateFormat("yyyyMMdd");

		public Date[] getStartEnd(int year, int weeknum) {
			/*
			 * 参数说明 int year 年分 例如 2005 int weeknum 第几周 例如33 返回一个Calendar数组，长度为2
			 * 分别是开始日期和结束日期
			 */
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, year);
			calendar.set(Calendar.WEEK_OF_YEAR, weeknum);
			int nw = calendar.get(Calendar.DAY_OF_WEEK);
			calendar.add(Calendar.DATE, 1 - nw);
			Date start = calendar.getTime();
			// end.add(Calendar.DATE, 7 - nw);
			calendar.add(Calendar.DATE, 6);
			Date end = calendar.getTime();
			Date[] darr = new Date[] { start, end };
			return darr;
		}

		public String getFullTimeStr(Date d) {
			String ret = "";
			try {
				ret = formatter.format(d);
			} catch (Exception ex) {
				// 其他错误处理
			}
			return ret;
		}

		public String weekBeginEnd(int year, int weeknum, String beginDate, String endDate) {
			String result = "";
			Date[] darr = getStartEnd(year, weeknum);
			if ((getFullTimeStr(darr[0]).compareTo(beginDate) < 0) && (getFullTimeStr(darr[1]).compareTo(endDate) > 0)) {
				result = beginDate + "-" + endDate;
			} else if ((getFullTimeStr(darr[0]).compareTo(beginDate) < 0) && (getFullTimeStr(darr[1]).compareTo(endDate) < 0)) {
				result = beginDate + "-" + getFullTimeStr(darr[1]);
			} else if ((getFullTimeStr(darr[0]).compareTo(beginDate) > 0) && (getFullTimeStr(darr[1]).compareTo(endDate) < 0)) {
				result = getFullTimeStr(darr[0]) + "-" + getFullTimeStr(darr[1]);
			} else if ((getFullTimeStr(darr[0]).compareTo(beginDate) > 0) && (getFullTimeStr(darr[1]).compareTo(endDate) > 0)) {
				result = getFullTimeStr(darr[0]) + "-" + endDate;
			}
			return result;
		}

	}

	/**
	 * 时间戳转换成日期字符串
	 * 
	 * @param timestamp
	 * @return
	 */
	public static String getDateByTimestamp(Long timestamp) {
		return dateSimpleFormat.format(timestamp);
	}

	/**
	 * 时间戳转换成时间的
	 *
	 * @param timestamp
	 * @return
	 */
/*	public static String getDateByTimestamp(Long timestamp) {
		return dateSimpleFormat.format(timestamp);
	}*/

	/**
	 * 时间戳转换成日期字符串
	 *
	 * @param timestamp
	 * @return
	 */
	public static String getDayTime(Long timestamp) {
		return dateSFormat.format(timestamp);
	}

	/**
	 * 时间戳转换成日期字符串
	 *
	 * @param timestamp
	 * @return
	 */
	public static String getOneDay(Long timestamp) {
		return simpleFormat3.format(timestamp);
	}

	/**
	 * 时间戳转换成日期字符串
	 *
	 * @param timestamp
	 * @return
	 */
	public static String changeDate(Long timestamp) {
		return simpleFormat4.format(timestamp);
	}

	/**
	 * 将指定的分钟数转换成小时与分钟
	 * 
	 * @param minutes
	 * @return
	 * @throws ParseException
	 */
	public static String getDuration(int minutes) {
		int h = minutes / 60;
		int m = minutes % 60;
		String result = "";
		if (h != 0) {
			result = h + "小时";
		}
		if (m != 0) {
			result = result + m + "分钟";
		}
		if (h == 0 && m == 0) {
			result = "0分钟";
		}
		return result;
	}

	/**
	 * 获得两个时间的间隔，单位为分钟
	 * 
	 * @param startTime
	 *            形式：HHmmss
	 * @param endTime
	 *            形式：HHmmss
	 * @return
	 * @throws ParseException
	 */
	public static int getMinutes(String startTime, String endTime) throws ParseException {
		Date startDate = dateFormat.parse(startTime);
		Date endDate = dateFormat.parse(endTime);
		long duration = endDate.getTime() - startDate.getTime();
		long minutes = duration / 60000;
		return Integer.parseInt(String.valueOf(minutes));
	}

	/**
	 * 将字符串类型的日期转换成Date类型
	 * 
	 * @param format
	 *            格式
	 * @param time
	 *            时间字符串
	 * @return
	 */
	public static Date parseString(String format, String time) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = dateFormat.parse(time);
		} catch (ParseException ex) {
			Logger.getLogger(DateUtil.class.getName()).log(Level.SEVERE, null, ex);
		}
		return date;
	}

	/**
	 * 获得当前时间在指定天数以前的时间
	 * 
	 * @param days
	 * @return
	 */
	public static Long getAnyDaysBefore(Long days) {

		Date nowdate = new Date();

		Long befordays = nowdate.getTime() - days * 24 * 60 * 60 * 1000;
		return befordays;

	}


	/**
	 * 获得当前时间在指定天数以前的零点
	 *
	 * @param days
	 * @return
	 */
	public static Long getAnyDaysBefore(int days) {

		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.DATE,c.get(Calendar.DATE) - days);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTimeInMillis();

	}

	/**
	 * 产生10位时间戳
	 * 
	 * @return
	 */
	public static String getTimestamp() {
		Date nowdate = new Date();
		return String.valueOf(nowdate.getTime()).substring(0, 10);
	}

	public static Date getStrToDate(String pstr, String strDate) {
		Date outDate = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pstr);
			outDate = sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return outDate;
	}
	
	
	
	
	   public  static List<Date> getNminList(Date date, int n) {
	        Date start = dayStartDate(date);//转换为天的起始date
	        Date nextDayDate = nextDay(start);//下一天的date
	         
	        List<Date> result = new ArrayList<Date>();
	        while (start.compareTo(nextDayDate) < 0) {
	            result.add(start);
	            //日期加n分钟
	            start = addNMin(start, n);
	        }
	         
	        return result;
	    }
	 
	    private static Date addNMin(Date start, int offset) {
	        Calendar c = Calendar.getInstance();
	        c.setTime(start);
	        c.add(Calendar.MINUTE, offset);
	        return c.getTime();
	    }
	 
	    private static Date nextDay(Date start) {
	        Calendar c = Calendar.getInstance();
	        c.setTime(start);
	        c.add(Calendar.DATE, 1);
	        return c.getTime();
	    }
	 
	    private static Date dayStartDate(Date date) {
	        Calendar c = Calendar.getInstance();
	        c.setTime(date);
	        c.set(Calendar.HOUR_OF_DAY, 0);
	        c.set(Calendar.MINUTE, 0);
	        c.set(Calendar.SECOND, 0);
	        c.set(Calendar.MILLISECOND, 0);
	        return c.getTime();
	    }
	    
	    /**
	     * 根据年份获取天数
	     */
	    public static Long getDaysByYear(Long year){
	    	if(year != null){
	    		if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0){
	    		    return 366L;
	    		}else{
	    			return 365L;
	    		}
	    	}
	    	return null;
	    }
	    
	    /**
	     * 把long 转换成 日期 再转换成String类型
	     */
	    public static String transferLong2Date(String dateFormat, Long millSec) {
	    	if(null == millSec)
	    		return "";
	        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
	        Date date = new Date(millSec);
	        return sdf.format(date);
	    }
	    /**
	     * 获取上个月
	     * @return
	     */
	    public static String getLastMonth(){
	    	  Calendar c = Calendar.getInstance();
	    	  c.add(Calendar.MONTH, -1);
	    	  SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM");
	    	  String time = format.format(c.getTime());
	    	  return time;
	    }

		/**
		 * 获取下个月
		 * @return
		 */
		public static String getNextMonth(){
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, 1);
			SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
			String time = format.format(c.getTime());
			return time;
		}

	    /** 
	     * 当前年的开始时间 
	     * 
	     * @return 
	     */  
	    public static Date getYearStartTime(String year) {
	        Calendar c = Calendar.getInstance();
	        Date now = null;
	        try {  
	            c.set(Calendar.YEAR, Integer.parseInt(year));
	            c.set(Calendar.MONTH, 0);
	            c.set(Calendar.DATE, 1);
	            now = simpleFormat.parse(simpleFormat.format(c.getTime()));  
	        } catch (Exception e) {
	            e.printStackTrace();  
	        }  
	        return now;  
	    }  
	  
	    /** 
	     * 当前年的结束时间 
	     * 
	     * @return 
	     */  
	    public static Date getYearEndTime(String year) {
	        Calendar c = Calendar.getInstance();
	        Date now = null;
	        try {  
	            c.set(Calendar.YEAR, Integer.parseInt(year));
	            c.set(Calendar.MONTH, 11);
	            c.set(Calendar.DATE, 31);
	            now = dateSimpleFormat.parse(dateSimpleFormat.format(c.getTime()) + " 23:59:59");  
	        } catch (Exception e) {
	            e.printStackTrace();  
	        }  
	        return now;  
	    }  
	    
		public static Long getWorkdaysByMonth(String year, String month){
			 Long workdays=0L;
			 Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, Integer.parseInt(year));
				cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
				cal.set(Calendar.DATE, 1);
		        while(cal.get(Calendar.MONTH) < Integer.parseInt(month)&&cal.get(Calendar.YEAR)== Integer.parseInt(year)){
		        	int day = cal.get(Calendar.DAY_OF_WEEK);
					if(!(day == Calendar.SUNDAY || day == Calendar.SATURDAY)){
						workdays++;
					}
					cal.add(Calendar.DATE, 1);
		        }
			
			return workdays;
		}


	public static Date geLastWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday(date));
		cal.add(Calendar.DATE, -7);
		return cal.getTime();
	}

	public static Date getThisWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 获得当前日期是一个星期的第几天
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		return cal.getTime();
	}

	public static Date getNextWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday(date));
		cal.add(Calendar.DATE, 7);
		return cal.getTime();
	}
	//返回当天下周一某个整点时间戳 13位
	public static Long getNextWeekMondayMillis(Date date, int num) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday(date));
		cal.add(Calendar.DATE, 7);
		cal.set(Calendar.HOUR_OF_DAY, num);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}
	//返回传入时间某个整点时间戳 13位
	public static Long getPointMillis(Date date, int num) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, num);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}
	//返回当天某个整点时间戳 13位
	public static Long getMorning(int num) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, num);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}
	/**
	 * 获取日期的O点时间
	 * @param date
	 * @return
	 */
	public static Date todayFirstDate(Long date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	/**
	 * 获取日期的23点时间
	 * @param date
	 * @return
	 */
	public static Date todayLastDate(Long date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}
	/**
	 *获取本周起止时间（中国习惯：周一是一周的开始）
	 * @param timeInMillis
	 * @return
	 */
	public static Date[] getGapStartEnd(Long timeInMillis, Integer gapDay){
		Date[] weekStartEnd = new Date[2];
		if(gapDay>0){
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(timeInMillis);
			Calendar cal=(Calendar)calendar.clone();
			cal.add(Calendar.DATE,1-gapDay);
			Date date1=cal.getTime();
			cal=(Calendar)calendar.clone();
			cal.add(Calendar.DATE,gapDay-1);
			Date date2=cal.getTime();
			weekStartEnd[0]=todayFirstDate(date1.getTime());
			weekStartEnd[1]=todayLastDate(date2.getTime());
//			SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String str1=f.format(weekStartEnd[0]);
//			String str2=f.format(weekStartEnd[1]);
//			System.out.println("date1="+str1+" date2="+str2);
		}
		return weekStartEnd;
	}
	/**
	 *获取本周起止时间（中国习惯：周一是一周的开始）
	 * @param timeInMillis
	 * @return
	 */
	public static Date[] getWeekStartEnd(Long timeInMillis){
		Calendar ca= Calendar.getInstance();
		ca.setTimeInMillis(timeInMillis);
		int dayOfWeek = ca.get(Calendar.DAY_OF_WEEK);
		//中国习惯：周一是一周的开始
		if (dayOfWeek==1) {
			dayOfWeek=7;
		}else{
			dayOfWeek--;
		}
		Calendar cal=(Calendar)ca.clone();
		cal.add(Calendar.DATE,1-dayOfWeek);
		Date date1=cal.getTime();
		cal=(Calendar)ca.clone();
		cal.add(Calendar.DATE,7-dayOfWeek);
		Date date2=cal.getTime();
		Date[] weekStartEnd = new Date[2];
		weekStartEnd[0]=todayFirstDate(date1.getTime());
		weekStartEnd[1]=todayLastDate(date2.getTime());
		return weekStartEnd;
	}
	//时间字符串转为时间戳
	public static long convert2long(String date) {
		try {
			return dateSimpleFormat.parse(date).getTime();
		} catch (ParseException var3) {
			var3.printStackTrace();
		}
		return 0L;
	}
	//时间字符串转为时间戳
	public static long convert2long(String date,String format) {
		try {
			SimpleDateFormat sf = new SimpleDateFormat(format);
			return sf.parse(date).getTime();
		} catch (ParseException var3) {
			var3.printStackTrace();
		}
		return 0L;
	}
	//计算时间戳之差 获取小时数 即
	public static Double getHour(Long startTime,Long endTime){
        Double hour =0.0;
		Long yshu  = endTime-startTime;
		Double d = yshu.doubleValue();
		hour = d/1000/3600;
		hour = (double) Math.round(hour * 100) / 100;
		return hour;
	}
	//时间字符串转为时间戳
	public static long convertlongDate(String date,String format) {
		try {
			SimpleDateFormat sf = new SimpleDateFormat(format);
			return sf.parse(date).getTime();
		} catch (ParseException var3) {
			var3.printStackTrace();
		}
		return 0L;
	}

	/**根据日期的当前年月获取月初的时间戳*/
	public static Long getMonthBeginEnd(String  date,int type) throws ParseException {
        //type 为0时 当前月份的月初  1时 下月的月初    date格式2018-12-15
		long time = DateUtil.convertlongDate(date,"yyyy-MM-dd");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
     	Date dateD = new Date(time);
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateD);
		cal.add(Calendar.MONTH, type);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		String firstday = format.format(cal.getTime());
		return convert2long(firstday);
	}

	/**根据日期的当前月份的时间以及上一月份的时间戳*/
	public static Long getMonthTime(Long time,int type){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		Date dateD = new Date(time);
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateD);
		cal.add(Calendar.MONTH, type);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		String firstday = format.format(cal.getTime());
		return convert2long(firstday);
	}
	public static String getLastMonthMM(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		SimpleDateFormat format =  new SimpleDateFormat("yyyyMM");
		String time = format.format(c.getTime());
		return time;
	}

	/*当前日期加天数*/
	public static String plusDay(int num) {
		Date d = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String currdate = format.format(d);
		System.out.println("现在的日期是：" + currdate);
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的
		d = ca.getTime();
		String enddate = format.format(d);
		System.out.println("增加天数以后的日期：" + enddate);
		return enddate;
	}

	/*输入日期返回该日期的周一以及周末时间*/
	public static String weekBeginAndEnd(String date,int type) throws ParseException {
        String enddate = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateTiem=sdf.parse(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateTiem);
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if(dayWeek==1){
			dayWeek = 8;
		}
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		Date mondayDate = cal.getTime();
		String weekBegin = sdf.format(mondayDate);
		cal.add(Calendar.DATE, 4 +cal.getFirstDayOfWeek());
		Date sundayDate = cal.getTime();
		String weekEnd = sdf.format(sundayDate);
		if(type==1){
			//周一
			return weekBegin;
		}else{
			//周日
			return weekEnd;
		}
	}

	public static void main(String[] args) throws ParseException {
//	System.out.println(transferLong2Date("HH:mm:ss",1565798399999L));
//     System.out.print(getMonthTime(System.currentTimeMillis(),0));
//	 System.out.print(getMonthTime(System.currentTimeMillis(),-1));
		//isMonthFinalDay(new Date());
		weekBeginAndEnd("2019-01-01",1);
		//isMonthFinalDay(new Date());
		Long a = 1000L;
		Long b =20000L;
		Double aa = (a.doubleValue()/b.doubleValue())*1000;
		System.out.print(aa);
//		weekBeginAndEnd("2019-01-01",1);
//		//isMonthFinalDay(new Date());
//		System.out.println(getNextMonth());
		System.out.println(String.valueOf(0));


		System.out.print(getYearEndTime(System.currentTimeMillis()));
	}
}
