package com.wlhse.service;

import com.wlhse.dto.TaskDto;
import com.wlhse.util.R;

import javax.servlet.http.HttpServletRequest;


public interface TaskService {

    R createNewTask(TaskDto taskDto);

    R getTaskByEmployeeId(HttpServletRequest request);

    R receiveTask(int taskId);

    R getOrderedTask(HttpServletRequest request);

    R getTaskDetails(int tableId, String status);
}
