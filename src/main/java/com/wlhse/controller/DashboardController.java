package com.wlhse.controller;

import com.wlhse.entity.DashboardQualityManagement;
import com.wlhse.entity.DashboardRecorderManagement;
import com.wlhse.entity.DashboardScheduleManagement;
import com.wlhse.service.DashboardService;
import com.wlhse.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author tobing
 * @Date 2020/10/28 21:24
 * @Description 【质量管理】大屏Controller
 */
@RestController
@RequestMapping("/api/v3")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @RequestMapping(value = "/queryDashboardQualityManagement", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public R queryDashboardQualityManagement(@RequestBody DashboardQualityManagement qualityManagement) {
        return dashboardService.queryDashboardQualityManagement(qualityManagement);
    }

    @RequestMapping(value = "/queryDashboardRecorderManagement", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public R queryDashboardRecorderManagement(@RequestBody DashboardRecorderManagement recorderManagement) {
        return dashboardService.queryDashboardRecorderManagement(recorderManagement);
    }

    @RequestMapping(value = "/queryDashboardScheduleManagement", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public R queryDashboardScheduleManagement(@RequestBody DashboardScheduleManagement scheduleManagement) {
        return dashboardService.queryDashboardScheduleManagement(scheduleManagement);
    }

    /**
     * 下载文件模板
     *
     * @return R
     */
    @RequestMapping(value = "/downloadDashboardQualityManagementTemplate", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadDashboardQualityManagementTemplate() throws IOException {
        return dashboardService.downloadDashboardQualityManagementTemplate();
    }

    /**
     * 下载文件模板
     *
     * @return R
     */
    @RequestMapping(value = "/downloadDashboardRecorderManagementTemplate", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadDashboardRecorderManagementTemplate() throws IOException {
        return dashboardService.downloadDashboardRecorderManagement();
    }

    /**
     * 下载文件模板
     *
     * @return R
     */
    @RequestMapping(value = "/downloadDashboardScheduleManagementTemplate", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadDashboardScheduleManagementTemplate() throws IOException {
        return dashboardService.downloadDashboardScheduleManagement();
    }

    /**
     * 上传文件，解析数据
     *
     * @param file 文件
     * @return R
     */
    @RequestMapping(value = "/uploadDashboardQualityManagement", method = RequestMethod.POST)
    public R uploadDashboardQualityManagementExcel(@RequestParam(value = "file", required = false) MultipartFile file) {
        return dashboardService.uploadDashboardQualityManagementExcel(file);
    }

    /**
     * 上传文件，解析数据
     *
     * @param file 文件
     * @return R
     */
    @RequestMapping(value = "/uploadDashboardRecorderManagement", method = RequestMethod.POST)
    public R uploadDashboardRecorderManagement(@RequestParam(value = "file", required = false) MultipartFile file) {
        return dashboardService.uploadDashboardRecorderManagement(file);
    }

    /**
     * 上传文件，解析数据
     *
     * @param file 文件
     * @return R
     */
    @RequestMapping(value = "/uploadDashboardScheduleManagement", method = RequestMethod.POST)
    public R uploadDashboardScheduleManagement(@RequestParam(value = "file", required = false) MultipartFile file) {
        return dashboardService.uploadDashboardScheduleManagement(file);
    }


    /**
     * 查询出现问题最多的配置要素
     *
     * @return R
     */
    @RequestMapping(value = "/queryMostProblemElement", method = RequestMethod.GET)
    public R queryMostProblemElement(@RequestParam(value = "startDate", defaultValue = "") String startDate,
                                     @RequestParam(value = "endDate", defaultValue = "") String endDate) {
        return dashboardService.queryMostProblemElement(startDate, endDate);
    }

}
