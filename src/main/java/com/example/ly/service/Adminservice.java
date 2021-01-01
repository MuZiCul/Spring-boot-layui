package com.example.ly.service;

import com.example.ly.VO.AdminVO;
import com.example.ly.VO.DataVO;
import com.example.ly.VO.ProductVO;
import com.example.ly.VO.UserVO;

public interface Adminservice {
    public UserVO<AdminVO> findData();
}
