package com.example.ly.service;

import com.example.ly.VO.DataVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService service;
    @Test
    void findDta() {
   //     DataVO dataVO=service.findData();
    }
}