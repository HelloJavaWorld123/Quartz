package com.quartz.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Created By User: RXK
 * Date: 2017/9/11
 * Time: 16:47
 * Version: V1.0
 * Description:只是配置调度中心
 */

@Configuration
public class QuartzConfig
{

	/**
	 * 配置调度工厂
	 * 将需要定时执行的job 以及定时的trigger 配置到调度中心  就可以在自定义的时间时  执行job
	 * @return
	 */
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(){
		SchedulerFactoryBean bean  = new SchedulerFactoryBean();
		//设置 调度工厂是否自动加载 新添加的任务  也就是新添加的任务在启动时自动的加载 实现动态的加载新的任务
		bean.setOverwriteExistingJobs(true);
		//设置应用启动时  延迟加载调度工厂 单位为秒
		/*bean.setStartupDelay(1);*/



		/*bean.setAutoStartup(true);*/

		return bean;
	}
}
