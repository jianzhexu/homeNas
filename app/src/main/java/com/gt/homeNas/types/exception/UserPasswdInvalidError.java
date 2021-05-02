package com.gt.homeNas.types.exception;

public class UserPasswdInvalidError extends TypesException{
    public UserPasswdInvalidError() {
        super("登陆密码不符合规则");
    }
}
