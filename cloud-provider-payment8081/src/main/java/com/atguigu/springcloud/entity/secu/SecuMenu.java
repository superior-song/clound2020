package com.atguigu.springcloud.entity.secu;
import lombok.Data;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: SecuMenuEntity.java</p>
 * <p>Description:暂无信息实体</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * @author songxiaoliang
 * @date 2019-12-30 14:41:31
 */
@Data
public class SecuMenu{

    /**暂无信息*/
    private Long id;
    /**添加人*/
    private Long creater;
    /**添加时间*/
    private Long createrTime;
    /**是否删除（0否 1是）*/
    private Long deleted;
    /**部门id*/
    private Long deptId;
    /**资源编号*/
    private String code;
    /**资源名称*/
    private String name;
    /**系统*/
    private String system;
    /**模块*/
    private String module;
    /**资源类型*/
    private Long resourceType;
    /**资源图标*/
    private String iconname;
    /**排序号*/
    private Long orderNum;
    /**级数*/
    private Long levels;
    private Long parentId;
    /** 扩展属性集合 **/
    private final Map<String,Object> map=new HashMap<String, Object>();
    /**
     *无参构造函数
     */
    public SecuMenu(){
        super();
    }

}
