package com.example.ly.VO;

import lombok.Data;

import java.util.List;

@Data
public class UserVO<T> {//json数据
    private Integer code;
    private String msg;
    private Integer count;
    private List<T> data;
}
