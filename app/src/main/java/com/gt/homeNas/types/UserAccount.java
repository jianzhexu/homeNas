package com.gt.homeNas.types;

import com.gt.homeNas.domain.exception.UserAccountNotFoundException;
import com.gt.homeNas.types.exception.UserAccountInvalidError;
import lombok.Data;
import lombok.Getter;

@Getter
public class UserAccount {
    private final String userAccount;
    private final String CACHE_KEY = "Account";
    public UserAccount(String userAccount) throws UserAccountInvalidError {
        if (null == userAccount || userAccount.trim().length() <= 0)  {
            throw new UserAccountInvalidError();
        }
        this.userAccount = userAccount;
    }
}
