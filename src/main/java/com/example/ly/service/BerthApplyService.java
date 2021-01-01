package com.example.ly.service;

import com.example.ly.VO.BerthApplyDataVO;
import com.example.ly.VO.BerthApplyVO;
import com.example.ly.VO.DataVO;
import com.example.ly.VO.ProductVO;

public interface BerthApplyService {
    public BerthApplyDataVO<BerthApplyVO> findData();
}
