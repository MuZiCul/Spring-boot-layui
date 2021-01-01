package com.example.ly.service;

import com.example.ly.VO.DataVO;
import com.example.ly.VO.ProductVO;

public interface ProductService {
    public DataVO<ProductVO> findData(Integer page,Integer limit);
}
