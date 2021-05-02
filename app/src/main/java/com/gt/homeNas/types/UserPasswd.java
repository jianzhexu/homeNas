package com.gt.homeNas.types;

import com.gt.homeNas.types.exception.UserPasswdInvalidError;
import lombok.Data;
import lombok.Getter;

@Getter
public class UserPasswd {

    private final String userPasswd;
    private final String CACHE_KEY = "PASSWD";
    public UserPasswd(String userPasswd) throws UserPasswdInvalidError {
        if (null == userPasswd || userPasswd.trim().length() <= 0) {
            throw new UserPasswdInvalidError();
        }
        this.userPasswd = userPasswd;
    }
}
