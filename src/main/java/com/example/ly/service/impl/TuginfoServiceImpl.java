package com.example.ly.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.ly.VO.TugUseDataVO;
import com.example.ly.VO.TugUseVO;
import com.example.ly.VO.TuginfoDataVO;
import com.example.ly.VO.TuginfoVO;
import com.example.ly.entity.*;
import com.example.ly.mapper.*;
import com.example.ly.service.TuginfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class TuginfoServiceImpl implements TuginfoService {
    @Autowired
    private TuginfodispatchMpper tuginfodispatchMpper;
    @Autowired
    private InouttugMapper inouttugMapper;
    @Autowired
    private TugapplyoperationinfoMapper tugapplyoperationinfoMapper;
    @Autowired
    private TugapplyopecontentMapper tugapplyopecontentMapper;
    @Autowired
    private TugproposerinfoMapper tugproposerinfoMapper;
    @Autowired
    private TugapplymsginfoMapper tugapplymsginfoMapper;

    @Override
    public TuginfoDataVO<TuginfoVO> findData() {
        TuginfoDataVO tuginfoDataVO=new TuginfoDataVO();
        tuginfoDataVO.setCode(0);
        tuginfoDataVO.setMsg("");
        tuginfoDataVO.setCount(30);

        List<Tuginfodispatch> tuginfodispatchList=tuginfodispatchMpper.selectList(null);//旧用户列表
        List<TuginfoVO> tuginfoVOS = new ArrayList<>();//返回信息列表列表
        for (Tuginfodispatch tuginfodispatch : tuginfodispatchList) {//遍历userList
            TuginfoVO tuginfoVO = new TuginfoVO();
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("id",tuginfodispatch.getMkey());//gettype返回原用户信息中的type（应该返回的是type表中的id）
            if (tuginfodispatch.getMkey()!=null){
            if (!tuginfodispatch.getMkey().equals("")){
                tuginfoVO.setid(tuginfodispatch.getMkey());//序号
            }}else {
                tuginfoVO.setid(0);//序号
            }
            if (tuginfodispatch.getDispatchid()!=null){
            if (!tuginfodispatch.getDispatchid().equals("")){
                tuginfoVO.setshipname("理工拖轮"+tuginfodispatch.getDispatchid());//船名
            }else{
                tuginfoVO.setshipname("未填写");//船名
            }}else {
                tuginfoVO.setshipname("未填写");//船名
            }
            if (tuginfodispatch.getDispatchtime()!=null){
            if (!tuginfodispatch.getDispatchtime().equals("")) {

                tuginfoVO.setsubtime(tuginfodispatch.getDispatchtime());//提交时间
            }else{
                tuginfoVO.setsubtime("未填写");//提交时间
            }}else{
                tuginfoVO.setsubtime("未填写");//提交时间

            }
            if (tuginfodispatch.getKstime()!=null){
            if (!tuginfodispatch.getKstime().equals("")) {
                tuginfoVO.setstime(tuginfodispatch.getKstime());//开始时间
            }else{
                tuginfoVO.setstime("未开始");//开始时间
            }}else {
                tuginfoVO.setstime("未开始");//开始时间
            }
            if (tuginfodispatch.getJstime()!=null){
            if (!tuginfodispatch.getJstime().equals("")) {
                tuginfoVO.setetime(tuginfodispatch.getJstime());//结束时间
            }else if(tuginfodispatch.getJstime().equals("-1")){
                tuginfoVO.setetime("未结束");//结束时间
            }else{
                tuginfoVO.setetime("未结束");//结束时间
            }
            }else {
                tuginfoVO.setetime("未结束");//结束时间
            }
            if (tuginfodispatch.getJstime()!=null||tuginfodispatch.getKstime()!=null){
            if (tuginfodispatch.getJstime().equals("")||tuginfodispatch.getKstime().equals("")) {

                tuginfoVO.setworktime("未开始或未结束");//作业时长
                }else{
                long WorkTime = countTime(tuginfodispatch.getKstime(), tuginfodispatch.getJstime());
                tuginfoVO.setworktime(String.valueOf(WorkTime / 3600));//工作时间
            }}else {
                tuginfoVO.setworktime("未开始或未结束");//作业时长
            }
            if (tuginfodispatch.getssscores()!=null){
            if (!tuginfodispatch.getssscores().equals("")) {
                tuginfoVO.setscore(tuginfodispatch.getssscores());//本次积分
            }else{
                tuginfoVO.setscore("未积分");//本次积分
            }}else {
                tuginfoVO.setscore("未积分");//本次积分
            }
            if (tuginfodispatch.getReserve3()!=null){
            if (!tuginfodispatch.getReserve3().equals("")) {
                tuginfoVO.setdriverinfo(tuginfodispatch.getReserve3());//驾驶员信息
            }else{
                tuginfoVO.setdriverinfo("未填写");//驾驶员信息
            }
            }else {
                tuginfoVO.setdriverinfo("未填写");//驾驶员信息
            }
            if(tuginfodispatch.getShenhe()!=null)
            {if (tuginfodispatch.getShenhe().equals("S")) {
                tuginfoVO.setscoreaudit("已审核");//积分审核
            }else {
                tuginfoVO.setscoreaudit("未审核");//积分审核
            }
            }else {
                tuginfoVO.setscoreaudit("未审核");//积分审核

            }
            tuginfoVOS.add(tuginfoVO);//将userVO中的data数据添加到
        }
        tuginfoDataVO.setData(tuginfoVOS);//data数据
        return tuginfoDataVO;
    }

    public long countTime(String date1,String date2){
        long time=0;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Date d1;
        Date d2;
        try {
            d1=sdf.parse(date1);
            d2=sdf.parse(date2);
            long daysBetween=(d2.getTime()-d1.getTime())/1000;
            time=daysBetween;
            //System.out.println("相隔 "+daysBetween+"s");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return time;
    }
}
