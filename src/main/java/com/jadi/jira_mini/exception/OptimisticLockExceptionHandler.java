package com.jadi.jira_mini.exception;

public class OptimisticLockExceptionHandler extends RuntimeException{

    public OptimisticLockExceptionHandler(String message) {
        super(message);
    }
}
