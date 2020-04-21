package com.atguigu.springcloud.controller.ucm;

import com.atguigu.springcloud.common.ApiResponse;
import com.atguigu.springcloud.config.quartz.util.QuartzUtils;
import com.atguigu.springcloud.entity.vo.JobTask;
import com.atguigu.springcloud.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: QuartzController.java</p>
 * <p>Description:定时任务控制器</p>
 * <p>Copyright: Copyright (c) 2019</p>
 * @author songxiaoliang
 * @date 2019-7-7 23:02:07
 */
@RestController
@RequestMapping(value = "/v1/sys/quartz",produces = "application/json")
@Api(value = "定时任务通用接口", tags = "QuartzController", description = "定时任务通用接口")
@Slf4j
public class QuartzController {
	@Autowired
	private SchedulerFactoryBean schedulerFactory;

	@ApiOperation(value = "立即执行一个job", notes = "立即执行一个job",response = ApiResponse.class)
	@RequestMapping(value = "/runAJobNow",method = RequestMethod.POST)
	public  ApiResponse runAJobNow( @ApiParam(name = "paramMap",value="任务参数") @RequestBody JobTask jobTask,
								   HttpServletRequest request, HttpServletResponse response){
		ApiResponse result = new ApiResponse();
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			QuartzUtils.runAJobNow(scheduler,jobTask.getJobName(),jobTask.getJobGroupName(),null);
			result.init200Response();
			result.setMessage("后台正在执行...");
		}catch (BusinessException e){
			result.init203Response();
			log.error("业务错误:" + e.getMessage() );
			result.setMessage(e.getMessage());
		}catch (Exception e){
			log.error("服务器错误:" + e );
			result.init50XResponse();
		}
		return result;
	}

	@ApiOperation(value = "获取所有计划中的任务列表", notes = "获取所有计划中的任务列表",response = ApiResponse.class)
	@RequestMapping(value = "/queryAllJob",method = RequestMethod.POST)
	public  ApiResponse queryAllJob(@ApiParam(name = "token",value="令牌") @RequestParam(value = "token", required=false) String token,
									HttpServletRequest request, HttpServletResponse response){
		ApiResponse result = new ApiResponse();
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			List<Map<String, Object>> quartzList = QuartzUtils.queryAllJob(scheduler);
			result.setData(quartzList);
		}catch (BusinessException e){
			result.init203Response();
			log.error("业务错误:" + e.getMessage() );
			result.setMessage(e.getMessage());
		}catch (Exception e){
			log.error("服务器错误:" + e );
			result.init50XResponse();
		}
		return result;
	}
	@ApiOperation(value = "获取所有正在运行的job", notes = "获取所有正在运行的job",response = ApiResponse.class)
	@RequestMapping(value = "/queryRunJob",method = RequestMethod.POST)
	public  ApiResponse queryRunJob(@ApiParam(name = "token",value="令牌") @RequestParam(value = "token", required=false) String token,
									HttpServletRequest request, HttpServletResponse response){
		ApiResponse result = new ApiResponse();
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			List<Map<String, Object>> quartzList = QuartzUtils.queryRunJob(scheduler);
			result.setData(quartzList);
		}catch (BusinessException e){
			result.init203Response();
			log.error("业务错误:" + e.getMessage());
			result.setMessage(e.getMessage());
		}catch (Exception e){
			log.error("服务器错误:" + e );
			result.init50XResponse();
		}
		return result;
	}
}
