package com.example.ly.dao;

import com.example.ly.domian.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(User use) {
        // TODO Auto-generated method stub
        //jdbcTemplate.update("insert into user (name,password) values(?,?)", ,);
        Date d=new Date();//获取注册时间
        String sql="insert into user (name,password,type,time) values (?,?,?,?)";

        List<Object[]> batchArgs=new ArrayList<Object[]>();
        batchArgs.add(new Object[]{
                use.getName(),use.getPassword(),"1",d
        });
        jdbcTemplate.batchUpdate(sql, batchArgs);
    }

}
