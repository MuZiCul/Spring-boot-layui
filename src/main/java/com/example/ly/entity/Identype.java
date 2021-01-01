package com.example.ly.entity;

import lombok.Data;

@Data
public class Identype {
    private Integer id;
    private String idtype;

    public String getidtype() {
        return idtype;
    }
}
