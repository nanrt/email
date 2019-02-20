package com.example.demo.mail;

import com.example.demo.service.MailService;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <dl>
 * <dt>MailTest</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2019-02-19 10:09</dd>
 * </dl>
 *
 * @author nanrt
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MailTest {
	@Autowired
	MailService mailService;

	/**
	 * @description:测试简单邮件
	 * @date: 2019/2/20 15:39
	 * @author:nrt
	 */
	@Test
	public void test() {
		mailService.sendSimplEmail("nrt", "邮件发送成功");
	}

	/**
	 * @description: 测试发送附件邮件
	 * @date: 2019/2/20 15:39
	 * @author:nrt
	 */
	@Test
	public void testHtmlEmail() throws MessagingException, IOException {
		Map<String, File> aa = new HashMap<>();
		File file = new ClassPathResource("file.txt").getFile();
		aa.put("附件", file);

		mailService.sendHtmlEmail("nanrt", "你好", aa);
		System.out.println("success");
	}

	/**
	 * @description:测试发送模板邮件
	 * @date: 2019/2/20 15:40
	 * @author:nrt
	 */
	@Test
	public void testTemplateEmail() throws MessagingException, IOException, TemplateException {
		Map<String, Object> params = new HashMap<>();
		params.put("username", "nanrt");
		mailService.sentTemplateEmail("nanrt", params);
	}

}
