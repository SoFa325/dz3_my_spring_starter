package org.example.synthetichumancorestarter.commands;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ExecutorConfig {
    @Bean(name = "criticalExecutor")
    public ThreadPoolTaskExecutor criticalExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(0);
        executor.setThreadNamePrefix("critical-");
        executor.initialize();
        return executor;
    }

    @Bean(name = "commonExecutor")
    public ThreadPoolTaskExecutor commonExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("common-");
        executor.initialize();
        return executor;
    }
}