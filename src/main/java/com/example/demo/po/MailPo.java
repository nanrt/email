package com.example.demo.po;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <dl>
 * <dt>mailPo</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 2019-02-19 10:00</dd>
 * </dl>
 *
 * @author nanrt
 */
@Component
@ConfigurationProperties(prefix = "maail")
public class MailPo {
   private String from;
   private String to;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
}
