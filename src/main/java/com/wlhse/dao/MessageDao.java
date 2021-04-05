package com.wlhse.dao;

import com.wlhse.dto.outDto.UserIdOutDto;
import com.wlhse.entity.Message;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDao {

    int insertMessage(Message message);

    int updateMessage(Message message);

    //根据id获取用户获取的消息列表
    List<Message> getMessageReceiveList(int id);

    //根据用户id获取下发的消息
    List<Message> getMessageSendList(int id);

    //根据用户id获取收到的信息的数量
    int getReceiveMessageNum(int id);

    //获取消息的详情内容
    Message getMessageDetail(int messageId);

    List<UserIdOutDto> getReceiver(String companyCode,String moduleCode);
}
