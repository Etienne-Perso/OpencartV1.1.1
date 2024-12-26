package TestBase;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;



public class BaseClass {
	
	public static WebDriver driver; 
	public Logger logger;   //Log4j2
	public Properties p;
	
	@BeforeClass(groups={"Sanity", "Regression", "Master"})
	@Parameters({"os", "browser"})
	public void setup(String os, String br) throws IOException {
		
		//Loading config.properties file
		FileReader f= new FileReader("./src//test//resources//config.properties"); 
		p=new Properties();
		p.load(f); 
		
		logger=LogManager.getLogger(this.getClass()); //Log4j2
		
		//1) Remote execution:
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			DesiredCapabilities cap=new DesiredCapabilities(); 
			
			//os
			if(os.equalsIgnoreCase("windows")) {
				
				cap.setPlatform(Platform.WIN11);
				
			}else if(os.equalsIgnoreCase("Mac")) {
				
				cap.setPlatform(Platform.MAC); 
				
			}else {
				System.out.println("No matching operating system");
			}
			
			//browser
			switch (br.toLowerCase()) {
			
			case "chrome": cap.setBrowserName("chrome"); break;
			case "edge": cap.setBrowserName("MicrosoftEdge"); break;
			default: System.out.println("No matching browser"); return; 
			
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap); //instantiate the driver with the remote url
		}
		
		
		//2) Local execution:
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			
			switch(br.toLowerCase()) {  // for multiple browsers execution when running xml file 
			
			case "chrome": driver=new ChromeDriver(); break;
			case "edge": driver=new EdgeDriver(); break;
			case "firefox": driver=new FirefoxDriver(); break; 
			default :System.out.println("Invalid browser name..."); return; 
			}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		//driver.get("https://tutorialsninja.com/demo/");
		driver.get(p.getProperty("appURL")); //reading url value from config.properties file
		driver.manage().window().maximize();
	}
	
	
	@AfterClass(groups={"Sanity", "Regression", "Master"})
	public void tearDown() {
		driver.quit();
	}
	
	public String randomeString() {
		return RandomStringUtils.randomAlphabetic(5);  
	}
	
	public String randomeNumber() {
		return RandomStringUtils.randomNumeric(10); 
	}
	
	public String randomeAlphaNumeric() {

		return (RandomStringUtils.randomAlphabetic(3)+"*"+RandomStringUtils.randomNumeric(3)); 
	}

	public String captureScreen(String tname) throws IOException{
		
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver; 
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE); 
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";   
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		
		return targetFilePath;
	}
}
