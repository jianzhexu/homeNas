package com.gt.homeNas.domain;

import com.gt.homeNas.domain.entity.User;
import com.gt.homeNas.types.UserAccount;
import com.gt.homeNas.types.UserId;

import java.util.Optional;

public interface UserRepository {

    public Optional<User> findByAccount(UserAccount userAccount);
    public Optional<User> findById(UserId userId);
}
