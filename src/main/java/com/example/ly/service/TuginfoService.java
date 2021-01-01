package com.example.ly.service;

import com.example.ly.VO.TuginfoDataVO;
import com.example.ly.VO.TuginfoVO;

public interface TuginfoService {
    public TuginfoDataVO<TuginfoVO> findData();
}
