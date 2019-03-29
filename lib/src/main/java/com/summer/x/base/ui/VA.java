package com.summer.x.base.ui;

import java.io.Serializable;

public abstract class VA implements Serializable {

    public VA(){

    }

    /**
     * 不建议在此初始化大量数据 会卡顿
     */
    public abstract void initVA();
}
