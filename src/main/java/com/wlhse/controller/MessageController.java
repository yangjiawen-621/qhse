package com.wlhse.controller;

import com.wlhse.service.MessageService;
import com.wlhse.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v3")
public class MessageController {

    @Resource
    MessageService messageService;


    @RequestMapping(value = "/getReceiveMessageList",method = RequestMethod.GET)
    public R getReceiveMessageList(HttpServletRequest request, Integer pageNum){
        return messageService.getReceiveMessageList(request,pageNum);
    }


    @RequestMapping(value = "/readMessage")
    public R readMessage(HttpServletRequest request,@RequestParam Integer messageId){
        return messageService.readMessage(request,messageId);
    }

    @RequestMapping(value = "/getReceiveMessageCnt",method = RequestMethod.GET)
    public R getReceiveMessageCnt(HttpServletRequest request){
        return messageService.getReceiveMessageCnt(request);
    }

    @RequestMapping(value = "/sendMessage/{tag}")
    public R sendMessage(@RequestParam("receiverId") Integer receiverId, @PathVariable("tag")Integer sourceId,HttpServletRequest request){
        return messageService.senMessageInInputCheckApprove(sourceId,receiverId,request);
    }

    @RequestMapping(value = "/getReceiver",method = RequestMethod.GET)
    public R getReceiver(@RequestParam("companyCode")String companyCode,@RequestParam("moduleCode")String moduleCode){
        return messageService.getReceiver(companyCode,moduleCode);
    }


    //打回通知
    @RequestMapping("/callBack")
    public R callBack(@RequestParam("tableId")Integer tableId,@RequestParam("sourceId")Integer sourceId,HttpServletRequest request){
        return messageService.callBack(tableId,sourceId,request);
    }
}
