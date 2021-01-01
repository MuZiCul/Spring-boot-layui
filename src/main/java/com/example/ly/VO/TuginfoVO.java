package com.example.ly.VO;

import lombok.Data;

@Data
public class TuginfoVO {

    private Integer id;//id
    private String shipname;//船名
    private String subtime;//提交时间
    private String stime;//开始时间
    private String etime;//结束时间
    private String worktime;//工作时间
    private String score;//本次分数
    private String driverinfo;//驾驶员信息
    private String scoreaudit;//积分审核
    public void setid(Integer qaz) {
        id=qaz;
    }
    public void setshipname(String A) {
        shipname=A;
    }
    public void setsubtime(String A4) {      subtime=A4;    }
    public void setstime(String B) {        stime=B;    }
    public void setetime(String C) {
        etime=C;
    }

    public void setworktime(String D) {
        worktime=D;
    }

    public void setscore(String E) {
        score=E;
    }

    public void setdriverinfo(String F) {
        driverinfo=F;
    }

    public void setscoreaudit(String G) {
        scoreaudit=G;
    }

}
