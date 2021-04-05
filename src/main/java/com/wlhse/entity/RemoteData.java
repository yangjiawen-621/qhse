package com.wlhse.entity;

import lombok.Data;

@Data
public class RemoteData {
    //计划id
    int monitorPlanId;
    //设备编号
    String EQ_NUMBER;
    //记录仪编号
    String RECORD_NUMBER;
    //人员信息
    String WORK_USER;
    //填报日期
    String WRITE_DATE;
    //联系人电话
    String PHONE;
    //科室
    String C_DPT_NAME;
    //负责人
    String PRINCIPAL;
    //基层单位名称
    String DPT_NAME;
    //作业地点
    String WORK_LOCATION;
    //项目名称
    String PROJECT_NAME;
    //作业进度
    String WORK_PLAN;
}
