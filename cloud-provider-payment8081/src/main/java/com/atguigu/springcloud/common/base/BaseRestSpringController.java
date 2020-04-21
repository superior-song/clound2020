package com.atguigu.springcloud.common.base;

import com.atguigu.springcloud.entity.vo.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: BaseRestSpringController.java</p>
 * <p>Description:通用的BaseRestSpringController</p>
 * <p>Copyright: Copyright (c) 2019</p>
 * @author songxiaoliang
 * @date 2019-7-10 12:05:22
 **/
@Slf4j
public abstract class BaseRestSpringController {


	@Value("${egooapi.uploadpath}")
	private String UPLOAD_PATH;

	/**
	 * 获取当前登录用户
	 * @param request
	 * @return
	 */
	public LoginUser getCurrentLoginUser(HttpServletRequest request){
		return null;
	}
}
