package com.wlhse.dao;

import com.wlhse.dto.inDto.InterfaceModuleInDto;
import com.wlhse.entity.ModulePojo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleDao {

    // 查当前所有的模块信息
    List<ModulePojo> queryAllSYSModules();
    // 根据ModuleCode查Module
    ModulePojo queryByModuleCode(String moduleCode);

    // 查当前所有的模块信息
    List<ModulePojo> queryModulesByEmpId(int employeeId);

    List<String> queryModulesStringByEmpId(int employeeId);

    //获取某员工对于某接口的访问权限数
    int getInterfaceCountByEmpId(InterfaceModuleInDto interfaceModuleInDto);

}
