package net.email;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import data.config.Config;
import data.config.ConfigPath;

public class Email {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	private static Transport transport;
	static Session session;
	public boolean start() {
		logger.info("∆Ù∂Ø” œ‰∑˛ŒÒ");
		Object SMTPHost = null;
		Object sendMail = null;
		Object password = null;
		if((SMTPHost=Config.getConfigData(new ConfigPath("net","email","confirm")))==null&
				(sendMail=Config.getConfigData(new ConfigPath("net","email","sendMail")))==null&
				(password=Config.getConfigData(new ConfigPath("net","email","password")))==null
				) {
			return false;
		}
		Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp"); 
        props.setProperty("mail.smtp.host", (String) SMTPHost);  
        props.setProperty("mail.smtp.auth", "true");  
        session = Session.getInstance(props);
		try {
			transport = session.getTransport();
		} catch (NoSuchProviderException e) {
			logger.error(e.toString());
			return false;
		}
        try {
			transport.connect((String)sendMail,(String) password);
		} catch (MessagingException e) {
			logger.error(e.toString());
			return false;
		}
        return ConfirmLetter.start();
	}
	
	public void close() throws MessagingException {
		transport.close();
	}
	public boolean sendMessage(ConfirmLetter message) {
		try {
			transport.sendMessage(message.message, message.message.getAllRecipients());
		} catch (MessagingException e) {
			logger.error(e.toString());
			return false;
		}
		return true;
	}
}
