package com.wlhse.service.impl;

import com.wlhse.dao.ModuleDao;
import com.wlhse.dao.RoleModuleDao;
import com.wlhse.dto.inDto.RoleModuleDto;
import com.wlhse.dto.inDto.RoleModuleInDto;
import com.wlhse.dto.outDto.RoleModuleOutDto;
import com.wlhse.entity.RoleModulePojo;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.RoleModuleService;
import com.wlhse.util.TreeUtil;
import com.wlhse.util.state_code.CodeDict;
import com.wlhse.util.state_code.NR;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleModuleServiceImpl implements RoleModuleService {

    @Resource
    private RoleModuleDao dao;

    @Resource
    private TreeUtil treeUtill;

    @Resource
    private ModuleDao moduleDao;

    @Value("${RESOURCES_URL}")
    private String url;

    @Override
    public String getModuleTreeByRoleCode(String roleCode) {
        return NR.r(treeUtill.GetRoleModuleTree(dao.queryByRoleCode(roleCode)));
    }

    @Override
    public String getModuleTree() {
        return NR.r(treeUtill.GetModuleTree(moduleDao.queryAllSYSModules()));
    }

    @Override
    @Transactional
    public String updateSYSRoleModule(RoleModuleInDto roleModuleInDto) {
        List<RoleModuleDto> roleModuleDto = roleModuleInDto.getRoleModule();
        if (StringUtils.isNotBlank(roleModuleDto.get(0).getRoleCode())) {
            String code = roleModuleDto.get(0).getRoleCode();
            if (null != dao.queryByRoleCode(code)) {
                dao.deleteSYSRoleModuleByRoleCode(code);
            }
        }
        if (dao.addRoleModule(roleModuleDto) <= 0)
            throw new WLHSException("新增失败");
        return NR.r();
    }

    // 返回选中节点的ID
    public String getChooseNodeID(String roleCode) {
        List<RoleModulePojo> list = dao.queryByRoleCode(roleCode);

        List<String> chooseNodeId = new ArrayList<>();

        for (RoleModulePojo dto : list) {
            chooseNodeId.add(dto.getModuleCode());
        }
        return NR.r(chooseNodeId);
    }

    @Override
    public String getRoleModuleOutDto(String uName) {
        return NR.r(treeUtill.getRoleModuleOutDto(dao.queryByUserName(uName, url, url)));
    }

}
