package com.ts.example.email;

import java.io.IOException;
import java.text.ParseException;

import javax.mail.MessagingException;

import org.testng.annotations.Test;

import com.ts.commons.TestCaseUtil;

public class TestCase extends TestCaseUtil{
	
	@Test
	public void test() throws MessagingException, IOException, ParseException
	{
		String account ="cguillen@testingsoft.com";/*valid accouunt@gmail.com.*/
		String password = "cguillen123";/*proper password.*/
		
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

