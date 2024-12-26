package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccount;

public class TC002_Login_Test extends BaseClass{

	@Test(groups={"Sanity", "Master"})
	public void verify_login() {
		
		logger.info("****  Starting TC002_Login_Test *****");
		
		try {
		//Home page to login page
		HomePage hp=new HomePage(driver);
		hp.myAccount_click();
		hp.loginAccount_click();
		
		//Inside Login page
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		//Validating the login message
		MyAccount macc=new MyAccount(driver);
		boolean targetPage=macc.ismyAccountdisplayed();
		//Assert.assertEquals(targetPage, true, "Login failed");
		Assert.assertTrue(targetPage);
		
		}catch(Exception e) {
			Assert.fail();
			}
		logger.info("****  Finished TC002_Login_Test  ****");
	}
}
