package com.gt.homeNas.Infrastructure.repository;

import com.gt.homeNas.domain.UserRepository;
import com.gt.homeNas.domain.entity.User;
import com.gt.homeNas.types.UserAccount;
import com.gt.homeNas.types.UserId;
import com.gt.homeNas.types.UserName;
import com.gt.homeNas.types.UserPasswd;
import com.gt.homeNas.types.exception.UserAccountInvalidError;
import com.gt.homeNas.types.exception.UserPasswdInvalidError;

import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public Optional<User> findByAccount(UserAccount userAccount) {
        if ("123".equals(userAccount.getUserAccount())) {
            try {
                return Optional.of(new User(new UserName("张三"), new UserAccount("123"),new UserPasswd("123"),new UserId("123")));
            } catch (UserAccountInvalidError | UserPasswdInvalidError userAccountInvalidError) {
                userAccountInvalidError.printStackTrace();
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(UserId userId) {
        try {
            return Optional.of(new User(new UserName("张三"), new UserAccount("123"),new UserPasswd("123"),new UserId("123")));
        } catch (UserAccountInvalidError | UserPasswdInvalidError userAccountInvalidError) {
            userAccountInvalidError.printStackTrace();
        }
        return Optional.empty();
    }
}
