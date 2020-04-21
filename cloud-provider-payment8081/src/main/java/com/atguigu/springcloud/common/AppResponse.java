package com.atguigu.springcloud.common;

import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * 接口返回值定义
 *
 * @author egoo
 * @date 2019/3/11
 */
@Data
public class AppResponse {
	private Integer code;
	private String status;
	private String message;
	private Object data;
	private Long total;
	private Integer currentPage;
	private Integer totalPage;
	public AppResponse() {
		init200Response();
	}

	public void init200Response(){
		this.setCode(HttpStatus.OK.value());
		this.setStatus("success");
	}

	public void init404Response() {
		this.setCode(HttpStatus.NOT_FOUND.value());
		this.setStatus("fail");
	}

	public void init50XResponse() {
		this.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		this.setStatus("error");
		this.setMessage("服务器错误");
	}

	public void init203Response() {
		this.setCode(203);
		this.setStatus("invalid params");
	}

	public void initPageData(PageInfo<Map<String, Object>> tPageInfo){
        this.setData(tPageInfo.getList());
		this.setTotal(tPageInfo.getTotal());
		this.setTotalPage(tPageInfo.getPages());
		this.setCurrentPage(tPageInfo.getPageNum());
	}
}
