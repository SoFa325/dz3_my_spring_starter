package org.example.synthetichumancorestarter.androidaudit;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@ConditionalOnClass(KafkaTemplate.class)
@ConditionalOnProperty(name = "synthetic.core.audit.kafka-enabled")
public class KafkaAuditSender implements AuditSender {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topic;

    public KafkaAuditSender(
            KafkaTemplate<String, String> kafkaTemplate,
            @Value("${synthetic.core.audit.kafka-topic:android-audit}") String topic
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @Override
    public void send(String method, Object[] args, Object result) {
        String message = String.format(
                "{\"method\":\"%s\",\"args\":%s,\"result\":%s}",
                method, Arrays.toString(args), result
        );
        kafkaTemplate.send(topic, message);
    }
}