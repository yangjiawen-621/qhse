package com.wlhse.controller;

import com.wlhse.dto.inDto.RoleModuleInDto;
import com.wlhse.service.RoleModuleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController("RoleModuleController")
@RequestMapping("/api/v3")
//角色授权
public class RoleModuleController {
    @Resource
    private RoleModuleService service;

    //获取当前角色的模块树-传入RoleCode
    @RequestMapping(value = "/role_module/tree", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getRoleModuleTree(@RequestParam(value = "roleCode",required = false) String roleCode) {
        return service.getModuleTreeByRoleCode(roleCode);
    }

    //所有的权限对应的树
    @RequestMapping(value = "/module/tree", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getModuleTree() {
        return service.getModuleTree();
    }

    //更新角色模块
    @RequestMapping(value = "/role_module", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public String UpdateRoleModule(@RequestBody(required = false) RoleModuleInDto pojo) {
        return service.updateSYSRoleModule(pojo);
    }

    //当前选中的ID
    @RequestMapping(value = "/role_module/node", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getChooseModuleCode(@RequestParam(value = "roleCode",required = false) String roleCode) {
        return service.getChooseNodeID(roleCode);
    }

    //所有的权限对应的树
    @RequestMapping(value = "/module", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getRoleModuleOutDto(@RequestParam(value = "uName",required = false)String uName) {
        return service.getRoleModuleOutDto(uName);
    }
}
