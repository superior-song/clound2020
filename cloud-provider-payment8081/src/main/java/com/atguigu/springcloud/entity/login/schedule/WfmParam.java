package com.atguigu.springcloud.entity.login.schedule;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: Pan Lang
 * @Date: 2019/4/3 11:54
 * @Description:
 * @throws: $return$
 */
@Data
public class WfmParam {
	private double startTime;
	private double endTime;
	private Integer agentId;
	private List<Integer> intList;
}
