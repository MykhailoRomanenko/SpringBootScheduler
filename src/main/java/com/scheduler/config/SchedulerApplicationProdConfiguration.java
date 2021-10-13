package com.scheduler.config;

import com.scheduler.task4.ProfileDependantComponent;
import com.scheduler.task4.ProfileDependantComponentProdImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class SchedulerApplicationProdConfiguration {
    @Bean
    public ProfileDependantComponent profileDependantComponent() {
        return new ProfileDependantComponentProdImpl();
    }
}
