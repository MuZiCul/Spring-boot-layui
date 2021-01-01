package com.example.ly.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.ly.VO.BerthApplyDataVO;
import com.example.ly.VO.BerthApplyVO;
import com.example.ly.entity.*;
import com.example.ly.mapper.*;
import com.example.ly.service.BerthApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BerthApplyServiceImpl implements BerthApplyService {
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

    @Override
    public BerthApplyDataVO<BerthApplyVO> findData() {
        BerthApplyDataVO berthApplyDataVO=new BerthApplyDataVO();
        berthApplyDataVO.setCode(0);
        berthApplyDataVO.setMsg("");
        berthApplyDataVO.setCount(tugapplyMapper.selectCount(null));//总条数

        List<Tugapply> tugapplylist=tugapplyMapper.selectList(null);//旧用户列表
        List<BerthApplyVO> BerthApplyVOList = new ArrayList<>();//返回信息列表列表
        for (Tugapply tugapply : tugapplylist) {//遍历userList
            BerthApplyVO berthApplyVO = new BerthApplyVO();
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("id",tugapply.getId());//gettype返回原用户信息中的type（应该返回的是type表中的id）
            Tugapply tugapply1 = tugapplyMapper.selectOne(wrapper);//根据id查type
            if(tugapply1!=null){
                berthApplyVO.setshipname(tugapply1.getChinesename());//船名
                berthApplyVO.setaudittime(tugapply1.getApplydate());//申请时间
            }
            Inouttug inouttug = inouttugMapper.selectOne(wrapper);
            if(inouttug!=null){
                if (inouttug.getAboutport().equals("1")){
                    berthApplyVO.setinPort("进港");//进出港
                }else if (inouttug.getAboutport().equals("2")){
                    berthApplyVO.setinPort("出港");//进出港
                }else if (inouttug.getAboutport().equals("")){

                    berthApplyVO.setinPort("未填写");//进出港
                }{
                    berthApplyVO.setinPort(inouttug.getAboutport());//进出港
                }
            }else{
                berthApplyVO.setinPort("未填写");//进出港
            }
            Tugapplyoperationinfo tugapplyoperationinfo = tugapplyoperationinfoMapper.selectOne(wrapper);//根据id查type
            if(tugapplyoperationinfo!=null){
                if (tugapplyoperationinfo.getOperationarea().equals("")&&tugapplyoperationinfo.getOperationberth().equals("")){

                    berthApplyVO.setarea("未填写");//作业区域及泊位

                }else{
                    if (tugapplyoperationinfo.getOperationarea().equals("")||!tugapplyoperationinfo.getOperationberth().equals("")){
                        berthApplyVO.setarea(tugapplyoperationinfo.getOperationberth());//作业区域及泊位
                    }else if (!tugapplyoperationinfo.getOperationarea().equals("")&&!tugapplyoperationinfo.getOperationberth().equals("")){
                        berthApplyVO.setarea(tugapplyoperationinfo.getOperationarea()+"&"+tugapplyoperationinfo.getOperationberth());

                    }else {
                        berthApplyVO.setarea(tugapplyoperationinfo.getOperationarea());
                    }
                }
            }
            Tugapplyopecontent tugapplyopecontent = tugapplyopecontentMapper.selectOne(wrapper);
            if(tugapplyopecontent!=null){
                if (tugapplyopecontent.getTugnumberone().equals("")||tugapplyopecontent.getTugnumbertwo().equals("")){
                if (tugapplyopecontent.getTugnumberone().equals("")||!tugapplyopecontent.getTugnumbertwo().equals("")){
                    berthApplyVO.setmount(tugapplyopecontent.getTugnumbertwo());
                }else if (tugapplyopecontent.getTugnumberone().equals("")&&tugapplyopecontent.getTugnumbertwo().equals("")){
                    berthApplyVO.setmount("未填写");
                }else {
                berthApplyVO.setmount(tugapplyopecontent.getTugnumberone());
            }
                }else{
                    berthApplyVO.setmount(tugapplyopecontent.getTugnumberone()+"&"+tugapplyopecontent.getTugnumbertwo());

                }
            }
            Tugproposerinfo tugproposerinfo = tugproposerinfoMapper.selectOne(wrapper);//拖轮艘数
            if(tugproposerinfo!=null){
                if (tugproposerinfo.getProposername().equals("")){
                    berthApplyVO.setapplicant("未填写");
                }else{
                berthApplyVO.setapplicant(tugproposerinfo.getProposername());//申请人
                }
                if (tugproposerinfo.getSacompany().equals("")){
                    berthApplyVO.setcompany("未填写");//所属单位
                }else {
                berthApplyVO.setcompany(tugproposerinfo.getSacompany());//所属单位
            }}

            berthApplyVO.setstate("未派工");//派工状态
            berthApplyVO.setdetail("无详情");//详情
            BerthApplyVOList.add(berthApplyVO);//将userVO中的data数据添加到
        }
        berthApplyDataVO.setData(BerthApplyVOList);//data数据
        return berthApplyDataVO;
    }
}
