package com.gt.homeNas.domain.entity;

import com.gt.homeNas.types.UserAccount;
import com.gt.homeNas.types.UserId;
import com.gt.homeNas.types.UserName;
import com.gt.homeNas.types.UserPasswd;
import lombok.Data;
@Data
public class User {
    private UserName userName;
    private UserAccount userAccount;
    private final UserPasswd userPasswd;
    private UserId userId;

    public User(UserName userName, UserAccount userAccount, UserPasswd userPasswd, UserId userId) {
        this.userName = userName;
        this.userAccount = userAccount;
        this.userPasswd = userPasswd;
        this.userId = userId;
    }

    public String getPasswd() {
        return this.userPasswd.getUserPasswd();
    }

    @Override
    public String toString() {
        return "User{" +
                "userAccount=" + userAccount.getUserAccount() +
                ", userPasswd=" + userPasswd.getUserPasswd() +
                ", userId=" + userId.getId() +
                '}';
    }
}
