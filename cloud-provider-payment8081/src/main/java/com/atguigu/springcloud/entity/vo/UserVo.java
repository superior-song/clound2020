package com.atguigu.springcloud.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 登录临时类
 */
@Data
public class UserVo implements Serializable {
	/**用户名*/
	@NotBlank(message = "用户名不能为空。")
	@Size(min=1,max=30,message="用户名长度在1-30个字符。")
	private String username;
	/**用户密码*/
	@NotBlank(message = "密码不能为空。")
	private String password;

}
