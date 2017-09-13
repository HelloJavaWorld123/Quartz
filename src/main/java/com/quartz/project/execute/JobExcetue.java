package com.quartz.project.execute;

import com.quartz.project.job.EveryDateJob;
import com.quartz.project.job.Love;
import org.quartz.*;
import org.quartz.impl.jdbcjobstore.TriggerStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * Created By User: RXK
 * Date: 2017/9/12
 * Time: 16:01
 * Version: V1.0
 * Description:
 */
@Component
@EnableScheduling
public class JobExcetue
{
	private static final String JOB_NAME = "job"+Thread.currentThread().getName();
	private static final String JOB_GROUP = "GROUP"+Thread.currentThread().getId();
	private static final String TRIGGER_NAME = "trigger"+Thread.currentThread().getId();
	private static final String TRIGGER_GROUP = "tri_group"+Thread.currentThread().getName();


	private static Logger LOG = LoggerFactory.getLogger(JobBuilder.class);
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


	@Autowired
	private Scheduler scheduler;

	/**
	 * 不适用配置的方式  创建一个任务
	 */
	@Scheduled(cron = "1/10 * * * * ?")
	public void exceNewJob()
	{

		//先创建一个JobDetail
		JobDetail jobDetail = JobBuilder.newJob(Love.class).withIdentity(JOB_NAME, JOB_GROUP).build();

		//创建一个 触发器 但是先创建一个schedule
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity(TRIGGER_NAME, TRIGGER_GROUP).build();
		try
		{
			scheduler.scheduleJob(jobDetail, trigger);

		} catch (SchedulerException e)
		{
			LOG.info("任务执行失败：{}", e.getMessage());
			e.printStackTrace();
		}
	}


	/**
	 * 通过注解新增加一个任务
	 * 必须保证jobDetail 与 trigger 的名称以及组名 是唯一的
	 */
	@Scheduled(cron = "2/8 * * * * ?")
	public void execDay(){

		//在执行具体的job时，创建的是JobDetail 而并非是JOb的实列
		JobDetail detail = JobBuilder.newJob(EveryDateJob.class).withIdentity(JOB_NAME, JOB_GROUP).build();

		//创建一个 触发器
		Trigger trigger =  TriggerBuilder.newTrigger().withIdentity(TRIGGER_NAME, TRIGGER_GROUP).build();

		detail.getJobDataMap().put("hello","world");
		detail.getJobDataMap().put("Quartz","能不能取出数据");


		try
		{
			Trigger.TriggerState state = scheduler.getTriggerState(TriggerKey.triggerKey(TRIGGER_NAME,TRIGGER_GROUP));
			LOG.info("当前触发器的状态：{}" ,state);

			scheduler.scheduleJob(detail,trigger);
		} catch (SchedulerException e)
		{
			e.printStackTrace();
		}
	}
}
