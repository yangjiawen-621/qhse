package com.wlhse.service;

import com.wlhse.entity.DashboardQualityManagement;
import com.wlhse.entity.DashboardRecorderManagement;
import com.wlhse.entity.DashboardScheduleManagement;
import com.wlhse.util.R;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author tobing
 * @Date 2020/10/28 21:26
 * @Description Dashboard业务层接口
 */
public interface DashboardService {

    /**
     * 查询质量管理、质量报告计划进度
     *
     * @param qualityManagement 质量管理
     * @return R
     */
    R queryDashboardQualityManagement(DashboardQualityManagement qualityManagement);

    /**
     * 查询记录仪管理、记录仪使用情况
     *
     * @param recorderManagement 记录仪使用情况
     * @return R
     */
    R queryDashboardRecorderManagement(DashboardRecorderManagement recorderManagement);

    /**
     * 查询标准进度管理
     *
     * @param scheduleManagement 标准进度管理
     * @return R
     */
    R queryDashboardScheduleManagement(DashboardScheduleManagement scheduleManagement);

    /**
     * 下载质量报告计划进度文件模板
     *
     * @return R
     */
    ResponseEntity<byte[]> downloadDashboardQualityManagementTemplate() throws IOException;

    /**
     * 下载记录管理文件模板
     *
     * @return R
     */
    public ResponseEntity<byte[]> downloadDashboardRecorderManagement() throws IOException;

    /**
     * 下载标准进度管理文件模板
     *
     * @return R
     */
    public ResponseEntity<byte[]> downloadDashboardScheduleManagement() throws IOException;

    /**
     * 文件上传
     *
     * @param file file
     * @return R
     */
    R uploadDashboardQualityManagementExcel(MultipartFile file);

    /**
     * 文件上传
     *
     * @param file file
     * @return R
     */
    R uploadDashboardRecorderManagement(MultipartFile file);

    /**
     * 文件上传
     *
     * @param file file
     * @return R
     */
    R uploadDashboardScheduleManagement(MultipartFile file);

    /**
     * 查询最多问题的配置要素
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return R
     */
    R queryMostProblemElement(String startDate, String endDate);
}
