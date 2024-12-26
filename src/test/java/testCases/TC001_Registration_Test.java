package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.HomePage;
import pageObjects.RegistrationPage;

public class TC001_Registration_Test extends BaseClass {
	
	@Test(groups={"Regression","Master"})
	public void verify_Account_Registration() {
		try {
		logger.info("My test is starting....");
		//Accessing registration page
		HomePage hp=new HomePage(driver); 
		hp.myAccount_click();
		hp.myRegister_click();
		
		
		logger.info("Putting the data in the fields");
		//Operating on the registration page
		RegistrationPage rp=new RegistrationPage(driver); 
		rp.fname_sendKeys(randomeString().toUpperCase());
		rp.lname_sendKeys(randomeString().toUpperCase());
		rp.email_sendKeys(randomeString()+"@gmail.com");
		rp.phone_sendKeys(randomeNumber());
		
		String password =randomeAlphaNumeric();
		rp.password_sendKeys(password);
		rp.confirmpwd_sendkeys(password);
		rp.checkbox_click();
		rp.btn_continue_click();
		
		logger.info("Validating my test....");
		//Validation on the message 
		//Assert.assertEquals(rp.getConfirmationMsg(), "Your Account Has Been Created!123"); 
		if(rp.getConfirmationMsg().equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
			
		}else {
			
			logger.error("test failed");
			logger.debug("Debug logs....");
			Assert.assertTrue(false); 
		}
		
		logger.info("My test is succeeded");
		}
		catch(Exception e) {
			
			Assert.fail();
		}
		logger.info("My test is finished Successfully");
	}

}
