package com.example.ly.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.ly.VO.BerthApplyVO;
import com.example.ly.VO.TugUseDataVO;
import com.example.ly.VO.TugUseVO;
import com.example.ly.entity.*;
import com.example.ly.mapper.*;
import com.example.ly.service.TugUseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TugUseServiceImpl implements TugUseService {
    @Autowired
    private TugapplyMapper tugapplyMapper;
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
    public TugUseDataVO<TugUseVO> findData() {
        TugUseDataVO tugUseDataVO=new TugUseDataVO();
        tugUseDataVO.setCode(0);
        tugUseDataVO.setMsg("");
        tugUseDataVO.setCount(30);

        List<Tugapply> tugapplylist=tugapplyMapper.selectList(null);//旧用户列表
        List<TugUseVO> tugUseVOS = new ArrayList<>();//返回信息列表列表
        for (Tugapply tugapply : tugapplylist) {//遍历userList
            TugUseVO tugUseVO = new TugUseVO();
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("id",tugapply.getId());//gettype返回原用户信息中的type（应该返回的是type表中的id）
            tugUseVO.setid(tugapply.getId());//审核状态
            Tugapply tugapply1 = tugapplyMapper.selectOne(wrapper);//根据id查type
            if(tugapply1!=null){
                tugUseVO.setshipname(tugapply1.getChinesename());//船名
                tugUseVO.setaudittime(tugapply1.getApplydate());//申请时间
            }else {
                tugUseVO.setshipname("未填写");//船名
                tugUseVO.setaudittime("未填写");//申请时间
            }
            Tugapplymsginfo tugapplymsginfo = tugapplymsginfoMapper.selectOne(wrapper);//根据id查type
            if(tugapplymsginfo!=null) {
                if ("00".equals(tugapplymsginfo.getChargestate())) {
                    tugUseVO.setaudittime("未审核");//审核状态
                } else if (tugapplymsginfo.getChargestate().equals("01")) {
                    tugUseVO.setaudittime("（已核查）未计费");//审核状态
                } else if (tugapplymsginfo.getChargestate().equals("10")) {
                    tugUseVO.setaudittime("正在计费");//审核状态
                } else if (tugapplymsginfo.getChargestate().equals("11")) {
                    tugUseVO.setaudittime(tugapplymsginfo.getApplydate());//审核时间
                } else {
                    tugUseVO.setaudittime(tugapplymsginfo.getApplydate());//审核状态
                }
            }else{
                tugUseVO.setaudittime("未填写");//审核状态
            }
            Inouttug inouttug = inouttugMapper.selectOne(wrapper);
            if(inouttug!=null){
                if (inouttug.getAboutport().equals("1")){
                    tugUseVO.setinPort("进港");//进出港
                }else if (inouttug.getAboutport().equals("2")){
                    tugUseVO.setinPort("出港");//进出港
                }else {
                    tugUseVO.setinPort(inouttug.getAboutport());//进出港
                }
            }else{
                tugUseVO.setinPort("未选择");//进出港
            }
            Tugapplyoperationinfo tugapplyoperationinfo = tugapplyoperationinfoMapper.selectOne(wrapper);//根据id查type
            if(tugapplyoperationinfo!=null){
                if (tugapplyoperationinfo.getOperationarea().equals("")||tugapplyoperationinfo.getOperationberth().equals("")){
                    tugUseVO.setarea("未填写");
                }else {
                    tugUseVO.setarea(tugapplyoperationinfo.getOperationarea() + "&" + tugapplyoperationinfo.getOperationberth());//作业区域及泊位

                }
                }else {
                tugUseVO.setarea("未填写");
            }
            Tugapplyopecontent tugapplyopecontent = tugapplyopecontentMapper.selectOne(wrapper);
            if(tugapplyopecontent!=null) {
                if (tugapplyopecontent.getTugnumberone().equals("") || tugapplyopecontent.getTugnumbertwo().equals("")) {
                    tugUseVO.setmount("未填写");
                } else {
                    tugUseVO.setmount(tugapplyopecontent.getTugnumberone() + "&" + tugapplyopecontent.getTugnumbertwo());
                }
            }else {
                tugUseVO.setmount("未填写");
            }
            Tugproposerinfo tugproposerinfo = tugproposerinfoMapper.selectOne(wrapper);//拖轮艘数
            if(tugproposerinfo!=null) {
                if (tugproposerinfo.getProposername().equals("")) {
                    tugUseVO.setapplicant("未填写");//申请人
                } else if (tugproposerinfo.getSacompany().equals("")){
                    tugUseVO.setcompany("未填写");//所属单位
                    }else{
                    tugUseVO.setapplicant(tugproposerinfo.getProposername());//申请人
                    tugUseVO.setcompany(tugproposerinfo.getSacompany());//所属单位
                }
            }else{
                tugUseVO.setapplicant("未填写");//申请人
                tugUseVO.setcompany("未填写");//所属单位
            }

            tugUseVO.setstate("未派工");//派工状态
            tugUseVO.setdetail("无详情");//详情
            tugUseVOS.add(tugUseVO);//将userVO中的data数据添加到
        }
        tugUseDataVO.setData(tugUseVOS);//data数据
        return tugUseDataVO;
    }
}
