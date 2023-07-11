package com.CSMIS.CSMISKhaingFamily.Function;

import java.time.Month;
import java.util.Calendar;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
	
	 @Bean
     public JobDetail feedbackCleanupJobDetail() {
        return JobBuilder.newJob(FeedbackCleanup.class)
                .withIdentity("feedbackCleanupJob")
                .storeDurably()
                .build();
    }
	 
	public Trigger feedbackCleanupTrigger() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Month.DECEMBER.getValue()); // Specify the month (0-11) when the date should run
        calendar.set(Calendar.DAY_OF_MONTH, 1); // Specify the day of the month when the date should run
        calendar.set(Calendar.HOUR_OF_DAY, 0); // Specify the hour (0-23) when the date should run
        calendar.set(Calendar.MINUTE, 0); // Specify the minute when the job should run
        
        return TriggerBuilder.newTrigger()
                .forJob(feedbackCleanupJobDetail())
                .withIdentity("feedbackCleanupTrigger")
                .withSchedule(CronScheduleBuilder
                        .monthlyOnDayAndHourAndMinute(calendar.get(Calendar.DAY_OF_MONTH),
                                calendar.get(Calendar.HOUR_OF_DAY),
                                calendar.get(Calendar.MINUTE))
                )
                .build();
        	
        		
        		
        	
    }


}
