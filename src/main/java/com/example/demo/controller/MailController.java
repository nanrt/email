package com.example.demo.controller;

import com.example.demo.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <dl>
 * <dt>MailController</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 2019-02-19 10:55</dd>
 * </dl>
 *
 * @author nanrt
 */
@RestController
public class MailController {
	@Autowired
	MailService mailService;

	@GetMapping("/send")
	public void send(){
		mailService.sendSimplEmail("主题","发送成功");
	}

}
