package com.example.ly.service;

import com.example.ly.VO.UserVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AdminserviceTest {
    @Autowired
    private Adminservice adminservice;
    @Test
    void findData() {
        UserVO dataVO=adminservice.findData();
        int i=1;
    }
}