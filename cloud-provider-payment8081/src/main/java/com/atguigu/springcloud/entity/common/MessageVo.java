package com.atguigu.springcloud.entity.common;


import lombok.Data;

import java.io.Serializable;

/**
 * 定时发送还班消息提醒实体
 */

@Data
public class MessageVo implements Serializable {

	private Long uuid;//主键id

	private  Long agentId ;//座席ID

	private Long changeType;//换班方式

	private String name ;//用户Code

	private Long userId; //用户id

	private Long changeDate ;//换班时间

	private Long eventType ;//班次类型

	private Long targetAgentId ;//目标座席Id

	private String targetName ;//目标用户Code

	private Long targetEventType ;//目标班次类型

	private Long targetUserId; //

	private Long shiftEndType ;//还班截止日期（0：当月 1：次月）


}
