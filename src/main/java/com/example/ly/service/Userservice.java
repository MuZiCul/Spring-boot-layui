package com.example.ly.service;

import com.example.ly.dao.UserDao;
import com.example.ly.domian.User;
import org.springframework.beans.factory.annotation.Autowired;

public interface Userservice {
    void save(User user);
}

