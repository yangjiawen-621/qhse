package com.wlhse.dao;

import com.wlhse.dto.MonitorPlanDetail;
import com.wlhse.dto.RecordCountDto;
import com.wlhse.entity.DashboardQualityManagement;
import com.wlhse.entity.DashboardRecorderManagement;
import com.wlhse.entity.DashboardScheduleManagement;
import com.wlhse.entity.RecordDateQueryParam;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author tobing
 * @Date 2020/10/28 21:25
 * @Description Dashboard持久层接口
 */
@Repository
public interface DashboardDao {

    /**
     * 查询质量管理、质量报告计划进度
     *
     * @param qualityManagement 质量管理
     * @return R
     */
    List<DashboardQualityManagement> queryDashboardQualityManagement(DashboardQualityManagement qualityManagement);

    /**
     * 查询记录仪管理、记录仪使用情况
     *
     * @param recorderManagement 记录仪使用情况
     * @return R
     */
    List<DashboardRecorderManagement> queryDashboardRecorderManagement(DashboardRecorderManagement recorderManagement);

    /**
     * 查询标准进度管理
     *
     * @param scheduleManagement 标准进度管理
     * @return R
     */
    List<DashboardScheduleManagement> queryDashboardScheduleManagement(DashboardScheduleManagement scheduleManagement);

    /**
     * 更新【进度管理】数据
     *
     * @param dashboardScheduleManagement d
     * @return int
     */
    int updateDSM(DashboardScheduleManagement dashboardScheduleManagement);

    /**
     * 更新【质量管理】数据
     *
     * @param dashboardQualityManagement d
     * @return int
     */
    int updateDQM(DashboardQualityManagement dashboardQualityManagement);

    /**
     * 更新【记录仪管理】数据
     *
     * @param dashboardRecorderManagement d
     * @return int
     */
    int updateDRM(DashboardRecorderManagement dashboardRecorderManagement);

    /**
     * 统计【进度管理】
     *
     * @return
     */
    int countDSM();

    /**
     * 统计【质量管理】
     *
     * @return
     */
    int countDQM();

    /**
     * 统计【记录仪管理】
     *
     * @return
     */
    int countDRM();

    /**
     * 插入【进度管理】
     *
     * @param dashboardScheduleManagement dashboardScheduleManagement
     */
    void insertDSM(DashboardScheduleManagement dashboardScheduleManagement);

    /**
     * 插入【质量管理】
     *
     * @param dashboardQualityManagement dashboardQualityManagement
     */
    void insertDQM(DashboardQualityManagement dashboardQualityManagement);

    /**
     * 插入【记录仪管理】
     *
     * @param dashboardRecorderManagement dashboardRecorderManagement
     */
    void insertDRM(DashboardRecorderManagement dashboardRecorderManagement);

    /**
     * 查询【出现最多问题的配置要素】
     *
     * @param param param
     * @return R
     */
    List<RecordCountDto> queryMostProblemElement(RecordDateQueryParam param);
}
