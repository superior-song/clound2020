package com.atguigu.springcloud.entity.ucm;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: UcmUserRoleEntity.java</p>
 * <p>Description:角色-用户实体表实体</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * @author songxiaoliang
 * @date 2020-1-3 16:20:33
 */
@Data
public class UcmUserRole{

	/**暂无信息*/
	private Long id;
	/**所属机构*/
	private String orgCode;
	/**用户编号*/
	private String userref;
	/**角色编号*/
	private String role;
	/** 扩展属性集合 **/
	private final Map<String,Object> map=new HashMap<String, Object>();

	/**
	 *无参构造函数
	 */
	public UcmUserRole(){
		super();
	}

}
