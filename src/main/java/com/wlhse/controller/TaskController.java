package com.wlhse.controller;


import com.wlhse.dto.TaskDto;
import com.wlhse.service.TaskService;
import com.wlhse.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Description:
 * Author:Coco
 * create:2020-08-27 4:49 PM
 **/
@RestController("TaskController")
@RequestMapping("/api/v3")
public class TaskController {

    @Resource
    TaskService taskService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/createNewTask",method = RequestMethod.POST)
    public R createNewTask(@RequestBody(required = false) TaskDto taskDto){
        return taskService.createNewTask(taskDto);
    }

    @RequestMapping(value = "/getTaskList",method = RequestMethod.GET)
    public R getTaskList(HttpServletRequest request){
        return taskService.getTaskByEmployeeId(request);
    }

    @RequestMapping(value = "/receiveTask",method = RequestMethod.PUT)
    public R  receiveTask(@RequestParam(value = "taskId",required = false)Integer taskId){
        return taskService.receiveTask(taskId);
    }

    @RequestMapping(value = "/getOrderedTask",method = RequestMethod.GET)
    public R getOrderedTask(HttpServletRequest request){
        return taskService.getOrderedTask(request);
    }

    @RequestMapping(value = "/getTaskDetails",method = RequestMethod.GET)
    public R getDetails(@RequestParam(value = "tableId",required = false)Integer tableId
            ,@RequestParam(value = "status",required=false)String status){
        return taskService.getTaskDetails(tableId,status);
    }

}
