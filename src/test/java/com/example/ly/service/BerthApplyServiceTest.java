package com.example.ly.service;

import com.example.ly.VO.BerthApplyDataVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BerthApplyServiceTest {

    @Autowired
    private BerthApplyService berthApplyService;

    @Test
    void findData() {
        BerthApplyDataVO berthApplyDataVO=berthApplyService.findData();
        int i=1;
    }
}