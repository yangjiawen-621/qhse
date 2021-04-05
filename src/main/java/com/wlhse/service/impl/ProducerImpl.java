package com.wlhse.service.impl;

import com.wlhse.entity.Mail;
import com.wlhse.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ProducerImpl implements Producer {
	
	@Autowired
	@Qualifier("jmsTemplate")
	private JmsTemplate jmsTemplate;
	
	public void sendMail(Mail mail) {
		jmsTemplate.convertAndSend(mail);
	}

}
