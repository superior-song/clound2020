package com.atguigu.springcloud.entity.ucm;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: UcmRoleEntity.java</p>
 * <p>Description:角色实体实体</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * @author songxiaoliang
 * @date 2020-1-3 16:16:22
 */
@Data
public class UcmRole{

	/**暂无信息*/
	private Long id;
	/**创建人*/
	private Long creater;
	/**新增时间*/
	private Long createTime;
	/**更新人*/
	private Long updater;
	/**更新时间*/
	private Long updateTime;
	/**是否删除*/
	private Long deleted;
	/**所属机构*/
	private Long deptId;
	/**是否生效*/
	private Long valid;
	/**角色编号*/
	private String code;
	/**角色名称*/
	private String name;
	/**角色类型*/
	private String roleType;
	/**备注*/
	private String remark;
	/** 扩展属性集合 **/
	private final Map<String,Object> map=new HashMap<String, Object>();

	/**
	 *无参构造函数
	 */
	public UcmRole(){
		super();
	}

}
