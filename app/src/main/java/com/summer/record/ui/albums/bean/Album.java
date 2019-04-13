package com.summer.record.ui.albums.bean;

import com.summer.x.base.ui.VA;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Album extends VA {

    private Integer id;

    private String name;

    private String head;

    private Long ctime;

    private Long utime;
}
