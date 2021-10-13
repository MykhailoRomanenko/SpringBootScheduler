package com.scheduler.config;

import com.scheduler.task4.ProfileDependantComponent;
import com.scheduler.task4.ProfileDependantComponentDevImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class SchedulerApplicationDevConfiguration {
    @Bean
    public ProfileDependantComponent profileDependantComponent() {
        return new ProfileDependantComponentDevImpl();
    }
}
