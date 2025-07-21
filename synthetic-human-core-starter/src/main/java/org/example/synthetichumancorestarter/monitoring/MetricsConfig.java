package org.example.synthetichumancorestarter.monitoring;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class MetricsConfig {

    @Bean
    public Gauge commonQueueGauge(
            @Qualifier("commonExecutor") ThreadPoolTaskExecutor executor,
            MeterRegistry registry
    ) {
        return Gauge.builder("android.occupancy",
                        () -> executor.getThreadPoolExecutor().getQueue().size())
                .description("Current task queue size")
                .register(registry);
    }

    @Bean
    public Gauge commonQueueRemainingCapacity(
            @Qualifier("commonExecutor") ThreadPoolTaskExecutor executor,
            MeterRegistry registry
    ) {
        return Gauge.builder("android.queue.remaining.capacity",
                        () -> executor.getThreadPoolExecutor().getQueue().remainingCapacity())
                .description("Remaining capacity in common command queue")
                .register(registry);
    }
}