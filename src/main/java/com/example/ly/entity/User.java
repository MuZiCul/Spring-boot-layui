package com.example.ly.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private String password;
    private Integer type;//应该是user.type.id，typeid才准确
    private String time;
    public int gettype() {return type;}
    public int id() {return id;}
    public String name() {return name;}
    public String time() {return time;}



}

