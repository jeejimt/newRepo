package org.iit.mmp.patientmodule.pages;

import java.util.HashMap;

import org.iit.mmp.helper.HelperClass;
import org.iit.util.AppLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ScheduleAppointmentPage {
	By createAppointmentButton = By.xpath("//input[@value='Create new appointment']");
	By datePicker = By.id("datepicker");
	By continueButton = By.id("ChangeHeatName");
	By symptxtbox = By.name("sym");
	By submitButton =By.xpath("//input[@value='Submit']");
	By providerNameDetails = By.xpath("//a[contains(text(),'Provider')]");
	By timeDetails =  By.xpath("//a[contains(text(),'Time')]");
	By headerDetails =  By.xpath("(//h3[@class='panel-title'])[2]");
	By symDetails =  By.xpath("//a[contains(text(),'Symptoms')]");
	
WebDriver driver;
HelperClass helperObj;

	public ScheduleAppointmentPage(WebDriver driver) {
	this.driver=driver;
	helperObj=	 new HelperClass(driver);
}
	public HashMap<String,String> bookAnAppointment(String doctorName) throws InterruptedException {
		HashMap<String,String> hmap=new HashMap<String,String>();
		AppLibrary lib=new AppLibrary();		
		helperObj=	 new HelperClass(driver);
		String dateofApp = AppLibrary.getFutureDate(20);
		String timeofApp="11Am";
		String sym="Booking an appointment for " + doctorName+ " on date "+dateofApp+" For Symptom Fever";
		
//driver.findElement(createAppointmentButton).click();
driver.findElement(By.xpath("//h4[contains(text(),'"+doctorName+"')]/ancestor::ul/following-sibling::button")).click();
driver = helperObj.switchToAFrameAvailable("myframe", 20);
driver.findElement(datePicker).sendKeys(dateofApp);
driver.findElement(datePicker).sendKeys(Keys.TAB);
Select timeSelector=new Select(driver.findElement(By.id("time")));
timeSelector.selectByVisibleText(timeofApp);
Thread.sleep(1000);
driver.findElement(continueButton).click();
driver.findElement(symptxtbox).clear();
Thread.sleep(2000);
driver.findElement(symptxtbox).sendKeys(sym);
driver.findElement(submitButton).click();
hmap.put("doctorName",doctorName );
hmap.put("dateofAppointment",dateofApp );
hmap.put("timeOfAppointment",timeofApp );
hmap.put("symptoms", sym);
return hmap;
		
	}
	public boolean validateAppointmentDetailsinSchedulePage(HashMap<String,String> hmap) {
		driver.findElement(By.xpath("//span[contains(text(),'Schedule Appointment ' )]")).click();
		String appDate=driver.findElement(headerDetails).getText();
		
		String timeArr[]=driver.findElement(timeDetails).getText().split(":");
		String providerName[]=driver.findElement(providerNameDetails).getText().split(":");
		
		String symArr[]=driver.findElement(symDetails).getText().split(":");
		

		//validation

boolean result=false;

if ((timeArr[1].contains(hmap.get("timeOfAppointment"))) && (symArr[1].contains(hmap.get("symptoms"))) && (hmap.get("doctorName").contains(providerName[1]) && hmap.get("dateofAppointment").contains(appDate)))

{
	System.out.println("successful");
			result=true;
}
		else
			System.out.println("not successful");
return result;
	}
	
}



