package org.example.synthetichumancorestarter.androidaudit;

public interface AuditSender {
    void send(String method, Object[] args, Object result);
}
