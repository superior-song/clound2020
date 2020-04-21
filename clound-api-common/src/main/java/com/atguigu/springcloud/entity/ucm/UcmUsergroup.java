package com.atguigu.springcloud.entity.ucm;

import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: UcmUsergroupEntity.java</p>
 * <p>Description:用户组实体</p>
 * <p>Copyright: Copyright (c) 2019</p>
 * @author songxiaoliang
 * @date 2019-7-11 10:17:36
 */
@Data
public class UcmUsergroup{

	/**ID*/
	private Long uuid;
	/**创建人*/
	private Long creater;
	/**创建时间*/
	private Date createtime;
	/**更新人*/
	private Long updater;
	/**更新时间*/
	private Date updatetime;
	/**删除标志*/
	private String deleted;
	/**所属机构*/
	private String orgcode;
	/**是否生效*/
	private String valid;
	/**对象类型*/
	private String classtype;
	/**组编号*/
	private String code;
	/**组名称*/
	private String name;
	/**部门*/
	private String department;
	/**备注*/
	private String remark;
	/**节点类型*/
	private String nodetype;
	/**节点类型路径*/
	private String nodetypepath;
	/**父行政组*/
	private String parent;
	/**全路径编码*/
	private String fullcode;
	/**层级*/
	private Long levels;
	/**暂无信息*/
	private String system;
	/**技能组别名*/
	private String agentgroupalias;
	/**技能组配置路径*/
	private String agentgroupconfpath;
	/**DESTSYS*/
	private String destsys;
	/**组类型*/
	private String grouptype;
	/** 扩展属性集合 **/
	private final Map<String,Object> map=new HashMap<String, Object>();

	/**
	 *无参构造函数
	 */
	public UcmUsergroup(){
		super();
	}

}
