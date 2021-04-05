package com.wlhse.dao;

import com.wlhse.dto.TaskDto;
import com.wlhse.dto.TaskStatusDto;
import com.wlhse.dto.outDto.TaskOutDto;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QHSETaskDao {

    int insertNewTask(TaskDto taskDto);

    List<TaskOutDto> getAllTaskByEmployeeId(int employeeId);

    int updateTaskStatusByTableId(TaskStatusDto taskStatusDto);

    int receiveTask(int taskId);

    List<TaskDto> getOrderedTask(int employeeId);

    int updateCheckStatus(int tableID,String status);

    int deleteTask(int tableId);
}
