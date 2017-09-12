package com.quartz.project.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created By User: RXK
 * Date: 2017/9/12
 * Time: 10:00
 * Version: V1.0
 * Description:
 * 在没有配置触发器的时候  需要在创建任务的时候创建触发器 并注册在调度中心
 */
@Component
public class Love implements Job
{
	private static final Logger LOGGER = LoggerFactory.getLogger(Love.class);


	@Override
	public void execute(JobExecutionContext jobExecutionContext)
			throws JobExecutionException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");

		LOGGER.info("开始执行新定义的JOB");
		System.out.println("现在是北京时间："+sdf.format(new Date()));
	}
}
