package com.atguigu.springcloud.config.transaction;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.Properties;

@Configuration
public class TransactionConfiguration {

	public static final String AOP_POINTCUT_EXPRESSION  = "execution(* com.atguigu.springcloud.*.impl(..))";
	@Autowired
	private PlatformTransactionManager platformTransactionManager;

	/**
	 * 获取事务配置
	 *
	 * @return
	 */
	public static Properties getAttrubites() {
		Properties attributes = new Properties();

		//查询
		attributes.setProperty("get*", "PROPAGATION_REQUIRED,-Throwable,readOnly");
		attributes.setProperty("query*", "PROPAGATION_REQUIRED,-Throwable,readOnly");
		attributes.setProperty("find*", "PROPAGATION_REQUIRED,-Throwable,readOnly");
		attributes.setProperty("select*", "PROPAGATION_REQUIRED,-Throwable,readOnly");
		attributes.setProperty("count*", "PROPAGATION_REQUIRED,-Throwable,readOnly");

		//添加
		attributes.setProperty("add*", "PROPAGATION_REQUIRED,-Exception");
		attributes.setProperty("insert*", "PROPAGATION_REQUIRED,-Exception");
		attributes.setProperty("save*", "PROPAGATION_REQUIRED,-Exception");
		attributes.setProperty("create*", "PROPAGATION_REQUIRED,-Exception");
		attributes.setProperty("sync*", "PROPAGATION_REQUIRED,-Exception");

		//更新
		attributes.setProperty("update*", "PROPAGATION_REQUIRED,-Exception");
		attributes.setProperty("modify*", "PROPAGATION_REQUIRED,-Exception");

		//删除
		attributes.setProperty("delete*", "PROPAGATION_REQUIRED,-Exception");
		attributes.setProperty("remove*", "PROPAGATION_REQUIRED,-Exception");
		return attributes;

	}

	/**
	 * 采用注解实例化的拦截器Bean，注入切面配置信息
	 */
	@Bean
	public DefaultPointcutAdvisor defaultPointcutAdvisor(TransactionInterceptor ti){
		ti.setTransactionManager(platformTransactionManager);
		ti.setTransactionAttributes(getAttrubites());
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
		return new DefaultPointcutAdvisor(pointcut, ti);
	}
}