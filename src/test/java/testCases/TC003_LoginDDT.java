package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccount;
import utilities.DataProviders;

/*
Data is valid    ---> login success ---> test pass ---> logout
			     ---> login failed  ---> test fail

Data is invalid  ---> login success ---> test fail ---> logout
			     ---> login failed  ---> test pass 
 */

public class TC003_LoginDDT extends BaseClass {
	
	@Test(dataProvider="LoginData", dataProviderClass= DataProviders.class, groups="DataDriven")
	public void verify_LogingDDT(String email, String pwd, String exp) {
		
		logger.info("**** Starting TC003_LoginDDT ****");
		
		try {
		//Home page to login page
		HomePage hp=new HomePage(driver);
		hp.myAccount_click();
		hp.loginAccount_click();
				
		//Inside Login page
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
				
		//Validating the login message
		MyAccount macc=new MyAccount(driver);
		boolean targetPage=macc.ismyAccountdisplayed();
		//Assert.assertEquals(targetPage, true, "Login failed");
		
		/*
		Data is valid    ---> login success ---> test pass ---> logout
					     ---> login failed  ---> test fail
     	*/
		if(exp.equalsIgnoreCase("Valid")) {
			if(targetPage==true) {
				macc.click_Logout();
				Assert.assertTrue(true);
			}else {
				Assert.assertTrue(false);
			}
		}
		
		/*
		Data is invalid  ---> login success ---> test fail ---> logout
					     ---> login failed  ---> test pass 
		*/
		if(exp.equalsIgnoreCase("Invalid")) {
			if(targetPage==true) {
				macc.click_Logout();
				Assert.assertTrue(false);
			}else {
				Assert.assertTrue(true); 
			}
		}
		}catch(Exception e) {
			Assert.fail();
		}
	
		logger.info("**** Finished TC003_LoginDDT ****");
	}
}
