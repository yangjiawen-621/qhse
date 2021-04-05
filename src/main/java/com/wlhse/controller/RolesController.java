package com.wlhse.controller;

import com.wlhse.entity.RolesPojo;
import com.wlhse.service.RolesService;
import com.wlhse.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController("RolesController")
@RequestMapping("/api/v3")
//角色管理
public class RolesController {

    @Resource
    private RolesService rolesService;

    //条件查询，查所有或者名字模糊查询，考虑分页
    @RequestMapping(value = "/roles", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getRoles(@RequestParam(value = "roleName", required = false, defaultValue = "") String roleName, @ModelAttribute RolesPojo rolesPojo) {
            return rolesService.getRolesByCondition(roleName, rolesPojo.getPageIdx(), rolesPojo.getPageSize(), rolesPojo.getType());
    }

    //根据id查询
    @RequestMapping(value = "/roles/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getRoleById(@PathVariable("id") Integer id) {
            return rolesService.getRoleById(id);
    }

    //新增
    @RequestMapping(value = "/roles", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    public String addRoles(@RequestBody(required = false) RolesPojo rolesPojo) {
        return rolesService.addRole(rolesPojo);
    }

    //删除
    @RequestMapping(value = "/roles/{id}", method = RequestMethod.DELETE,produces = "application/json; charset=utf-8")
    public String removeRoles(@PathVariable("id") int id) {
            return rolesService.deleteRole(id);
    }

    //更新
    @RequestMapping(value = "/roles/{id}", method = RequestMethod.PUT,produces = "application/json; charset=utf-8")
    public String modifyRoles(@PathVariable("id") int id,@RequestBody(required = false) RolesPojo rolesPojo) {
            rolesPojo.setRoleID(id);
            return rolesService.updateRole(rolesPojo);
    }

    @RequestMapping(value = "/roleExist")
    public R isExit(@RequestParam(required = false,value = "name") String name){
        return rolesService.isExit(name);
    }
}
