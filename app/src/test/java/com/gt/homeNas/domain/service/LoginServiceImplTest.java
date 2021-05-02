package com.gt.homeNas.domain.service;

import com.gt.homeNas.domain.UserRepository;
import com.gt.homeNas.domain.entity.User;
import com.gt.homeNas.domain.exception.UserAccountNotFoundException;
import com.gt.homeNas.domain.exception.UserAccountPasswdErrorException;
import com.gt.homeNas.domain.service.impl.LoginServiceImpl;
import com.gt.homeNas.types.UserAccount;
import com.gt.homeNas.types.UserId;
import com.gt.homeNas.types.UserName;
import com.gt.homeNas.types.UserPasswd;
import com.gt.homeNas.types.exception.UserAccountInvalidError;
import com.gt.homeNas.types.exception.UserPasswdInvalidError;
import junit.framework.TestCase;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class LoginServiceImplTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void testGeneralLogin() throws UserAccountInvalidError, UserPasswdInvalidError {
        UserAccount userAccount = new UserAccount("123123");
        UserPasswd userPasswd = new UserPasswd("1231");
        UserId userId = new UserId("123123");
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByAccount(userAccount)).thenReturn(Optional.of(new User(new UserName("张三"),
                userAccount,userPasswd,userId
        )));
        LoginService loginService = new LoginServiceImpl(userRepository);
        try {
            User user = loginService.generalLogin(userAccount,userPasswd);
            assertEquals("用户登录成功",user.getPasswd(), userPasswd.getUserPasswd());
        } catch (UserAccountPasswdErrorException | UserAccountNotFoundException e) {
            e.printStackTrace();
        }
    }
}