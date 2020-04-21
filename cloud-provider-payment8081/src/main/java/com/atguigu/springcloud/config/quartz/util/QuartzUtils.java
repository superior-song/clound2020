package com.atguigu.springcloud.config.quartz.util;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;

import java.util.*;

@Slf4j
public class QuartzUtils {

	/**
	 * 立即执行一个job
	 *
	 * @param scheduler
	 * @param jobName
	 * @param jobGroupName
	 * @param paramMap
	 */
	public static void runAJobNow(Scheduler scheduler,String jobName, String jobGroupName,Map<String,String> paramMap) throws Exception{
		try {
			JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
			JobDataMap jobDataMap = scheduler.getJobDetail(jobKey).getJobDataMap();
			if(null!=paramMap){
				jobDataMap.putAll(paramMap);
			}
			scheduler.triggerJob(jobKey,jobDataMap);
		} catch (SchedulerException e) {
			log.error("立即执行内部异常",e);
			throw e;
		}
	}

	/**
	 * 获取所有计划中的任务列表
	 *
	 * @return
	 */
	public static List<Map<String, Object>> queryAllJob(Scheduler scheduler) {
		List<Map<String, Object>> jobList = null;
		try {
			GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
			Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
			jobList = new ArrayList<Map<String, Object>>();
			for (JobKey jobKey : jobKeys) {
				List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
				for (Trigger trigger : triggers) {
					Map<String, Object> map = new HashMap<>();
					map.put("jobName", jobKey.getName());
					map.put("jobGroupName", jobKey.getGroup());
					map.put("description", "触发器:" + trigger.getKey());
					Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
					map.put("jobStatus", triggerState.name());
					if (trigger instanceof CronTrigger) {
						CronTrigger cronTrigger = (CronTrigger) trigger;
						String cronExpression = cronTrigger.getCronExpression();
						map.put("jobTime", cronExpression);
					}
					jobList.add(map);
				}
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return jobList;
	}

	/**
	 * 获取所有正在运行的job
	 *
	 * @return
	 */
	public static List<Map<String, Object>> queryRunJob(Scheduler scheduler) {
		List<Map<String, Object>> jobList = null;
		try {
			List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
			jobList = new ArrayList<Map<String, Object>>(executingJobs.size());
			for (JobExecutionContext executingJob : executingJobs) {
				Map<String, Object> map = new HashMap<String, Object>();
				JobDetail jobDetail = executingJob.getJobDetail();
				JobKey jobKey = jobDetail.getKey();
				Trigger trigger = executingJob.getTrigger();
				map.put("jobName", jobKey.getName());
				map.put("jobGroupName", jobKey.getGroup());
				map.put("description", "触发器:" + trigger.getKey());
				Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
				map.put("jobStatus", triggerState.name());
				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					map.put("jobTime", cronExpression);
				}
				jobList.add(map);
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return jobList;
	}

}
