package com.atguigu.springcloud.support;

import com.alibaba.fastjson.JSON;
import org.springframework.context.MessageSource;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * @author wq
 * @date 2019/5/23 11:02
 * @desc 通用的utils
 **/
public class CommonUtils {

	private static String[] parsePatterns = {
			"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
			"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
			"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

	/**
	 * @Author baojunjie
	 * @Description double转成list
	 * @Date 10:25 AM 2019/7/27
	 * @Param [values]
	 * @return java.util.List<java.lang.Double>
	 **/
	public static List<Double> asList(Double... values){
		List<Double> list = new ArrayList<>();
		if (isNotNull(values)){
			Double[] doubleValue= values;
			for (int i=0;i<doubleValue.length;i++){
				list.add(doubleValue[i]);
			}
		}
		return list;
	}

	/**
	 * @Author baojunjie
	 * @Description double转成list
	 * @Date 10:25 AM 2019/7/27
	 * @Param [values]
	 * @return java.util.List<java.lang.Double>
	 **/
	public static List<String> asList(String... values){
		List<String> list = new ArrayList<>();
		if (isNotNull(values)){
			String[] stringValue= values;
			for (int i=0;i<stringValue.length;i++){
				list.add(stringValue[i]);
			}
		}
		return list;
	}

	/**
	 * @Author baojunjie
	 * @Description int转成list
	 * @Date 10:25 AM 2019/7/27
	 * @Param [values]
	 * @return java.util.List<java.lang.Double>
	 **/
	public static List<Integer> asList(Integer... values){
		List<Integer> list = new ArrayList<>();
		if (isNotNull(values)){
			Integer[] intValue= values;
			for (int i=0;i<intValue.length;i++){
				list.add(intValue[i]);
			}
		}
		return list;
	}


	/**
	 * 判断一个对象是否非空
	 *
	 * @param object Object
	 * @return true：非空 false：空
	 */
	public static boolean isNotNull(Object object) {
		return !isNull(object);
	}

	/**
	 * 判断一个对象是否为空
	 *
	 * @param object Object
	 * @return true：为空 false：非空
	 */
	public static boolean isNull(Object object) {
		return object == null;
	}

	/**
	 * @Author baojunjie
	 * @Description string验证是空
	 * @Date 8:51 AM 2018/12/18
	 * @Param [str]
	 * @return boolean
	 **/
	public final static boolean isNull(String str){
		return str == null || "".equals(str.trim()) || "null".equals(str.toLowerCase());
	}

	/**
	 * @Author baojunjie
	 * @Description String验证非空
	 * @Date 8:51 AM 2018/12/18
	 * @Param [str]
	 * @return boolean
	 **/
	public final static boolean isNotNull(String str){
		return !isNull(str);
	}

	/**
	 * @Author baojunjie
	 * @Description Object[]验证是空
	 * @Date 8:49 AM 2018/12/18
	 * @Param [objs]
	 * @return boolean
	 **/
	public final static boolean isNull(Object[] objs){
		if(objs==null||objs.length==0) return true;
		return false;
	}

	/**
	 * @Author baojunjie
	 * @Description Object[]验证非空
	 * @Date 8:48 AM 2018/12/18
	 * @Param [objs]
	 * @return boolean
	 **/
	public final static boolean isNotNull(Object[] objs){
		return !isNull(objs);
	}

	/**
	 * @Author baojunjie
	 * @Description Collection验证是空
	 * @Date 8:51 AM 2018/12/18
	 * @Param [collection]
	 * @return boolean
	 **/
	public final static boolean isNull(Collection collection){
		if(collection==null||collection.size()==0) return true;
		return false;
	}

	/**
	 * @Author baojunjie
	 * @Description Collection验证非空
	 * @Date 8:51 AM 2018/12/18
	 * @Param [collection]
	 * @return boolean
	 **/
	public final static boolean isNotNull(Collection collection){
		return !isNull(collection);
	}

	/**
	 * 将对象的日期转换成一个匹配的格式
	 *
	 * @param str 日期对象
	 * @return 匹配的日期
	 */
	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return org.apache.commons.lang3.time.DateUtils.parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 根据消息键和参数 获取消息 委托给spring messageSource
	 *
	 * @param code 消息键
	 * @param args 参数
	 * @return
	 */
	public static String message(String code, Object... args) {
		MessageSource messageSource = SpringUtils.getBean(MessageSource.class);
		return messageSource.getMessage(code, args, null);
	}

	/**
	 * @Author baojunjie
	 * @Description 对象转换
	 * @Date 7:26 PM 2019/7/18
	 * @Param [object, entityClass]
	 * @return T
	 **/
	public static <T> T convertBean(Object object, Class<T> entityClass) {
		if (null == object) {
			return null;
		}
		return JSON.parseObject(JSON.toJSONString(object), entityClass);

	}

	/**
	 * @Author baojunjie
	 * @Description 对象转map
	 * @Date 4:10 PM 2019/8/3
	 * @Param [obj]
	 * @return java.util.Map
	 **/
	public static Map ConvertObjToMap(Object obj){
		Map<String,Object> reMap = new HashMap<String,Object>();
		if (obj == null)
			return null;
		Field[] fields = obj.getClass().getDeclaredFields();
		try {
			for(int i=0;i<fields.length;i++){
				try {
					Field f = obj.getClass().getDeclaredField(fields[i].getName());
					f.setAccessible(true);
					Object o = f.get(obj);
					reMap.put(fields[i].getName(), o);
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return reMap;
	}


	/**
	 * @Author baojunjie
	 * @Description 保留小数点
	 * @Date 8:37 AM 2019/10/12
	 * @Param [dou, digits]
	 * @return java.lang.Double
	 **/
	public static Double doubleFormat(Double dou,int digits) {
		if (isNull(dou)){
			return null;
		}
		BigDecimal bg = new BigDecimal(dou);
		return bg.setScale(digits, BigDecimal.ROUND_HALF_UP).doubleValue();
	}


	public  static  void main(String args[]){
		System.out.println(doubleFormat(6.0, 1));

	}

}
