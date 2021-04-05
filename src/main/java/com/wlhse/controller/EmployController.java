package com.wlhse.controller;

import com.wlhse.dto.EmployeeManagementDto;
import com.wlhse.dto.getDto.EmloyeeGetDto;
import com.wlhse.entity.UserPojo;
import com.wlhse.service.EmployeeManagementService;
import com.wlhse.service.UserService;
import com.wlhse.util.GetCurrentUserIdUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController("EmployController")
@RequestMapping("/api/v3")
//员工管理
public class EmployController {

    @Resource
    private EmployeeManagementService employeeManagementService;

    @Resource
    private UserService service;

    @Resource
    private  GetCurrentUserIdUtil getCurrentUserIdUtil;

    @RequestMapping(value = "/employees", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String test_test2(@ModelAttribute EmloyeeGetDto dto, HttpServletRequest request) {
        Integer userId = getCurrentUserIdUtil.getUserId(request);
        return employeeManagementService.getAllEmployeeDto(dto, userId);
    }

    @RequestMapping(value = "/employee_management", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String quaryEmployee(@ModelAttribute EmployeeManagementDto employeeManagementDto, HttpServletRequest request) {
        int id = getCurrentUserIdUtil.getUserId(request);
        return employeeManagementService.listEmployee(employeeManagementDto,id);
    }


    @RequestMapping(value = "/employee_management", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String addEmployee(@RequestBody(required = false) EmployeeManagementDto employeeManagementDto) {
        return employeeManagementService.addEmployee(employeeManagementDto);
    }

    @RequestMapping(value = "/employee_management/{id}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public String updateEmployee(@RequestBody(required = false) EmployeeManagementDto employeeManagementDto, @PathVariable("id") int employeeID) {
        employeeManagementDto.setEmployeeID(employeeID);
        return employeeManagementService.updateEmployee(employeeManagementDto);
    }

    @RequestMapping(value = "/employee_management/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String queryEmployeeById(@PathVariable int id) {
        return employeeManagementService.queryById(id);
    }

    @RequestMapping(value = "/employee_management/{employeeID}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public String deleteEmployee(@PathVariable("employeeID") int employeeID) {
        return employeeManagementService.deleteEmployee(employeeID);
    }

    @RequestMapping(value = "/my_employee_management", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getMyEmployee(HttpServletRequest request) {
        return employeeManagementService.queryById(getCurrentUserIdUtil.getUserId(request));
    }

    @RequestMapping(value = "/my_employee_management", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public String updateMyEmployee(@RequestBody(required = false) EmployeeManagementDto employeeManagementDto, HttpServletRequest request) {
        employeeManagementDto.setEmployeeID(getCurrentUserIdUtil.getUserId(request));
        return employeeManagementService.updateEmployee(employeeManagementDto);
    }

    @RequestMapping(value = "/register/{id}", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String register(@RequestBody UserPojo userPojo, @PathVariable("id") int id) {
        userPojo.setEmployeeID(id);
        return service.register(userPojo);
    }

    @RequestMapping(value = "/employee_management/role_code/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getMyEmployee(@PathVariable("id") int id) {
        return employeeManagementService.queryRoleCodeByEmpId(id);
    }

    @RequestMapping(value = "/employee_management/role_code/{id}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public String getMyEmployee(@PathVariable("id") int id, @RequestBody(required = false) UserPojo userPojo) {
        return employeeManagementService.updateRoleCodeByEmpId(id, userPojo.getRoleCode(), userPojo.getStatus());
    }
}
