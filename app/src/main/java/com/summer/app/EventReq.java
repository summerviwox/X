package com.summer.app;

import com.summer.x.base.ui.DA;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventReq extends DA {

    private long startTime;

    private long endTime;

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}