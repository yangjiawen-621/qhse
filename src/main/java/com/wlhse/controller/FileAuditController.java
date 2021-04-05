package com.wlhse.controller;

import com.wlhse.dto.FileAuditDto;
import com.wlhse.dto.FileAuditRecordDto;
import com.wlhse.dto.inDto.YearElementsDto;
import com.wlhse.service.FileAuditService;
import com.wlhse.service.QHSEManageSysElementsService;
import com.wlhse.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController("FileAuditController")
@RequestMapping("/api/v3")
public class FileAuditController {

    @Resource
    private FileAuditService fileAuditService;
    @Resource
    QHSEManageSysElementsService qhseManageSysElementsService;

    @RequestMapping(value = "/getFileAuditProgress", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R getProgress(@RequestParam(value = "tableId", required = false) Integer tableId) {
        return qhseManageSysElementsService.getTableCheckedProgress(tableId);
    }

    @RequestMapping(value = "/add_fileaduit", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public R addFileAudit(@RequestBody(required = false) FileAuditDto fileAuditDto) {
        return fileAuditService.addFileAudit(fileAuditDto);
    }

    @RequestMapping(value = "/delete_fileaduit/{id}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public R deleteFileAudit(@PathVariable int id) {
        return fileAuditService.deleteFileAudit(id);
    }

    @RequestMapping(value = "/query_fileaduit", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String queryFileAudit(@ModelAttribute FileAuditDto fileAuditDto) {
        return fileAuditService.queryExistFile(fileAuditDto);
    }


    /**
     * 分页查询所有
     *
     * @param page 页码数
     * @param size 每页大小
     * @return 分页结果集
     */
    @RequestMapping(value = "/get_allfileaduitpage/{page}/{size}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R getAllFileAuditPage(@PathVariable("page") int page,
                                 @PathVariable("size") int size) {
        return fileAuditService.getAllFileAuditPage(page, size);
    }

    @RequestMapping(value = "/update_status", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public String updateStatus(@RequestBody(required = false) FileAuditRecordDto fileAuditRecordDto) {
        return fileAuditService.updateStatus(fileAuditRecordDto);
    }

    @RequestMapping(value = "/update_score", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public String updateScore(@RequestBody(required = false) FileAuditRecordDto fileAuditRecordDto) {
        return fileAuditService.updateScore(fileAuditRecordDto);
    }

    @RequestMapping(value = "/get_score", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getScore(@ModelAttribute FileAuditRecordDto fileAuditRecordDto) {
        return fileAuditService.getScore(fileAuditRecordDto);
    }

    @RequestMapping(value = "/add_fileaduitrecord", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public R addFileAuditRecord(@RequestBody(required = false) FileAuditRecordDto fileAuditRecordDto) {
        return fileAuditService.addFileAuditRecord(fileAuditRecordDto);
    }

    @RequestMapping(value = "/queryRecordId", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String queryRecordId(@ModelAttribute FileAuditRecordDto fileAuditRecordDto) {
        return fileAuditService.queryRecordId(fileAuditRecordDto);
    }

    @RequestMapping(value = "/delete_fileaduitrecord/{id1}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public R deleteFileAuditRecord(@PathVariable int id1) {
        return fileAuditService.deleteFileAuditRecord(id1);
    }


    /**
     * 修复部分bug
     *
     * @param fileAuditRecordDto 文件审核条件
     * @return 文件审核结果集
     */
    @RequestMapping(value = "/get_status", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getStatus(@ModelAttribute FileAuditRecordDto fileAuditRecordDto) {
        return fileAuditService.getStatus(fileAuditRecordDto);
    }

    @RequestMapping(value = "/update_checkstatus", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public String updateCheckStatus(@RequestBody(required = false) YearElementsDto yearElementsDto) {
        return fileAuditService.updateCheckStatus(yearElementsDto);
    }


    /**
     * 不录入文件审核
     * 【修改文件审核记录字段】
     *
     * @return R
     * @author tobing
     */
    @RequestMapping(value = "/noPassReasonFileAudit", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public R noPassReasonFileAudit(@RequestBody FileAuditRecordDto fileAuditRecordDto) {
        return fileAuditService.noPassReasonFileAudit(fileAuditRecordDto);
    }

}
