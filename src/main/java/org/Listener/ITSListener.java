package org.Listener;

import static com.jayway.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ITSListener implements ITestListener{		
	private String ipServer;
	private String build;
	private String testPlan;
	private String testID;	
	private String status;
	private double time = 0;
	
	/**
	 * This method call another method to get the duration of the TC and its Status
	 * when TC Fail
	 */
	@Override
	public void onTestFailure(ITestResult testResult) {
		getTimeAndStatus(testResult);
	}

	/**
	 * This method call another method to get the duration of the TC and its Status
	 *  when TC skip
	 */
	@Override
	public void onTestSkipped(ITestResult testResult) {
		getTimeAndStatus(testResult);
	}

	/**
	 * This method call another method to get the duration of the TC and its Status
	 * when TC Success
	 */
	@Override
	public void onTestSuccess(ITestResult testResult) {
		getTimeAndStatus(testResult);
	}

	/**
	 * This method call another method to get information of the TC
	 */
	@Override
	public void onTestStart(ITestResult result) {		
		getTestCaseInfo(result);
	}

	/**
	 * This method call another method to get the duration of the TC and its Status
	 * when TC fail with percentage of Success
	 */
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		getTimeAndStatus(result);
	}

	@Override
	public void onFinish(ITestContext context) {
	
	}	
	
	/**
	 * This method get the context parameters from the TestNG.xml executed.
	 */
	@Override
	public void onStart(ITestContext context) {			
		ipServer = context.getSuite().getParameter("ip");
		build = context.getSuite().getParameter("build");
		testPlan = context.getSuite().getParameter("testPlan");
	}
	
	/**
	 * This method gets the time of the TC, its status,
	 * print all gotten information and send it.
	 * @param result
	 */
	private void getTimeAndStatus(ITestResult result) {
		time =  (result.getEndMillis() - result.getStartMillis()) / 1000;
		status = getTestStatus(result);
		//printAll();
		sendToServer();
	}	
	
	/**
	 * This method return the state of the TC
	 * @param result = the result of the TC from TestNG execution
	 */
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
	
	/**
	 * This method gets the own information of the TC
	 * in this case the testID parameter
	 * @param result = the result of the TC from TestNG execution
	 */
	private void getTestCaseInfo(ITestResult result) {
		Object[] parameters = result.getParameters();
		
		if(parameters != null){
			testID = parameters[0].toString();
		}				   		
	}	

	/**
	 * This method print all information gotten
	 */
	/*private void printAll(){
		System.out.println("Server: " + ipServer);
		System.out.println("Build: " + build);
		System.out.println("TestPlan: " + testPlan);
		System.out.println("TestID: " + testID);
		System.out.println("Status: " + status);		
		System.out.println("Time: " + time);
		System.out.println("http://"+ ipServer +"/server/AutomationExecutionInfoListener.html");
	}*/
	
	/**
	 * This method send the gotten information to the "orquestador".
	 */
	private void sendToServer(){
		if(ipServer != "" &&  ipServer != null){
			if((testID != "" ||  testID != null) && (status != "" ||  status != null) && (build != "" ||  build != null) && (testPlan != "" ||  testPlan != null) && (time > 0)){			
				given().param("tcId", testID)
					   .param("status", status)
					   .param("build", build)
					   .param("testPlan", testPlan)
					   .param("time", time)
				//.expect().header("Status", "200")
				.when()
				.post("http://"+ ipServer +"/server/AutomationExecutionInfoListener.html");				
			}
		}		
	}
}