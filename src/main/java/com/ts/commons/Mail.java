package com.ts.commons;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.search.SearchTerm;

import com.sun.mail.imap.IMAPFolder;

public abstract class Mail implements Component {
	
		private  String  url;
		private  String  emailAccount;
		private  String  password;
		private  String  procotol;
		private  String  host;
		private int port;
		private Properties properties;
		private Session session;

		protected Mail(String url, String host, int port, String emailAccount, String password, String potocol, Properties properties)
		{
			this.url = url;
			this.host = host;
			this.port = port;
			this.emailAccount = emailAccount;
			this.password = password;
			this.procotol = potocol;
			this.properties= properties;
		}
		
		public Mail(String emailAccount, String password)
		{
			this.emailAccount = emailAccount;
			this.password = password;
		}
	
		public abstract Mail then();
		public abstract Mail and();
		
		public Mail Loggin() throws MessagingException  
		{
			Authenticator aut = new Authenticator() {
	      		   protected PasswordAuthentication getPasswordAuthentication() {
		       		   return new PasswordAuthentication(emailAccount,password);
	      		   }
			}; 
			session = Session.getDefaultInstance(properties, aut);
	        return this;
		}

		public Mail Logout() throws MessagingException  
		{
				Store store = getStoreFromProtocol();
	            if (store != null) 
	            { 
	            	store.close(); 
	            }
	          return this;
		}
		
		private Store getStoreFromProtocol() throws MessagingException
		{
			Store store = session.getStore(this.procotol);  
			if( !store.isConnected() )
			{
				store.connect(this.host,this.emailAccount, this.password);	
			}
	        
	        return store;
		}

		public IMAPFolder getIMAPFolder(String Location_) throws MessagingException
		{
			IMAPFolder folder = (IMAPFolder) getStoreFromProtocol().getFolder(Location_); 
	        
	        return folder;
		}
		
		public Message[] searchMessage(SearchTerm searchTerm, String Location_) throws MessagingException{
			Message[] messages= null;
			
			IMAPFolder folder = getIMAPFolder(Location_); 
			folder.open(Folder.READ_WRITE);

  			messages = folder.search(searchTerm);
		
			return messages;
		}
		
		public String getBody(Message msg) throws MessagingException, IOException {
			String content="";
			  Multipart mp;

			if(msg.isMimeType("text/plain") )
			{
			content = (String) msg.getContent();
			}
			if(msg.isMimeType("multipart/*") )
			{
				mp = (Multipart)msg.getContent();
				
				for (int i=0;i< mp.getCount() ;i++){
					BodyPart part = (BodyPart) mp.getBodyPart(i);					
					content += part.getContent().toString();
				}
			}
			return content;
		}
		
		public String getSubject(Message msg) throws MessagingException {
			String content = msg.getSubject();
			return content;
		}
		
		@SuppressWarnings("rawtypes")
		public Enumeration getAllHeaders(Message msg) throws MessagingException 
		{
			Enumeration content = msg.getAllHeaders();
			return content;
		}
		
		public void send(String to, String subject, String text ) {

			
			  try {
			   MimeMessage message = new MimeMessage(session);
			   message.setFrom(new InternetAddress(emailAccount));//change accordingly
			   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			   message.setSubject(subject);
			   message.setText(text);
			   Transport.send(message);
			 
			  } catch (MessagingException e) {throw new RuntimeException(e);}
			 
			 }

		
		public String getUrl() {
			return url;
		}


		public void setUrl(String url) {
			this.url = url;
		}


		public String getEmailAccount() {
			return emailAccount;
		}


		public void setEmailAccount(String emailAccount) {
			this.emailAccount = emailAccount;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public String getProcotol() {
			return procotol;
		}


		public void setProcotol(String procotol) {
			this.procotol = procotol;
		}


		public String getHost() {
			return host;
		}


		public void setHost(String host) {
			this.host = host;
		}


		public int getPort() {
			return port;
		}


		public void setPort(int port) {
			this.port = port;
		}


		public Properties getProperties() {
			return properties;
		}


		public void setProperties(Properties properties) {
			this.properties = properties;
		}
				
		

}
