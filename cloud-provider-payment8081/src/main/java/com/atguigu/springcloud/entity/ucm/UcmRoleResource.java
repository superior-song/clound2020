package com.atguigu.springcloud.entity.ucm;
import lombok.Data;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: UcmRoleResourceEntity.java</p>
 * <p>Description:暂无信息实体</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * @author songxiaoliang
 * @date 2020-1-3 16:20:05
 */
@Data
public class UcmRoleResource{

	/**暂无信息*/
	private Long id;
	/**暂无信息*/
	private String orgCode;
	/**暂无信息*/
	private String system;
	/**暂无信息*/
	private String module;
	/**暂无信息*/
	private String role;
	/**暂无信息*/
	private String resourceref;
	/** 扩展属性集合 **/
	private final Map<String,Object> map=new HashMap<String, Object>();

	/**
	 *无参构造函数
	 */
	public UcmRoleResource(){
		super();
	}

}
