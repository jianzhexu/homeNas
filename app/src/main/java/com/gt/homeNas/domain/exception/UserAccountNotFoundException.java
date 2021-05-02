package com.gt.homeNas.domain.exception;

/**
 * 用户不存在时，抛出异常
 */
public class UserAccountNotFoundException extends DomainException{

    public UserAccountNotFoundException() {
        super("用户不存在");
    }

    public UserAccountNotFoundException(String message) {
        super(message);
    }

    public UserAccountNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAccountNotFoundException(Throwable cause) {
        super(cause);
    }
}
