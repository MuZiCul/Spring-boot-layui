package com.example.ly.VO;

import lombok.Data;

@Data
public class AdminVO {
    private Integer id;
    private String name;
    private String password;
    private String identype;
    private String time;
    public void setidentype(String idtype) {
        identype=idtype;

    }


}
