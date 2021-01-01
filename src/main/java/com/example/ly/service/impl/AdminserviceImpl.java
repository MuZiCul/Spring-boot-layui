package com.example.ly.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.ly.VO.AdminVO;
import com.example.ly.VO.UserVO;
import com.example.ly.entity.Identype;
import com.example.ly.entity.User;
import com.example.ly.mapper.IdentypeMapper;
import com.example.ly.mapper.UserMapper;
import com.example.ly.service.Adminservice;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminserviceImpl implements Adminservice {

    @Autowired
    private UserMapper userMapper;//用户信息
    @Autowired
    private IdentypeMapper identypeMapper;//身份信息
    @Override
    public UserVO<AdminVO> findData() {

        UserVO userVO=new UserVO();
        userVO.setCode(0);
        userVO.setMsg("");
        userVO.setCount(userMapper.selectCount(null));//总条数

        List<User> userList=userMapper.selectList(null);//旧用户列表
        List<AdminVO> userVOList = new ArrayList<>();//新用户列表
        for (User user : userList) {//遍历userList
            AdminVO adminVO = new AdminVO();
            BeanUtils.copyProperties(user,adminVO);//用户列表信息映射

            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("id",user.gettype());//gettype返回原用户信息中的type（应该返回的是type表中的id）
            Identype identype = identypeMapper.selectOne(wrapper);//根据id查type

            if(identype!=null){
                adminVO.setidentype(identype.getidtype());//设置adminvo中的type为查询后的type
            }
            userVOList.add(adminVO);//将userVO中的data数据添加到
        }
        userVO.setData(userVOList);//data数据
        return userVO;
    }
}
