package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import TestBase.BaseClass;


public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporter; //UI of the report
	public ExtentReports extent; //populate common info on the report
	public ExtentTest test; //creating test case in the report and update status of the test methods
	
	String repName; 
	
	public void onStart(ITestContext testContext) {
		/*
			SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
			Date dt= new Date();
			String timeStamp= df.format(dt); 
		*/
		
		
		String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //time Stamp
		repName="Test-Report-"+timeStamp+".html"; 
		
		
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName); //specify the location of my report generation 
		
		sparkReporter.config().setDocumentTitle("Opencart Automation Report"); //Title of report
		sparkReporter.config().setReportName("Opencart Functional Testing"); // Name of the report
		sparkReporter.config().setTheme(Theme.DARK); 
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Application", "Opencart");   //Info related to the project
		extent.setSystemInfo("Module", "Admin");           //Info related to the project
		extent.setSystemInfo("Sub Module", "Customers");   //Info related to the project
		
		extent.setSystemInfo("User name", System.getProperty("user.name")); //Who is running the tests
		extent.setSystemInfo("Environment", "QA");
		
		String os= testContext.getCurrentXmlTest().getParameter("os"); //Getting the os parameter from the xml file
		extent.setSystemInfo("Operating System", os);
		
		String browser= testContext.getCurrentXmlTest().getParameter("browser"); //Getting the browser parameter from the xml file
		extent.setSystemInfo("Browser", browser);
		
		List <String> includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();  //Getting the tests groups from xml file
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}
	
	public void onTestSuccess(ITestResult result) {
		
		test= extent.createTest(result.getTestClass().getName()); //Create a new entry in the report by displaying the class name of the test
		test.assignCategory(result.getMethod().getGroups());      //To display groups in report 
		test.log(Status.PASS, result.getName()+" got successfully executed"); //update status p/f/s
	}
	
	public void onTestFailure(ITestResult result) {
		
		test= extent.createTest(result.getTestClass().getName()); //Create a new entry in the report
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName()+" got failed"); //update status p/f/s
		test.log(Status.INFO, result.getThrowable().getMessage()); //getting all the failure logs in the report  
		
		//If the screen shot is not captured properly then try_catch block will handle that exception 
		try { 
			String imgPath = new BaseClass().captureScreen(result.getName()); //capturing the image path
			test.addScreenCaptureFromPath(imgPath);      					//attaching the image path to the report
		
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void onTestSkipped(ITestResult result) {
		
		test= extent.createTest(result.getName()); //Create a new entry in the report
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+" got skipped"); //update status p/f/s
		test.log(Status.INFO, result.getThrowable().getMessage()); //getting all the skipped logs in the report  
	}
	
	
	public void onFinish(ITestContext testContext) {
		
		extent.flush();
		
		
		//Opening automatically the extent report after it's generation
		String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName; 
		File extentReport =new File(pathOfExtentReport); 
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		
		}catch(IOException e1) {
			e1.printStackTrace();
		}
		
		/*
		//Sending automatically the report by email to the team or colleagues after it's generation
		
		**** Ajouter cette dependance dans le fichier pom.xml ****
		<!-- https://mvnrepository.com/artifact/org.apache.commons/commons-email -->
			<dependency>
			    <groupId>org.apache.commons</groupId>
			    <artifactId>commons-email</artifactId>
			    <version>1.6.0</version>
			</dependency>

		
		try {
			URL url=new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName); 
			
			//Create the email message
			ImageHtmlEmail email= new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.googlemail.com"); 
			email.setSmtpPort(465); 
			email.setAuthenticator(new DefaultAuthenticator("etiennetraining@gamil.com")); 
			email.setSSLOnConnect(true);
			email.setFrom("etiennetraining@gamil.com"); //Sender
			email.setSbject("Test Results");
			email.setMsg("Please find attached report");
			email.addTo("toitraining@gmail.com"); //Receiver
			email.attach(url, "extent report", "please check report...");
			email.send(); //send the mail
		}catch(Exception e2) {
			e2.printStackTrace();
		}	
	  */
	}
}