package com.wlhse.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wlhse.cache.JedisClient;
import com.wlhse.dao.EmployeeManagementDao;
import com.wlhse.dao.UserDao;
import com.wlhse.dto.EmployeeManagementDto;
import com.wlhse.dto.inDto.UserDto;
import com.wlhse.dao.UserRoleDao;
import com.wlhse.dto.inDto.WeChatBindInfo;
import com.wlhse.dto.outDto.UserOutDto;
import com.wlhse.entity.UserPojo;
import com.wlhse.entity.UserRolePojo;
import com.wlhse.exception.WLHSException;

import com.wlhse.service.UserService;
import com.wlhse.util.*;
import com.wlhse.util.state_code.NR;
import com.wlhse.util.token.TokenUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Resource
    private UserOutDto userOutDto;

    @Resource
    private JedisClient jedisClient;

    @Resource
    private UserRoleDao userRoleDao;

    @Resource
    private UserRolePojo userRolePojo;

    @Resource
    private DeleteCacheUtil deleteCacheUtil;


    @Resource
    EmployeeManagementDao employeeDao;

    private final static String newPwd = "123456";

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public String login(UserDto userDto, HttpServletRequest request) {
        String ip = GetIPUtil.getIpAddr(request);
        logger.info("------------------------");
        int path = HashUtil.getPath(ip);
        System.out.println(path);
        if (jedisClient.get(ip, path) != null && Integer.parseInt(jedisClient.get(ip, path)) >= 5)
            throw new WLHSException("密码输入错误频繁,请稍后再输入");
        String userName = userDto.getUserName();
        String psw = userDto.getPsw();
        String MD5psw = MD5Util.getMD5(psw);
        userOutDto = userDao.getUserPojo(userName, MD5psw);
        logger.info(userDto.toString());
        if (userOutDto == null)
            throw new WLHSException("密码错误");
        if (!userOutDto.getStatus().equals("启用"))
            throw new WLHSException("账户已停用");
        String token = TokenUtil.generateString();
        //设置15天的token过期，根据客户需求可以在增加时间长度
        EmployeeManagementDto employeeManagementDto = employeeDao.queryById(userOutDto.getEmployeeId());
        userOutDto.setCompanyCode(employeeManagementDto.getCompanyCode());
        userOutDto.setCompanyName(employeeManagementDto.getCompanyName());
        userOutDto.setEmployeeName(employeeManagementDto.getName());
        userOutDto.setGroup(employeeManagementDto.getEmpGroup());
        Map<String,String> map=new HashMap<>();
        map.put("userId",String.valueOf(userOutDto.getUserId()));
        map.put("employeeId",String.valueOf(userOutDto.getEmployeeId()));
        jedisClient.hset(token,map);
        userOutDto.setToken(token);
        return NR.r(userOutDto);
    }

    @Override
    @Transactional
    public String register(UserPojo userPojo) {
        String userName = userPojo.getuName();
        if (userName.length() < 4)
            throw new WLHSException("用户名过短");
        if (userDao.countUsername(userName) != 0)
            throw new WLHSException("用户名重复");
        if (userDao.countEmployee(userPojo.getEmployeeID()) != 0)
            throw new WLHSException("该用户已存在");
        String psw = userPojo.getPwd();
        if (StringUtils.isBlank(psw))
            throw new WLHSException("请输入密码");
        String MD5psw = MD5Util.getMD5(psw);
        userPojo.setPwd(MD5psw);
        userDao.addUser(userPojo);
        userRolePojo.setRoleCode(userPojo.getRoleCode());
        userRolePojo.setuName(userName);
        userRoleDao.addSYSUSERRole(userRolePojo);
        //清除缓存
        deleteCacheUtil.deleteEmployeesCache1();
        return NR.r();
    }

    @Override
    @Transactional
    public String reset(UserPojo userPojo) {
        String userName = userPojo.getuName();
        UserPojo exsitPojo = userDao.getUserByName(userName);
        String MD5psw = MD5Util.getMD5(newPwd);
        exsitPojo.setPwd(MD5psw);
        if (userDao.updateUser(exsitPojo) < 0)
            throw new WLHSException("更新失败");
        return NR.r();
    }

    @Override
    public R bindWeChat(WeChatBindInfo weChatBindInfo) throws Exception {
        JSONObject sessionInfo = JSONObject.parseObject(jcode2Session(weChatBindInfo.getPermissionCode()));
        String openId=sessionInfo.getString("openid");
        userDao.bindWechat(weChatBindInfo.getUserId(),openId);
        return R.ok();
    }


    private String jcode2Session(String code)throws Exception{
        String sessionInfo = Jcode2SessionUtil.jscode2session("wxe93ae9db327a2e41","bea5b57909fe98bf0cbbe18321f01263",code,"authorization_code");//登录grantType固定
        return sessionInfo;
    }
}
