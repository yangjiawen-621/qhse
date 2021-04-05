package com.wlhse.controller;

import com.wlhse.service.DashboardSecurityService;
import com.wlhse.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author tobing
 * @Date 2020/11/21 20:44
 * @Description 大屏-安全管理
 */
@RestController
@RequestMapping("/api/v3")
public class DashboardSecurityController {

    @Autowired
    private DashboardSecurityService dashboardSecurityService;

    /***************************安全管理*********************************/

    /**
     * 查询【安全管理】大屏
     *
     * @return R
     */
    @RequestMapping(value = "/queryDashboardSecurity", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R queryDashboardSecurity(@RequestParam(value = "companyCode", defaultValue = "") String companyCode) {
        return dashboardSecurityService.queryDashboardSecurity(companyCode);
    }

    /**
     * 下载【安全管理】模板
     *
     * @return R
     * @throws IOException io
     */
    @RequestMapping(value = "/downloadDashboardSecurityTemplate", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadDashboardSecurityTemplate() throws IOException {
        ResponseEntity<byte[]> res = null;
        try {
            res = dashboardSecurityService.downloadDashboardSecurityTemplate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 上传【安全管理】数据更新
     *
     * @param file file
     * @return R
     */
    @RequestMapping(value = "/uploadDashboardSecurity", method = RequestMethod.POST)
    public R uploadDashboardSecurity(@RequestParam(value = "file", required = false) MultipartFile file) {
        return dashboardSecurityService.uploadDashboardSecurity(file);
    }

    /***************************百万工时*********************************/

    /**
     * 查询安全管理-百万工时
     *
     * @param companyCode companyCode
     * @return R
     */
    @RequestMapping(value = "/queryDashboardSecurityMillion", method = RequestMethod.GET)
    public R queryDashboardSecurityMillion(@RequestParam(value = "companyCode", defaultValue = "") String companyCode) {
        return dashboardSecurityService.queryDashboardSecurityMillion(companyCode);
    }

    /**
     * 下载安全管理-百万工时模板
     *
     * @return file
     * @throws IOException io
     */
    @RequestMapping(value = "/downloadDashboardSecurityMillionTemplate", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadDashboardSecurityMillionTemplate() throws IOException {
        ResponseEntity<byte[]> res = null;
        try {
            res = dashboardSecurityService.downloadDashboardSecurityMillionTemplate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 上传【安全管理】-百万工时
     *
     * @param file file
     * @return R
     */
    @RequestMapping(value = "/uploadDashboardSecurityMillion", method = RequestMethod.POST)
    public R uploadDashboardSecurityMillion(@RequestParam(value = "file", required = false) MultipartFile file) {
        return dashboardSecurityService.uploadDashboardSecurityMillion(file);
    }

    /***************************安技项目管理*********************************/

    /**
     * 根据公司、项目级别查询数据
     *
     * @param companyCode  companyCode
     * @param projectLevel 项目级别
     * @return R
     */
    @RequestMapping(value = "/queryDashboardSecurityProjectByLevel", method = RequestMethod.GET)
    public R queryDashboardSecurityProjectByLevel(@RequestParam(value = "companyCode", defaultValue = "") String companyCode,
                                                  @RequestParam(value = "projectLevel", defaultValue = "") String projectLevel) {
        return dashboardSecurityService.queryDashboardSecurityProjectByLevel(companyCode, projectLevel);
    }

    /**
     * 根据companyCode查询项目汇总
     *
     * @param companyCode companyCode
     * @return R
     */
    @RequestMapping(value = "/queryDashboardSecurityProjectCount", method = RequestMethod.GET)
    public R queryDashboardSecurityProjectCount(@RequestParam(value = "companyCode", defaultValue = "") String companyCode) {
        try {
            return dashboardSecurityService.queryDashboardSecurityProjectCount(companyCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.ok();
    }


    /**
     * 下载安全管理-安技项目管理
     *
     * @return file
     * @throws IOException io
     */
    @RequestMapping(value = "/downloadDashboardSecurityProjectTemplate", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadDashboardSecurityProjectTemplate() throws IOException {
        ResponseEntity<byte[]> res = null;
        try {
            res = dashboardSecurityService.downloadDashboardSecurityProjectTemplate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 上传【安全管理】-安技项目管理
     *
     * @param file file
     * @return R
     */
    @RequestMapping(value = "/uploadDashboardSecurityProject", method = RequestMethod.POST)
    public R uploadDashboardSecurityProject(@RequestParam(value = "file", required = false) MultipartFile file) {
        return dashboardSecurityService.uploadDashboardSecurityProject(file);
    }
}
