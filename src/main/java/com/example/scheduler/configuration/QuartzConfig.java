package com.example.scheduler.configuration;

import com.example.Job.CreateQuartzJob;
import com.example.Job.DailyAddMerchants;
import com.example.Job.DailyCheckDetails;
import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import org.springframework.stereotype.Service;

@Service
public class QuartzConfig {
    /*
    @Autowired
    private JobDetailFactoryBean dailyAddMerchantsJobDetail;
    */

    private static Scheduler scheduler;

    public JobDetailFactoryBean dailyAddMerchantsJobDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(CreateQuartzJob.class);
        factoryBean.setDurability(true);
        return factoryBean;
    }

    //@Bean
    public void schedulerStart() throws SchedulerException {
        scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        System.out.println("schedular starting..");
        //scheduler.scheduleJob(AddMerchants(),);

        //scheduler.scheduleJob(chkDeails(),trigger_DailyAddMerchant()); // 每天固定一次
        //scheduler.scheduleJob(AddMerchants(),trigger_DailyAddMerchant()); // 每天固定一次
        scheduler.scheduleJob(chkDeails(),triggerInterval()); // 間隔2分鐘
        scheduler.scheduleJob(AddMerchants(),triggerInterval()); // 間隔2分鐘
    }
    public JobDetail AddMerchants() {
        return JobBuilder.newJob(DailyAddMerchants.class)
                .withIdentity("AddMerchants","googleAPI")
                .build();
    }
    public JobDetail chkDeails() {
        return JobBuilder.newJob(DailyCheckDetails.class)
                .withIdentity("chkDeails","googleAPI")
                .build();
    }
    //每日定期
    public Trigger trigger_DailyAddMerchant() {
        return TriggerBuilder.newTrigger()
                .withIdentity("trigger_DailyAddMerchants", "googleMapAPI")
                .startAt(DateBuilder.todayAt(15, 57, 0)) // 在每天上午10點啟動
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(24).repeatForever()) // 每天重複執行
                .build();
    }
    //時間間隔
    public Trigger triggerInterval() {
        Trigger trigger = TriggerBuilder.newTrigger().withSchedule(simpleSchedule()
                .withIntervalInMinutes(2)
                .repeatForever()).build();
        return trigger;
    }

}

