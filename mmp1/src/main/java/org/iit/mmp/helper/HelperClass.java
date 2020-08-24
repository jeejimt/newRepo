package org.iit.mmp.helper;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperClass {
	WebDriver driver;

	//constructor 
	public HelperClass(WebDriver driver) {
		this.driver=driver;
		
	}

	//switching Frames
	public WebDriver switchToAFrameAvailable(String frame,int timeinsec ) {
		WebDriverWait wait=new WebDriverWait(driver,timeinsec);
		driver=wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
		return driver;

	}

	//screenshots
	public void captureScreenshot(String tc_Name ) throws IOException {

		TakesScreenshot tsh =(TakesScreenshot)driver;
		File srcFile=tsh.getScreenshotAs(OutputType.FILE);
		String destPath=System.getProperty("user.dir")+"/screenshots/"+tc_Name+Calendar.getInstance().getTimeInMillis()+".jpg";
		File destFile=new File(destPath);
		FileUtils.copyFile(srcFile, destFile);
	}

	//launching module

	public void launchModule(String url) {
		driver.get(url);
	}

	//navigate to any module
	public void navigateToAModule(String moduleName) {		
		driver.findElement(By.xpath("//span[contains(text(),'"+moduleName+"')]")).click();				
	}
	//patient login
	public void login(String uName,String pWord) {			
		driver.findElement(By.id("username")).sendKeys(uName);
		driver.findElement(By.id("password")).sendKeys(pWord);
		driver.findElement(By.name("submit")).click();
	}
	//admin login
	public void adminLogin(String uName,String pWord)  {	
		driver.findElement(By.id("username")).sendKeys(uName);
		driver.findElement(By.id("password")).sendKeys(pWord);
		driver.findElement(By.name("admin")).click();
	}
	//clicking buttons
	public void  clickButtons(String textval) throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ; 
		driver.findElement(By.xpath("//input[@value='"+textval+"']")).click();			
	}	
	//to give appointment date
	public String giveDate() {
		waitMethod(By.id("app_date"));
		Select dateLB=new Select(driver.findElement(By.id("app_date")));
		dateLB.selectByIndex(1);
		String selectDate=driver.findElement(By.id("app_date")).getText();
		return selectDate;
	}
	//wait
	public  void waitMethod(By locator) {
		WebDriverWait w=new WebDriverWait(driver,15);
		w.until(ExpectedConditions.visibilityOfElementLocated(locator));	
	}
}

