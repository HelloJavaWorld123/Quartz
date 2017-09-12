package com.quartz.project;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created By User: RXK
 * Date: 2017/9/11
 * Time: 18:04
 * Version: V1.0
 * Description: 该类可以从数据库中定时查询   并获取到已经设置好的 新的任务执行的定时间隔 可以实现动态的生成定时任务并执行
 */
@RestController
@Configuration
@EnableScheduling
public class controller
{
	public  static final Logger LOGGER = LoggerFactory.getLogger(controller.class);


	/*@Autowired
	private JobService service;
*/

	@Resource(name = "scheduler")
	private Scheduler scheduler;

	@Autowired
	@Qualifier("cronTrigger")
	private CronTrigger cronTrigger;

	@Resource(name = "jobDetail")
	private JobDetail jobDetail;


	@Scheduled(fixedRate = 5000) //单位 毫秒
	public void updateTrigger(){
		LOGGER.info("新定义的定时任务");
		//先获取定时触发器 从调度器中
		try
			{
			CronTrigger orginalTrigger =(CronTrigger) scheduler.getTrigger(cronTrigger.getKey());
			LOGGER.info("trigger :{} ",orginalTrigger);

			//获取之前的表达式
			String  orginalCron = cronTrigger.getCronExpression();
			//TODO 从数据库查找先指定的cron，得到newCron；
			String newCron = "2/10 * * * * ?";

			if(!newCron.equals(orginalTrigger))
			{
				CronScheduleBuilder scheduleBuilder =CronScheduleBuilder.cronSchedule(newCron);

				LOGGER.info("scheduleBuilder : {}",scheduleBuilder);

				//需要将现有的trigger重新设置 Cron，则先根据key获取到指定的trigger 在重新创建
				Trigger trigger = (CronTrigger)scheduler.getTrigger(cronTrigger.getKey());

				//重新创建
				trigger.getTriggerBuilder().withIdentity(trigger.getKey()).build();

				//设置调度器 按照新的trigger 重新执行job
				scheduler.rescheduleJob(cronTrigger.getKey(),trigger);
				orginalCron = newCron;
			}

			} catch (SchedulerException e)
			{
				LOGGER.debug("新的定时任务执行失败：{} ",e.getMessage());
				e.printStackTrace();
			}

	}






}
