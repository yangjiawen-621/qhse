package com.wlhse.controller;

import com.wlhse.dao.DrFileDao;
import com.wlhse.dao.MonitorFileDao;
import com.wlhse.dto.QualityCheckTableRecordAttachInfoDto;
import com.wlhse.dto.QualityCheckTableRecordDto;
import com.wlhse.dto.QualityFileInputInfoDto;
import com.wlhse.dto.inDto.FilePropagationFileInfo;
import com.wlhse.entity.ElementInputFileInfo;
import com.wlhse.service.QhseElementsInputService;
import com.wlhse.service.QualityCheckTableRecordService;
import com.wlhse.service.UploadService;
import com.wlhse.util.IdUtil;
import com.wlhse.util.R;
import com.wlhse.util.state_code.CodeDict;
import com.wlhse.util.state_code.NR;
import com.wlhse.util.token.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/api/v3")
@Controller("FileUploadUtils")
public class FileUploadUtils {

    @Resource
    private UploadService uploadService;
    @Resource
    private QhseElementsInputService qhseElementsInputService;

    @Resource
    private QualityCheckTableRecordService qualityCheckTableRecordService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    MonitorFileDao monitorFileDao;

    @Resource
    DrFileDao drFileDao;


    public String setFile(MultipartFile file, String str) throws Exception {

        String rootPath = System.getProperty("catalina.home") + "\\webapps\\" + str;
        // 原始名称
        String originalFileName = file.getOriginalFilename();
        // 新文件名
        String token = TokenUtil.generateString();
        String newFileName = token + originalFileName.substring(originalFileName.lastIndexOf("."));
        File newFile = new File(rootPath + File.separator + File.separator + newFileName);
        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdirs();
        }
        file.transferTo(newFile);
        return newFileName;
    }

    @RequestMapping(value = "/file_upload", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String uploadFile(@RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return NR.r(CodeDict.CODE_MESSAGE, -1, CodeDict.UPLOAD_EMPTY, null, null, 0, 0);
        } else {
            String fileName = setFile(file, "resources\\");
            return NR.r(CodeDict.CODE_MESSAGE_DATA, 0, 0, fileName, null, 0, 0);
        }
    }

    @RequestMapping(value = "/uploadQHSEProblem", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String uploadQHSEProblem(@RequestParam(value = "file", required = false) MultipartFile file) throws Exception {//上传图片

        if (file.isEmpty()) {
            return NR.r(CodeDict.CODE_MESSAGE, -1, CodeDict.UPLOAD_EMPTY, null, null, 0, 0);
        } else if ("jpg".equals(file.getOriginalFilename().split("\\.")[1].toLowerCase()) || "png".equals(file.getOriginalFilename().split("\\.")[1].toLowerCase()) ||
                "bmp".equals(file.getOriginalFilename().split("\\.")[1].toLowerCase())) {
            String fileName = setFile(file, "resources\\QHSEProblem\\photoes");
            return NR.r(CodeDict.CODE_MESSAGE_DATA, 0, 0, fileName, null, 0, 0);
        } else {
            return NR.r(CodeDict.CODE_MESSAGE, -1, CodeDict.UPLOAD_TYPE_ERROR, null, null, 0, 0);
        }
    }

    @RequestMapping(value = "/uploadQHSEFill", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String uploadQHSEFill(@RequestParam(value = "file", required = false) MultipartFile file) throws Exception {//上传图片和视频
        if (file.isEmpty()) {
            return NR.r(CodeDict.CODE_MESSAGE, -1, CodeDict.UPLOAD_EMPTY, null, null, 0, 0);
        } else if ("jpg".equals(file.getOriginalFilename().split("\\.")[1].toLowerCase()) || "png".equals(file.getOriginalFilename().split("\\.")[1].toLowerCase()) ||
                "bmp".equals(file.getOriginalFilename().split("\\.")[1].toLowerCase())) {
            String fileName = setFile(file, "resources\\QHSEFill\\photoes");
            return NR.r(CodeDict.CODE_MESSAGE_DATA, 0, 0, fileName, null, 0, 0);
        } else if ("mp4".equals(file.getOriginalFilename().split("\\.")[1].toLowerCase()) || "avi".equals(file.getOriginalFilename().split("\\.")[1].toLowerCase()) ||
                "flash".equals(file.getOriginalFilename().split("\\.")[1].toLowerCase()) ||
                "rmvb".equals(file.getOriginalFilename().split("\\.")[1].toLowerCase()) ||
                "rm".equals(file.getOriginalFilename().split("\\.")[1].toLowerCase())) {
            String fileName = setFile(file, "resources\\QHSEFill\\videos\\");
            return NR.r(CodeDict.CODE_MESSAGE_DATA, 0, 0, fileName, null, 0, 0);
        } else {
            return NR.r(CodeDict.CODE_MESSAGE, -1, CodeDict.UPLOAD_TYPE_ERROR, null, null, 0, 0);
        }
    }

    @RequestMapping(value = "/employees_excel_upload", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String uploadEmployees(@RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return NR.r(CodeDict.CODE_MESSAGE, -1, CodeDict.UPLOAD_EMPTY, null, null, 0, 0);
        } else {
            String fileName = setFile(file, "Employees\\");
            String path = System.getProperty("catalina.home") + "\\webapps\\Employees\\" + fileName;
            return uploadService.uploadEmployees(path);
        }
    }

    @RequestMapping(value = "/reports_excel_upload", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String uploadReports(@RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return NR.r(CodeDict.CODE_MESSAGE, -1, CodeDict.UPLOAD_EMPTY, null, null, 0, 0);
        } else {
            String fileName = setFile(file, "Reports\\");
            String path = System.getProperty("catalina.home") + "\\webapps\\Reports\\" + fileName;
            return uploadService.uploadReports(path);
        }
    }

    //checklistEXCEL上传
    @RequestMapping(value = "/check_list_excel_upload", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public R uploadCheckList(@RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return R.error(CodeDict.UPLOAD_EMPTY, "上传文件为空");
        } else {
            String fileName = setFile(file, "CheckList\\");
            String path = System.getProperty("catalina.home") + "\\webapps\\CheckList\\" + fileName;
            return uploadService.uploadCheckList(path);
        }
    }

    //事故上传
    @RequestMapping(value = "/accident_upload", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String uploadAccident(@RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return NR.r(CodeDict.CODE_MESSAGE, -1, CodeDict.UPLOAD_EMPTY, null, null, 0, 0);
        } else {
            String fileName = setFile(file, "resources\\QHSEAccident\\");
            return NR.r(CodeDict.CODE_MESSAGE_DATA, 0, 0, fileName, null, 0, 0);
        }
    }

    //事件上传
    @RequestMapping(value = "/event_upload", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String uploadEvent(@RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return NR.r(CodeDict.CODE_MESSAGE, -1, CodeDict.UPLOAD_EMPTY, null, null, 0, 0);
        } else {
            String fileName = setFile(file, "resources\\QHSEEvent\\");
            return NR.r(CodeDict.CODE_MESSAGE_DATA, 0, 0, fileName, null, 0, 0);
        }
    }

    //要素证据上传
    @RequestMapping(value = "/evidence_upload", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String uploadEvidence(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) throws Exception {
        String originName = file.getOriginalFilename();
        if (file.isEmpty()) {
            return NR.r(CodeDict.CODE_MESSAGE, -1, CodeDict.UPLOAD_EMPTY, null, null, 0, 0);
        } else {
            ElementInputFileInfo elementInputFileInfo = new ElementInputFileInfo();
            elementInputFileInfo.setElementOriginFileName(originName);
            String fileName = setFile(file, "resources\\QHSEEvidence\\");
            elementInputFileInfo.setNewElementFileName(fileName);
            qhseElementsInputService.insertNewOriginFileName(elementInputFileInfo);
            return NR.r(CodeDict.CODE_MESSAGE_DATA, 0, 0, fileName, null, 0, 0);
        }
    }

    //---------管理要素审核excel录入数据库
    @RequestMapping(value = "/managesyselements_excel_upload", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public R uploadQHSEMSElements(@RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return R.error(CodeDict.UPLOAD_EMPTY, "上传文件为空");
        } else {
            String fileName = setFile(file, "ManageSysElements\\");
            String path = System.getProperty("catalina.home") + "\\webapps\\ManageSysElements\\" + fileName;
            return uploadService.uploadQHSEManageSysElements(path);
        }
    }

    //upload files for file propagation.
    @RequestMapping(value = "/propagationFileUpload", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String uploadFilesForPropagation(@RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return NR.r(CodeDict.CODE_MESSAGE, -1, CodeDict.UPLOAD_EMPTY, null, null, 0, 0);
        } else {
            FilePropagationFileInfo filePropagationFileInfo = new FilePropagationFileInfo();
            filePropagationFileInfo.setOriginName(file.getOriginalFilename());
            String fileName = setFile(file, "FilePropagation\\");
            filePropagationFileInfo.setFilePath(fileName);
            IdUtil idUtil = new IdUtil(2, 5, 3);
            filePropagationFileInfo.setId(idUtil.getId());
            boolean b = uploadService.insertFilePropagationFileRecord(filePropagationFileInfo);
            if (b)
                return NR.r(CodeDict.CODE_MESSAGE_DATA, 0, 0, fileName, null, 0, 0);
            else
                return NR.r(CodeDict.CODE_MESSAGE, -1, CodeDict.UPLOAD_EMPTY, null, null, 0, 0);

        }
    }

    @RequestMapping(value = "/QualityCheck_excel_upload", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public R uploadQuality_Check(@RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return R.error(CodeDict.UPLOAD_EMPTY, "上传文件为空");
        } else {
            String fileName = setFile(file, "CheckList\\");
            String path = System.getProperty("catalina.home") + "\\webapps\\CheckList\\" + fileName;
            return uploadService.uploadQualityCheck(path);
        }
    }

    @RequestMapping(value = "/uploaddanger", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String uploadDanger(@RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        //上传图片
        if (file.isEmpty()) {
            return NR.r(CodeDict.CODE_MESSAGE, -1, CodeDict.UPLOAD_EMPTY, null, null, 0, 0);
        }
//        else if ("jpg".equals(file.getOriginalFilename().split("\\.")[1].toLowerCase()) || "png".equals(file.getOriginalFilename().split("\\.")[1].toLowerCase()) ||
//                "bmp".equals(file.getOriginalFilename().split("\\.")[1].toLowerCase())) {
        else if (CONTENT_TYPES.contains(file.getContentType())) {
            String originFileName = file.getOriginalFilename();
            String fileName = setFile(file, "resources\\QHSEDanger\\photoes");
            drFileDao.insertNewFile(fileName, originFileName);
            //拼接生成图片下载链接链接
            String downloadLink = "/downloadDangerFile?fileName=" + fileName;
            return NR.r(CodeDict.CODE_MESSAGE_DATA, 0, 0, downloadLink, null, 0, 0);
        } else {
            return NR.r(CodeDict.CODE_MESSAGE, -1, CodeDict.UPLOAD_TYPE_ERROR, null, null, 0, 0);
        }
    }

    // 【质量】隐患图片上传
    // tobing
    @RequestMapping(value = "/qualityUploaddanger", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String qualityUploadDanger(@RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        //上传图片
        if (file.isEmpty()) {
            return NR.r(CodeDict.CODE_MESSAGE, -1, CodeDict.UPLOAD_EMPTY, null, null, 0, 0);
        }
//        else if ("jpg".equals(file.getOriginalFilename().split("\\.")[1].toLowerCase()) || "png".equals(file.getOriginalFilename().split("\\.")[1].toLowerCase()) ||
//                "bmp".equals(file.getOriginalFilename().split("\\.")[1].toLowerCase())) {
        else if (CONTENT_TYPES.contains(file.getContentType())) {
            String originFileName = file.getOriginalFilename();
            String fileName = setFile(file, "resources\\QualityDanger\\photoes");
            drFileDao.insertNewFile(fileName, originFileName);
            //拼接生成图片下载链接链接
            String downloadLink = "/downloadDangerFile?fileName=" + fileName;
            return NR.r(CodeDict.CODE_MESSAGE_DATA, 0, 0, downloadLink, null, 0, 0);
        } else {
            return NR.r(CodeDict.CODE_MESSAGE, -1, CodeDict.UPLOAD_TYPE_ERROR, null, null, 0, 0);
        }
    }

    private static final List<String> CONTENT_TYPES =
            Arrays.asList("image/jpeg", "image/gif", "image/png", "image/bmp");

    // TODO 微信小程序文件名会有异常
    @RequestMapping(value = "/uploadregulation", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String uploadregulation(@RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return NR.r(CodeDict.CODE_MESSAGE, -1, CodeDict.UPLOAD_EMPTY, null, null, 0, 0);
        }
//        else if ("jpg".equals(file.getOriginalFilename().split("\\.")[1].toLowerCase()) || "png".equals(file.getOriginalFilename().split("\\.")[1].toLowerCase()) ||
//                "bmp".equals(file.getOriginalFilename().split("\\.")[1].toLowerCase())) {
        else if (CONTENT_TYPES.contains(file.getContentType())) {
            String originFileName = file.getOriginalFilename();
            String fileName = setFile(file, "resources\\QHSERegulation\\photoes");
            drFileDao.insertNewFile(fileName, originFileName);
            //拼接生成图片下载链接链接
            String downloadLink = "/downloadRegulationFile?fileName=" + fileName;
            return NR.r(CodeDict.CODE_MESSAGE_DATA, 0, 0, downloadLink, null, 0, 0);
        } else {
            return NR.r(CodeDict.CODE_MESSAGE, -1, CodeDict.UPLOAD_TYPE_ERROR, null, null, 0, 0);
        }
    }

    // 【质量】违章图片上传
    // tobing
    @RequestMapping(value = "/qaulityUploadregulation", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String qualityUploadregulation(@RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return NR.r(CodeDict.CODE_MESSAGE, -1, CodeDict.UPLOAD_EMPTY, null, null, 0, 0);
        }
//        else if ("jpg".equals(file.getOriginalFilename().split("\\.")[1].toLowerCase()) || "png".equals(file.getOriginalFilename().split("\\.")[1].toLowerCase()) ||
//                "bmp".equals(file.getOriginalFilename().split("\\.")[1].toLowerCase())) {
        else if (CONTENT_TYPES.contains(file.getContentType())) {
            String originFileName = file.getOriginalFilename();
            String fileName = setFile(file, "resources\\QualityRegulation\\photoes");
            drFileDao.insertNewFile(fileName, originFileName);
            //拼接生成图片下载链接链接
            String downloadLink = "/downloadRegulationFile?fileName=" + fileName;
            return NR.r(CodeDict.CODE_MESSAGE_DATA, 0, 0, downloadLink, null, 0, 0);
        } else {
            return NR.r(CodeDict.CODE_MESSAGE, -1, CodeDict.UPLOAD_TYPE_ERROR, null, null, 0, 0);
        }
    }

    //上传远程监控截图,直接将截图下载链接放置于响应结果中
    @RequestMapping(value = "/uploadScreenShot", method = RequestMethod.POST)
    @ResponseBody
    public String uploadScreenShot(@RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return NR.r(CodeDict.CODE_MESSAGE, -1, CodeDict.UPLOAD_EMPTY, null, null, 0, 0);
        }
//        else if ("jpg".equals(file.getOriginalFilename().split("\\.")[1].toLowerCase()) || "png".equals(file.getOriginalFilename().split("\\.")[1].toLowerCase()) ||
//                "bmp".equals(file.getOriginalFilename().split("\\.")[1].toLowerCase())) {
        else if (CONTENT_TYPES.contains(file.getContentType())) {
            String originFileName = file.getOriginalFilename();
            String fileName = setFile(file, "RemoteMonitor\\screenShot");
            monitorFileDao.insertNewFile(fileName, originFileName);
            //拼接生成图片下载链接链接
            String downloadLink = "/screenShotDownload?fileName=" + fileName;
            return NR.r(CodeDict.CODE_MESSAGE_DATA, 0, 0, downloadLink, null, 0, 0);
        } else {
            return NR.r(CodeDict.CODE_MESSAGE, -1, CodeDict.UPLOAD_TYPE_ERROR, null, null, 0, 0);
        }
    }

    @RequestMapping(value = "/addQualityAttach", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String addQualityAttach(@RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return NR.r(CodeDict.CODE_MESSAGE, -1, CodeDict.UPLOAD_EMPTY, null, null, 0, 0);
        } else {
            String OriginalFilename = file.getOriginalFilename();
            QualityCheckTableRecordAttachInfoDto attachInfoDto = new QualityCheckTableRecordAttachInfoDto();
            attachInfoDto.setAttachOriginName(OriginalFilename);
            String FilePath = setFile(file, "resources\\QualityCheck");
            attachInfoDto.setAttachFilePath(FilePath);
            boolean b = uploadService.insertAttachInfoDto(attachInfoDto);
            if (b)
                return NR.r(CodeDict.CODE_MESSAGE_DATA, 0, 0, FilePath, null, 0, 0);
            else
                return NR.r(CodeDict.CODE_MESSAGE, -1, CodeDict.UPLOAD_EMPTY, null, null, 0, 0);
        }
    }

    //要素多文件上传
    @RequestMapping(value = "/uploadAllElement", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String uploadAllElement(@RequestParam(value = "file", required = false) List<MultipartFile> file) throws Exception {
        String fileName = "";
        List<String> lists = new ArrayList();
        if (file.isEmpty()) {
            return NR.r(CodeDict.CODE_MESSAGE, -1, CodeDict.UPLOAD_EMPTY, null, null, 0, 0);
        } else {
            for (int i = 0; i < file.size(); i++) {
                String originName = file.get(i).getOriginalFilename();
                if ("jpg".equals(file.get(i).getOriginalFilename().split("\\.")[1].toLowerCase()) || "png".equals(file.get(i).getOriginalFilename().split("\\.")[1].toLowerCase()) ||
                        "bmp".equals(file.get(i).getOriginalFilename().split("\\.")[1].toLowerCase())) {
                    fileName = setFile(file.get(i), "resources\\QHSEEvidence\\photo");
                } else {
                    fileName = setFile(file.get(i), "resources\\QHSEEvidence\\attach");
                }
                ElementInputFileInfo elementInputFileInfo = new ElementInputFileInfo();
                elementInputFileInfo.setElementOriginFileName(originName);
                elementInputFileInfo.setNewElementFileName(fileName);
                qhseElementsInputService.insertNewOriginFileName(elementInputFileInfo);
                lists.add(fileName);
            }
        }
        return NR.r(CodeDict.CODE_MESSAGE_DATA, 0, 0, lists, null, 0, 0);
    }

    @RequestMapping(value = "/Quality_evidence_upload", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String uploadQualityEvidence(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) throws Exception {
        String originName = file.getOriginalFilename();
        if (file.isEmpty()) {
            return NR.r(CodeDict.CODE_MESSAGE, -1, CodeDict.UPLOAD_EMPTY, null, null, 0, 0);
        } else {
            QualityFileInputInfoDto qualityFileInputInfoDto = new QualityFileInputInfoDto();
            qualityFileInputInfoDto.setOriginalFileName(originName);
            String fileName = setFile(file, "resources\\QualityEvidence\\");
            qualityFileInputInfoDto.setNewFileName(fileName);
            qhseElementsInputService.insertNewOriginFileNames(qualityFileInputInfoDto);
            return NR.r(CodeDict.CODE_MESSAGE_DATA, 0, 0, fileName, null, 0, 0);
        }
    }

    //---------质量要素excel录入数据库
    @RequestMapping(value = "/uploadQualityElements", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public R uploadQualityElements(@RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return R.error(CodeDict.UPLOAD_EMPTY, "上传文件为空");
        } else {
            String fileName = setFile(file, "QualityManagerSysElement\\");
            String path = System.getProperty("catalina.home") + "\\webapps\\QualityManagerSysElement\\" + fileName;
            return uploadService.uploadQualityManageSysElements(path);
        }
    }

    @RequestMapping(value = "/addQualityExcel", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public R addQualityExcel(@RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return R.error(CodeDict.UPLOAD_EMPTY, "上传文件为空");
        } else {
            String fileName = setFile(file, "QualityManagerSysElement\\");
            String path = System.getProperty("catalina.home") + "\\webapps\\QualityManagerSysElement\\" + fileName;
            return uploadService.addQualityExcel(path);
        }
    }

}