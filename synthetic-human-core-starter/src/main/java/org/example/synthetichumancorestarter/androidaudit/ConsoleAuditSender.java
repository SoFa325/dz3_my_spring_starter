package org.example.synthetichumancorestarter.androidaudit;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@ConditionalOnProperty(name = "synthetic.core.audit.console-enabled", havingValue = "true", matchIfMissing = true)
public class ConsoleAuditSender implements AuditSender {
    @Override
    public void send(String method, Object[] args, Object result) {
        System.out.printf("[AUDIT] %s - Args: %s - Result: %s%n",
                method, Arrays.toString(args), result);
    }
}