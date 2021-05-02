package com.gt.homeNas.application.common;

import javafx.stage.Stage;

public class StageManager<T extends AppContext> extends Stage {
    private T t;

    public StageManager(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public StageManager() {
    }

    public void setT(T t) {
        this.t = t;
    }
}
