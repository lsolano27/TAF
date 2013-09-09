package com.ts.commons.mail;

import java.util.Properties;

import com.ts.commons.Mail;

public final class Gmail extends Mail
{

	public Gmail(String emailAccount, String password) {
		super(emailAccount, password);
		// TODO Auto-generated constructor stub
	}

	public static Mail imapConection(String account, String password)
	{
		String protocol = "imaps";
			
		Properties prop = new Properties();
		prop.setProperty("mail.store.protocol", protocol);
		Gmail gmail = new Gmail(account, password);
		gmail.setHost("imap.googlemail.com");
		gmail.setUrl("www.gmail.com");
		gmail.setPort(993);
		gmail.setProcotol(protocol);
		gmail.setProperties(prop);
		return gmail;
	}
	
	public static Mail smtpConection(String account, String password)
	{
		String host = "smtp.gmail.com";
		int port = 465;
		Properties props = new Properties();
	  	props.put("mail.smtp.host", host);
	  	props.put("mail.smtp.socketFactory.port", ""+port);
	  	props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	  	props.put("mail.smtp.auth", "true");
	  	props.put("mail.smtp.port", ""+port);
		
	  	Gmail gmail = new Gmail(account, password);
	  	gmail.setHost(host);
	  	gmail.setUrl( "www.gmail.com");
	  	gmail.setPort(port);
	  	gmail.setProcotol("smtp");
	  	gmail.setProperties(props);
		return gmail;
	}
	
	@Override
	public Mail then() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mail and() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
