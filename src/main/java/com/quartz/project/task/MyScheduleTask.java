package com.quartz.project.task;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.TimeZone;

/**
 * Created By User: RXK
 * Date: 2017/9/11
 * Time: 16:37
 * Version: V1.0
 * Description:任务类
 */
@Component
@Configuration
@EnableScheduling
public class MyScheduleTask
{
	private static final Logger LOGGER = LoggerFactory.getLogger(MyScheduleTask.class);


	@Autowired
	private Scheduler scheduler;

	@Autowired
	private JobDetail jobDetail;

	@Autowired
	private CronTrigger cronTrigger;

	@Scheduled(cron = "1/10 * * * * ?")
	public void world()
			throws SchedulerException
	{
		LOGGER.info("开始定时需要执行的任务");
		System.out.println("hello world"+scheduler.getTriggerState(cronTrigger.getKey()));

		try
			{
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(cronTrigger.getKey());
			LOGGER.info("trigger :{}" ,trigger);
			String cronExpression = trigger.getCronExpression();
			LOGGER.info("cronExpression {}" ,cronExpression);

			TimeZone timeZone = trigger.getTimeZone();
			LOGGER.info(" timeZone  >>>>>>{}",timeZone);


			List<String> groupNames = scheduler.getTriggerGroupNames();
			LOGGER.info("groupNames :{}",groupNames);

			Trigger.TriggerState state = scheduler.getTriggerState(cronTrigger.getKey());

			LOGGER.info("state {}",state);


			List<JobExecutionContext> jobs = scheduler.getCurrentlyExecutingJobs();
			LOGGER.info("jobs ：{}" ,jobs);

			JobDetail jobDetail = scheduler.getJobDetail(this.jobDetail.getKey());
			LOGGER.info("jobDetail {}",jobDetail);

			SchedulerContext context = scheduler.getContext();
			LOGGER.info("context {}" ,context);

			List<String> jobGroupNames = scheduler.getJobGroupNames();
			LOGGER.info("jobGroupNames {}" ,jobGroupNames);

			} catch (SchedulerException e)
			{
			e.printStackTrace();
			}


	}
}
