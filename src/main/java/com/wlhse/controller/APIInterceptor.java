package com.wlhse.controller;

import com.wlhse.cache.JedisClient;
import com.wlhse.dao.ModuleDao;
import com.wlhse.dto.inDto.InterfaceModuleInDto;
import com.wlhse.util.SortCodeUtil;
import com.wlhse.util.state_code.CodeDict;
import com.wlhse.util.state_code.NR;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

//自定义拦截器 AOP
public class APIInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private JedisClient jedisClient;

    @Resource
    private SortCodeUtil sortCodeUtil;

    @Resource
    private ModuleDao moduleDao;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        try {
            String token = request.getHeader("Authorization");

            String employeeId = "";
            if (StringUtils.isNotBlank(token)) {
                Map<String, String> map = jedisClient.hGetAll(token);
                employeeId= map.get("employeeId");
            }

            //当前用户登陆了，且token未过期
            if (!employeeId.equals("")){
                return true;
            }
            //未登录，且
            PrintWriter pw = response.getWriter();
               pw.write(NR.r(CodeDict.ILLEGAL_FAIL, 0, 0, null, null, 0, 0));
            return false;
//
//            String servletPath = request.getServletPath();
//            String method = request.getMethod();
//            InterfaceModuleInDto interfaceModuleInDto = new InterfaceModuleInDto(Integer.parseInt(employeeId), sortCodeUtil.getNoNumberString(servletPath), method);
//            int count = moduleDao.getInterfaceCountByEmpId(interfaceModuleInDto);
//            if (StringUtils.isNotBlank(employeeId) && count >= 1) {
//                //spring请求的链式执行顺序为Filter-->拦截器-->controller
//                if (!("GET".equals(method))) {
//                    logger.info("employeeId:" + employeeId + " url:" + servletPath + " method:" + method);
//                }
//                return true;
//            } else {
//                PrintWriter pw = response.getWriter();
//
//                pw.write(NR.r(CodeDict.ILLEGAL_FAIL, 0, 0, null, null, 0, 0));
//                return false;
//            }
        } catch (Exception e) {
            logger.info(request.getRequestURI()+" 请求出错");
            PrintWriter pw = response.getWriter();
            pw.write(NR.r(CodeDict.ILLEGAL_FAIL, 0, 0, null, null, 0, 0));
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }
}
