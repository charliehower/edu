package org.platform.snail.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.platform.snail.service.EmailServiceInterface;
import org.platform.snail.utils.Debug;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class EmailServiceImpl implements EmailServiceInterface {

	protected String from;
	protected JavaMailSender sender;
	
	private String subject="",content="";
	
	

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * �������ʹ����html��ʽ�ʼ�
	 * 
	 * @throws MessagingException
	 */
	public void sendBatchEmail(String subject, String content,
			List<String> address) throws MessagingException {
		MimeMessage msg = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");
		String toList = getMailList(address);
		InternetAddress[] iaToList = new InternetAddress().parse(toList);
		msg.setRecipients(Message.RecipientType.TO, iaToList);
		helper.setFrom(from);
		helper.setSubject(subject);
		helper.setText(content, true);
		sender.send(msg);
	}

	/**
	 * ����ת���ַ�
	 */
	public String getMailList(List<String> to) {
		StringBuffer toList = new StringBuffer();
		int length = to.size();
		if (to != null && length < 2) {
			toList.append(to.get(0));
		} else {
			for (int i = 0; i < length; i++) {
				toList.append(to.get(i));
				if (i != (length - 1)) {
					toList.append(",");
				}
			}
		}
		return toList.toString();
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public JavaMailSender getSender() {
		return sender;
	}

	public void setSender(JavaMailSender sender) {
		this.sender = sender;
	}

	public boolean sendSMS(String[] mobile, String content) {
		Debug.out("start send msg for list");
		boolean state = true;
		try {
			int i = 0;
			if (mobile != null) {
				for (int j = 0; j < mobile.length; j++) {
					i = this.sendSMS(mobile[j], content);

					if (i == 0) {
						state = true;
					} else {
						state = false;
					}
					Debug.out(state);

				}
			}

		} catch (Exception e) {
			state = false;
			e.printStackTrace();
		}

		return state;
	}

	private int sendSMS(String mobile, String content) {
		Socket socket = null;
		int port = 8824;
		Debug.out(mobile);
		Debug.out(content);
		String result = "9";

		try {
			socket = new Socket("10.0.2.132", port);
			OutputStream socketOut = socket.getOutputStream();
			socketOut.write((mobile + "#" + content).getBytes());
			BufferedReader br = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			while ((result = br.readLine()) != null)
				return Integer.valueOf(result.trim()).intValue();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Integer.valueOf(result.trim()).intValue();
	}

	public static void main(String args[]) {
		EmailServiceImpl t = new EmailServiceImpl();
		int a = t.sendSMS("13809491320", "0928");
		System.out.println(a);
	}

}
