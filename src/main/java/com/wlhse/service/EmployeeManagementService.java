package com.wlhse.service;


import com.wlhse.dto.EmployeeManagementDto;
import com.wlhse.dto.getDto.EmloyeeGetDto;

public interface EmployeeManagementService {

//    String getLikeList(String name);

    String getAllEmployeeDto(EmloyeeGetDto dto, int userId);

    String listEmployee(EmployeeManagementDto employeeManagementDto, int id);

    String addEmployee(EmployeeManagementDto employeeManagementDto);

    String updateEmployee(EmployeeManagementDto employeeManagementDto);

    //query By Id
    String queryById(int id);

    //删除员工
    String deleteEmployee(Integer employeeID);

    String queryRoleCodeByEmpId(int employeeId);

    String updateRoleCodeByEmpId(int employeeId, String roleCode, String status);

    String queryRoleCodeByEmpId123(int employeeId);

    EmployeeManagementDto querryRoleById(int id);

    String getEmployeeNameByEmployeeID(int eId);


}
