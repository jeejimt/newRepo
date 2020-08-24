package org.iit.mmp.adminmodule.pages;
import java.util.HashMap;
import org.iit.mmp.helper.HelperClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PatientDetailsAdmPage {
	
	By admPaName=By.xpath("//td[contains(text(),'Patient Name')]");
	By admPaSsn=By.xpath("//td[contains(text(),'SSN')]/following-sibling::td[1]");
	By admPaAge=By.xpath("//td[contains(text(),'AGE')]/following-sibling::td[1]");
	By admPaWeight=By.xpath("//td[contains(text(),'Weight')]/following-sibling::td[1]");
	By admPaHeight=By.xpath("//td[contains(text(),'Height')]/following-sibling::td[1]");
	By admPaAddress=By.xpath("//td[contains(text(),'Address')]/following-sibling::td[1]");
	WebDriver driver;
	HelperClass helper;
	public PatientDetailsAdmPage(WebDriver driver){
		this.driver=driver;	
		 helper=new  HelperClass(driver);
		}
	
	public  HashMap<String,String>  paDetailsAdm() {
		
		HashMap<String,String> hmapPa=new HashMap<String,String>();	
		String patientName=driver.findElement(admPaName).getText().trim().split(":")[1];
		String patientSsn=driver.findElement(admPaSsn).getText().trim();
		String patientAge=driver.findElement(admPaAge).getText().trim();	
		String patientWeight=driver.findElement(admPaWeight).getText().trim();
		String patientHeight=driver.findElement(admPaHeight).getText().trim();
		String patientAddress=driver.findElement(admPaAddress).getText().trim();
		hmapPa.put("paName", patientName);
		hmapPa.put("paSsn", patientSsn);
		hmapPa.put("paAge", patientAge);
		hmapPa.put("paWeight", patientWeight);
		hmapPa.put("paHeight", patientHeight);
		hmapPa.put("paAddress", patientAddress);
		
	return hmapPa;	
}
}
