package com.example.batch;

import com.example.batch.job.WebScrappingJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class BatchApplication {

    public static void main(String[] args) {

        Scheduler scheduler = null;
        JobDataMap jobDataMap = new JobDataMap();

        jobDataMap.put("WTF", "WTF, Guys Shut the front door");

        JobDetail jobDetail = JobBuilder.newJob(WebScrappingJob.class)
                .withIdentity(WebScrappingJob.class.getName(), "groupA")
                .withDescription("WTF is the people")
                .setJobData(jobDataMap)
                .usingJobData("Key", 1.4443f)
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(WebScrappingJob.class.getName(), "groupA")
                .withSchedule(SimpleScheduleBuilder.repeatMinutelyForTotalCount(30))
                .build();

        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }

}
