package com.wlhse.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wlhse.cache.JedisClient;
import com.wlhse.dao.CompanyYearManagerDao;
import com.wlhse.dao.MessageDao;
import com.wlhse.dto.outDto.UserIdOutDto;
import com.wlhse.entity.Message;
import com.wlhse.service.MessageService;
import com.wlhse.util.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Slf4j
public class MessageServiceImp implements MessageService {

    @Resource
    MessageDao messageDao;
    @Resource
    JedisClient jedisClient;
    @Resource
    CompanyYearManagerDao companyYearManagerDao;


    @Override
    public R senMessageInInputCheckApprove(int sourceId, int receiverId,HttpServletRequest request) {
        log.info("发消息");
        //int senderId = getUserId(request);
        Message message=new Message();
        message.setStatus("未读");
        message.setReceiverId(receiverId);
        message.setSenderId(123);
        //录入流程结束，向审核一级领导发送通知
        if (sourceId==0){
            message.setSource("要素证据录入");
            message.setTittle("待审核任务通知");
            message.setBody("你有年度要素证据审核任务，请及时处理。");
        }else {
            //审核流程结束，向批准一级领导发送通知
            message.setSource("要素证据审核");
            message.setTittle("待批准任务通知");
            message.setBody("你有年度要素证据批准任务，请及时处理。");
        }
        messageDao.insertMessage(message);
        return R.ok();
    }

    @Override
    public R getReceiveMessageList(HttpServletRequest request, int pageNum) {
        R r=new R();
        int userId = getUserId(request);
        PageHelper.startPage(pageNum,10);
        List<Message> messageReceiveList = messageDao.getMessageReceiveList(userId);
        r.put("data",new PageInfo<>(messageReceiveList));
        return r;
    }

    @Override
    @Transactional
    public R readMessage(HttpServletRequest request, int messageId) {
        R r=new R();
        int userId = getUserId(request);
        Message messageDetail = messageDao.getMessageDetail(messageId);
        if (messageDetail.getReceiverId()!=userId&&messageDetail.getSenderId()!=userId){
            r.put("data","不要看不是你的东西哦");
        }
        else {
            r.put("data",messageDetail);
            Message message=new Message();
            message.setStatus("已读");
            message.setId(messageId);
            messageDao.updateMessage(message);
        }
        return r;
    }

    @Override
    public R getReceiveMessageCnt(HttpServletRequest request) {
        int userId = getUserId(request);
        int receiveMessageNum = messageDao.getReceiveMessageNum(userId);
        R r=new R();
        r.put("data",receiveMessageNum);
        return r;
    }

    @Override
    public R getReceiver(String companyCode,String moduleCode) {
        R r=new R();
        List<UserIdOutDto> userIdOutDtos=messageDao.getReceiver(companyCode,moduleCode);
        r.put("data",userIdOutDtos);
        return r;
    }

    @Override
    @Transactional
    public R callBack(int tableId, int sourceId,HttpServletRequest request) {
        int senderId = getUserId(request);
        Message message=new Message();
        message.setSenderId(senderId);
        message.setStatus("未读");
        message.setTittle("要素证据录入打回通知");
        message.setBody("你录入的年度要素证据内容不合规范，已被打回。详细驳回信息请进入要素证据录入查看。请及时处理。");
        //审核阶段打回
        if (sourceId==0){
            message.setSource("要素证据审核");
        }
        else {
            //批准阶段打回
            message.setSource("要素证据批准");
        }
        //获取此审核表被打回者的id
        List<Integer> inputPersonId=companyYearManagerDao.getInputPersonId(tableId);
        //开始逐一通知
        for (Integer receiverId:inputPersonId){
            message.setReceiverId(receiverId);
            messageDao.insertMessage(message);
        }
        return R.ok();
    }


    private int getUserId(HttpServletRequest request){
        try {
            String token = request.getHeader("Authorization");
            if (StringUtils.isNotBlank(token))
                return Integer.parseInt(jedisClient.hGetAll(token).get("userId"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
