package org.example.schedule;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class MyJobDetail {
	@Bean
	JobDetail jobDetail() {
		return JobBuilder
				.newJob(Check.class)
				.storeDurably()
				.build();
	}

	@Bean
	Trigger trigger() {
		return TriggerBuilder
				.newTrigger()
				.forJob(jobDetail())
				.withSchedule(CronScheduleBuilder.cronSchedule("0 0 * * * ?"))
				.build();
	}
}
