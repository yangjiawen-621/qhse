package com.wlhse.service.impl;

import com.github.pagehelper.PageHelper;
import com.wlhse.dao.RoleModuleDao;
import com.wlhse.dao.RolesDao;
import com.wlhse.entity.RolesPojo;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.RolesService;
import com.wlhse.util.R;
import com.wlhse.util.SortCodeUtil;
import com.wlhse.util.state_code.CodeDict;
import com.wlhse.util.state_code.NR;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RolesService {

    @Resource
    private RolesDao rolesDao;

    @Resource
    private SortCodeUtil sortCodeUtil;

    @Resource
    private RoleModuleDao roleModuleDao;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public String getRolesByCondition(String roleName, int pageIdx, int pageSize, String type) {
        List<RolesPojo> list;
        if (type.equals("all"))
            return NR.r(rolesDao.queryRoles(roleName));
        int total = rolesDao.queryTotal(roleName);
        PageHelper.startPage(pageIdx, pageSize);
        list = rolesDao.queryRoles(roleName);
        return NR.r(CodeDict.CODE_MESSAGE_DATA_PAGE_LIST, 0, 0, null, list, total, pageIdx);
    }

    @Override
    public String getRoleById(Integer roleID) {
        return NR.r(rolesDao.queryRoleById(roleID));
    }

    @Override
    @Transactional
    public String addRole(RolesPojo rolesPojo) {
        String s = rolesDao.queryMaxRoleCode();
        if (StringUtils.isNotBlank(s)) {
            s = sortCodeUtil.getMaxCodeString(s);
        } else {
            s = "0001";
        }
        rolesPojo.setRoleCode(s);
        int i=0;
        try{
             i = rolesDao.addRoles(rolesPojo);
        }
        catch (Exception e){
            throw new WLHSException(500,"该角色已存在");
        }
        if (i<= 0){
            return R.error(500,"添加失败").toJSONString();
        }
        return NR.r();
    }

    @Override
    @Transactional
    public String deleteRole(Integer roleID) {
        RolesPojo rolesPojo = rolesDao.queryRoleById(roleID);
        if (roleModuleDao.deleteSYSRoleModuleByRoleCode(rolesPojo.getRoleCode())<0)
            throw new WLHSException("删除失败，原因1");
        if (rolesDao.deleteRoles(roleID) <= 0) {
            throw new WLHSException("删除失败，原因2");
        }
        return NR.r();
    }

    @Override
    @Transactional
    public String updateRole(RolesPojo rolesPojo) {
        if (rolesDao.updateRoles(rolesPojo) <= 0)
            throw new WLHSException("更新失败");
        return NR.r();
    }

    @Override
    public R isExit(String name) {
        if (rolesDao.queryRoleByName(name)==null){
         return     R.ok("用户不存在");
        }
        return R.error("用户已存在");
    }
}
