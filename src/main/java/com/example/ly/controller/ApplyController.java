package com.example.ly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ApplyController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping( "/tug")
    @ResponseBody
    public Map<String, String> tug(
    String TugApplyID,
    String ApplyDate,
    String ChineseName,
    String MMSI,
    String Nationality,
    String EnglishName,
    String CallSign,
    String IMO,
    String ShipTypeCode,
    String Length,
    String ShipWidth,
    String GrossTon,
    String NetTon,
    String DeadWeightTon,
    String UpdateTime,
    String IsInport,
    String IsRespAgree,
    String RespAgreeMsg,
    String VoyageID,
    String StartPort,
    String DestPort,
    String PrePort,
    String NextPort,
    String PreArrivalTime,
    String PreDepartTime,
    String PlanBerthTime,
    String PlanUnberthTime,
    String TradeType,
    String AboutPort,
    String LoadorUnload,
    String CargoTon,
    String ArrivalDraftFront,
    String ArrivalDraftBehind,
    String DepartDraftFront,
    String DepartDraftBehind,
    String OperationArea,
    String OperationBerth,
    String TugNumberSum,
    String OperationNameOne,
    String TugNumberOne,
    String OperationNameTwo,
    String TugNumberTwo,
    String ProposerName,
    String SACompany
            ) {
        Map<String, String> ret = new HashMap<String, String>();
/*
        {TugApplyID:TugApplyID,
                ApplyDate:ApplyDate,
                ChineseName:ChineseName,
                MMSI:MMSI,
                Nationality:Nationality,
                EnglishName:EnglishName,
                CallSign:CallSign,
                IMO:IMO,
                ShipTypeCode:ShipTypeCode,
                Length:Length,
                ShipWidth:ShipWidth,
                GrossTon:GrossTon,
                NetTon:NetTon,
                DeadWeightTon:DeadWeightTon,
                UpdateTime:UpdateTime,
                IsInport:IsInport,
                IsRespAgree:IsRespAgree,
                RespAgreeMsg:RespAgreeMsg}
        {TugApplyID,
                ApplyDate,
                ChineseName,
                MMSI,
                Nationality,
                EnglishName,
                CallSign,
                IMO,
                ShipTypeCode,
                Length,
                ShipWidth,
                GrossTon,
                NetTon,
                DeadWeightTon,
                UpdateTime,
                IsInport,
                IsRespAgree,
                RespAgreeMsg}
*/

        System.out.println("**********进入tugapply表-1");
        /*tugapply表*/
        String tugapplySql="insert ignore into tugapply (TugApplyID,ApplyDate,ChineseName,MMSI,Nationality,EnglishName,CallSign,IMO,ShipTypeCode,Length,ShipWidth,GrossTon,NetTon,DeadWeightTon,UpdateTime,IsInport,IsRespAgree,RespAgreeMsg) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        List<Object[]> tugapplyBatchArgs=new ArrayList<Object[]>();
        tugapplyBatchArgs.add(new Object[]{TugApplyID,
                ApplyDate,
                ChineseName,
                MMSI,
                Nationality,
                EnglishName,
                CallSign,
                IMO,
                ShipTypeCode,
                Length,
                ShipWidth,
                GrossTon,
                NetTon,
                DeadWeightTon,
                UpdateTime,
                IsInport,
                IsRespAgree,
                RespAgreeMsg});
        jdbcTemplate.batchUpdate(tugapplySql, tugapplyBatchArgs);
        System.out.println("**********退出tugapply表");
        System.out.println("**********进入portregisterinfo表-2");
/*        {VoyageID:VoyageID,
                StartPort:StartPort,
                DestPort:DestPort,
                PrePort:PrePort,
                NextPort:NextPort,
                PreArrivalTime:PreArrivalTime,
                PreDepartTime:PreDepartTime,
                PlanBerthTime:PlanBerthTime,
                PlanUnberthTime:PlanUnberthTime,
                TradeType:TradeType}*/

        /*portregisterinfo*/
        String portregisterinfoSql="insert ignore into portregisterinfo (VoyageID,StartPort,DestPort,PrePort,NextPort,PreArrivalTime,PreDepartTime,PlanBerthTime,PlanUnberthTime,TradeType) values (?,?,?,?,?,?,?,?,?,?)";
        List<Object[]> portregisterinfoBatchArgs=new ArrayList<Object[]>();
        portregisterinfoBatchArgs.add(new Object[]{VoyageID,
                StartPort,
                DestPort,
                PrePort,
                NextPort,
                PreArrivalTime,
                PreDepartTime,
                PlanBerthTime,
                PlanUnberthTime,
                TradeType});
        jdbcTemplate.batchUpdate(portregisterinfoSql, portregisterinfoBatchArgs);
        System.out.println("**********退出portregisterinfo表");
        System.out.println("**********进入inouttug表-3");
/*
        {TugApplyID:TugApplyID,
                VoyageID:VoyageID,
                AboutPort:AboutPort,
                LoadorUnload:LoadorUnload,
                CargoTon:CargoTon,
                ArrivalDraftFront:ArrivalDraftFront,
                ArrivalDraftBehind:ArrivalDraftBehind,
                DepartDraftFront:DepartDraftFront,
                DepartDraftBehind:DepartDraftBehind}
        {TugApplyID,VoyageID,AboutPort,LoadorUnload,CargoTon,ArrivalDraftFront,ArrivalDraftBehind,DepartDraftFront,DepartDraftBehind}
*/

        /*inouttug*/
        String inouttugSql="insert ignore into inouttug (TugApplyID,VoyageID,AboutPort,LoadorUnload,CargoTon,ArrivalDraftFront,ArrivalDraftBehind,DepartDraftFront,DepartDraftBehind) values (?,?,?,?,?,?,?,?,?)";
        List<Object[]> inouttugBatchArgs=new ArrayList<Object[]>();
        inouttugBatchArgs.add(new Object[]{TugApplyID,VoyageID,AboutPort,LoadorUnload,CargoTon,ArrivalDraftFront,ArrivalDraftBehind,DepartDraftFront,DepartDraftBehind});
        jdbcTemplate.batchUpdate(inouttugSql, inouttugBatchArgs);
        System.out.println("**********退出inouttug表");
        System.out.println("**********进入tugapplyoperationinfo表-4");
/*        {TugApplyID:TugApplyID,
                VoyageID:VoyageID,
                OperationArea:OperationArea,
                OperationBerth:OperationBerth,
                TugNumberSum:TugNumberSum}
        {TugApplyID,VoyageID,OperationArea,OperationBerth,TugNumberSum}*/
        /*tugapplyoperationinfo*/
        String tugapplyoperationinfoSql="insert ignore into tugapplyoperationinfo (TugApplyID,VoyageID,OperationArea,OperationBerth,TugNumberSum) values (?,?,?,?,?)";
        List<Object[]> tugapplyoperationinfoBatchArgs=new ArrayList<Object[]>();
        tugapplyoperationinfoBatchArgs.add(new Object[]{TugApplyID,VoyageID,OperationArea,OperationBerth,TugNumberSum});
        jdbcTemplate.batchUpdate(tugapplyoperationinfoSql, tugapplyoperationinfoBatchArgs);
        System.out.println("**********退出tugapplyoperationinfo表");
        System.out.println("**********进入tugapplyopecontent表-5");
/*        {TugApplyID:TugApplyID,
                VoyageID:VoyageID,
                OperationNameOne:OperationNameOne,
                TugNumberOne:TugNumberOne,
                OperationNameTwo:OperationNameTwo,
                TugNumberTwo:TugNumberTwo}
        {TugApplyID,VoyageID,OperationNameOne,TugNumberOne,OperationNameTwo,TugNumberTwo}*/
        /*tugapplyopecontent*/
        String tugapplyopecontentSql="insert ignore into tugapplyopecontent (TugApplyID,VoyageID,OperationNameOne,TugNumberOne,OperationNameTwo,TugNumberTwo) values (?,?,?,?,?,?)";
        List<Object[]> tugapplyopecontentBatchArgs=new ArrayList<Object[]>();
        tugapplyopecontentBatchArgs.add(new Object[]{TugApplyID,VoyageID,OperationNameOne,TugNumberOne,OperationNameTwo,TugNumberTwo});
        jdbcTemplate.batchUpdate(tugapplyopecontentSql, tugapplyopecontentBatchArgs);
        System.out.println("**********退出tugapplyopecontent表");
        System.out.println("**********进入tugproposerinfo表-6");
/*        {TugApplyID:TugApplyID,
                VoyageID:VoyageID,
                ProposerName:ProposerName,
                SACompany:SACompany}
        {TugApplyID,VoyageID,ProposerName,SACompany}*/
        /*tugproposerinfo*/
        String tugproposerinfoSql="insert ignore into tugproposerinfo (TugApplyID,VoyageID,ProposerName,SACompany) values (?,?,?,?)";
        List<Object[]> tugproposerinfoBatchArgs=new ArrayList<Object[]>();
        tugproposerinfoBatchArgs.add(new Object[]{TugApplyID,VoyageID,ProposerName,SACompany});
        jdbcTemplate.batchUpdate(tugproposerinfoSql, tugproposerinfoBatchArgs);
        System.out.println("**********退出tugproposerinfo");
        System.out.println("**********插入完成");
        ret.put("type", "success");

        return ret;

    }
}
