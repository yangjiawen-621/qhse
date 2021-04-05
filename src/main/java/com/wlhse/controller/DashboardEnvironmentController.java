package com.wlhse.controller;

import com.wlhse.service.DashboardEnvironmentManagementService;
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
 * @Date 2020/11/21 20:45
 * @Description 大屏-环保管理
 */
@RestController
@RequestMapping("/api/v3")
public class DashboardEnvironmentController {

    @Autowired
    private DashboardEnvironmentManagementService dashboardEnvironmentManagementService;


    /**
     * 大屏-环保管理-查询
     *
     * @param companyCode companyCode
     * @return R
     */
    @RequestMapping(value = "/queryDashboardEnvironmentManagement", method = RequestMethod.GET)
    public R queryDashboardEnvironmentManagement(@RequestParam(value = "companyCode", defaultValue = "") String companyCode) {
        R r = null;
        try {
            r = dashboardEnvironmentManagementService.queryDashboardEnvironmentManagement(companyCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }


    /**
     * 大屏-环保管理-模板下载
     *
     * @return R
     * @throws IOException io
     */
    @RequestMapping(value = "/downloadDashboardEnvironmentManagementTemplate", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadDashboardEnvironmentManagementTemplate() throws IOException {
        ResponseEntity<byte[]> res = null;
        try {
            res = dashboardEnvironmentManagementService.downloadDashboardEnvironmentManagementTemplate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 大屏-环保管理-模板上传
     *
     * @return R
     * @throws IOException io
     */
    @RequestMapping(value = "/uploadDashboardEnvironmentManagement", method = RequestMethod.POST)
    public R uploadDashboardEnvironmentManagement(@RequestParam(value = "file", required = false) MultipartFile file) {
        return dashboardEnvironmentManagementService.uploadDashboardEnvironmentManagement(file);
    }


}
