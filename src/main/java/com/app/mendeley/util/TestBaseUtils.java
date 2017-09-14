package com.app.mendeley.util;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * @author Sathya Thameem
 *
 */
public class TestBaseUtils {

		// initialize properties file
		// logging
		// selenium/webdriver layer -  selenium commands
	
		Properties OR = null;
		Properties CONFIG=null;
		WebDriver driver =null;
		WebDriver mozilla=null;
		WebDriver chrome=null;
		WebDriver ie=null;
		static TestBaseUtils w;
		
		private TestBaseUtils(){
			
			if(OR==null){
				try{
					// initialize OR
					OR = new Properties();
					String sPropFileName = "/src/main/resources/configfiles/OR.properties";
					FileInputStream fs  = new FileInputStream(System.getProperty("user.dir")+sPropFileName);
					OR.load(fs);
		
					
				}catch(Exception e){
					System.out.println("Error on intializing properties files");
				}
				
				
				
			}
			
		}
		
		/// ****************Application Independent functions************************ ///

		// opening the browser
		public void openBrowser(String browser){
			String browserType = OR.getProperty(browser);
		
			if(browserType.equals("Mozilla") && mozilla==null){
				//Need to set the Gecko driver
				driver = new FirefoxDriver();
				mozilla=driver;
			}else if(browserType.equals("Mozilla") && mozilla!=null){
				driver=mozilla;
			}else if(browserType.equals("Chrome") && chrome==null){
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/main/resources/drivers/chromedriver");
				System.out.println("chrome browser");
				driver=new ChromeDriver();
				chrome=driver;
			}else if(browserType.equals("Chrome") && chrome==null){
				driver=chrome;
			}
			
			else if(browserType.equals("IE")){
				// set the IE server exe path and initialize
			}
			// max
			driver.manage().window().maximize();;
			// implicit wait
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
		}
		// navigates to a URL
		public void navigate(String URL){
			String strHome = OR.getProperty(URL);
			
			driver.navigate().to(strHome);
		}
		// clicking on any object
		public void click(String objectName){
			//System.out.println("Clicking on " + objectName);
			String clickIcon = OR.getProperty(objectName);
			WebElement element = driver.findElement(By.xpath(clickIcon));
			if(!element.isDisplayed()){
				WebDriverWait wait = new WebDriverWait(driver,20);
			    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(clickIcon)));
		    }
			element.click();
		}
		
		public void type(String text, String objectName){
			
			if(text.equals("null")){
			   text = "";
			}
			driver.findElement(By.xpath(OR.getProperty(objectName))).sendKeys(text);
		}
		
		public void select(String text, String objectName){
			String list = OR.getProperty(objectName);
			
		
			
			
				WebDriverWait wait = new WebDriverWait(driver,20);
			    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(list)));
		 			
			Select select = new Select(driver.findElement(By.xpath(list)));
			select.selectByVisibleText(text);
		}
		
		public boolean isElementPresent(String objectName){
			//System.out.println("Checking object presence "+ objectName);
			int count = driver.findElements(By.xpath(OR.getProperty(objectName))).size();
			if(count==0)
				return false;
			else
				return true;
		}
		
		public boolean verifyPageByTitle(String objectName){
			String sExpectedTitle = OR.getProperty(objectName);
			
			String sActualTitle = driver.getTitle();
			//System.out.println("The Actual Title "+sActualTitle);
			if(sExpectedTitle.equals(sActualTitle)){
				 return true;
			}else{
				return false;
			}
		}	
		
		public boolean isErrorMessageDisplayed(String errMessage,String location){
		
			String error = OR.getProperty(errMessage);
			System.out.println("Expected Error Message "+error);
			String xpathLocation = OR.getProperty(location);
			System.out.println("xpath error location"+xpathLocation);
			String actualError = driver.findElement(By.xpath(xpathLocation)).getText();
			System.out.println("Actual Error Message "+actualError);
			if(error.equals(actualError)){
					return true;
			}else{
				return false;
			}
		}
		
		/********Singleton**********/
		public static TestBaseUtils getInstance(){
			
			if(w==null){
				
				w= new TestBaseUtils();
			}
		return w;
		}
		
		/** Close the browsers...**/
		public void cleanUp(){
			driver.close();
		}
		
	
	}
	

