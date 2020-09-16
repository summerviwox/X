package com.summer.app.model;

import java.io.Serializable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * record
 * @author 
 */
@Getter
@Setter
public class Record implements Serializable {
    private Integer id;

    private String locpath;

    private String netpath;

    private Long ctime;

    private Long utime;

    private String atype;

    private String btype;

    private String address;

    private Long duration;

    private String name;

    private String content;

    private Integer classify;

    private Integer enable;

    private Integer parentid;

    private Integer ctype;

    private String remark;

    private static final long serialVersionUID = 1L;
}