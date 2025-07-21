package org.example.synthetichumancorestarter.androidaudit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditAspect {
    private final AuditSender auditSender;

    public AuditAspect(AuditSender auditSender) {
        this.auditSender = auditSender;
    }

    @Around("@annotation(WeylandWatchingYou)")
    public Object auditMethod(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();

        Object result = pjp.proceed();

        auditSender.send(methodName, args, result);
        return result;
    }
}