package org.Listener;

import static com.jayway.restassured.RestAssured.given;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.Optional;

import com.ts.commons.TSRetry;
import com.ts.commons.TSRunReportXls.ExcelReport;

@SuppressWarnings("unused")
public class ITSListener implements ITestListener, ITestNGListener{
	private String ipServer;
	private String build;
	private String testPlan;
	private String testID;	
	private String status;
	private double time = 0;
	
	public String getStatus() {
		return status;
	}

	public double getTime() {
		return time;
	}

	/**
	 * This method call another method to get the duration of the TC and its Status when TC Fail
	 */
	@Override
	public void onTestFailure(ITestResult testResult) {
		getTimeAndStatus(testResult);
		
		try {
			String datePath = getDate("dd_MM_yyyy");
			String description = createDescription(testResult, testResult.getName(), datePath), path = "", methodName = testResult.getName();			
			reportGenerator(methodName, status, String.valueOf(time), description, datePath);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String createDescription(ITestResult testResult, String methodName, String datePath) throws IOException{
		String error = testResult.getThrowable().getMessage();
		String filePath = "N/A";
		if( ! error.equals("")){
			File newErrorDir = new File("Report/" + datePath + "/Fails");
			newErrorDir.mkdirs();
			File newErrorTxt = new File(newErrorDir.getPath() + "/" + methodName + ".txt");
			FileWriter w = new FileWriter(newErrorTxt);	
			BufferedWriter bw = new BufferedWriter(w);
			PrintWriter wr = new PrintWriter(bw);
			wr.write(error); 
			filePath = newErrorTxt.getAbsolutePath();
			wr.close();
			bw.close();
		}
		return filePath;
	}

	/**
	 * This method call another method to get the duration of the TC and its Status when TC skip
	 */
	@Override
	public void onTestSkipped(ITestResult testResult) {
		getTimeAndStatus(testResult);
		
		try {
			String methodName = testResult.getName();
			
			if(testResult.getParameters().length != 0)
			{
				methodName+=":"+testResult.getParameters()[0].toString();
			}				
			
			reportGenerator("N/A", status, methodName, "", getDate("dd_MM_yyyy"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method call another method to get the duration of the TC and its Status when TC Success
	 */
	@Override
	public void onTestSuccess(ITestResult testResult) {
		getTimeAndStatus(testResult);
		
		try {
			String methodName = testResult.getName();			
			reportGenerator(methodName, status, String.valueOf(time), "", getDate("dd_MM_yyyy"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method call another method to get information of the TC
	 */
	@Override
	public void onTestStart(ITestResult result) {		
		getTestCaseInfo(result);
	}	

	/**
	 * This method call another method to get the duration of the TC and its Status when TC fail with percentage of Success
	 */
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		getTimeAndStatus(result);
	}
	
	private void setTSRetry(ITestContext context){
		for (ITestNGMethod method : context.getAllTestMethods()) {
			method.setRetryAnalyzer(new TSRetry());
		}
	}

	@Override
	public void onFinish(ITestContext context) {}
	
	/**
	 * This method get the context parameters from the TestNG.xml executed.
	 */
	@Override
	public void onStart(ITestContext context) {		
		setTSRetry(context);
		ipServer = context.getSuite().getParameter("ip");
		build = context.getSuite().getParameter("build");
		testPlan = context.getSuite().getParameter("testPlan");
	}
	
	/**
	 * This method gets the time of the TC, its status, print all gotten information and send it.
	 * @param result
	 */
	private void getTimeAndStatus(ITestResult result) {
		time =  (result.getEndMillis() - result.getStartMillis()) / 1000;
		status = getTestStatus(result);
		//sendToServer();
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
	 * This method gets the own information of the TC in this case the testID parameter
	 * @param result = the result of the TC from TestNG execution
	 */
	private void getTestCaseInfo(ITestResult result) {
		Object[] parameters = result.getParameters();
	
		if(parameters != null && parameters.length > 0){
			testID = parameters[0].toString();
		}
	}

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
				.when()
				.post("http://"+ ipServer +"/server/AutomationExecutionInfoListener.html");				
			}
		}		
	}
	
	private void reportGenerator(String tcName, String tcStatus, String time, @Optional("")String description, String datePath) throws Exception {
		new ExcelReport("AllTestCasesStatuses" + datePath + ".xls")
						.inDirectory("Report/"+datePath)
						.buildReportHeader("TEST CASE", "STATUS", "DATE", "TIME", "DESCRIPTION", "TOTAL", "SUCCESS", "FAILURE", "SKIP")
						.addRow(tcName, tcStatus, String.valueOf(new Date()), time, description);
	}
	
	/*private WebDriver getDriver(ITestResult result) {
		//TODO jalar el driver q esta en uso de forma implicita
		Object currentClass = result.getInstance();
		WebDriver driver = ((test2TSListener) currentClass).uiInstance.getDriver();
        return driver;
	}	*/
	
	private String getDate(String dateFormat) {
		return new SimpleDateFormat(dateFormat).format(Calendar.getInstance().getTime());
	}
}