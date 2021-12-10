package com.scheduler;

import com.mylib.lib.service.MyService;
import com.scheduler.task4.ProfileDependantComponent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SchedulerApplication {

    public static void main(String[] args) {
       ApplicationContext context =  SpringApplication.run(SchedulerApplication.class, args);
        //System.out.println(context.getBean(ProfileDependantComponent.class).getName());
        context.getBean(MyService.class).myMethod();
    }
}
