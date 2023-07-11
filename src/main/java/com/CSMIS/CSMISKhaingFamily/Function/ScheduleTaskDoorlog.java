package com.CSMIS.CSMISKhaingFamily.Function;

import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import freemarker.template.TemplateException;

@Component
public class ScheduleTaskDoorlog {
	
	 @Autowired
	 private NextMonthService nextMonthService;
	
	 @Autowired
	 private EndMonthReceipt endMonthReceipt;
	 
	     
    
    @Scheduled(cron = "0 0 10 * * ?")
    public void nextMonthMail() throws MessagingException, IOException, TemplateException {
        LocalDate currentDate = LocalDate.now();
        LocalDate endOfMonth = currentDate.with(TemporalAdjusters.lastDayOfMonth());
        LocalDate lastWeekStart = endOfMonth.minusDays(6);

        LocalDateTime scheduledDateTime = LocalDateTime.of(lastWeekStart, LocalTime.of(10, 0));
        LocalDateTime currentDateTime = LocalDateTime.now();
        if (currentDateTime.toLocalDate().isEqual(scheduledDateTime.toLocalDate())) {
                nextMonthService.operatoremail();
            }
    }
    
    @Scheduled(cron = "0 0 16 * * ?")
    public void monthlyreceipt() throws MessagingException, IOException, TemplateException {
        LocalDate currentDate = LocalDate.now();
        LocalDate endOfMonth = currentDate.with(TemporalAdjusters.lastDayOfMonth());
        LocalDateTime currentDateTime = LocalDateTime.now();
       if(currentDateTime.toLocalDate().isEqual(endOfMonth)) {
    	   endMonthReceipt.operatorendreceipt();;
       }
    }
}
