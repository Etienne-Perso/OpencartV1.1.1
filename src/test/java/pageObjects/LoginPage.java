package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txt_emailAddress; 
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txt_password; 
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement login_btn; 
	
	public void setEmail(String email) {
		txt_emailAddress.sendKeys(email);
	}
	
	public void setPassword(String pwd) {
		txt_password.sendKeys(pwd);
	}
	
	public void clickLogin() {
		login_btn.click();
	}
	

}
