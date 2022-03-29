package com.summer.app;

import com.summer.x.base.ui.VA;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventReq extends VA {

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