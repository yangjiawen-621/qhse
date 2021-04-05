package com.wlhse.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.wlhse.dao.CompanyDao;
import com.wlhse.dao.DashboardSecurityDao;
import com.wlhse.dao.DashboardSecurityMillionDao;
import com.wlhse.dao.DashboardSecurityProjectDao;
import com.wlhse.entity.*;
import com.wlhse.service.DashboardSecurityService;
import com.wlhse.util.*;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @Date 2020/11/21 21:07
 * @Description
 */
@Service
@Transactional
public class DashboardSecurityServiceImpl implements DashboardSecurityService {


    private static final Logger logger = LoggerFactory.getLogger(DashboardSecurityServiceImpl.class);
    @Autowired
    private DashboardSecurityDao dashboardSecurityDao;
    @Autowired
    private DashboardSecurityMillionDao dashboardSecurityMillionDao;
    @Autowired
    private DashboardSecurityProjectDao dashboardSecurityProjectDao;
    @Autowired
    private CompanyDao companyDao;

    /*************************上传文件**************************/

    @Override
    public R uploadDashboardSecurity(MultipartFile file) {
        if (file == null) {
            return R.error("文件不能为空");
        }
        try {
            EasyExcel.read(file.getInputStream(), DashboardSecurityPojo.class,
                    new DashboardSecurityListener(dashboardSecurityDao)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("文件异常");
        }
        return R.ok("更新成功");
    }

    @Override
    public R uploadDashboardSecurityProject(MultipartFile file) {
        if (file == null) {
            return R.error("文件不能为空");
        }
        try {
            EasyExcel.read(file.getInputStream(), DashboardSecurityProjectPojo.class,
                    new DashboardSecurityProjectListener(dashboardSecurityProjectDao)).sheet().doRead();
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return R.error("文件异常");
        }
        return R.ok("更新成功");
    }

    @Override
    public R uploadDashboardSecurityMillion(MultipartFile file) {
        if (file == null) {
            return R.error("文件不能为空");
        }
        try {
            EasyExcel.read(file.getInputStream(), DashboardSecurityMillionPojo.class,
                    new DashboardSecurityMillionListener(dashboardSecurityMillionDao)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("文件异常");
        }
        return R.ok("更新成功");
    }


    /*************************下载文件**************************/
    @Override
    public ResponseEntity<byte[]> downloadDashboardSecurityTemplate() throws IOException {
        return templateUtils("查患纠违事件季度完成情况");
    }

    @Override
    public ResponseEntity<byte[]> downloadDashboardSecurityProjectTemplate() throws IOException {
        return templateUtils("安技项目管理");
    }

    @Override
    public ResponseEntity<byte[]> downloadDashboardSecurityMillionTemplate() throws IOException {
        return templateUtils("百万工时");
    }

    private ResponseEntity<byte[]> templateUtils(String name) throws IOException {
        // TODO 文件路径
        String path = System.getProperty("catalina.home") + "\\webapps\\" + "DashboardTemplate";
        // String path = "D:\\fileTest";
        File file = new File(path + File.separator + name + ".xlsx");
        // 文件不存在：创建模板文件
        if (!file.exists()) {
            if ("查患纠违事件季度完成情况".equals(name)) {
                createDashboardSecurityTemplate(file);
            } else if ("百万工时".equals(name)) {
                createDashboardSecurityMillionTemplate(file);
            } else if ("安技项目管理".equals(name)) {
                createDashboardSecurityProjectTemplate(file);
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

    /*************************查询**************************/
    @Override
    public R queryDashboardSecurity(String companyCode) {
        DashboardSecurityPojo dashboardSecurityPojo = new DashboardSecurityPojo();
        dashboardSecurityPojo.setCompanyCode(companyCode);
        List<DashboardSecurityPojo> dashboardSecurityPojoList = null;
        try {
            dashboardSecurityPojoList = dashboardSecurityDao.queryDashboardSecurity(dashboardSecurityPojo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (dashboardSecurityPojoList == null || dashboardSecurityPojoList.size() == 0) {
            return R.ok();
        }
        return R.ok().put("data", dashboardSecurityPojoList.get(0));
    }

    @Override
    public R queryDashboardSecurityMillion(String companyCode) {
        Calendar calendar = Calendar.getInstance();
        // 获取月度时间区间
        Date minMonthDay = DashboardDateUtils.getMinMonthDay(calendar);
        Date maxMonthDay = DashboardDateUtils.getMaxMonthDay(calendar);

        // 获取年度时间区间
        Date minYearDay = DashboardDateUtils.getMinYearDay(calendar);
        Date maxYearDay = DashboardDateUtils.getMaxYearDay(calendar);

        // 分别查询年度、月度数据
        List<DashboardSecurityMillionDto> millionMonthDtoList =
                dashboardSecurityMillionDao.queryMonthDSM(new DashboardQueryParam(companyCode, minMonthDay, maxMonthDay));
        List<DashboardSecurityMillionDto> millionYearDtoList =
                dashboardSecurityMillionDao.queryYearDSM(new DashboardQueryParam(companyCode, minYearDay, maxYearDay));

        // 封装结果
        DashboardSecurityMillionDto res = new DashboardSecurityMillionDto();
        if (millionMonthDtoList != null && millionMonthDtoList.size() > 0 && millionMonthDtoList.get(0) != null) {
            res.setMonthSubtotal(millionMonthDtoList.get(0).getMonthSubtotal());
        }
        if (millionYearDtoList != null && millionYearDtoList.size() > 0 && millionYearDtoList.get(0) != null) {
            res.setYearSubtotal(millionYearDtoList.get(0).getYearSubtotal());
        }
        res.setYear(String.valueOf(calendar.get(Calendar.YEAR)));
        res.setMonth(String.valueOf(calendar.get(Calendar.MONTH) + 1));
        Map<String, Object> map = new HashMap<>();
        map.put("data", res);
        return R.ok(map);
    }

    @Override
    public R queryDashboardSecurityProjectByLevel(String companyCode, String projectLevel) {
        DashboardSecurityProjectPojo projectPojo = new DashboardSecurityProjectPojo();
        projectPojo.setCompanyCode(companyCode);
        projectPojo.setProjectLevel(projectLevel);
        List<DashboardSecurityProjectPojo> projectPojoList =
                dashboardSecurityProjectDao.queryDashboardSecurityProjectByLevel(projectPojo);
        if (projectPojoList == null || projectPojoList.size() == 0) {
            return R.ok();
        }
        return R.ok().put("data", projectPojoList);
    }

    @Override
    public R queryDashboardSecurityProjectCount(String companyCode) {
        DashboardSecurityProjectPojo projectPojo = new DashboardSecurityProjectPojo();
        projectPojo.setCompanyCode(companyCode);
        List<DashboardSecurityProjectCount> countList = dashboardSecurityProjectDao.queryDashboardSecurityProjectCount(projectPojo);
        Double totalUsage = dashboardSecurityProjectDao.queryDashboardSecurityProjectTotalUsageRate(projectPojo);
        Map<String, Object> map = new HashMap<>();
        map.put("data", countList);
        map.put("totalUsage", totalUsage);
        return R.ok(map);
    }


    /**
     * 创建模板文件
     *
     * @param file file
     * @throws IOException io
     */
    private void createDashboardSecurityTemplate(File file) throws IOException {
        List<DashboardSecurityPojo> dashboardSecurityPojoList = new ArrayList<>();
        // 查询所有公司数据
        List<CompanyPojo> companyPojoList = companyDao.queryQhseCompany();
        for (int i = 0; i < companyPojoList.size(); i++) {
            CompanyPojo companyPojo = companyPojoList.get(i);
            if (companyPojo.getCompanyCode() == null || companyPojo.getCompanyCode().length() != 6) {
                companyPojoList.remove(i);
                continue;
            }
            DashboardSecurityPojo dashboardSecurityPojo = new DashboardSecurityPojo();
            dashboardSecurityPojo.setCompanyName(companyPojo.getName());
            dashboardSecurityPojo.setCompanyCode(companyPojo.getCompanyCode());
            dashboardSecurityPojoList.add(dashboardSecurityPojo);
        }
        EasyExcel.write(file, DashboardSecurityPojo.class).
                sheet("模板").doWrite(dashboardSecurityPojoList);
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(file, DashboardSecurityPojo.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            excelWriter.write(dashboardSecurityPojoList, writeSheet);
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    /**
     * 创建安全管理文件模板-安技项目管理
     *
     * @param file file
     * @throws IOException io
     */
    private void createDashboardSecurityProjectTemplate(File file) throws IOException {
        List<DashboardSecurityProjectPojo> dashboardSecurityProjectPojoList = new ArrayList<>();
        // 查询所有公司数据
        List<CompanyPojo> companyPojoList = companyDao.queryQhseCompany();
        for (int i = 0; i < companyPojoList.size(); i++) {
            CompanyPojo companyPojo = companyPojoList.get(i);
            if (companyPojo.getCompanyCode() == null || companyPojo.getCompanyCode().length() != 6) {
                companyPojoList.remove(i);
                continue;
            }
            DashboardSecurityProjectPojo projectPojo = new DashboardSecurityProjectPojo();
            projectPojo.setCompanyName(companyPojo.getName());
            projectPojo.setCompanyCode(companyPojo.getCompanyCode());
            dashboardSecurityProjectPojoList.add(projectPojo);
        }
        EasyExcel.write(file, DashboardSecurityProjectPojo.class).
                sheet("模板").doWrite(dashboardSecurityProjectPojoList);
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(file, DashboardSecurityProjectPojo.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            excelWriter.write(dashboardSecurityProjectPojoList, writeSheet);
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    /**
     * 创建安全管理文件模板-百万工时
     *
     * @param file
     * @throws IOException
     */
    private void createDashboardSecurityMillionTemplate(File file) throws IOException {
        List<DashboardSecurityMillionPojo> millionPojoList = new ArrayList<>();
        // 查询所有公司数据
        List<CompanyPojo> companyPojoList = companyDao.queryQhseCompany();
        for (int i = 0; i < companyPojoList.size(); i++) {
            CompanyPojo companyPojo = companyPojoList.get(i);
            if (companyPojo.getCompanyCode() == null || companyPojo.getCompanyCode().length() != 6) {
                companyPojoList.remove(i);
                continue;
            }
            DashboardSecurityMillionPojo millionPojo = new DashboardSecurityMillionPojo();
            millionPojo.setCompanyName(companyPojo.getName());
            millionPojo.setCompanyCode(companyPojo.getCompanyCode());
            millionPojoList.add(millionPojo);
        }
        EasyExcel.write(file, DashboardSecurityMillionPojo.class).
                sheet("模板").doWrite(millionPojoList);
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(file, DashboardSecurityMillionPojo.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            excelWriter.write(millionPojoList, writeSheet);
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }
}
