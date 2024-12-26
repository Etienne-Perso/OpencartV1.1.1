package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class MyAccount extends BasePage{

	public MyAccount(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement txt_myAccount;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement Logout_btn; 
	
	public boolean ismyAccountdisplayed() {
		try {
			return(txt_myAccount.isDisplayed());
		}catch(Exception e) {
			return false; 
		}
		
	}
	
	public void click_Logout() {
		Logout_btn.click();
	}
}
