package com.atguigu.springcloud.entity.vo;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.springcloud.entity.ucm.UcmResource;
import com.atguigu.springcloud.entity.ucm.UcmUser;
import com.atguigu.springcloud.entity.ucm.UcmRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


/**
 * <p>Title: LoginUser.java</p>
 * <p>Description:登录用户对象</p>
 * <p>Copyright: Copyright (c) 2019</p>
 * @author songxiaoliang
 * @date 2019-7-7 23:02:07
 */
@Data
public class LoginUser extends UcmUser implements UserDetails,Serializable {

	private static final long serialVersionUID = -1379274258881257107L;
	/** 拥有的角色 */
	private List<UcmRole> roleList = new ArrayList<>();
	/** 权限菜单 */
	private List<UcmResource> ucmResourceList = new ArrayList<>();
	/** 前端树形菜单 */
	private JSONObject menuIteam = new JSONObject();
	/** 权限CODE集合 */
	private List<String> codeList= new ArrayList<>();
	/** token */
	private String token;
	/** 登陆时间戳（毫秒） */
	private Long loginTime;
	/** 过期时间戳 */
	private Long expireTime;

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return ucmResourceList.parallelStream().filter(p -> !StringUtils.isEmpty(p.getCode()))
				.map(p -> new SimpleGrantedAuthority(p.getCode())).collect(Collectors.toSet());
	}

	// 账户是否未过期
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 账户是否未锁定
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
//		return getStatus() != Status.LOCKED;
		return true;
	}

	// 密码是否未过期
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 账户是否激活
	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}

	public Long getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Long loginTime) {
		this.loginTime = loginTime;
	}

	public Long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}

}
