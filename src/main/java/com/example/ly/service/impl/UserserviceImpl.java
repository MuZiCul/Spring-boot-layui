package com.example.ly.service.impl;

import com.example.ly.domian.User;
import com.example.ly.dao.UserDao;
import com.example.ly.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserserviceImpl implements Userservice {
    @Autowired
    private UserDao dao;

    @Override
    public void save(User use) {
        // TODO Auto-generated method stub
        dao.save(use);
    }



}
