package com.ts.example.Shell;

import org.testng.annotations.Test;

import com.ts.commons.TestCaseUtil;

public class TestCase extends TestCaseUtil{
	
	@Test
	public void test()
	{
		String user = "root"; /*Valid user*/
		String linux ="127.0.0.1";/*valid ip*/
		String password ="";/*valid password*/
		
		MiPc miPc = new MiPc();
		using(
				miPc.
				connect(linux,user, password, user)
		).
		check(
				miPc.
				validateExpectedOutPut()
		);
		
		
	}
	
}


