package com.zzk.quartzdemo.job5;

import com.zzk.quartzdemo.job1.HelloJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.AnnualCalendar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/** @description: Trigger设置开始和结束时间
  * @create:  
  **/
public class CalendarSchedule {
    public static void main(String[] args) throws SchedulerException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //创建一个JobDetail的实例，将该实例与HelloJob绑定
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("zhlJob").build();
        AnnualCalendar holidays = new AnnualCalendar();
        GregorianCalendar nationalDay = new GregorianCalendar(2017, 10, 27);  // 排除今天的时间2017年11月27日（月份是从0～11的）

        holidays.setDayExcluded(nationalDay,true); //排除的日期，如果为false则为包含*/
        //创建Scheduler实例
        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = stdSchedulerFactory.getScheduler();
        //向Scheduler注册日历
        scheduler.addCalendar("holidays",holidays,false,false);
        Trigger simpleTrigger = TriggerBuilder.newTrigger()
                .withIdentity("zhlTrigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever()) //每一秒执行一次job
                .modifiedByCalendar("holidays")   //将我们设置好的Calander与trigger绑定
                .build();
        //让trigger应用指定的日历规则
        //scheduler.scheduleJob(jobDetail,simpleTrigger);
        System.out.println("现在的时间 ："+sf.format(new Date()));
        System.out.println("最近的一次执行时间 ："+sf.format(scheduler.scheduleJob(jobDetail,simpleTrigger))); //scheduler与jobDetail、trigger绑定，并打印出最近一次执行的事件
        scheduler.start();
    }
}
