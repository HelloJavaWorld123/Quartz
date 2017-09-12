package com.quartz.project.job;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created By User: RXK
 * Date: 2017/9/12
 * Time: 16:53
 * Version: V1.0
 * Description:
 */
public class EveryDateJob implements Job
{
	private static Logger LOG = LoggerFactory.getLogger(EveryDateJob.class);


	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException
	{
		LOG.info("笑口常开 好彩自然来");
	}
}
