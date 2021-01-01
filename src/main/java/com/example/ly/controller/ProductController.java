package com.example.ly.controller;

import com.example.ly.VO.DataVO;
import com.example.ly.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/list")
    public DataVO list(Integer page,Integer limit){
        return productService.findData(page,limit);
    }

}
