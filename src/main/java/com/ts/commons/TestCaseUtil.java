package com.ts.commons;

import org.Listener.ITSListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;

@Listeners({ITSListener.class})
public abstract class TestCaseUtil implements Component{
	
	private Component component;
	private int amountOfusingWithOutChecks = 0;
	
	public TestCaseUtil and()
	{
		return this;
	}
	
	public TestCaseUtil then()
	{
		return this;
	}
	
	public TestCaseUtil andUsing(Component component)
	{
		this.amountOfusingWithOutChecks ++;
		this.setComponent(component);
		return this;
	}
	
	public TestCaseUtil andUsing(Until until)
	{
		using(until);
		return this;
	}
	
	public TestCaseUtil using(Until until)
	{
		this.amountOfusingWithOutChecks ++;
		wait(until);
		return this;
	}
	
	public static void wait(Until until)
	{
		boolean expectedCondition = true;
		long startTime = System.currentTimeMillis();
		
		while( expectedCondition )
		{
					expectedCondition = !until.execute();
			long endTime = System.currentTimeMillis();
			long time = endTime - startTime;
			time = (FirefoxDriver.IMPLICT_TIME_UNIT.toMillis(FirefoxDriver.IMPLICT_TIME)) -  time;
			if(time < 0)
			{
				throw new RuntimeException("The Until condition never achieves the expected  result.");
			}			
		}
	}

	public TestCaseUtil using(Component component)
	{
		this.amountOfusingWithOutChecks ++;
		this.setComponent(component);
		return this;
	}
	public TestCaseUtil check (Validator validator)
	{
		this.amountOfusingWithOutChecks --;
		validator.Validate();
		return this;
	}
	
	public TestCaseUtil andFinally (Validator validator)
	{
		this.amountOfusingWithOutChecks --;
		validator.Validate();
		return this;
	}
	
	@AfterClass
	public void validateTestStandar(ITestResult result)
	{		
		if((amountOfusingWithOutChecks != 0) && (result.getStatus() != 2))
		{
			throw new RuntimeException("You have an Using without its corresponding check or andFinally");
		}
	}

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}	
}