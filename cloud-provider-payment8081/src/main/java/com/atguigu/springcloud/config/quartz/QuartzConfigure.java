package com.atguigu.springcloud.config.quartz;

import com.atguigu.springcloud.config.quartz.job.SynchronizeAgentFromGenesys;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * <p>Title: QuartzConfigure.java</p>
 * <p>Description:定时任务配置类</p>
 * <p>Copyright: Copyright (c) 2019</p>
 * @author songxiaoliang
 * @date 2019-7-10 14:02:07
 */
@Slf4j
@Configuration
public class QuartzConfigure {
	// 配置文件路径
	private static final String QUARTZ_CONFIG = "/quartz.properties";

	private DataSource dataSource;

	@Value("${quartz.job.synchronizeAgentFromGenesysJobTrigger}")
	private String synchronizeAgentFromGenesysJobTrigger;

	@Value("${quartz.job.synchronizeJob1Trigger}")
	private String synchronizeJob1Trigger;
	@Value("${quartz.job.synchronizeJob2Trigger}")
	private String synchronizeJob2Trigger;
	@Value("${quartz.job.synchronizeJob3Trigger}")
	private String synchronizeJob3Trigger;
	@Value("${quartz.job.synchronizeJob4Trigger}")
	private String synchronizeJob4Trigger;
	@Value("${quartz.job.synchronizeJob5Trigger}")
	private String synchronizeJob5Trigger;
	@Value("${quartz.job.synchronizeJob6Trigger}")
	private String synchronizeJob6Trigger;
	/**
	 * 从quartz.properties文件中读取Quartz配置属性
	 * @return
	 * @throws IOException
	 */
	@Bean
	public Properties quartzProperties() throws IOException {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource(QUARTZ_CONFIG));
		propertiesFactoryBean.afterPropertiesSet();
		return propertiesFactoryBean.getObject();
	}

	/**
	 * JobFactory与schedulerFactoryBean中的JobFactory相互依赖,注意bean的名称
	 * 在这里为JobFactory注入了Spring上下文
	 *
	 * @param applicationContext
	 * @return
	 */
	@Bean
	public JobFactory jobFactory(ApplicationContext applicationContext) {
		AutoWiredSpringBeanToJobFactory jobFactory = new AutoWiredSpringBeanToJobFactory();
		jobFactory.setApplicationContext(applicationContext);
		return jobFactory;
	}

	/**
	 *
	 * @param jobFactory  为SchedulerFactory配置JobFactory
	 * @param jobTrigger
	 * @return
	 * @throws IOException
	 */
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory, Trigger... jobTrigger) throws IOException {
		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		factory.setJobFactory(jobFactory);
		factory.setOverwriteExistingJobs(true);
		factory.setAutoStartup(true); // 设置自行启动
		factory.setQuartzProperties(quartzProperties());
		factory.setTriggers(jobTrigger);
		factory.setDataSource(dataSource);// 使用应用的dataSource替换quartz的dataSource
		return factory;
	}
	@Bean
	public JobDetailFactoryBean synchronizeAgentFromGenesysDetail() {
		//集群模式下必须使用JobDetailFactoryBean，MethodInvokingJobDetailFactoryBean 类中的 methodInvoking 方法，是不支持序列化的
		JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
		jobDetail.setDurability(true);
		jobDetail.setRequestsRecovery(true);
		jobDetail.setJobClass(SynchronizeAgentFromGenesys.class);
		return jobDetail;
	}

	@Bean
	public CronTriggerFactoryBean synchronizeAgentFromGenesysJobTrigger(JobDetail synchronizeAgentFromGenesysDetail) {
		CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
		tigger.setJobDetail(synchronizeAgentFromGenesysDetail);
		tigger.setStartDelay(2000);   //延迟启动
		tigger.setCronExpression(synchronizeAgentFromGenesysJobTrigger);
		return tigger;
	}
	@Bean
	public JobDetailFactoryBean synchronizeJob1Detail() {
		//集群模式下必须使用JobDetailFactoryBean，MethodInvokingJobDetailFactoryBean 类中的 methodInvoking 方法，是不支持序列化的
		JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
		jobDetail.setDurability(true);
		jobDetail.setRequestsRecovery(true);
		jobDetail.setJobClass(SynchronizeAgentFromGenesys.class);
		return jobDetail;
	}

	@Bean
	public CronTriggerFactoryBean synchronizeJob1Trigger(JobDetail synchronizeJob1Detail) {
		CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
		tigger.setJobDetail(synchronizeJob1Detail);
		tigger.setStartDelay(2000);   //延迟启动
		tigger.setCronExpression(synchronizeJob1Trigger);
		return tigger;
	}
	@Bean
	public JobDetailFactoryBean synchronizeJob2Detail() {
		//集群模式下必须使用JobDetailFactoryBean，MethodInvokingJobDetailFactoryBean 类中的 methodInvoking 方法，是不支持序列化的
		JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
		jobDetail.setDurability(true);
		jobDetail.setRequestsRecovery(true);
		jobDetail.setJobClass(SynchronizeAgentFromGenesys.class);
		return jobDetail;
	}

	@Bean
	public CronTriggerFactoryBean synchronizeJob2Trigger(JobDetail synchronizeJob2Detail) {
		CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
		tigger.setJobDetail(synchronizeJob2Detail);
		tigger.setStartDelay(2000);   //延迟启动
		tigger.setCronExpression(synchronizeJob2Trigger);
		return tigger;
	}
	@Bean
	public JobDetailFactoryBean synchronizeJob3Detail() {
		//集群模式下必须使用JobDetailFactoryBean，MethodInvokingJobDetailFactoryBean 类中的 methodInvoking 方法，是不支持序列化的
		JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
		jobDetail.setDurability(true);
		jobDetail.setRequestsRecovery(true);
		jobDetail.setJobClass(SynchronizeAgentFromGenesys.class);
		return jobDetail;
	}

	@Bean
	public CronTriggerFactoryBean synchronizeJob3Trigger(JobDetail synchronizeJob3Detail) {
		CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
		tigger.setJobDetail(synchronizeJob3Detail);
		tigger.setStartDelay(2000);   //延迟启动
		tigger.setCronExpression(synchronizeJob3Trigger);
		return tigger;
	}
	@Bean
	public JobDetailFactoryBean synchronizeJob4Detail() {
		//集群模式下必须使用JobDetailFactoryBean，MethodInvokingJobDetailFactoryBean 类中的 methodInvoking 方法，是不支持序列化的
		JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
		jobDetail.setDurability(true);
		jobDetail.setRequestsRecovery(true);
		jobDetail.setJobClass(SynchronizeAgentFromGenesys.class);
		return jobDetail;
	}

	@Bean
	public CronTriggerFactoryBean synchronizeJob4Trigger(JobDetail synchronizeJob4Detail) {
		CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
		tigger.setJobDetail(synchronizeJob4Detail);
		tigger.setStartDelay(2000);   //延迟启动
		tigger.setCronExpression(synchronizeJob4Trigger);
		return tigger;
	}
	@Bean
	public JobDetailFactoryBean synchronizeJob5Detail() {
		//集群模式下必须使用JobDetailFactoryBean，MethodInvokingJobDetailFactoryBean 类中的 methodInvoking 方法，是不支持序列化的
		JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
		jobDetail.setDurability(true);
		jobDetail.setRequestsRecovery(true);
		jobDetail.setJobClass(SynchronizeAgentFromGenesys.class);
		return jobDetail;
	}

	@Bean
	public CronTriggerFactoryBean synchronizeJob5Trigger(JobDetail synchronizeJob5Detail) {
		CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
		tigger.setJobDetail(synchronizeJob5Detail);
		tigger.setStartDelay(2000);   //延迟启动
		tigger.setCronExpression(synchronizeJob5Trigger);
		return tigger;
	}
	@Bean
	public JobDetailFactoryBean synchronizeJob6Detail() {
		//集群模式下必须使用JobDetailFactoryBean，MethodInvokingJobDetailFactoryBean 类中的 methodInvoking 方法，是不支持序列化的
		JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
		jobDetail.setDurability(true);
		jobDetail.setRequestsRecovery(true);
		jobDetail.setJobClass(SynchronizeAgentFromGenesys.class);
		return jobDetail;
	}

	@Bean
	public CronTriggerFactoryBean synchronizeJob6Trigger(JobDetail synchronizeJob6Detail) {
		CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
		tigger.setJobDetail(synchronizeJob6Detail);
		tigger.setStartDelay(2000);   //延迟启动
		tigger.setCronExpression(synchronizeJob6Trigger);
		return tigger;
	}

}