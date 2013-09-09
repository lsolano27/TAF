package com.ts.Shell;

import org.testng.annotations.Test;

import com.ts.commons.TestCaseUtil;
import com.ts.commons.Validator;

public class TestCase extends TestCaseUtil{
	
	@Test
	public void test()
	{
		MiPc miPc = new MiPc();
		using(
				miPc.
				connect("10.0.0.35","root", "TesterUser", "root").
				setSkipVT100Filter(true).
				open().
				execute("ping 10.0.0.1", "Approximate")
		).
		check(
				new Validator() {
					
					@Override
					public void Validate() {
						// TODO Auto-generated method stub
						
					}
				}
		);
		
		
	}
	
}


