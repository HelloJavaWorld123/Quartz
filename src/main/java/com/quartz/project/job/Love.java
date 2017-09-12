package com.quartz.project.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created By User: RXK
 * Date: 2017/9/12
 * Time: 10:00
 * Version: V1.0
 * Description:按照注解的方式的  新添加任务
 */
@Component
@Configuration
@EnableScheduling
public class Love
{
	private static final Logger LOGGER = LoggerFactory.getLogger(Love.class);

	@Scheduled(cron = "3/3 * * * * ?")
	public void MyLitalBonby(){
		LOGGER.info("鄙人 此生的信念 ------->");
		System.out.println("All My Love   ------> song ");




	}


}
