package com.example.ly.controller;
import com.example.ly.VO.BerthApplyDataVO;
import com.example.ly.domian.User;
import com.example.ly.mapper.UserMapper;
import com.example.ly.service.Adminservice;
import com.example.ly.service.BerthApplyService;
import com.example.ly.service.impl.UserserviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import com.example.ly.VO.UserVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ch.qos.logback.core.joran.action.ActionConst.NULL;

@RestController
public class UserController {
    @Autowired
    private UserMapper mapper;
    @Autowired
    private UserserviceImpl userservice;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private Adminservice adminservice;

    @RequestMapping("/delUser")
    @ResponseBody
    public Map<String, String> delUser(Integer userId) {
        Map<String, String> ret = new HashMap<String, String>();
        String sql = "delete from user where id='" + userId + "'";
        int rows = jdbcTemplate.update(sql);
        if (rows==1){
            ret.put("type", "success");
            return ret;
        }else{
            ret.put("type", "fail");
            return ret;
        }

    }

    @RequestMapping("/edit")
    @ResponseBody
    public Map<String, String> edit(Integer id, String name,String password,String identype) {
     /*   {id: 1, name: "46546", password: "456464", identype: "普通用户", time: null}*/
        Map<String, String> ret = new HashMap<String, String>();
        System.out.println("ID是："+id);
        System.out.println("用户名是："+name);
        String sql = "select name from user where name = '" + name + "'";
        List list = jdbcTemplate.queryForList(sql);
        int n = list.size();
        if (n <= 0) {
            String sqlName = "update user set name = '" + name + "' where id = '" + id + "'";
            int Namesql = jdbcTemplate.update(sqlName);
            System.out.println("密码是："+password);
            String sqlPassword = "update user set password = '" + password + "' where id = '" + id + "'";
            int Passwordsql = jdbcTemplate.update(sqlPassword);
            System.out.println("类型是："+identype);
            String sqlIdentype=null;
            if (identype!="普通用户"||identype!="超级用户"){
                sqlIdentype = "update user set type = 1 where id = '" + id + "'";
            }else if(identype!="普通用户"){
                sqlIdentype = "update user set type = 1 where id = '" + id + "'";
            }else if(identype!="超级用户"){
                sqlIdentype = "update user set type = 2 where id = '" + id + "'";
            }else {
                sqlIdentype = "update user set type = 1 where id = '" + id + "'";
            }
            int Identypesql = jdbcTemplate.update(sqlIdentype);
            if(Namesql+Passwordsql+Identypesql==3){
                ret.put("type", "success");
                System.out.println("修改成功");
                return ret;
            }else if(Namesql!=1){
                ret.put("error", "用户名修改失败！");
                System.out.println("用户名修改失败");
            }else if(Passwordsql!=1){
                ret.put("error", "密码修改失败！");
                System.out.println("用户密码修改失败");
            }else if(Identypesql!=1){
                ret.put("error", "用户身份修改失败！");
                System.out.println("用户身份修改失败");
            }else {
                ret.put("fail", "修改失败，未知错误，请联系管理员处理！");
                System.out.println("未知错误！");
            }
        } else {
            ret.put("type", "exist");
            return ret;
        }


        return ret;
    }

    @RequestMapping("/rrrpassword")
    @ResponseBody
    public Map<String, String> Rpassword(String username, String rpassword) {
        System.out.println(username+"用户开始修改密码！");
        Map<String, String> ret = new HashMap<String, String>();
        //调用jdbcTemplate对象的方法实现修改操作
        String sql = "update user set password = '" + rpassword + "' where name = '" + username + "'";
        int rows = jdbcTemplate.update(sql);
        if (rows==1){
            System.out.println(username+"用户修改密码结束！");
            ret.put("type", "success");
            return ret;
        }else{
            ret.put("type", "fail");
            return ret;
        }
    }


    @RequestMapping("/save")
    @ResponseBody
    public Map<String, String> save(String registername, String registerpassword) {
        Map<String, String> ret = new HashMap<String, String>();
        String sql = "select * from user where name = '" + registername + "'";
        List list = jdbcTemplate.queryForList(sql);
        int n = list.size();
        if (n <= 0) {
            User user = new User(registername, registerpassword);
            userservice.save(user);
            ret.put("type", "success");
            return ret;
        } else {
            ret.put("type", "fail");
            return ret;
        }

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> loginAct(String username, String password) {
        Map<String, String> ret = new HashMap<String, String>();
        if (username == null||password == null) {
            ret.put("type", "error");
            return ret;
        }
        String sql = "select * from user where name = '" + username + "'";
        List list = jdbcTemplate.queryForList(sql);
        int nusername = list.size();
        if (nusername <= 0) {
                ret.put("type", "fail");
                return ret;
        } else {
            String sqltype = "select type from user where name = '" + username + "'";
            List listtype = jdbcTemplate.queryForList(sqltype);

            System.out.println(listtype);
                if(Substring(list,"password",8,",",1).equals(password)){
                    ret.put("identity", String.valueOf(listtype));
                    ret.put("password", Substring(list,"password",8,",",1));
                    ret.put("type", "success");
                }else {
                    ret.put("type", "PasswordError");
                }
                return ret;
        }
    }
    public String Substring(List list,String firstWord,int firstLength,String secondWord,int secondLength){
        System.out.println("*********************"+list);
        mapper.selectList(null).forEach(System.out::println);

        System.out.println("***Begin***");
        String l= String.valueOf(list);

        System.out.println("取"+l+"在"+firstWord+"和"+secondWord+"之间的字符串");
        int beginIndex=0,endIndex=0,tee=0,nee=0;
        String midpassword=NULL;
        String rpassword=NULL;;
        for(int i=0;i<1000;i++){//找第一个字符：
            beginIndex=i;
            endIndex=i+firstLength;
            midpassword=l.substring(beginIndex,endIndex);

            if(firstWord.equals(midpassword)){
                tee=i+9;
                System.out.println("第一个词："+midpassword);
                System.out.println("第一个词位置:"+tee);
                break;
            }
        }
        for(int i=tee;i<1000;i++){//找第二个字符
            beginIndex=i;
            endIndex=i+secondLength;
            midpassword=l.substring(beginIndex,endIndex);

            if(secondWord.equals(midpassword)){
                nee=beginIndex;
                System.out.println("第二个词："+midpassword);
                System.out.println("第二个词位置:"+nee);
                break;
            }
        }
        rpassword=l.substring(tee,nee);
        System.out.println("答案："+rpassword);
        System.out.println("***End***");
        return rpassword;

    }
    @RequestMapping("/userlist")
    public UserVO auserList(){
        return adminservice.findData();
    }

}
