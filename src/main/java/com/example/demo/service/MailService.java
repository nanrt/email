package com.example.demo.service;

import com.example.demo.po.MailPo;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * <dl>
 * <dt>MainService</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 2019-02-19 9:41</dd>
 * </dl>
 *
 * @author nanrt
 */
@Service
public class MailService {

	@Autowired
	JavaMailSender jms;
	@Autowired
	MailPo mailPo;

	/**
	 * @param subject
	 * @param text
	 * @description:发送简单邮件
	 * @date: 2019/2/19 11:30
	 * @author:nrt
	 */
	public void sendSimplEmail(String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(mailPo.getFrom());
		message.setTo(mailPo.getTo());
		message.setSubject(subject);
		message.setText(text);

		jms.send(message);
	}

	/**
	 * @description:发送带附件邮件
	 * @param subject
	 * @param text
	 * @param attachmentMap
	 * @return:
	 * @date: 2019/2/20 15:37
	 * @author:nrt
	 */
	public void sendHtmlEmail(String subject, String text, Map<String, File> attachmentMap) throws MessagingException {

		MimeMessage mimeMessage = jms.createMimeMessage();

		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

		mimeMessageHelper.setFrom(mailPo.getFrom());
		mimeMessageHelper.setTo(mailPo.getTo());

		mimeMessageHelper.setSubject(subject);
		mimeMessageHelper.setText(text);

		if (attachmentMap != null) {
			attachmentMap.entrySet().stream().forEach(entrySet -> {
				try {

					File file = entrySet.getValue();
					if (file.exists()) {
						mimeMessageHelper.addAttachment(entrySet.getKey(), new FileSystemResource(file));
					}
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			});
		}

		jms.send(mimeMessage);
	}

	/**
	 * @description:发送模板邮件
	 * @param subject
	 * @param text
	 * @date: 2019/2/20 15:38
	 * @author:nrt
	 */
	public void sentTemplateEmail(String subject, Map<String, Object> text) throws MessagingException, IOException, TemplateException {
		MimeMessage mimeMessage = jms.createMimeMessage();

		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

		mimeMessageHelper.setFrom(mailPo.getFrom());
		mimeMessageHelper.setTo(mailPo.getTo());

		Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
		configuration.setClassForTemplateLoading(this.getClass(), "/templates");

		String html = FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate("template.ftl"), text);
		mimeMessageHelper.setSubject(subject);
		mimeMessageHelper.setText(html, true);

		jms.send(mimeMessage);

	}
}
