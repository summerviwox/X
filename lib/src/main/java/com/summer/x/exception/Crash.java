package com.summer.x.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Crash {
    private Integer id;

    private Long createdtime;

    private String platform;

    private String timestr;

    private String user;

    private String error;
}