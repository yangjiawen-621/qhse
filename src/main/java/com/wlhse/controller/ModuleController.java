package com.wlhse.controller;

import com.wlhse.cache.JedisClient;
import com.wlhse.service.ModuleService;
import com.wlhse.util.GetCurrentUserIdUtil;
import com.wlhse.util.state_code.CodeDict;
import com.wlhse.util.state_code.NR;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController("ModuleController")
@RequestMapping("/api/v3")
public class ModuleController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ModuleService moduleService;

    @Resource
    private JedisClient jedisClient;

    @Resource
    private GetCurrentUserIdUtil getCurrentUserIdUtil;

    @RequestMapping(value = "/menu_module", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String listProblemSource(HttpServletRequest request) {
        try {
            if (null != request.getHeader("Authorization") && jedisClient.exists(request.getHeader("Authorization"))) {
                return moduleService.getMenuModule(getCurrentUserIdUtil.getUserId(request));
            } else {
                return NR.r(CodeDict.NOT_LOGGED_, 1, 1, null, null, 0, 0);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
//        return moduleService.getMenuModule(getCurrentUserIdUtil.getUserId(request));
    }
}
