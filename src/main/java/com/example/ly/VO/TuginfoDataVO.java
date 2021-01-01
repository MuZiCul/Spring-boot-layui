package com.example.ly.VO;

import lombok.Data;

import java.util.List;

    @Data
    public class TuginfoDataVO<T> {
        private Integer code;
        private  String msg;
        private  long count;
        private List<T> data;
}
