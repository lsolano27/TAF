package org.Listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import static com.jayway.restassured.RestAssured.given;

public class ITSListener implements ITestListener{		
	private String ipServer;
	private String build;
	private String testPlan;
	private String testID;	
	private String status;
	private double time;
	
	@Override
	public void onTestFailure(ITestResult testResult) {
		getTimeAndStatus(testResult);
	}

	@Override
	public void onTestSkipped(ITestResult testResult) {
		getTimeAndStatus(testResult);
	}

	@Override
	public void onTestSuccess(ITestResult testResult) {
		getTimeAndStatus(testResult);
	}

	@Override
	public void onTestStart(ITestResult result) {		
		getTestCaseInfo(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onFinish(ITestContext context) {
		printAll();
		sendToServer();
	}	
	
	@Override
	public void onStart(ITestContext context) {			
		ipServer = context.getSuite().getParameter("ip");
		build = context.getSuite().getParameter("build");
		testPlan = context.getSuite().getParameter("testPlan");
	}
	
	private void getTimeAndStatus(ITestResult result) {
		time =  (result.getEndMillis() - result.getStartMillis()) / 1000;
		status = getTestStatus(result);
	}	
	
	private String getTestStatus(ITestResult result){
		switch (result.getStatus()) {
		case 2:
			return "FAILURE";			
		case 3:
			return "SKIP";	
		case 16:
			return "STARTED";	
		case 1:
			return "SUCCESS";	
		case 4:
			return "SUCCESS_PERCENTAGE_FAILURE";	
		default:
			return "";				
		}	
	}
	
	private void getTestCaseInfo(ITestResult result) {
		Object[] parameters = result.getParameters();
		
		if(parameters != null){
			testID = parameters[0].toString();
		}				   		
	}	

	private void printAll(){
		System.out.println("Server: " + ipServer);
		System.out.println("Build: " + build);
		System.out.println("TestPlan: " + testPlan);
		System.out.println("TestID: " + testID);
		System.out.println("Status: " + status);		
		System.out.println("Time: " + time);
		System.out.println("http://"+ ipServer +"/AutomationExecutionInfoListener");
	}
	
	private void sendToServer(){		
		given().param("tcId", testID)
				.param("status", status)
				.param("build", build)
				.param("testPlan", testPlan)
				.param("time", time)
		.when()
		.post("http://"+ ipServer +"/AutomationExecutionInfoListener");
	}
}