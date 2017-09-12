package com.quartz.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created By User: RXK
 * Date: 2017/9/11
		 * Time: 15:25
		 * Version: V1.0
		 * Description:
		 */
@SpringBootApplication
@ComponentScan(basePackages = "com.quartz.project")
public class QuartzApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(QuartzApplication.class,args);
	}
}
