package com.wlhse.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.wlhse.cache.JedisClient;
import com.wlhse.dao.CompanyDao;
import com.wlhse.dao.EmployeeManagementDao;
import com.wlhse.dao.UserDao;
import com.wlhse.dao.UserRoleDao;
import com.wlhse.dto.EmployeeManagementDto;
import com.wlhse.dto.getDto.EmloyeeGetDto;
import com.wlhse.entity.UserPojo;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.EmployeeManagementService;
import com.wlhse.util.DeleteCacheUtil;
import com.wlhse.util.HashUtil;
import com.wlhse.util.MD5Util;
import com.wlhse.util.state_code.NR;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class EmloyeeManagementServiceImpl implements EmployeeManagementService {

    @Resource
    private EmployeeManagementDao employeeDao;

    @Resource
    private UserDao userDao;

    @Resource
    private UserRoleDao userRoleDao;

    @Resource
    private JedisClient jedisClient;

    @Resource
    private DeleteCacheUtil deleteCacheUtil;

    @Resource
    private CompanyDao companyDao;

    @Resource
    private EmployeeManagementDao employeeManagementDao;

    //改dao 接口有问题
    //返回使用频率的list   ---继续返回
    @Override
    public String getAllEmployeeDto(EmloyeeGetDto dto, int userId) {
        String companyCode = employeeManagementDao.queryCompanyCodeByEmpId(userId);
        dto.setCompanyCode(companyCode);
        List<EmployeeManagementDto> list = new ArrayList<>();
        String redisName = "EmployeeDto" + "_" + dto.getName() + "_" + dto.getCompanyCode();

        int path = HashUtil.getPath(redisName);
        Map<String, String> employee_redis = jedisClient.hGetAll(redisName, path);

        //有缓存
        if (employee_redis.size() == 0) {
            List<EmployeeManagementDto> allEmployeeDto = employeeDao.getAllEmployeeDto(dto.getName(), dto.getCompanyCode());
            jedisClient.pipeLineHSet(redisName, allEmployeeDto, path);
        }

        String userRedisName = "user_" + userId + "_employee_" + dto.getInputType();
        int path1 = HashUtil.getPath(userRedisName);
        Set<String> userRedisNameStrings = jedisClient.zRevRange(userRedisName, path1);
        //没有 使用缓存
        Map<String, String> employee_redis1 = jedisClient.hGetAll(redisName, path);
        if (userRedisNameStrings.size() > 0) {
            for (String s : userRedisNameStrings) {
                String userJson = employee_redis1.get(s);
                if (StringUtils.isNotBlank(userJson)) {
                    EmployeeManagementDto employeeDto = JSON.parseObject(userJson, EmployeeManagementDto.class);
                    if (employeeDto != null) list.add(employeeDto);
                    employee_redis1.remove(s);
                }
            }
        }
        for (Map.Entry<String, String> entry : employee_redis1.entrySet()) {
            EmployeeManagementDto employeeDto = JSON.parseObject(entry.getValue(), EmployeeManagementDto.class);
            if (employeeDto != null) list.add(employeeDto);
        }
        return NR.r(list);
    }

    // redisName 还差查询条件
    @Override
    public String listEmployee(EmployeeManagementDto employeeManagementDto, int id) {
        String companyCode = employeeManagementDto.getCompanyCode();
        if(companyCode == null || companyCode.equals("")) {
            companyCode = employeeManagementDao.queryCompanyCodeByEmpId(id);
            employeeManagementDto.setCompanyCode(companyCode);
        }
        String redisName = "employee_redis"
                + "_" + employeeManagementDto.getType()
                + "_" + employeeManagementDto.getPageIdx()
                + "_" + employeeManagementDto.getPageSize()
                + "_" + employeeManagementDto.getName()
                + "_" + employeeManagementDto.getCompanyCode()
                + "_" + employeeManagementDto.getBirthday()
                + "_" + employeeManagementDto.getPosition()
                + "_" + employeeManagementDto.getStation()
                + "_" + employeeManagementDto.getEducation()
                + "_" + employeeManagementDto.getEmpGroup()
                + "_" + employeeManagementDto.getCategory();
        String result;
        int path = HashUtil.getPath(redisName);
        List<EmployeeManagementDto> list = new ArrayList<>();
        Map<String, String> employee_redis = jedisClient.hGetAll(redisName, path);
        //有缓存
        if (employee_redis.size() > 0) {
            for (Map.Entry<String, String> entry : employee_redis.entrySet()) {
                EmployeeManagementDto employeeManagementDto1 = JSON.parseObject(entry.getValue(), EmployeeManagementDto.class);
                list.add(employeeManagementDto1);
            }
            if (employeeManagementDto.getType().equals("all")){
                result = NR.r(list);
            }else{
                result = NR.r(list, employeeDao.getEmployeeTotal(employeeManagementDto), employeeManagementDto.getPageIdx());
            }
        }
        //没有缓存 进来加载缓存
        else {
            if (employeeManagementDto.getType().equals("all")) {
                list = employeeDao.queryEmployeeByCondition(employeeManagementDto);
                result = NR.r(list);
            } else {
                int total = employeeDao.getEmployeeTotal(employeeManagementDto);
                PageHelper.startPage(employeeManagementDto.getPageIdx(), employeeManagementDto.getPageSize());
                list = employeeDao.queryEmployeeByCondition(employeeManagementDto);
                result = NR.r(list, total, employeeManagementDto.getPageIdx());
            }
            //使用PipeLine
            jedisClient.pipeLineHSet(redisName, list, path);
        }
        return result;
    }

    @Override
    //批量删除
    //判断hash存再添加
    @Transactional
    public String addEmployee(EmployeeManagementDto employeeManagementDto) {
        //获取二级单位name，以及选择单位name
//        String companyCode = employeeManagementDto.getCompanyCode();
//        String lastLevelCompanyName = companyDao.queryByCompanyCode(companyCode);
//        String secondLevelCompanyName = companyDao.queryByCompanyCode(companyCode.substring(0, 4));
        //员工编号，姓名，二级单位名称，底层单位名称
        String hash = HashUtil.hash(employeeManagementDto.getCompanyCode() + employeeManagementDto.getName() +
                employeeManagementDto.getEmpGroup() + employeeManagementDto.getCategory());
        employeeManagementDto.setHash(hash);
        if (employeeManagementDao.queryByHash(hash) > 0)
            throw new WLHSException("存在此员工");
        if (employeeDao.addEmployee(employeeManagementDto) <= 0)
            throw new WLHSException("添加员工失败");
        deleteCacheUtil.deleteEmployeesCache();
        return NR.r();
    }

    @Override
    @Transactional
    public String updateEmployee(EmployeeManagementDto employeeManagementDto) {
        // 旧密码是否与数据库符合 && 新密码是否符合规范
        if (employeeManagementDto == null)
            throw new WLHSException("更新失败");

        String oldPassword = employeeManagementDto.getOldPassword();
        String newPassword = employeeManagementDto.getNewPassword();
        if (StringUtils.isNotBlank(oldPassword) && StringUtils.isNotBlank(newPassword)) {
            int employeeID = employeeManagementDto.getEmployeeID();
            UserPojo userPojo = userDao.getByEmployeeId(employeeID);
            judgePsw(oldPassword, newPassword, userPojo);
        } else {
            // 只更新employee  先更新用户信息，同时清空缓存
            if (employeeDao.updateEmployee(employeeManagementDto) <= 0)
                throw new WLHSException("更新失败");
            deleteCacheUtil.deleteEmployeesCache();
        }
        return NR.r();
    }

    private void judgePsw(String oldPassword, String newPassword, UserPojo userPojo) {
        // 更新密码
        // 旧密码与数据库比对
        String MD5psw = MD5Util.getMD5(oldPassword);
        if (!MD5psw.equals(userPojo.getPwd()))
            throw new WLHSException("原密码输入错误");
        if (StringUtils.isBlank(newPassword))
            throw new WLHSException("新密码输入为空");
        userPojo.setPwd(MD5Util.getMD5(newPassword));
        if (userDao.updateUser(userPojo) <= 0)
            throw new WLHSException("更新失败");
    }

    //query By Id
    @Override
    public String queryById(int employeeId) {
        String redisName = "employee_id_" + employeeId;
        int path = HashUtil.getPath(redisName);
        String s = jedisClient.get(redisName, path);
        EmployeeManagementDto employeeManagementDto;
        //有缓存
        if (StringUtils.isNotBlank(s)) {
            employeeManagementDto = JSON.parseObject(s, EmployeeManagementDto.class);
        } else {
            employeeManagementDto = employeeDao.queryById(employeeId);
            String s1 = JSON.toJSONString(employeeManagementDto);
            //7天
            jedisClient.setSecondsIdx(redisName, s1, 604800, path);
        }
        return NR.r(employeeManagementDto);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String deleteEmployee(Integer employeeID) {
        // 通过ID查出uName
        UserPojo userPojo = userDao.getByEmployeeId(employeeID);
        if (null != userPojo) {
            String uName = userPojo.getuName();
            // 删除 sys_user_role表 by UName
            if (null != userRoleDao.queryByUName(uName)) {
                userRoleDao.deleteByUName(uName);
            }
            // 删除 sys_user表 by EmployeeId
            userDao.deleteUser(userPojo);
            // 删除 employee by EmployeeId
            employeeDao.deleteEmployee(employeeID);
            deleteCacheUtil.deleteEmployeesCache();
            return NR.r();
        } else {
            // 员工未注册账户
            // 删除 employee by EmployeeId
            employeeDao.deleteEmployee(employeeID);
            deleteCacheUtil.deleteEmployeesCache();
            return NR.r();
        }
    }

    @Override
    public String queryRoleCodeByEmpId(int employeeId) {
        return NR.r(employeeDao.queryRoleCodeByEmpId(employeeId));
    }

    //redis 数据格式 存储所有数据  employee--employeeId--employeeJson
    // 存储使用次数 employee_userId(使用者id)--使用次数--employeeJson
    @Override
    @Transactional
    public String updateRoleCodeByEmpId(int employeeId, String roleCode, String status) {
        deleteCacheUtil.deleteEmployeesCache1();
        if (StringUtils.isNotBlank(roleCode)) {
            employeeDao.updateRoleCodeByEmpId(employeeId, roleCode);
        }
        if (StringUtils.isNotBlank(status)) {
            employeeDao.updateStatusByEmpId(employeeId, status);
        }
        return NR.r();
    }

    @Override
    public String queryRoleCodeByEmpId123(int employeeId) {
        return NR.r(employeeDao.getEmployeePojo(employeeId));
    }

    @Override
    public EmployeeManagementDto querryRoleById(int id) {
        return employeeDao.queryById(id);
    }

    @Override
    public String getEmployeeNameByEmployeeID(int eId) {
        return  employeeDao.queryEmployeeNameByEmployeeId(eId);
    }
}
