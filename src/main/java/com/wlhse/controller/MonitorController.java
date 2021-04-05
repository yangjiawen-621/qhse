package com.wlhse.controller;

import com.alibaba.excel.EasyExcel;
import com.wlhse.dao.CompanyDao;
import com.wlhse.dao.MesSumDataDao;
import com.wlhse.dao.MonitorPlanDetailDao;
import com.wlhse.dto.MonitorPlan;
import com.wlhse.dto.MonitorPlanDetail;
import com.wlhse.entity.MesSumData;
import com.wlhse.entity.MonitorInputCheckRecord;
import com.wlhse.service.MesSumDataService;
import com.wlhse.service.MonitorPlanService;
import com.wlhse.util.GetCurrentUserIdUtil;
import com.wlhse.util.MesDataListener;
import com.wlhse.util.MonitorDataListener;
import com.wlhse.util.R;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;

@RestController
@RequestMapping("/api/v3")
public class MonitorController {

    @Resource
    MonitorPlanDetailDao monitorPlanDetailDao;

    @Resource
    MonitorPlanService monitorPlanService;

    @Resource
    CompanyDao companyDao;

    @Resource
    MesSumDataDao mesSumDataDao;

    @Resource
    MesSumDataService mesSumDataService;
    @Resource
    GetCurrentUserIdUtil currentUserIdUtil;
    private Logger log= LoggerFactory.getLogger(this.getClass());
    //批量上传远程监控计划详情
    @RequestMapping(value = "/uploadMonitorPlanExcel",method = RequestMethod.POST)
    R addDetailsForMonitorPlan(@RequestParam(value = "file",required = false) MultipartFile file,@RequestParam(value = "planId",required = false)Integer planId) throws IOException {
        EasyExcel.read(file.getInputStream(), MonitorPlanDetail.class,
                new MonitorDataListener(monitorPlanDetailDao,planId)).
                sheet().doRead();
        return R.ok();
    }

    //创建新的远程监控计划
    @RequestMapping(value = "/createNewMonitorPlan",method = RequestMethod.POST)
    R createNewMonitorPlan(@RequestBody(required = false)MonitorPlan plan, HttpServletRequest request){
        return monitorPlanService.createNewMonitorPlan(plan,request);
    }

    //获取远程监控计划列表
    @RequestMapping(value = "/getMonitorPlanList",method = RequestMethod.GET)
    R getMonitorPlanList(HttpServletRequest request){
        return monitorPlanService.getMonitorPlanByPersonId(request);
    }


    //更改已上传的远程监控任务详情
    @RequestMapping(value = "/updateMonitorPlanDetail",method = RequestMethod.PUT)
    R updateMonitorPlanDetail(@RequestBody(required = false) MonitorPlanDetail monitorPlanDetail){
        return monitorPlanService.updateMonitorPlanDetail(monitorPlanDetail);
    }

    //删除远程监控计划
    @RequestMapping(value = "/deletePlan/{planId}",method = RequestMethod.DELETE)
    R deletePlan(@PathVariable("planId")Integer id){
        return monitorPlanService.deletePlan(id);
    }

    //删除远程监控计划详情
    @RequestMapping(value = "/deletePlanDetail/{detailId}",method = RequestMethod.DELETE)
    R deleteDetail(@PathVariable("detailId")Integer detailId){
        return monitorPlanService.deletePlanService(detailId);
    }

    //根据计划ID获取远程监控计划的全部详情内容
    @RequestMapping(value = "/getDetails/{planId}",method = RequestMethod.GET)
    R getDetails(@PathVariable("planId")Integer planId){
        return monitorPlanService.getDetails(planId);
    }

    /*2020-9-9新增的接口，还没写到接口文档里*/
    //单独新增远程监控计划详情项目
    @RequestMapping(value = "/createNewDetail",method = RequestMethod.POST)
    R createNewDetail(@RequestBody(required = false)MonitorPlanDetail monitorPlanDetail){
        return monitorPlanService.createNewDetailPlan(monitorPlanDetail);
    }

    //录入当天的详情内容
    @RequestMapping(value = "/inputDetail",method = RequestMethod.POST)
    R inputDetail(@RequestBody(required = false) MonitorInputCheckRecord monitorInputCheckRecord,HttpServletRequest request){
        return monitorPlanService.insertNewInputRecord(monitorInputCheckRecord,request);
    }

    //获取当天录入的内容
    @RequestMapping(value = "/getInputtedDetailInfo/{detailId}",method = RequestMethod.GET)
    R getInputtedDetailInfo(@PathVariable("detailId")Integer detailId){
        return monitorPlanService.getRecordDetail(detailId);
    }

    //当天的详情信息已经录入，需要更新录入的信息（输入核查信息也要使用该接口）
    //新增核查结论字段
    @RequestMapping(value = "/updateInputtedDetailInfo",method = RequestMethod.PUT)
    R updateInputtedDetailInfo(@RequestBody(required = false)MonitorInputCheckRecord monitorInputCheckRecord,HttpServletRequest request){
        Integer userId = currentUserIdUtil.getUserId(request);
        monitorInputCheckRecord.setCheckPersonID(userId.toString());
        return monitorPlanService.updateInputtedRecord(monitorInputCheckRecord);
    }
    //获取需要核查的远程监控记录详情
    @RequestMapping(value = "/getCheckDetail",method = RequestMethod.GET)
    R getNeedToCheckRecords(@RequestParam("planId") Integer planId,@RequestParam("date")String date){
        return monitorPlanService.getNeedToCheckRecords(planId,date);
    }

