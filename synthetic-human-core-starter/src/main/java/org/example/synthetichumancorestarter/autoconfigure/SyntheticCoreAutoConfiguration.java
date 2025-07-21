package org.example.synthetichumancorestarter.autoconfigure;

import io.micrometer.core.instrument.MeterRegistry;
import org.example.synthetichumancorestarter.androidaudit.AuditAspect;
import org.example.synthetichumancorestarter.androidaudit.AuditSender;
import org.example.synthetichumancorestarter.androidaudit.ConsoleAuditSender;
import org.example.synthetichumancorestarter.androidaudit.KafkaAuditSender;
import org.example.synthetichumancorestarter.commands.CommandService;
import org.example.synthetichumancorestarter.commands.ExecutorConfig;
import org.example.synthetichumancorestarter.errors.GlobalExceptionHandler;
import org.example.synthetichumancorestarter.monitoring.MetricsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@AutoConfiguration
@EnableConfigurationProperties(SyntheticCoreProperties.class)
@Import({
        ExecutorConfig.class,
        MetricsConfig.class,
        GlobalExceptionHandler.class
})
public class SyntheticCoreAutoConfiguration {

    private final SyntheticCoreProperties properties;

    @Autowired
    public SyntheticCoreAutoConfiguration(SyntheticCoreProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public CommandService commandService(
            @Qualifier("criticalExecutor") ThreadPoolTaskExecutor criticalExecutor,
            @Qualifier("commonExecutor") ThreadPoolTaskExecutor commonExecutor,
            MeterRegistry meterRegistry
    ) {
        return new CommandService(criticalExecutor, commonExecutor, meterRegistry);
    }

    @Bean
    @ConditionalOnMissingBean(AuditSender.class)
    public AuditSender auditSender() {
        return new ConsoleAuditSender();
    }

    @Bean
    @ConditionalOnProperty(
            name = "synthetic.core.audit.mode",
            havingValue = "console"
    )
    @ConditionalOnMissingBean
    public ConsoleAuditSender consoleAuditSender() {
        return new ConsoleAuditSender();
    }

    @Bean
    @ConditionalOnClass(KafkaTemplate.class)
    @ConditionalOnProperty(
            name = "synthetic.core.audit.mode",
            havingValue = "kafka"
    )
    @ConditionalOnMissingBean
    public KafkaAuditSender kafkaAuditSender(KafkaTemplate<String, String> kafkaTemplate) {
        return new KafkaAuditSender(
                kafkaTemplate,
                properties.getAudit().getKafkaTopic()
        );
    }

    @Bean
    public AuditAspect auditAspect(AuditSender auditSender) {
        return new AuditAspect(auditSender);
    }
}