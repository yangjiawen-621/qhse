//package com.wlhse.service.impl;
//
//import com.github.pagehelper.PageHelper;
//import com.wlhse.dao.UserLogDao;
//import com.wlhse.dto.getDto.BaseGetDto;
//import com.wlhse.dto.inDto.UserLogInDto;
//import com.wlhse.entity.UserLogPojo;
//import com.wlhse.service.UserLogService;
//import com.wlhse.util.state_code.CodeDict;
//import com.wlhse.util.state_code.NR;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//@Service
//public class UserLogServiceImpl implements UserLogService {
//
//    @Resource
//    private UserLogDao userLogDao;
//
//    @Resource
//    private UserLogPojo userLogPojo;
//
//
//    @Override
//    public String getAllUserLogPojoService(BaseGetDto baseGetDto) {
//        String result;
//        if (baseGetDto.getType().equals("all")) {
//            List<UserLogPojo> allUserLogPojo = userLogDao.getAllUserLogPojo();
//            result = NR.r(CodeDict.CODE_MESSAGE_DATA, 0, 0, allUserLogPojo, null, 0, 0);
//        } else {
//            int allUserLogPojoCount = userLogDao.getAllUserLogPojoCount();
//            PageHelper.startPage(baseGetDto.getPageIdx(), baseGetDto.getPageSize());
//            List<UserLogPojo> allUserLogPojo = userLogDao.getAllUserLogPojo();
//            result = NR.r(CodeDict.CODE_MESSAGE_DATA_PAGE_LIST, 0, 0, null, allUserLogPojo, allUserLogPojoCount, baseGetDto.getPageIdx());
//        }
//        return result;
//    }
//
//    @Override
//    public String getUserLogPojoCountService(UserLogInDto userLogInDto) {
//        if (StringUtils.isNotBlank(userLogInDto.getStartDate()) && StringUtils.isEmpty(userLogInDto.getEndDate())) {
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//            userLogInDto.setEndDate(df.format(new Date()));
//        }
//        Integer userLoginCount = userLogDao.getUserLogPojoCount(userLogInDto);
//        return NR.r(CodeDict.CODE_MESSAGE_DATA, 0, 0, userLoginCount, null, 0, 0);
//    }
//
//    @Override
//    public String deleteUserLogPojoService(Integer id) {
//        int i = userLogDao.deleteUserLogPojo(id);
//        return NR.r(CodeDict.CODE_MESSAGE, i, CodeDict.DELETE_FAILE, null, null, 0, 0);
//    }
//
//    @Override
//    public String insertUserLogPojoService(Integer userId) {
//        userLogPojo.setUserId(userId);
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//        userLogPojo.setLoginTime(df.format(new Date()));
//        int i = userLogDao.insertUserLogPojo(userLogPojo);
//        return NR.r(CodeDict.CODE_MESSAGE, i, CodeDict.INSERT_FAILE, null, null, 0, 0);
//    }
//}
