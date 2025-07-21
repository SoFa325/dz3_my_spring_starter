package org.example.synthetichumancorestarter;

import org.example.synthetichumancorestarter.androidaudit.AuditSender;
import org.example.synthetichumancorestarter.androidaudit.ConsoleAuditSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
    @Bean
    public AuditSender testAuditSender() {
        return new ConsoleAuditSender();
    }
}