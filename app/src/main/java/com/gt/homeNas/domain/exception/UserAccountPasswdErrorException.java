package com.gt.homeNas.domain.exception;

public class UserAccountPasswdErrorException extends DomainException{
    public UserAccountPasswdErrorException() {
        super("用户密码错误");
    }
}
