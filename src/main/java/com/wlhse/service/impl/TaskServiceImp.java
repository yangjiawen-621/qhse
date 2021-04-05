package com.wlhse.service.impl;

import com.wlhse.cache.JedisClient;
import com.wlhse.dao.QHSEManageSysElementsDao;
import com.wlhse.dao.QHSETaskDao;
import com.wlhse.dto.ProgressDto;
import com.wlhse.dto.TaskDto;
import com.wlhse.dto.outDto.TaskOutDto;
import com.wlhse.service.TaskService;
import com.wlhse.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Author:Coco
 * create:2020-08-27 4:51 PM
 **/
@Service
public class TaskServiceImp implements TaskService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    QHSETaskDao taskDao;
    @Resource
    JedisClient jedisClient;
    @Resource
    QHSEManageSysElementsDao qhseManageSysElementsDao;
    @Override
    public R createNewTask(TaskDto taskDto) {
        int i = taskDao.insertNewTask(taskDto);
        if (i==1){
            return R.ok();
        }
        return  R.error("下达任务失败");
    }

    @Override
    public R getTaskByEmployeeId(HttpServletRequest request) {
        R r=new R();
        String token = request.getHeader("Authorization");
        Map<String, String> map = jedisClient.hGetAll(token);
        int employeeId = Integer.valueOf(map.get("employeeId"));
        List<TaskOutDto> allTaskByEmployeeId = taskDao.getAllTaskByEmployeeId(employeeId);
        r.put("data",allTaskByEmployeeId);
        return r;
    }

    @Override
    public R receiveTask(int taskId) {
        int i = taskDao.receiveTask(taskId);
        if (i==1){
            return R.ok();
        }
        return R.error("接收失败");
    }

    @Override
    public R getOrderedTask(HttpServletRequest request) {
        R r=new R();
        String token = request.getHeader("Authorization");
        Map<String, String> map = jedisClient.hGetAll(token);
        int employeeId = Integer.valueOf(map.get("employeeId"));
        List<TaskDto> orderedTask = taskDao.getOrderedTask(employeeId);
        r.put("data",orderedTask);
        return r;
    }

    @Override
    public R getTaskDetails(int tableId, String status) {
        R r=new R();
        String total = jedisClient.get("T" + tableId);
        if (total==null){
            int allLeafNodeNumber = qhseManageSysElementsDao.getAllLeafNodeNumber(tableId);
            total=String.valueOf(allLeafNodeNumber);
            jedisClient.set("T"+tableId,total);
        }
        ProgressDto progressDto=new ProgressDto();
        int finishedNum=0;
        switch (status){
            case "录入证据中":finishedNum=qhseManageSysElementsDao.getInputtedNum(tableId); break;
            case "审核中":finishedNum=qhseManageSysElementsDao.getCheckedNum(tableId);break;
            case "批准中":finishedNum=qhseManageSysElementsDao.getApprovedNum(tableId);break;
            case "重新录入中":case "重新审核中":case "重新批准中":finishedNum=qhseManageSysElementsDao.getReInputNum(tableId);
        }
        progressDto.setTotal(total);
        progressDto.setFinishedNum(finishedNum);
        r.put("data",progressDto);
        return r;
    }

}
