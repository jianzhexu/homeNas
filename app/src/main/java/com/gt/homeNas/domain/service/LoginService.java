package com.gt.homeNas.domain.service;

import com.gt.homeNas.domain.entity.User;
import com.gt.homeNas.domain.exception.UserAccountNotFoundException;
import com.gt.homeNas.domain.exception.UserAccountPasswdErrorException;
import com.gt.homeNas.types.UserAccount;
import com.gt.homeNas.types.UserPasswd;

public interface LoginService {
    public User generalLogin(UserAccount userAccount, UserPasswd userPasswd) throws UserAccountPasswdErrorException, UserAccountNotFoundException;
}
