package com.wlhse.controller;


import com.wlhse.dao.DrFileDao;
import com.wlhse.dao.FileDao;
import com.wlhse.dao.MonitorFileDao;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * Description:download file propagation file
 * Author:Coco
 * create:2020-08-04 5:04 PM
 **/
@RequestMapping("/api/v3")
@Controller("FileDownloadController")
public class FileDownloadController {
    @Resource
    FileDao fileDao;
    @Resource
    MonitorFileDao monitorFileDao;
    @Resource
    DrFileDao drFileDao;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @RequestMapping(value = "/downloadFilePropagationFile", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
        public ResponseEntity<byte[]> downloadFile(@RequestParam(value = "fileName")String fileName, HttpServletRequest request) throws IOException {
        String path =System.getProperty("catalina.home") + "\\webapps\\" + "FilePropagation\\";
        File file = new File(path + File.separator + fileName);
        System.out.println(file.getPath());
        fileName=fileDao.getFilePropagationOriginFileName(fileName);
        System.out.println("原文件名: "+fileName);
        HttpHeaders headers = new HttpHeaders();
        //Solve the garbled problem
        String downloadFileName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
        headers.setContentDispositionFormData("attachment", downloadFileName);
        //application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/downloadDangerFile", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public void downloadDangerFile(@RequestParam(value = "fileName",required = false)String fileName, HttpServletRequest request,
                                       HttpServletResponse response)throws IOException{
        String path =System.getProperty("catalina.home") +"\\webapps\\" + "\\resources\\" + "QHSEDanger\\" + "photoes\\";
        File file = new File(path + File.separator + fileName);
        fileName = drFileDao.getOriginName(fileName);
        //将文件原名保存在响应头
        response.setHeader("fileName",new String(fileName.getBytes("UTF-8"),"ISO8859-1"));
        FileInputStream fis=new FileInputStream(file);
        byte[] b=new byte[fis.available()];
        fis.read(b);
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(b);

    }

    @RequestMapping(value = "/downloadRegulationFile", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public void downloadRegulationFile(@RequestParam(value = "fileName",required = false)String fileName, HttpServletRequest request,
                                                  HttpServletResponse response)throws IOException{
        String path =System.getProperty("catalina.home") +"\\webapps\\" + "\\resources\\" + "QHSERegulation\\" + "photoes\\";
        File file = new File(path + File.separator + fileName);
        fileName = drFileDao.getOriginName(fileName);
        //将文件原名保存在响应头
        response.setHeader("fileName",new String(fileName.getBytes("UTF-8"),"ISO8859-1"));
        FileInputStream fis=new FileInputStream(file);
        byte[] b=new byte[fis.available()];
        fis.read(b);
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(b);

    }


    //回显图片
    @RequestMapping(value = "/screenShotDownload",method = RequestMethod.GET)
    public void downloadScreenShot(@RequestParam(value = "fileName",required = false)String fileName, HttpServletRequest request,
                                                     HttpServletResponse response) throws IOException {
        String path =System.getProperty("catalina.home") +"\\webapps\\" + "RemoteMonitor\\" + "screenShot";
        File file = new File(path + File.separator + fileName);
        fileName=monitorFileDao.getOriginName(fileName);
        //将文件原名保存在响应头
        response.setHeader("fileName",new String(fileName.getBytes("UTF-8"),"ISO8859-1"));
        FileInputStream fis=new FileInputStream(file);
        byte[] b=new byte[fis.available()];
        fis.read(b);
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(b);
    }

    @RequestMapping(value = "/downloadQualityAttach", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public ResponseEntity<byte[]> downloadQualityAttach(@RequestParam(value = "fileName")String fileName,HttpServletRequest request) throws IOException {
        String path =System.getProperty("catalina.home") + "\\webapps\\"+"\\resources\\" + "QualityCheck\\";
        File file = new File(path + File.separator + fileName);
        System.out.println(file.getPath());
        fileName=fileDao.getQualityAttachOriginFileName(fileName);
       //System.out.println("原文件名: "+fileName);
        HttpHeaders headers = new HttpHeaders();
        //Solve the garbled problem
        String downloadFileName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
        headers.setContentDispositionFormData("attachment", downloadFileName);
        //application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }

    //下载远程监控计划详情的数据导入模板
    @RequestMapping(value = "/downloadMonitorDetailExcelTemplate",method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadMonitorDetailExcelTemplate() throws IOException {
        String path =System.getProperty("catalina.home") + "\\webapps\\"+"MonitorTemplate";
        File file = new File(path + File.separator + "详情导入模板.xlsx");
        String fileName="远程监控计划详情导入模板.xlsx";
        HttpHeaders headers = new HttpHeaders();
        //Solve the garbled problem
        String downloadFileName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
        headers.setContentDispositionFormData("attachment", downloadFileName);
        //application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }

    //下载统计数据导入模板
    @RequestMapping(value = "/downloadStatisticsTemplate",method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadStatisticsTemplate() throws IOException {
        String path =System.getProperty("catalina.home") + "\\webapps\\"+"MonitorTemplate";
        File file = new File(path + File.separator + "统计数据导入模板.xlsx");
        String fileName="统计数据导入模板.xlsx";
        HttpHeaders headers = new HttpHeaders();
        //Solve the garbled problem
        String downloadFileName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
        headers.setContentDispositionFormData("attachment", downloadFileName);
        //application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }
}
