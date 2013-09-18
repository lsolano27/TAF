package com.ts.example.email;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.search.AndTerm;
import javax.mail.search.BodyTerm;
import javax.mail.search.OrTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;

import org.testng.Assert;

import com.ts.commons.Mail;
import com.ts.commons.Validator;
import com.ts.commons.mail.Gmail;

public class MyGmail extends Mail{

	
	public MyGmail(String emailAccount, String password) {
		super(emailAccount, password);
	}

	public MyGmail sendGreeting() throws MessagingException
	{
		Mail gmail =  Gmail.smtpConection(getEmailAccount(), getPassword());
		gmail.Loggin();
		gmail.send(getEmailAccount(), "hello from email test case.", "hello world from email test case");
		return this;
	}
	
	public MyGmail sendbBye() throws MessagingException
	{
		Mail gmail = Gmail.smtpConection(getEmailAccount(), getPassword());
		gmail.Loggin();
		gmail.send(getEmailAccount(), "bye from email test case.", "hello world from email test case");
		return this;
	}
	
	public MyGmail validateHelloInSubject()
	{
	
		Validator validator = new Validator() {
			
			@Override
			public void Validate() {
				try {
											
					SubjectTerm subjectTerm = new SubjectTerm("hello from email test case.");
					Mail gmail = Gmail.imapConection(getEmailAccount(), getPassword());
					gmail.Loggin();
					Message[] msg = gmail.searchMessage(subjectTerm, "inbox");
					
					Assert.assertEquals(""+msg.length, "2");
					gmail.Logout();
				
				} catch (MessagingException e) {
					Assert.fail(e.getMessage());
				} 				
			}
		};
		validator.Validate();
		return this;
		
	}
	
	public MyGmail validateHelloWorldInBody()
	{
		Validator validator = new Validator() {
			
			@Override
			public void Validate() {
				try {
					
					BodyTerm bodyTerm = new BodyTerm("[\"hello world from email test case\"]");//Use regular expression in terms to get yours messages
					Mail gmail = Gmail.imapConection(getEmailAccount(), getPassword());
					gmail.Loggin();
					Message[] msg = gmail.searchMessage(bodyTerm, "inbox");
					Assert.assertEquals(""+msg.length, "3");
					gmail.Logout();
				
				} catch (MessagingException e) {
					Assert.fail(e.getMessage());
				} 	
				
			}
		};
		validator.Validate();
		return this;
		
	}	
	
	public Validator validateBothSujects()
	{
		return new Validator() {
			
			@Override
			public void Validate() {
				try {
				
					AndTerm andTerms = new AndTerm(new SearchTerm[]{new BodyTerm("hello world from email test case"),new SubjectTerm("bye from email test case.")});
					OrTerm orTerm = new OrTerm(andTerms, new SubjectTerm("hello from email test case.")  );
					Mail gmail = Gmail.imapConection(getEmailAccount(), getPassword());
					gmail.Loggin();
					Message[] msg = gmail.searchMessage(orTerm, "inbox");
					Assert.assertEquals(""+msg.length, "3");
					gmail.Logout();
				} catch (MessagingException e) {
					Assert.fail(e.getMessage());
				}

				
			}
		};		
	}

	@Override
	public MyGmail then() {
		return this;
	}

	@Override
	public MyGmail and() {
		return null;
	}
}
