package com.example.ly.controller;

import com.example.ly.VO.BerthApplyDataVO;
import com.example.ly.service.BerthApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BerthApplyController {

    @Autowired
    private BerthApplyService berthApplyService;

    @RequestMapping("/portlist")
    public BerthApplyDataVO berthApplyDataVO(){
        return berthApplyService.findData();
    }
}
