package org.platform.snail.service;

import java.util.List;

import javax.mail.MessagingException;

public interface EmailServiceInterface {
	public abstract void sendBatchEmail(String subject,String content,List<String> address)throws MessagingException;
	public abstract boolean sendSMS(String[] mobile,String content);
}
