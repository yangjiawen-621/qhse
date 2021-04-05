package com.wlhse.controller;

import com.wlhse.dao.FileDao;
import com.wlhse.dto.QualityCheckDto;
import com.wlhse.dto.inDto.QualityCheckInDto;
import com.wlhse.service.QualityCheckService;
import com.wlhse.util.R;
import com.wlhse.util.state_code.CodeDict;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController("QualityCheckController")
@RequestMapping("/api/v3")
public class QualityCheckController {

    @Resource
    private QualityCheckService qualityCheckService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    FileDao fileDao;

    //查询所有
    @RequestMapping(value = "/queryAllTable", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R queryAllTable() {
        return qualityCheckService.queryAllTable();
    }

    //查询+条件
    @RequestMapping(value = "/queryTableByYearAndCom", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R queryTableByYearAndCom(@ModelAttribute QualityCheckDto qualityCheckDto) {
        logger.info("传入的数据:"+qualityCheckDto.toString());
        return qualityCheckService.queryTableByYearAndCom(qualityCheckDto);
    }

    //查询已推送+条件
    @RequestMapping(value = "/queryTableByYearAndComAndPush", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R queryTableByYearAndComAndPush(@ModelAttribute QualityCheckDto qualityCheckDto) {
        logger.info("传入的数据:"+qualityCheckDto.toString());
        return qualityCheckService.queryTableByYearAndComAndPush(qualityCheckDto);
    }

    //添加
    @RequestMapping(value = "/addQualityCheck", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public R addQualityCheck(@RequestBody(required = false) QualityCheckDto qualityCheckDto) {
        logger.info("传入的数据:"+qualityCheckDto.toString());
        return qualityCheckService.addQualityCheck(qualityCheckDto);
    }


    //改
    @RequestMapping(value = "/updateQualityCheck/{id}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public R updateQualityCheck(@PathVariable("id") int id,@RequestBody(required = false)QualityCheckDto qualityCheckDto) {
        return qualityCheckService.updateQualityCheck(qualityCheckDto);
    }

    //删除
    @RequestMapping(value = "/deleteQualityCheck/{id}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public R deleteQuality_Check(@PathVariable("id") int id) {
        return qualityCheckService.deleteQualityCheck(id);
    }
    //推送
    @RequestMapping(value = "/pushTable/{id}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public R pushTable(@PathVariable("id") int id) {
        return qualityCheckService.pushTable(id);
    }
    //下达或通过
    @RequestMapping(value = "/issuedTable", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public R issuedTable(@RequestBody(required = false) QualityCheckDto qualityCheckDto) {
        return qualityCheckService.issuedTable(qualityCheckDto);
    }
    //查询已下达+条件
    @RequestMapping(value = "/queryTableByYearAndComAndIssued", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R queryTableByYearAndComAndIssued(@ModelAttribute QualityCheckDto qualityCheckDto) {
        logger.info("传入的数据:"+qualityCheckDto.toString());
        return qualityCheckService.queryTableByYearAndComAndIssued(qualityCheckDto);
    }
    //已整改推送
    @RequestMapping(value = "/modifyPush/{id}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public R modifyPush(@PathVariable("id") int id) {
        return qualityCheckService.modifyPush(id);
    }
    //查询已整改+条件
    @RequestMapping(value = "/queryByYearComAndModify", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R queryByYearComAndModify(@ModelAttribute QualityCheckDto qualityCheckDto) {
        logger.info("传入的数据:"+qualityCheckDto.toString());
        return qualityCheckService.queryByYearComAndModify(qualityCheckDto);
    }
    //下达或通过
    @RequestMapping(value = "/backTable", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public R backTable(@RequestBody(required = false) QualityCheckDto qualityCheckDto) {
        return qualityCheckService.backTable(qualityCheckDto);
    }
    @RequestMapping(value = "/getOriginFileName", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R pictureDownload(String fileName) {
        fileName=fileDao.getQualityAttachOriginFileName(fileName);
        System.out.println(fileName);
        R ok = R.ok();
        ok.put("data",fileName);
        return ok;
    }
    @RequestMapping(value = "/deleteAttach", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public R deleteAttach(@RequestParam(value = "fileName")String fileName ) {
        if(fileDao.deleteAttachOriginFileName(fileName)<=0){
            return R.error("删除失败");
        }
        return R.ok();
    }

    //查询所有
    @RequestMapping(value = "/queryAllPassTable", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R queryAllPassTable() {
        return qualityCheckService.queryAllPassTable();
    }

    //零星添加
    @RequestMapping(value = "/addOddQualityCheck", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public R addOddQualityCheck(@RequestBody(required = false) QualityCheckDto qualityCheckDto) {
        logger.info("传入的数据:" + qualityCheckDto.toString());
        return qualityCheckService.addQualityCheck2(qualityCheckDto);
    }





}