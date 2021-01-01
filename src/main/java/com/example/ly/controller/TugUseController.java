package com.example.ly.controller;

import com.example.ly.VO.BerthApplyDataVO;
import com.example.ly.VO.TugUseDataVO;
import com.example.ly.service.BerthApplyService;
import com.example.ly.service.TugUseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TugUseController {
    @Autowired
    private TugUseService tugUseService;

    @RequestMapping("/tuguselist")
    public TugUseDataVO tugUseDataVO(){
        return tugUseService.findData();
    }
}
