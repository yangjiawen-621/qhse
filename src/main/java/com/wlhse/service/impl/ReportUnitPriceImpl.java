package com.wlhse.service.impl;

import com.wlhse.dao.ReportUnitPriceDao;
import com.wlhse.entity.ReportUnitPricePojo;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.ReportUnitPriceService;
import com.wlhse.util.HashUtil;
import com.wlhse.util.R;
import com.wlhse.util.state_code.NR;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReportUnitPriceImpl implements ReportUnitPriceService {
    @Resource
    private ReportUnitPriceDao reportUnitPriceDao;

    @Value("${RESOURCES_URL}")
    private String url;

    @Override
    @Transactional
    public R newUnitPrice(ReportUnitPricePojo reportUnitPricePojo) {
        String costYear = reportUnitPricePojo.getCostYear();
        if(reportUnitPricePojo.getType().equals("all")){
            List<ReportUnitPricePojo> list = reportUnitPriceDao.getUnitPriceByContiton(reportUnitPricePojo);
            String costYear1 = String.valueOf(Integer.parseInt(costYear) - 1);
            ReportUnitPricePojo reportUnitPricePojo1 = new ReportUnitPricePojo();
            reportUnitPricePojo1.setCostYear(costYear1);
            List<ReportUnitPricePojo> list1 = reportUnitPriceDao.getUnitPriceByContiton(reportUnitPricePojo1);
            if(list.size()<=0){
                ReportUnitPricePojo reportUnitPricePojo2 = new ReportUnitPricePojo();
                for (ReportUnitPricePojo r: list1){
                    reportUnitPricePojo2.setCostYear(costYear);
                    reportUnitPricePojo2.setUnitPrice(r.getUnitPrice());
                    reportUnitPricePojo2.setReportType(r.getReportType());
                    reportUnitPricePojo2.setAuditorProportion(r.getAuditorProportion());
                    reportUnitPricePojo2.setApproverProportion(r.getApproverProportion());
                    reportUnitPriceDao.addUnitPrice(reportUnitPricePojo2);
                }
                return R.ok();
            }else {
                throw new WLHSException("已存在该年份单价表！不能批量新增！");
            }
        }else {
            String reportType = reportUnitPricePojo.getReportType();
            int count = reportUnitPriceDao.getUnitPriceCount(costYear,reportType);
            if (count != 0)
                throw new WLHSException("重复新增单价,请检查!");
            reportUnitPriceDao.addUnitPrice(reportUnitPricePojo);
            return R.ok();
        }
    }

    @Override
    public String queryUnitPrice(ReportUnitPricePojo reportUnitPricePojo) {
        reportUnitPricePojo.setUrl(url);
        List<ReportUnitPricePojo> list = reportUnitPriceDao.getUnitPriceByContiton(reportUnitPricePojo);
        return NR.r(list);
    }

    @Override
    public String queryUnitPriceById(int id) {
        return NR.r(reportUnitPriceDao.getUnitPriceById(id, url));
    }

    @Override
    public String updateUnitPrice(ReportUnitPricePojo reportUnitPricePojo) {
        if (reportUnitPriceDao.updateUnitPrice(reportUnitPricePojo) <= 0)
            throw new WLHSException("更新失败");
        return NR.r();
    }

    @Override
    @Transactional
    public String deleteUnitPrice(int id) {
        if (reportUnitPriceDao.deleteUnitPriceById(id) <= 0)
            throw new WLHSException("删除失败");
        return NR.r();
    }
}
