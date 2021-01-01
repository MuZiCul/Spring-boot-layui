package com.example.ly.controller;

import com.example.ly.VO.TugUseDataVO;
import com.example.ly.VO.TuginfoDataVO;
import com.example.ly.service.TugUseService;
import com.example.ly.service.TuginfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TuginfoController {
    @Autowired
    private TuginfoService tuginfoService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/tuginfolist")
    public TuginfoDataVO tuginfoDataVO(){
        return tuginfoService.findData();
    }

    @RequestMapping("/tuginfoget")
    @ResponseBody
    public Map<String, String> tuginfoget(Integer id,String sco) {
        Map<String, String> ret = new HashMap<String, String>();
        String sqlsoc = "update tuginfodispatch set scores = '" + sco + "' where mkey = '" + id + "'";
        String sqlshenhe = "update tuginfodispatch set shenhe ='S'     where mkey = '" + id + "'";
        jdbcTemplate.update(sqlsoc);
        jdbcTemplate.update(sqlshenhe);
        if ((jdbcTemplate.update(sqlsoc)+jdbcTemplate.update(sqlshenhe))==2){
            ret.put("type", "success");
            return ret;
        }else{
            ret.put("type", "fail");
            return ret;

        }

    }

}
