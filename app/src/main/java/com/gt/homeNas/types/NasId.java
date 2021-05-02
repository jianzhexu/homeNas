package com.gt.homeNas.types;

import lombok.Getter;

@Getter
public class NasId {

    private final String id;

    public NasId(String id) {
        this.id = id;
    }
}
