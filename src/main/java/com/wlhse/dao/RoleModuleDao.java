package com.wlhse.dao;

import com.wlhse.dto.inDto.RoleModuleDto;
import com.wlhse.dto.outDto.RoleModuleOutDto;
import com.wlhse.entity.RoleModulePojo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleModuleDao {
    // 批量插入
    int addRoleModule(List<RoleModuleDto> roleModule);

    int deleteSYSRoleModuleByRoleCode(String roleCode);

    List<RoleModulePojo> queryByRoleCode(String roleCode);

    List<RoleModuleOutDto> queryByUserName(@Param("uName")String uName,@Param("url")String url,@Param("url1")String url1);
}
