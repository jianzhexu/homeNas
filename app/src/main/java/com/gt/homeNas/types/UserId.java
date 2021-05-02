package com.gt.homeNas.types;

import lombok.Data;
import lombok.Getter;

@Getter
public class UserId {

    private final String id;

    public UserId(String id) {
        this.id = id;
    }
}
