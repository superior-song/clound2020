package com.atguigu.springcloud.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author  wq
 * @date  2019/6/3 15:45
 *
 * @desc 用户封装分页返回信息
 **/
@Data
@AllArgsConstructor
public class ApiPageResponse{

	private Integer total;

	private Integer currPage;

	private Integer pageSize;

	private ApiResponse apiResponse;

}
