package com.wlhse.controller;

import com.wlhse.entity.Mail;
import com.wlhse.service.Producer;
import com.wlhse.util.GetCurrentUserIdUtil;
import com.wlhse.util.state_code.CodeDict;
import com.wlhse.util.state_code.NR;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller("ActiveMQController")
@RequestMapping("/api/v3")
public class ActiveMQController {

    @Resource
    Producer producer;

    @Resource
    private GetCurrentUserIdUtil getCurrentUserIdUtil;

    @RequestMapping(value = "/produce", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String produce(@ModelAttribute("mail") Mail mail, HttpServletRequest request) {
        mail.setFormUserId(getCurrentUserIdUtil.getUserId(request));

        //需要获取header判断是谁在访问
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mail.setMessageDate(df.format(new Date()));
        mail.setMessageStatus("未读");
        producer.sendMail(mail);

        return NR.r(CodeDict.CODE_MESSAGE, 1, 1, null, null, 0, 0);
    }
}
