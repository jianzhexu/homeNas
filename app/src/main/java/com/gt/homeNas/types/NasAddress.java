package com.gt.homeNas.types;

import lombok.Getter;

import static com.google.common.base.Preconditions.checkNotNull;

@Getter
public class NasAddress {
    private final String host;
    private final int port;

    public NasAddress(String host, Integer port) {
        this.host = checkNotNull(host);
        this.port = checkNotNull(port);
    }
}
