package com.example.ly.domian;

import com.example.ly.dao.UserDao;
import com.example.ly.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;

public class User {
    private Integer id;
    private String Registername;
    private String Registerpassword;
    public User(String name2,String name3) {
        // TODO Auto-generated constructor stub
        this.Registername = name2;
        this.Registerpassword = name3;
    }
    public String getName() {
        return Registername;
    }
    public String getPassword() {
        return Registerpassword;
    }
    public void setName(String Registername) {
        this.Registername = Registername;
    }
    public void setPassword(String Registerpassword) {
        this.Registerpassword = Registerpassword;
    }


}
