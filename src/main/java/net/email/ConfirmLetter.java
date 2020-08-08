package net.email;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import data.config.Config;
import data.config.ConfigPath;

public class ConfirmLetter {
	MimeMessage message;
	private static StringBuffer content;
	private static int position;
	private static String sendMail;
	public ConfirmLetter(String receiveMail,String Captcha) throws UnsupportedEncodingException, MessagingException {
		this.message=new MimeMessage(Email.session);
		message.setFrom(new InternetAddress(sendMail, "UTF-8"));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "UTF-8"));
        message.setContent(getContent(Captcha), "text/html;charset=UTF-8");
        message.setSentDate(new Date());
        message.saveChanges();
	}
	public static boolean start() {
		Object tryContent;
		Object trysendMail = null;
		if((tryContent=Config.getConfigData(new ConfigPath("net","email","confirm")))==null&
				(trysendMail=Config.getConfigData(new ConfigPath("net","email","sendMail")))==null) {
			return false;
		}
		content=new StringBuffer(tryContent.toString());
		sendMail=trysendMail.toString();
		tryContent=null;
		trysendMail=null;
		if((position=content.indexOf("??????"))==-1) {
			return false;
		}
		return true;
	}
	private String getContent(String Captcha) {
		return content.replace(position, position+6, Captcha).toString();
	}
}