    //上传
    @RequestMapping(value = "/uploadMesSumDataExcel",method = RequestMethod.POST)
    R uploadMesSumDataExcel(@RequestParam(value = "file",required = false) MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), MesSumData.class,
                new MesDataListener(mesSumDataDao,companyDao)).
                sheet().doRead();
        return R.ok();
    }

    //查找视图，获取所有记录在案的日期
    @RequestMapping(value = "/getAllSumDate",method = RequestMethod.GET)
    R getAllSumDate(){
        return mesSumDataService.getAllSumDate();
    }

    //根据日期获取当日所登记的统计信息
    @RequestMapping(value = "/getStatisticsInfoByDate",method = RequestMethod.GET)
    R getStatisticsInfoByDate(@RequestParam(value = "date",required = false)String date){
        return mesSumDataService.getMesCheckDataByDate(date);
    }

    @RequestMapping(value = "/deleteSumData",method = RequestMethod.DELETE)
    R  deleteSumData(@RequestParam(value = "sumDataId",required = false)Integer sumDataId){
        return mesSumDataService.deleteSumData(sumDataId);
    }

    @RequestMapping(value = "/deleteSumDataByDate",method = RequestMethod.DELETE)
    R deleteSumDataByDate(@RequestParam(value = "date",required = false)String date){
        return mesSumDataService.deleteByDate(date);
    }

    @RequestMapping(value = "/getInputDates/{planId}",method = RequestMethod.GET)
    R getInputDatesByPlanId(@PathVariable("planId")Integer planId) throws ParseException {
        return monitorPlanService.getInputDatesByPlanId(planId);
    }

    @RequestMapping(value = "/getInputtedRecordDetailByDate",method = RequestMethod.GET)
    R getInputtedRecordDetailByDate(@RequestParam(value = "detailId",required = false)Integer detailId,@RequestParam(value = "date",required = false)String date){
        return monitorPlanService.getRecordDetailByDate(detailId,date);
    }

    @RequestMapping(value = "/getNeedToCheckedDetails",method = RequestMethod.GET)
    R getNeedToCheckedDetails(@RequestParam(value = "planId",required = false)Integer planId,@RequestParam(value = "date",required = false)String date){
        return monitorPlanService.getNeedToCheckPlanDetails(planId,date);
    }

    @RequestMapping(value = "/updateMesData",method = RequestMethod.PUT)
    R updateMesData(@RequestBody(required = false)MesSumData mesSumData){
        return mesSumDataService.updateData(mesSumData);
    }

    @RequestMapping(value = "/getSumDataById",method = RequestMethod.GET)
    R getSumDataById(@RequestParam(value = "mesDataId",required = false)Integer id){
        return mesSumDataService.getSumDataById(id);
    }

    @RequestMapping(value = "/getSumDataInTimePeriod",method = RequestMethod.GET)
    R getSumDataInTimePeriod(@RequestParam(value = "startTime",required = false)String startTime,@RequestParam(value = "endTime",required = false)String endTime){
        return mesSumDataService.getSumDataInTimePeriod(startTime,endTime);
    }


    //导出录入日报
    @RequestMapping(value = "/getDayReport",method = RequestMethod.GET)
    R getDayReport(@RequestParam(value = "date",required = false)String date,@RequestParam(value = "planId")Integer planId){
        return monitorPlanService.getDayReport(planId,date);
    }



    @RequestMapping(value = "/getTotalInputTime",method = RequestMethod.GET)
    R getTotalInputTime(@RequestParam("planId")Integer planId){
        return monitorPlanService.getTotalInputTime(planId);
    }


    @RequestMapping(value = "/getInputAndCheckDetail",method = RequestMethod.GET)
    R getInputAndCheckDetail(@RequestParam("planDetailId")Integer detailId){
        return monitorPlanService.getInputAndCheckDetail(detailId);
    }

    @RequestMapping(value = "/deleteInputInfo",method = RequestMethod.DELETE)
    R deleteInputInfo(@RequestParam("checkRecordId")Integer checkRecordId){
        return monitorPlanService.deleteInputInfo(checkRecordId);
    }

    @RequestMapping(value = "/getItemNum ",method = RequestMethod.GET)
    R getItemNum(@RequestParam("planId")Integer planId){
        return monitorPlanService.getItemNum(planId);
    }


    @RequestMapping(value = "/endPlanDetail",method = RequestMethod.GET)
    R endPlanDetail(@RequestParam("detailId")Integer detailId){
        return monitorPlanService.endDetail(detailId);
    }



    @RequestMapping(value = "/refreshMesData",method =RequestMethod.GET )
    R refreshMesData(@RequestParam("date")String date,@RequestParam("companyName")String companyName){
        return monitorPlanService.refreshMesData(date,companyName);
    }

    @RequestMapping(value = "/downloadMonitorData",method = RequestMethod.GET)
    void downloadMonitorData(@RequestParam("date")String date,@RequestParam("planId")Integer planId){
         monitorPlanService.downloadMonitorData(date,planId);
    }

    @RequestMapping(value = "/useDeviceTrend/{companyName}",method = RequestMethod.GET)
    public R getDeviceTrend(@PathVariable("companyName")String companyName){
        return monitorPlanService.getDeviceTrend(companyName);
    }

    @RequestMapping(value = "/getTeamInfo",method = RequestMethod.GET)
    public R getTeamInfo(@RequestParam(value = "date",required = false) String date){
       return monitorPlanService.getRemoteTeamInfo(date);
    }

    @RequestMapping(value = "/generateMonitorPlan",method = RequestMethod.GET)
    public R generateMonitorPlan(@RequestParam(value = "date",required = false)String date,HttpServletRequest request,@RequestParam("companyCode")String companyCode) throws ParseException {
        return monitorPlanService.generateMonitorPlan(date,request,companyCode);
    }
}
