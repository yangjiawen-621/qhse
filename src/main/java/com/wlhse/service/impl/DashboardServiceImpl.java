package com.wlhse.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.wlhse.dao.CompanyDao;
import com.wlhse.dao.DashboardDao;
import com.wlhse.dto.RecordCountDto;
import com.wlhse.entity.*;
import com.wlhse.service.DashboardService;
import com.wlhse.util.*;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author tobing
 * @Date 2020/10/28 21:27
 * @Description Dashboard业务层实现
 */
@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private DashboardDao dashboardDao;

    @Autowired
    private CompanyDao companyDao;

    @Override
    public R queryDashboardQualityManagement(DashboardQualityManagement qualityManagement) {
        List<DashboardQualityManagement> qualityManagementList =
                dashboardDao.queryDashboardQualityManagement(qualityManagement);
        R r = new R();
        r.put("data", qualityManagementList);
        return r;
    }

    @Override
    public R queryDashboardRecorderManagement(DashboardRecorderManagement recorderManagement) {
        List<DashboardRecorderManagement> recorderManagementList
                = dashboardDao.queryDashboardRecorderManagement(recorderManagement);
        R r = new R();
        r.put("data", recorderManagementList);
        return r;
    }

    @Override
    public R queryDashboardScheduleManagement(DashboardScheduleManagement scheduleManagement) {
        List<DashboardScheduleManagement> scheduleManagementList
                = dashboardDao.queryDashboardScheduleManagement(scheduleManagement);
        R r = new R();
        r.put("data", scheduleManagementList);
        return r;
    }

    @Override
    public ResponseEntity<byte[]> downloadDashboardQualityManagementTemplate() throws IOException {
        return generateResponseEntity("质量管理");
    }

    @Override
    public ResponseEntity<byte[]> downloadDashboardRecorderManagement() throws IOException {
        return generateResponseEntity("记录仪管理");
    }

    @Override
    public ResponseEntity<byte[]> downloadDashboardScheduleManagement() throws IOException {
        return generateResponseEntity("标准进度管理");
    }

    @Override
    @Transactional
    public R uploadDashboardQualityManagementExcel(MultipartFile file) {
        if (file == null) {
            return R.error("文件不能为空");
        }
        try {
            EasyExcel.read(file.getInputStream(), DashboardQualityManagement.class,
                    new DashboardDQMListener(dashboardDao)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("文件异常");
        }
        return R.ok("更新成功");
    }

    @Override
    @Transactional
    public R uploadDashboardRecorderManagement(MultipartFile file) {
        if (file == null) {
            return R.error("文件不能为空");
        }
        try {
            EasyExcel.read(file.getInputStream(), DashboardRecorderManagement.class,
                    new DashboardDRMListener(dashboardDao)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("文件异常");
        }
        return R.ok("更新成功");
    }

    @Override
    @Transactional
    public R uploadDashboardScheduleManagement(MultipartFile file) {
        // 校验文件是否为空
        if (file == null) {
            return R.error("文件不能为空");
        }
        try {
            EasyExcel.read(file.getInputStream(), DashboardScheduleManagement.class,
                    new DashboardDSMListener(dashboardDao)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("文件异常");
        }
        return R.ok("更新成功");
    }

    @Override
    public R queryMostProblemElement(String startDate, String endDate) {
        RecordDateQueryParam param = new RecordDateQueryParam(startDate, endDate);
        List<RecordCountDto> recordCountDtoList = null;
        try {
            recordCountDtoList = dashboardDao.queryMostProblemElement(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("data", recordCountDtoList);
        return R.ok(map);
    }


    /**
     * 返回指定文件名的文件
     *
     * @param name
     * @return
     * @throws IOException
     */
    private ResponseEntity<byte[]> generateResponseEntity(String name) throws IOException {
        // TODO 文件修改
        String path = System.getProperty("catalina.home") + "\\webapps\\" + "DashboardTemplate";
//        String path = "D:\\fileTest";
        File file = new File(path + File.separator + name + ".xlsx");
        // 文件不存在：创建模板文件
        if (!file.exists()) {
            if ("质量管理".equals(name)) {
                createDQMTemplateFile(file);
            } else if ("记录仪管理".equals(name)) {
                createDRMTemplateFile(file);
            } else if ("标准进度管理".equals(name)) {
                createDSMTemplateFile(file);
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        String fileName = name + ".xlsx";
        String downloadFileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
        headers.setContentDispositionFormData("attachment", downloadFileName);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }

    /**
     * 生成记录仪管理模板文件
     *
     * @param file File
     * @throws IOException
     */
    private void createDRMTemplateFile(File file) throws IOException {
        List<DashboardRecorderManagement> dashboardRecorderManagementList = new ArrayList<>();
        // 查询所有公司数据
        List<CompanyPojo> companyPojoList = companyDao.queryQhseCompany();
        for (int i = 0; i < companyPojoList.size(); i++) {
            CompanyPojo companyPojo = companyPojoList.get(i);
            if (companyPojo.getCompanyCode() == null || companyPojo.getCompanyCode().length() != 6) {
                companyPojoList.remove(i);
                continue;
            }
            DashboardRecorderManagement dashboardRecorderManagement = new DashboardRecorderManagement();
            dashboardRecorderManagement.setCompanyName(companyPojo.getName());
            dashboardRecorderManagement.setCompanyCode(companyPojo.getCompanyCode());
            dashboardRecorderManagementList.add(dashboardRecorderManagement);
        }
        EasyExcel.write(file, DashboardRecorderManagement.class).
                sheet("模板").doWrite(dashboardRecorderManagementList);
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(file, DashboardRecorderManagement.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            excelWriter.write(dashboardRecorderManagementList, writeSheet);
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    /**
     * 生成质量管理模板文件
     *
     * @param file File
     * @throws IOException
     */
    private void createDQMTemplateFile(File file) throws IOException {
        List<DashboardQualityManagement> dashboardQualityManagementList = new ArrayList<>();
        // 查询所有公司数据
        List<CompanyPojo> companyPojoList = companyDao.queryQhseCompany();
        for (int i = 0; i < companyPojoList.size(); i++) {
            CompanyPojo companyPojo = companyPojoList.get(i);
            if (companyPojo.getCompanyCode() == null || companyPojo.getCompanyCode().length() != 6) {
                companyPojoList.remove(i);
                continue;
            }
            DashboardQualityManagement dashboardQualityManagement = new DashboardQualityManagement();
            dashboardQualityManagement.setCompanyName(companyPojo.getName());
            dashboardQualityManagement.setCompanyCode(companyPojo.getCompanyCode());
            dashboardQualityManagementList.add(dashboardQualityManagement);
        }
        EasyExcel.write(file, DashboardQualityManagement.class).
                sheet("模板").doWrite(dashboardQualityManagementList);
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(file, DashboardQualityManagement.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            excelWriter.write(dashboardQualityManagementList, writeSheet);
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    /**
     * 生成标准进度模板文件
     *
     * @param file File
     * @throws IOException
     */
    private void createDSMTemplateFile(File file) throws IOException {
        List<DashboardScheduleManagement> dashboardScheduleManagementList = new ArrayList<>();
        // 查询所有公司数据
        List<CompanyPojo> companyPojoList = companyDao.queryQhseCompany();
        for (int i = 0; i < companyPojoList.size(); i++) {
            CompanyPojo companyPojo = companyPojoList.get(i);
            if (companyPojo.getCompanyCode() == null || companyPojo.getCompanyCode().length() != 6) {
                companyPojoList.remove(i);
                continue;
            }
            DashboardScheduleManagement dashboardScheduleManagement = new DashboardScheduleManagement();
            dashboardScheduleManagement.setCompanyName(companyPojo.getName());
            dashboardScheduleManagement.setCompanyCode(companyPojo.getCompanyCode());
            dashboardScheduleManagementList.add(dashboardScheduleManagement);
        }
        EasyExcel.write(file, DashboardScheduleManagement.class).
                sheet("模板").doWrite(dashboardScheduleManagementList);
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(file, DashboardScheduleManagement.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            excelWriter.write(dashboardScheduleManagementList, writeSheet);
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }
}
