package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
	//Constructor
	public HomePage(WebDriver driver){
		super(driver); 
	}
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement myAccount; 
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement myRegister; 
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement loginAccount; 
	
	public void myAccount_click() {
		myAccount.click();
	}

	public void myRegister_click() {
		myRegister.click();
	}
	
	public void loginAccount_click() {
		loginAccount.click();
	}
}
