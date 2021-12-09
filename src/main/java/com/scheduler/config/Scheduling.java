package com.scheduler.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Scheduling {

//    @Scheduled(fixedDelay = 10000)
    public void schedulingDelay() {
        log.info("scheduling with delay");
    }

//    @Scheduled(cron = "* 12 * * * *")
    public void schedulingCron() {
        log.info("scheduling with cron");
    }
}
