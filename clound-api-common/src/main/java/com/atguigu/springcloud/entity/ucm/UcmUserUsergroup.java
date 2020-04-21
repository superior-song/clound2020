package com.atguigu.springcloud.entity.ucm;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: UcmUserUsergroupEntity.java</p>
 * <p>Description:用户组-用户实体</p>
 * <p>Copyright: Copyright (c) 2019</p>
 * @author songxiaoliang
 * @date 2019-7-8 15:47:13
 */
@Data
public class UcmUserUsergroup{

	/**ID*/
	private Long uuid;
	/**所属机构*/
	private String orgcode;
	/**用户编号*/
	private String userref;
	/**用户组编号*/
	private String usergroup;
	/** 扩展属性集合 **/
	private final Map<String,Object> map=new HashMap<String, Object>();

	/**
	 *无参构造函数
	 */
	public UcmUserUsergroup(){
		super();
	}

}
