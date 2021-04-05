package com.wlhse.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.wlhse.dao.CompanyDao;
import com.wlhse.dao.DashboardEnvironmentManagementDao;
import com.wlhse.entity.CompanyPojo;
import com.wlhse.entity.DashboardEnvironmentManagement;
import com.wlhse.entity.DashboardEnvironmentManagementDto;
import com.wlhse.entity.DashboardQueryParam;
import com.wlhse.service.DashboardEnvironmentManagementService;
import com.wlhse.util.DashboardDateUtils;
import com.wlhse.util.DashboardEnvironmentManagementListener;
import com.wlhse.util.R;
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
import java.util.*;

/**
 * @Author tobing
 * @Date 2020/11/23 19:58
 * @Description
 */
@Service
@Transactional
public class DashboardEnvironmentManagementServiceImpl implements DashboardEnvironmentManagementService {

    @Autowired
    private DashboardEnvironmentManagementDao dashboardEnvironmentManagementDao;
    @Autowired
    private CompanyDao companyDao;

    /**
     * 模板上传
     *
     * @param file file
     * @return R
     */
    @Override
    public R uploadDashboardEnvironmentManagement(MultipartFile file) {
        if (file == null) {
            return R.error("文件不能为空");
        }
        try {
            EasyExcel.read(file.getInputStream(), DashboardEnvironmentManagement.class,
                    new DashboardEnvironmentManagementListener(dashboardEnvironmentManagementDao)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("文件异常");
        }
        return R.ok("更新成功");
    }

    /**
     * 模板下载
     *
     * @return file
     */
    @Override
    public ResponseEntity<byte[]> downloadDashboardEnvironmentManagementTemplate() throws IOException {
        return templateUtils("环保节能管理");
    }

    /**
     * 查询
     * 月份：水、电、气消耗量、污水量、污水运转率量
     * 季度：污水量、污水转运处置量
     * 年度：污水量、污水转运处置量
     *
     * @param companyCode companyCode
     * @return R
     */
    @Override
    public R queryDashboardEnvironmentManagement(String companyCode) {

        DashboardEnvironmentManagement dem = new DashboardEnvironmentManagement();
        dem.setCompanyCode(companyCode);
        Calendar calendar = Calendar.getInstance();

        // 查询月份数据
        Date minMonthDay = DashboardDateUtils.getMinMonthDay(calendar);
        Date maxMonthDay = DashboardDateUtils.getMaxMonthDay(calendar);
        List<DashboardEnvironmentManagementDto> monthDEM =
                dashboardEnvironmentManagementDao.queryMonthDEM(new DashboardQueryParam(companyCode, minMonthDay, maxMonthDay));
        // 查询年份数据
        Date maxYearDay = DashboardDateUtils.getMaxYearDay(calendar);
        Date minYearDay = DashboardDateUtils.getMinYearDay(calendar);
        List<DashboardEnvironmentManagementDto> yearDEM =
                dashboardEnvironmentManagementDao.queryYearDEM(new DashboardQueryParam(companyCode, minYearDay, maxYearDay));
        // 查询季度数据
        Date minQuarterDay = DashboardDateUtils.getMinQuarterDay(calendar);
        Date maxQuarterDay = DashboardDateUtils.getMaxQuarterDay(calendar);
        List<DashboardEnvironmentManagementDto> quarterDEM =
                dashboardEnvironmentManagementDao.queryQuarterDEM(new DashboardQueryParam(companyCode, minQuarterDay, maxQuarterDay));

        DashboardEnvironmentManagementDto res = new DashboardEnvironmentManagementDto();
        // 合并月份数据
        if (monthDEM != null && monthDEM.size() > 0 && monthDEM.get(0) != null) {
            DashboardEnvironmentManagementDto month = monthDEM.get(0);
            res.setDieselMonth(month.getDieselMonth());
            res.setElectricityMonth(month.getElectricityMonth());
            res.setGasMonth(month.getGasMonth());
            res.setGasolineMonth(month.getGasolineMonth());
            res.setWaterMonth(month.getWaterMonth());
            res.setSewageTransferMonth(month.getSewageTransferMonth());
            res.setSewageVolumeMonth(month.getSewageVolumeMonth());
        }
        // 合并年度数据
        if (yearDEM != null && yearDEM.size() > 0 && yearDEM.get(0) != null) {
            DashboardEnvironmentManagementDto year = yearDEM.get(0);
            res.setDieselYear(year.getDieselYear());
            res.setElectricityYear(year.getElectricityYear());
            res.setGasYear(year.getGasYear());
            res.setGasolineYear(year.getGasolineYear());
            res.setWaterYear(year.getWaterYear());
            res.setSewageTransferYear(year.getSewageTransferYear());
            res.setSewageVolumeYear(year.getSewageVolumeYear());
        }
        // 合并季度
        if (quarterDEM != null && quarterDEM.size() > 0 && quarterDEM.get(0) != null) {
            DashboardEnvironmentManagementDto quarter = quarterDEM.get(0);
            res.setSewageTransferQuarter(quarter.getSewageTransferQuarter());
            res.setSewageVolumeQuarter(quarter.getSewageVolumeQuarter());
        }
        String curYear = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        res.setYear(curYear);
        res.setMonthYear(curYear + month);
        res.setYear(String.valueOf(calendar.get(Calendar.YEAR)));

        // 封装并返回数据
        Map<String, Object> map = new HashMap<>();
        map.put("data", res);
        return R.ok(map);
    }


    /**
     * 模板文件工具
     *
     * @param name 文件名
     * @return file
     * @throws IOException io
     */
    private ResponseEntity<byte[]> templateUtils(String name) throws IOException {
        // TODO 文件修改
        String path = System.getProperty("catalina.home") + "\\webapps\\" + "DashboardTemplate";
//        String path = "D:\\fileTest";
        File file = new File(path + File.separator + name + ".xlsx");
        // 文件不存在：创建模板文件
        if (!file.exists()) {
            if ("环保节能管理".equals(name)) {
                createDashboardEnvironmentManagementTemplate(file);
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
     * 模板文件创建
     * 查询公司代码
     *
     * @param file file
     * @throws IOException io
     */
    private void createDashboardEnvironmentManagementTemplate(File file) throws IOException {
        List<DashboardEnvironmentManagement> environmentManagementList = new ArrayList<>();
        // 查询所有公司数据
        List<CompanyPojo> companyPojoList = companyDao.queryQhseCompany();
        for (int i = 0; i < companyPojoList.size(); i++) {
            CompanyPojo companyPojo = companyPojoList.get(i);
            if (companyPojo.getCompanyCode() == null || companyPojo.getCompanyCode().length() != 6) {
                companyPojoList.remove(i);
                continue;
            }
            DashboardEnvironmentManagement millionPojo = new DashboardEnvironmentManagement();
            millionPojo.setCompanyName(companyPojo.getName());
            millionPojo.setCompanyCode(companyPojo.getCompanyCode());
            environmentManagementList.add(millionPojo);
        }
        EasyExcel.write(file, DashboardEnvironmentManagement.class).
                sheet("模板").doWrite(environmentManagementList);
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(file, DashboardEnvironmentManagement.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            excelWriter.write(environmentManagementList, writeSheet);
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }
}
