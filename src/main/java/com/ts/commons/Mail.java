package com.ts.commons;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.util.MailSSLSocketFactory;

public abstract class Mail implements Component {
	
	 	private IMAPFolder folder ;
	    private Store store;
		private  String  url;
		private  String  emailAccount;
		private  String  password;
		private  String  procotol;
		private  String  host;
		private int port;
		private Properties properties;

		public Mail(String url, String host, int port, String emailAccount, String password, String potocol, Properties properties)
		{
			this.url = url;
			this.host = host;
			this.port = port;
			this.emailAccount = emailAccount;
			this.password = password;
			this.procotol = potocol;
			this.properties= properties;
		}
		
		
		public Mail createGMailImapConnection(String emailAccount, String password) throws GeneralSecurityException {
			  this.url = "imap.googlemail.com";
			  this.port = 993;
			  
			  this.properties = System.getProperties();
			  MailSSLSocketFactory socketFactory= new MailSSLSocketFactory();
			  socketFactory.setTrustAllHosts(true);
	        
			  properties.setProperty("mail.imap.host", url);
			  properties.setProperty("mail.imap.port", ""+port);     
			  properties.setProperty("mail.store.protocol", "imaps");
			  properties.put("mail.imap.ssl.checkserveridentity", "false");
			  properties.put("mail.imap.ssl.trust", "*");
			  properties.put("mail.imaps.ssl.socketFactory", socketFactory);
			  
			  return this;
	
	 }
		

		public abstract Mail then();
		public abstract Mail and();
		
		public Mail Loggin() throws MessagingException  
		{
	          Session session = Session.getDefaultInstance(properties, null);	     
	          store = session.getStore(this.procotol);          
	          store.connect(this.url,this.emailAccount, this.password);
	          return this;
		}

		public Mail Logout() throws MessagingException  
		{
	            if (folder != null && folder.isOpen())
	            { 
	            	folder.close(true); 
	            }
	            if (store != null) 
	            { 
	            	store.close(); 
	            }
	          return this;
		}

		public Message searchMessage(String subject,String Location_,Date DateSearch_) throws MessagingException{
			Message[] messages= null;
			
			folder = (IMAPFolder) store.getFolder(Location_); 
			folder.open(Folder.READ_WRITE);

       	
            SearchTerm st = new ReceivedDateTerm(ComparisonTerm.EQ,DateSearch_);

			messages = folder.search(st);
	        	        	        
	        for (Message message1 : messages) {
	        	String mgsSubject = message1.getSubject();
	        	if(mgsSubject.equals(subject)){
	        		return message1; 	        		
	        	}
			}
			return null;
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
		
		public Enumeration getAllHeaders(Message msg) throws MessagingException 
		{
			Enumeration content = msg.getAllHeaders();
			return content;
		}


		public IMAPFolder getFolder() {
			return folder;
		}


		public void setFolder(IMAPFolder folder) {
			this.folder = folder;
		}


		public Store getStore() {
			return store;
		}


		public void setStore(Store store) {
			this.store = store;
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
