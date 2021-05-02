package com.gt.homeNas.domain.service.impl;

import com.gt.homeNas.domain.UserRepository;
import com.gt.homeNas.domain.entity.User;
import com.gt.homeNas.domain.exception.UserAccountNotFoundException;
import com.gt.homeNas.domain.exception.UserAccountPasswdErrorException;
import com.gt.homeNas.domain.service.LoginService;
import com.gt.homeNas.types.UserAccount;
import com.gt.homeNas.types.UserPasswd;

import java.util.Optional;

public class LoginServiceImpl implements LoginService {

    private UserRepository userRepository;

    public LoginServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User generalLogin(UserAccount userAccount, UserPasswd userPasswd)
            throws UserAccountPasswdErrorException, UserAccountNotFoundException {
        Optional<User> userOptional = userRepository.findByAccount(userAccount);
        if (!userOptional.isPresent()) {
            throw new UserAccountNotFoundException();
        }
        if (!userPasswd.getUserPasswd().equals(userOptional.get().getPasswd())) {
            throw new UserAccountPasswdErrorException();

        }
        return userOptional.get();
    }
}
