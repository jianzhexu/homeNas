package com.gt.homeNas.types;

import lombok.Getter;

@Getter
public class UserName {
    private String name;

    public UserName(String name) {
        this.name = name;
    }
}
