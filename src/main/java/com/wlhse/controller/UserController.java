package com.wlhse.controller;

import com.wlhse.cache.JedisClient;
import com.wlhse.dao.UserDao;
import com.wlhse.dto.inDto.UserDto;
import com.wlhse.dto.inDto.WeChatBindInfo;
import com.wlhse.entity.UserPojo;
import com.wlhse.service.UserService;
import com.wlhse.util.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("UserController")
@RequestMapping("/api/v3")
@Slf4j
public class UserController {

    @Resource
    private UserService service;
    @Resource
    JedisClient jedisClient;
    @Resource
    UserDao userDao;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String Login1(@RequestBody(required = false) UserDto userDto, HttpServletRequest request) {
        return service.login(userDto,request);
    }

    @RequestMapping(value = "/reset_pwd", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public String ResetPwd(@RequestBody(required = false) UserPojo userPojo) {
        return service.reset(userPojo);
    }

    @RequestMapping(value = "/logout",method = RequestMethod.POST,produces = "application/json; charset=utf-8" )
    public R logout(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        jedisClient.delManyCahce(token,0);
        return R.ok();
    }

    //0 返回安全体系菜单，1返回质量体系菜单
    @RequestMapping(value = "/selectModule/{type}",method = RequestMethod.GET)
    public R selectModule(@PathVariable("type")Integer type,HttpServletRequest request){
        Map<String, Object> resultMap=new HashMap<>();
        List<String> qhseModuleList=new ArrayList<>();
        //安全模块的编码
        qhseModuleList.add("0001");
        qhseModuleList.add("0002");
        qhseModuleList.add("0007");
        qhseModuleList.add("0004");
        qhseModuleList.add("0005");
        qhseModuleList.add("0006");

        List<String> qualityModuleList=new ArrayList<>();
        qualityModuleList.add("0005");
        qualityModuleList.add("0006");

        qualityModuleList.add("0008");
        qualityModuleList.add("0009");
        qualityModuleList.add("0010");
        qualityModuleList.add("0003");
        List<String> resultList=new ArrayList<>();
        //获取用户的编码Code
        String token = request.getHeader("Authorization");
        //先获取用户id
        String userId = "";
        if (StringUtils.isNotBlank(token)) {
            Map<String, String> map = jedisClient.hGetAll(token);
            userId= map.get("userId");
        }
        //获取到当前登陆用户权限所属的一级模块编码
        List<String> userAuthMinCode = userDao.getUserAuthMinCode(userId);
        //根据userId获取角色和权限的最小编码
        if (type==0){
            //找到用户权限所属一级菜单编码中属于qhse体系的
            for(String code:userAuthMinCode){
                if (qhseModuleList.contains(code)){
                    resultList.add(code);
                }
            }
            resultMap.put("data",resultList);
            return R.ok(resultMap);
        }
        {
            for(String code:userAuthMinCode){
                if (qualityModuleList.contains(code)){
                    resultList.add(code);
                }
            }
            resultMap.put("data",resultList);
            return R.ok(resultMap);
        }
    }

    @RequestMapping(value = "/bindWeChat",method = RequestMethod.POST)
    public R bindWeChat(@RequestBody(required = false) WeChatBindInfo weChatBindInfo) throws Exception {
        return service.bindWeChat(weChatBindInfo);
    }
}
