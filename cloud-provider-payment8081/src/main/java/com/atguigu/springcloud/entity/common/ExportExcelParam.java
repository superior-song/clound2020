package com.atguigu.springcloud.entity.common;

import lombok.Data;

import java.util.Map;

/**
 * @author: baojunjie
 * @date: 2019/8/2 1:29 PM
 * @desc: 导出excel参数
 */
@Data
public class ExportExcelParam {

	private Integer type;
	private Map<String,Object> param;
}
