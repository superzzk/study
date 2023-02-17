package com.zzk.quartzdemo.trigger;

import com.zzk.quartzdemo.job1.HelloJob;
import org.quartz.CalendarIntervalScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TimeOfDay;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: quartz-demo
 * @description:
 * @author: zhangzhongkun
 * @create: 2019-03-28 14:47
 **/
public class CalendarIntervalTriggerDemo {
    public static void main(String[] args) throws SchedulerException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //1.创建一个jobDetail的实例，将该实例与HelloJob Class绑定
        JobDetail jobDetail = JobBuilder
                .newJob(HelloJob.class)
                .withIdentity("myJob", "group1") //定义name 和 group
                .build();

        //2.创建一个Trigger触发器的实例
        Trigger simpleTrigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .withSchedule( CalendarIntervalScheduleBuilder.calendarIntervalSchedule()
                        //.withIntervalInDays(1)
                        //.withIntervalInHours(1)
                        //.withIntervalInMinutes(1)
                        //.withIntervalInMonths(1)
                        .withIntervalInSeconds(1)
                        //.withIntervalInWeeks(1)
                        //.withIntervalInHours(1)
                )  //每天执行一次
                .build();


        //3.创建schedule实例
        StdSchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        System.out.println("现在的时间 ："+sf.format(new Date()));
        System.out.println();
        System.out.println("最近的一次执行时间 ："+sf.format(scheduler.scheduleJob(jobDetail,simpleTrigger))); //scheduler与jobDetail、trigger绑定，并打印出最近一次执行的事件
        scheduler.start();
    }
}
