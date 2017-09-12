package com.quartz.project.config;

import com.quartz.project.task.MyScheduleTask;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Created By User: RXK
 * Date: 2017/9/11
 * Time: 16:47
 * Version: V1.0
 * Description:定时任务的配置类
 * 除了可以配置调度工厂  可以配置 CronTriggerFactoryBean 定时任务时间的配置对象 以及MethodInvokingJobDetailFatoryBean对象 设置具体的定时工作的对象，
 * 或者在定义定时任务时 设置定时时间对象或者 定时job对象；
 *
 * 在Scheduler中配置trigger定时调度器   在定时调度器中配置 JobDetail 调度的任务   在JobDetail中配置具体的 task
 */

@Configuration
public class QuartzConfig
{
	public static final String JOB = "hello";
	public static final String JOB_GROUP = "WORLD";
	public static final String TRIGGER = "GOD";
	/**
	 * 配置定时需要执行的任务 jobDetail
	 * @return
	 */
	@Bean(name="jobDetail")
	public MethodInvokingJobDetailFactoryBean jobDetailFactoryBean(MyScheduleTask task){
		MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
		/*
		*定义的任务是否并发执行；
		* 比如说：定义一个任务，定时时间需要5秒，每5秒执行一次；但是实际执行时，任务执行的时间超过了5秒，那么当设置为true时，下一个任务会执行，无论上一个任务有没有执行完毕；
		* 当设置为false时，下一个任务不会执行，等到上一个任务执行完毕，才执行下一个任务
		 */

		jobDetail.setConcurrent(false);
		//设置定时执行任务的名称
		/*jobDetail.setName(JOB);*/
		//设置定时执行的任务的组名
		/*jobDetail.setGroup(JOB_GROUP);*/

		//加载需要执行的实体类的对象
		jobDetail.setTargetObject(task);
		//设置需要定时执行的实体类中的任务的相关的方法
		jobDetail.setTargetMethod("world");

		//通过以上配置 JobDetaFactoryBean 工厂将定时加载我们指定的任务（实体类）以及指定任务的修相关的方法

		return jobDetail;
	}

	/**
	 * 配置定时任务的触发器
	 * 在该配置中 配置指定的任务执行的时间间隔  或者说执行的时间规律 ；调度器将按照该时间定时的指定job
	 * 还有另外一种trigger，但是没有这个CronTrigger 在时间上要求的精准
	 * @return
	 */
	@Bean(name="cronTrigger")
	public CronTriggerFactoryBean cronTriggerFactoryBean(MethodInvokingJobDetailFactoryBean jobDetail){
		CronTriggerFactoryBean cronTrigger = new CronTriggerFactoryBean();
		cronTrigger.setJobDetail(jobDetail.getObject());

		cronTrigger.setCronExpression("1/10 * * * * ?");
		cronTrigger.setName(TRIGGER);
		return cronTrigger;
	}

	/**
	 * 配置调度工厂
	 * 将需要定时执行的job 以及定时的trigger 配置到调度中心  就可以在自定义的时间时  执行job
	 * @return
	 */
	@Bean(name = "scheduler")
	public SchedulerFactoryBean schedulerFactoryBean(Trigger cronTrigger){
		SchedulerFactoryBean bean  = new SchedulerFactoryBean();
		//设置 调度工厂是否自动加载 新添加的任务  也就是新添加的任务在启动时自动的加载 实现动态的加载新的任务
		bean.setOverwriteExistingJobs(true);
		//设置应用启动时  延迟加载调度工厂 单位为秒
		/*bean.setStartupDelay(1);*/

		//在调度工厂中 配置定时器
		bean.setTriggers(cronTrigger);
		return bean;
	}
}
