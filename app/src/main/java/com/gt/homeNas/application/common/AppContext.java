package com.gt.homeNas.application.common;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.gt.homeNas.domain.entity.User;
import com.gt.homeNas.types.UserAccount;
import com.gt.homeNas.types.UserId;
import com.gt.homeNas.types.UserName;
import com.gt.homeNas.types.UserPasswd;
import com.gt.homeNas.types.exception.UserAccountInvalidError;
import com.gt.homeNas.types.exception.UserPasswdInvalidError;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class AppContext {
    private Map<String, StageManager<?>> stageManagerMap = new HashMap<>();
    public static Cache<String, Object> CACHE = null;
    static {
        CACHE = CacheBuilder.newBuilder()
                .concurrencyLevel(5) //设置并发级别为8，并发级别是指可以同时写缓存的线程数
                .initialCapacity(10) //设置缓存容器的初始容量为10;
                .maximumSize(100) //设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
                .recordStats().build(new CacheLoader<String, Object>() {
                    @Override
                    public Object load(String key) throws Exception {
                        if ("USER".equals(key)) {
                            try {
                                return new User(new UserName("缓存用户"), new UserAccount("123"), new UserPasswd("123"),new UserId("123"));
                            } catch (UserAccountInvalidError | UserPasswdInvalidError userAccountInvalidError) {
                                userAccountInvalidError.printStackTrace();
                            }
                        }
                        return null;
                    }
                }); //是否需要统计缓存情况,该操作消耗一定的性能,生产环境应该去除
    }

    protected AppContext getController(String fxmlPath) {
        return this.stageManagerMap.get(fxmlPath).getT();
    }

    protected StageManager<AppContext> newWindow(String fxmlPath) {
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(fxmlPath)));
        StageManager<AppContext> stage = new StageManager<>();
        this.stageManagerMap.put(fxmlPath, stage);
        try {
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setT(fxmlLoader.getController());
            return stage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void initData() {

    }
}
