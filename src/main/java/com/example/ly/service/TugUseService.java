package com.example.ly.service;
import com.example.ly.VO.TugUseDataVO;
import com.example.ly.VO.TugUseVO;

public interface TugUseService {
    public TugUseDataVO<TugUseVO> findData();
}