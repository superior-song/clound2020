package com.atguigu.springcloud.entity.login;

import lombok.Data;

/**
 * com.atguigu.springcloud.entity.login
 *
 * @author egoo
 * @date 2019/3/14
 */
@Data
public class UserDo {
	/**
	 * 用户登录唯一ID
	 */
	private String userId;

	/**
	 * 租户ID
	 */
	private String tenantId;

	/**
	 * 用户密码
	 */
	private String password;

	/**
	 * 对应的状态码参考com.egoo.psecurity.service.entity.login.LoginStateConstants
	 */
	private Integer active;

	/**
	 * 用户角色信息
	 */
	private String role;
}