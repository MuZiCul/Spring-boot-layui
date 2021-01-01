package com.example.ly.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ly.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper extends BaseMapper<Product> {
}
