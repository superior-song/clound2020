package com.atguigu.springcloud.config.quartz.job;

import com.atguigu.springcloud.support.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 * @author: baojunjie
 * @date: 2019/7/12 9:05 AM
 * @desc: 从gennesys同步座席信息
 */
@Slf4j
@Component
@DisallowConcurrentExecution
public class SynchronizeAgentFromGenesys implements Job {
	private static boolean runing = false;


	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		if (runing) {
			return;
		}
		try {
			runing = true;
			log.info("从genesys同步座席信息任务开始{}----", DateUtil.getDateByTimestamp(System.currentTimeMillis()));
		} catch (Exception ex) {
			log.error("SynchronizeAgentFromGenesys执行失败", ex);
		} finally {
			runing = false;
		}
	}
}
