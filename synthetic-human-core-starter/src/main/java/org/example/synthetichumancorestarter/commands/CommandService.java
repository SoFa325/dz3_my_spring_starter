package org.example.synthetichumancorestarter.commands;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommandService {
    private final ThreadPoolTaskExecutor criticalExecutor;
    private final ThreadPoolTaskExecutor commonExecutor;
    private final MeterRegistry meterRegistry;


    public void executeCommand(CommandDTO command) {
        Runnable task = () -> {
            log.info("Executing: {}", command.description());

            meterRegistry.counter(
                    "android.tasks.completed",
                    "author", command.author(),
                    "priority", command.priority().name()
            ).increment();
        };

        if (command.priority() == CommandPriority.CRITICAL) {
            criticalExecutor.execute(task);
        } else {
            if (commonExecutor.getThreadPoolExecutor().getQueue().remainingCapacity() == 0) {
                throw new QueueOverflowException("Command queue full");
            }
            commonExecutor.execute(task);
        }
    }
}

