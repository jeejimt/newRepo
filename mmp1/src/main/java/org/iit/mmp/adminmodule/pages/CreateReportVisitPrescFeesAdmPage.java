package org.iit.mmp.adminmodule.pages;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.ScheduleAppointmentPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateReportVisitPrescFeesAdmPage {
	
	By continueButton = By.id("ChangeHeatName");
	By symptxtbox = By.name("sym");
	By submitButton =By.xpath("//input[@value='Submit']");
	By submitButton2 =By.xpath("//input[@value='submit']");
	By searchTB=By.xpath("//input[@id='search']");
	By searchButton=By.xpath("//input[@value='search']");
	By viewHistoryBT=By.xpath("//button[contains(text(),'View History')]");		
	By nameLink=By.xpath("//div[@id='show']//table//tbody//tr//td//a");
	By prescNameTB=By.id("exampleInputcardnumber1");
	By prescDescTA=By.xpath("//textarea[@class='form-control']");
	WebDriver driver;
	HelperClass helperObj;
	ScheduleAppointmentPage sap;
	HashMap<String, String> hmap_visit,hmap_prescription,hmap_report;
	public Properties prop;
	
	public CreateReportVisitPrescFeesAdmPage(WebDriver driver){
	    this.driver=driver;	
	    helperObj=new  HelperClass(driver);
	}
	 
	public void searchBySSN(String ssnno) throws InterruptedException {
		 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		driver.findElement(searchTB).sendKeys(ssnno);
		driver.findElement(searchButton).click();		
		driver.findElement(nameLink).click();
		Thread.sleep(1000);
	}
	
	public HashMap<String, String> visitDetails(String DrName) throws InterruptedException {
		
		ScheduleAppointmentPage sap=new ScheduleAppointmentPage(driver);
		HashMap<String,String> hmap_visit=sap.bookAnAppointment(DrName);
		return hmap_visit;
	}
	
	public  void  propertyFileMethod() throws IOException {
		prop=new  Properties();
		String datafilepath=System.getProperty("user.dir")+"/data.properties";
		FileInputStream fis=new FileInputStream((datafilepath));
		prop.load(fis);
	}
		
	public HashMap<String,String> prescriptonDetails() throws InterruptedException, IOException {
		 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		propertyFileMethod();
		String medicine=prop.getProperty("medicineName");
		String medDesc=prop.getProperty("medicineDescription");
		hmap_prescription=new HashMap<String,String>();
		String selectDate=helperObj.giveDate();
		driver.findElement(prescNameTB).sendKeys(medicine);
		driver.findElement(prescDescTA).sendKeys(medDesc);
		driver.findElement(submitButton2).click();
		hmap_prescription.put("prescDate", selectDate);
		hmap_prescription.put("medName", medicine);
		hmap_prescription.put("medDesc", medDesc);
		driver.switchTo().alert().accept();
		return hmap_prescription;	
	}
	
	public String feeDetails() throws InterruptedException {
		 //driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		Thread.sleep(1000);
		String selectDate=helperObj.giveDate();
		Select ser=new Select(driver.findElement(By.id("service")));
		ser.selectByIndex(1);		
		driver.findElement(submitButton2).click();
		String alertText=driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		return alertText;
	}
	public HashMap<String,String> reportDetails() throws Throwable { 
		
		Thread.sleep(1000);
		propertyFileMethod();
		String report=prop.getProperty("reportName");
		 String reportdesc=prop.getProperty("reportDescription");
		HashMap<String, String> hmap_report=new HashMap<String,String>();
		String selectDate=helperObj.giveDate();
		driver.findElement(By.name("report_name")).sendKeys(report);
		//upload file
		Thread.sleep(2000);
		String docuFilePath = System.getProperty("user.dir")+"/report.docx";	
		driver.findElement(By.id("file")).sendKeys(docuFilePath);
		//fileUpload(docuFilePath);
			driver.findElement(By.name("report_desc")).sendKeys(reportdesc);
			driver.findElement(submitButton2).click();
			hmap_report.put("AppDate",selectDate);
			hmap_report.put("reportName",report);		
			hmap_report.put("reportDesc",reportdesc);
			return hmap_report;
	}
	
	
	public void fileUpload(String docuFilePath) throws InterruptedException, AWTException ,Throwable {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String script="return document.getElementById(\"file\").click();";
		 js.executeScript(script);
		StringSelection clipBoardPath = new StringSelection(docuFilePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(clipBoardPath, null);
		
		Thread.sleep(1000);
		
		//Robot Class
		Robot robot = new Robot();
		// Cmd + Tab is needed since it launches a Java app and the browser looses focus
		robot.keyPress(KeyEvent.VK_META);  
		robot.keyPress(KeyEvent.VK_TAB); 
		robot.keyRelease(KeyEvent.VK_META); 
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.delay(1000);
		//Open Goto window
		robot.keyPress(KeyEvent.VK_META); 
		robot.keyPress(KeyEvent.VK_SHIFT); 
		robot.keyPress(KeyEvent.VK_G); 
		robot.keyRelease(KeyEvent.VK_META); 
		robot.keyRelease(KeyEvent.VK_SHIFT); 
		robot.keyRelease(KeyEvent.VK_G);
		robot.delay(5000);
		//Keyboard Action : CTRL+V paste clipboard value
		
		robot.keyPress(KeyEvent.VK_META);  
		robot.keyPress(KeyEvent.VK_V); 
		robot.keyRelease(KeyEvent.VK_META); 
		robot.keyRelease(KeyEvent.VK_V);
		//
		
		//Keyboard Action : Enter close go to window and upload window
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(500);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
		
	}
			

