package com.atguigu.springcloud.entity.ucm;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: UcmUserEntity.java</p>
 * <p>Description:用户实体</p>
 * <p>Copyright: Copyright (c) 2019</p>
 * @author songxiaoliang
 * @date 2019-7-11 10:18:41
 */
@Data
public class UcmUser{

	/**ID*/
	private Long id;
	/**是否生效*/
	private String valid;
	/**用户密码*/
	@NotBlank(message = "密码不能为空。")
	private String password;
	/**用户名*/
	@NotBlank(message = "用户名不能为空。")
	private String username;
	/**姓名*/
	private String name;
	private String position;
	/**性别*/
	private String gender;
	/**证件类型*/
	private String cardType;
	/**证件号码*/
	private String cardNum;
	/**邮箱*/
	private String mail;
	/**手机号码*/
	private String mobile;
	/**备注*/
	private String remark;
	/**籍贯*/
	private String nativePlace;
	/**血型*/
	private String bloodType;
	/**民族*/
	private String nation;
	/**政治面貌*/
	private String poloitiStatus;
	/**地址*/
	private String address;
	/**特长爱好*/
	private String specialties;
	/**毕业院校*/
	private String graducateFrom;
	/**专业*/
	private String major;
	/**毕业时间*/
	private Long graduateDate;
	/**所属组织机构号*/
	private String deptCode;
	/**部门名称*/
	private String deptName;
	/**删除标识(0未删除 1删除）*/
	private Long deleted;
	/** 扩展属性集合 **/
	private final Map<String,Object> map=new HashMap<String, Object>();

	/**
	 *无参构造函数
	 */
	public UcmUser(){
		super();
	}

}
