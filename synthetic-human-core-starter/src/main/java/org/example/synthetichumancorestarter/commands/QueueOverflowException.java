package org.example.synthetichumancorestarter.commands;

public class QueueOverflowException extends RuntimeException {
    public QueueOverflowException(String message) { super(message); }
}
