package com.example.ly.entity;

import lombok.Data;

@Data
public class portregisterinfo {
    private String voyageid;
    private String startport;
    private String destport;
    private String preport;
    private String nextport;
    private String prearrivaltime;
    private String predeparttime;
    private String planberthtime;
    private String planunberthtime;
    private String tradetype;
    private String remark;
}