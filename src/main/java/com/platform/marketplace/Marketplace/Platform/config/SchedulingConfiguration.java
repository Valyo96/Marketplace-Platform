package com.platform.marketplace.Marketplace.Platform.config;


import com.platform.marketplace.Marketplace.Platform.utility.scheduler.EventScheduler;
import com.platform.marketplace.Marketplace.Platform.utility.scheduler.OrganisationAccountScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableScheduling
public class SchedulingConfiguration {
    @Autowired
    private EventScheduler eventScheduler;

    @Autowired
    private OrganisationAccountScheduler organisationAccountScheduler;

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        return taskScheduler;
    }

}
