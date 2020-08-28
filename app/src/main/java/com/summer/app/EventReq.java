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

}