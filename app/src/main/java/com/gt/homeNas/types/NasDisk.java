package com.gt.homeNas.types;

import java.io.File;

public class NasDisk {

    private String path;

    private Integer size;

    private File file;

    public NasDisk(String path) {
        this.path = path;
    }
}
