package com.wlhse.dao;

import com.wlhse.dto.outDto.EmployOutDtoTest;
import com.wlhse.dto.EmployeeManagementDto;
import com.wlhse.entity.EmployeePojo;
import com.wlhse.dto.getDto.EmployeeDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeManagementDao {

    int addEmployee(EmployeeManagementDto employeeManagementDto);

    int deleteEmployee(@Param("employeeID") Integer employeeID);

    int updateEmployee(EmployeeManagementDto employeeManagementDto);

    List<EmployeeManagementDto> queryEmployeeByCondition(EmployeeManagementDto employeeManagementDto);

    // id查询单个
    EmployeeManagementDto queryById(int id);

    int addManyEmployeePojo(List<EmployeePojo> list);

    List<EmployOutDtoTest> queryByTestName(String name);

    //减少字段
    List<EmployeeManagementDto> getAllEmployeeDto(@Param("name1") String name1,@Param("companyCode") String companyCode);

    int getAllEmployeeDtoCount(@Param("name1") String name1);

    int getEmployeeTotal(EmployeeManagementDto employeeManagementDto);

    //根据员工id和用户Status更新
    int updateStatusByEmpId(@Param("employeeId") int employeeId,@Param("status") String status);

    //根据员工id和角色code更新
    int updateRoleCodeByEmpId(@Param("employeeId") int employeeId,@Param("roleCode") String roleCode);

    //根据员工id查询角色code，用户名，用户状态
    EmployeeManagementDto queryRoleCodeByEmpId(@Param("employeeId") int employeeId);

    String queryCompanyCodeByEmpId(@Param("employeeId") int employeeId);

    //根据员工id查询二级单位
    String querySecondCompanyCodeByEmpId(@Param("employeeId") int employeeId);

    int queryByHash(@Param("hash")String hash);

    int addNewEmployeePojo(EmployeePojo employeePojo);

    EmployeeDto getEmployeePojo(@Param("employeeId") int employeeId);

    int queryEmployeeID(@Param("hash") String hash);

    //get employee name by employee id
    String queryEmployeeNameByEmployeeId(int employeeId);
}
