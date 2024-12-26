package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage{

	
	public RegistrationPage(WebDriver driver) {
		super(driver); 
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txt_fname; 
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txt_lname; 
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txt_email;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txt_phone; 
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txt_password;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txt_confirmpwd;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chk_box;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btn_continue; 
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation; 
	
	
	public void fname_sendKeys(String fname) {
		txt_fname.sendKeys(fname);
	}
	
	public void lname_sendKeys(String lname) {
		txt_lname.sendKeys(lname);
	}
	
	public void email_sendKeys(String email) {
		txt_email.sendKeys(email);
	}
	
	public void phone_sendKeys(String phone) {
		txt_phone.sendKeys(phone);
	}
	
	public void password_sendKeys(String password) {
		txt_password.sendKeys(password);
	}
	
	public void confirmpwd_sendkeys(String confirmpwd) {
		txt_confirmpwd.sendKeys(confirmpwd);
	}
	
	public void checkbox_click() {
		chk_box.click();
	}
	
	public void btn_continue_click() {
		btn_continue.click();
	}
	
	public String getConfirmationMsg() {
		try {
			return (msgConfirmation.getText()); 
		
		}catch(Exception e) {
			return (e.getMessage()); 
		}
	}
	
}
