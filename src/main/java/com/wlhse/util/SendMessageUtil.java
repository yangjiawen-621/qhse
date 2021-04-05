package com.wlhse.util;

import com.wlhse.entity.Mail;
import com.wlhse.service.Producer;
import com.wlhse.util.state_code.CodeDict;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SendMessageUtil {

    @Resource
    private Producer producer;

    public Mail getMail(int type, int fromUserId, int toUserId, String orderNumber) {
        Mail mail = new Mail();
        mail.setFormUserId(fromUserId);
        mail.setToUserId(toUserId);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mail.setMessageDate(df.format(new Date()));
        mail.setMessageStatus("未读");

        if (type == CodeDict.SEND_TASK) {
            mail.setMessageText("您有条任务单号为" + orderNumber + "的新任务,请及时接收和编制计划.");
        } else if (type == CodeDict.SEND_VERIFY) {
            mail.setMessageText("您有条任务单号为" + orderNumber + "的任务待审核.");
        } else if (type == CodeDict.SEND_TASK_NO_PASS) {
            mail.setMessageText("您的任务单号为" + orderNumber + "的任务计划未通过审核,请及时修改.");
        } else if (type == CodeDict.SEND_PROBLEM_FOUND) {
            mail.setMessageText("发现新问题(" + orderNumber + ")请及时处理.");
        } else if (type == CodeDict.SEND_PROBLEM_RECTI) {
            mail.setMessageText("问题(" + orderNumber + ")已于" + df.format(new Date()) + "完成整改,请及时验证.");
        } else if (type == CodeDict.SEND_PROBLEM_VERIFY) {
            mail.setMessageText("问题(" + orderNumber + ")已于" + df.format(new Date()) + "完成验证.");
        } else if (type == CodeDict.SEND_TASK_PASS) {
            mail.setMessageText("您有条任务单号为" + orderNumber + "的任务计划通过审核,按计划执行.");
        } else if (type == CodeDict.SEND_TASK_FINISH) {
            mail.setMessageText("任务单号为(" + orderNumber + ")已于" + df.format(new Date()) + "完成.");
        } else if (type == CodeDict.SEND_VERIFY_NO_PASS) {
            mail.setMessageText("问题(" + orderNumber + ")未通过验证,请及时修改.");
        } else if (type == CodeDict.SEND_RECTI_NO_PASS) {
            mail.setMessageText("问题(" + orderNumber + ")未通过整改,请及时修改.");
        } else if (type == CodeDict.SEND_PROBLEM_NO_PASS) {
            mail.setMessageText("问题(" + orderNumber + ")未通过审核,请及时修改.");
        } else if (type == CodeDict.SEND_PROBLEM_EXAMINE) {
            mail.setMessageText("您有条问题(" + orderNumber + ")的问题待审核.");
        }
        return mail;
    }

    public void sendMessage(Mail mail) {
        producer.sendMail(mail);
    }
}
