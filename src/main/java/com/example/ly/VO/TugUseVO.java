package com.example.ly.VO;

import lombok.Data;

@Data
public class TugUseVO {

    private Integer id;//船名
    private String shipname;//船名
    private String audittime;//审核日期
    private String inPort;//进出港
    private String area;//作业区域及泊位
    private String mount;//拖轮搜数
    private String applicant;//申请人
    private String company;//所属船代
    private String state;//派工状态
    private String detail;//详情
    public void setid(Integer qaz) {
        id=qaz;
    }
    public void setshipname(String A) {
        shipname=A;
    }

    public void setaudittime(String B) {
        audittime=B;
    }

    public void setinPort(String C) {
        inPort=C;
    }

    public void setarea(String D) {
        area=D;
    }

    public void setmount(String E) {
        mount=E;
    }

    public void setapplicant(String F) {
        applicant=F;
    }

    public void setcompany(String G) {
        company=G;
    }

    public void setstate(String H) {
        state=H;
    }

    public void setdetail(String I) {
        detail=I;
    }
}
