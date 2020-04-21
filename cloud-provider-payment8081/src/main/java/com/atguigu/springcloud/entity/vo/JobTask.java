package com.atguigu.springcloud.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 登录临时类
 */
@Data
public class JobTask implements Serializable {
    private String jobName;//任务名称
    private String jobGroupName;//任务组名称
}
