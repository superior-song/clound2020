package com.atguigu.springcloud.entity.common;

import lombok.Data;

/**
 * @author: baojunjie
 * @date: 2019/8/2 2:05 PM
 * @desc: 日期信息
 */
@Data
public class DateVo {

	/**日期 yyyy-MM-dd*/
	private String date;

	/**星期*/
	private String weekDay;
}
