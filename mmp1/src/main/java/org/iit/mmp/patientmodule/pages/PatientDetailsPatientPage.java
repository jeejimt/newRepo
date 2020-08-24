package org.iit.mmp.patientmodule.pages;

import java.util.HashMap;
import org.iit.mmp.helper.HelperClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class PatientDetailsPatientPage {
	WebDriver driver;
	HelperClass helperObj;
	
	 public PatientDetailsPatientPage(WebDriver driver){
		this.driver=driver;	
		helperObj=new  HelperClass(driver);
     }
		                 	
	 public HashMap<String,String> personalDetails() throws InterruptedException{
			
		HashMap<String,String> hPatient=new HashMap<String,String>();	
		JavascriptExecutor js=(JavascriptExecutor) driver;
		String script="return document.getElementById(\"fname\").value;";
		String 	firstName=(String) js.executeScript(script);
		String script1 ="return document.getElementById(\"lname\").value;";
		String 	lastName=(String) js.executeScript(script1);
		String script2="return document.getElementById(\"ssn\").value;";
		String 	ssnNo=(String) js.executeScript(script2);
		String script3="return document.getElementById(\"age\").value;";
		String 	agePa=(String) js.executeScript(script3);
		String script4="return document.getElementById(\"weight\").value;";
		String 	weightPa=(String) js.executeScript(script4);
		String script5="return document.getElementById(\"height\").value;";
		String heightPa	=(String) js.executeScript(script5);
		String script6="return document.getElementById(\"addr\").value;";
		String 	address=(String) js.executeScript(script6);
		String script7="return document.getElementById(\"city\").value;";
		String cityadd	=(String) js.executeScript(script7);
		String script8="return document.getElementById(\"state\").value;";
		String stateadd	=(String) js.executeScript(script8);
		String paName=firstName+lastName;
		String paAddress=address+cityadd+stateadd;	
		hPatient.put("patientName", paName);
		hPatient.put("patientSsn", ssnNo);
		hPatient.put("patientAge", agePa);
		hPatient.put("patientWeight", weightPa);
		hPatient.put("patientHeight", heightPa);
		hPatient.put("patientAddress", paAddress);
		return hPatient;
		} 
		
		
}
