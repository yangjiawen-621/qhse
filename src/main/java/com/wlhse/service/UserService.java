package com.wlhse.service;


import com.wlhse.dto.inDto.UserDto;
import com.wlhse.dto.inDto.WeChatBindInfo;
import com.wlhse.entity.UserPojo;
import com.wlhse.util.R;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    String login(UserDto userDto, HttpServletRequest request);

    String register(UserPojo userPojo);

    String reset(UserPojo userPojo);

    R bindWeChat(WeChatBindInfo weChatBindInfo) throws Exception;
}
