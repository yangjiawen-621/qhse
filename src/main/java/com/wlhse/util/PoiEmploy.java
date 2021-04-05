package com.wlhse.util;

import com.wlhse.dao.CompanyDao;
import com.wlhse.dao.EmployeeManagementDao;
import com.wlhse.dto.EmployeeExcelDto;
import com.wlhse.dto.EmployeeManagementDto;
import com.wlhse.entity.EmployeePojo;
import com.wlhse.entity.UserPojo;
import com.wlhse.service.EmployeeManagementService;
import com.wlhse.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Component
class PoiEmploy {

    @Resource
    private EmployeeManagementDto employeeManagementDto;

    @Resource
    private EmployeeManagementDao employeeManagementDao;

    @Resource
    private DeleteCacheUtil deleteCacheUtil;

    @Resource
    private EmployeeManagementService employeeManagementService;

    @Resource
    private UserService service;

    void poiPoiEmploy(List<EmployeeExcelDto> list) {
        for (EmployeeExcelDto dto : list) {
            String hash = HashUtil.hash(dto.getCompanyCode() + dto.getName() + dto.getEmpGroup() + dto.getCategory());
            if (employeeManagementDao.queryByHash(hash) == 0) {
                EmployeeManagementDto employeeManagementDto = new EmployeeManagementDto();
                UserPojo userPojo = new UserPojo();
                String companyCode = dto.getCompanyCode();
                String employeeCode = dto.getEmployeeCode();
                String name = dto.getName();
                String sex = dto.getSex();
                String birthday = dto.getBirthday();
                String jobTime = dto.getJobTime();
                String empGroup = dto.getEmpGroup();
                String education = dto.getEducation();
                String email = dto.getEmail();
                String tel = dto.getTel();
                String category = dto.getCategory();
                String position=dto.getPosition();
                String station=dto.getStation();
                employeeManagementDto.setCompanyCode(companyCode);
                employeeManagementDto.setEmployeeCode(employeeCode);
                employeeManagementDto.setName(name);
                employeeManagementDto.setSex(sex);
                if (StringUtils.isNotBlank(birthday)) {
                    employeeManagementDto.setBirthday(birthday);
                } else {
                    employeeManagementDto.setBirthday("1990-1-1");
                }
                if (StringUtils.isNotBlank(jobTime)) {
                    employeeManagementDto.setJobTime(jobTime);
                } else {
                    employeeManagementDto.setJobTime("2000-1-1");
                }
                employeeManagementDto.setEmpGroup(empGroup);
                employeeManagementDto.setEducation(education);
                employeeManagementDto.setEmail(email);
                employeeManagementDto.setTel(tel);
                employeeManagementDto.setCategory(category);
                employeeManagementDto.setPosition(position);
                employeeManagementDto.setStation(station);
                employeeManagementService.addEmployee(employeeManagementDto);

                Integer id = employeeManagementDao.queryEmployeeID(hash);
                String uName = dto.getuName();
                String roleCode = dto.getRoleCode();
                userPojo.setEmployeeID(id);
                userPojo.setuName(uName);
                userPojo.setPwd("123456");
                userPojo.setStatus("启用");
                userPojo.setRoleCode(roleCode);
                service.register(userPojo);
            }
        }
//        if (list1.size() > 0) {
//            employeeManagementDao.addManyEmployeePojo(list1);
        deleteCacheUtil.deleteEmployeesCache();
//        }
    }
}
