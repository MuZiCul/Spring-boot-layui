package com.example.ly.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ly.VO.DataVO;
import com.example.ly.VO.ProductVO;
import com.example.ly.entity.Product;
import com.example.ly.entity.ProductCategory;
import com.example.ly.mapper.ProductCategoryMapper;
import com.example.ly.mapper.ProductMapper;
import com.example.ly.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProduceServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public DataVO<ProductVO> findData(Integer page,Integer limit) {
        DataVO dataVO=new DataVO();
        dataVO.setCode(0);
        dataVO.setMsg("");
        IPage<Product> productIPage = new Page<>(page,limit);
        IPage<Product> result = productMapper.selectPage(productIPage,null);

        dataVO.setCount(result.getTotal());
        List<Product> productList=result.getRecords();
        List<ProductVO> productVOList = new ArrayList<>();
        for (Product product : productList) {

            ProductVO productVO = new ProductVO();
            BeanUtils.copyProperties(product,productVO);

            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("id",product.getCategoryleveloneId());
            ProductCategory productCategory = productCategoryMapper.selectOne(wrapper);

            if(productCategory!=null){
                productVO.setCategorylevelone(productCategory.getName());
            }
            productVOList.add(productVO);
        }

        dataVO.setData(productVOList);

        return dataVO;
    }
}
