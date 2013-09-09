package com.ts.email;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.search.AndTerm;
import javax.mail.search.BodyTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.ts.commons.TestCaseUtil;
import com.ts.commons.mail.Gmail;

public class TestCase extends TestCaseUtil{
	
	@Test
	public void test() throws MessagingException, IOException, ParseException
	{
		String account ="cguillen@testingsoft.com";
		String password = "cguillen123";
		
		MyGmail gmail = new MyGmail(account, password);
		
		
		using(
				gmail. 
				sendGreeting().
				sendGreeting(). 
				sendbBye()
			).
		check(
			gmail.
			validateHelloInSubject(). 
			validateHelloWorldInBody().
			validateBothSujects()
		);
			
	}

}

