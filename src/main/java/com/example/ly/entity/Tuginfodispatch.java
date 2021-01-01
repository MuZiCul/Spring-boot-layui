package com.example.ly.entity;

import lombok.Data;

@Data
public class Tuginfodispatch {
    private Integer mkey;
    private String dispatchid;//派工ID
    private String dispatchtime;//派工时间
    private String tugname;//拖轮名称
    private String kstime;//开始作业时间
    private String jstime;//结束作业时间
    private String scores;//积分
    private String finishtime;//完成时间
    private String submittime;//提交时间
    private String isagree;//对方加分是否同意
    private String reasons;//不同意理由
    private String reserve1;//留守/非留守
    private String reserve2;//修改/未修改
    private String reserve3;//驾驶员信息
    private String scoreaudit;//积分审核 未审核/已审核
    private String state;//拖轮状态 shenhe
    private String shenhe;
    public String getssscores() {
        return scores;
    }





}
