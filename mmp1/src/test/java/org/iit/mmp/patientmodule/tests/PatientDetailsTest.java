package org.iit.mmp.patientmodule.tests;

import java.util.HashMap;
import org.iit.mmp.adminmodule.pages.PatientDetailsAdmPage;
import org.iit.mmp.adminmodule.pages.CreateReportVisitPrescFeesAdmPage;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.PatientDetailsPatientPage;
import org.iit.util.TestBase;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PatientDetailsTest extends TestBase{
	
	public  HashMap<String,String> hmapAdm;
	public  HashMap<String,String> hmapPat;
	public  String ssn;
	HelperClass helper;
	PatientDetailsPatientPage patientpage;
	CreateReportVisitPrescFeesAdmPage admPaPage;
	PatientDetailsAdmPage  padepage;
	
	/* Patient Profile Page Details
	 * @param patientUrl
	 * @param patientUname
	 * @param patientPword
	 * @throws InterruptedException
	 */
	@Parameters({"patientUrl","patientUname","patientPword"})
	@Test
	public void profilePatientDetails(String patientUrl,String patientUname,String patientPword) throws InterruptedException {
		patientpage=new PatientDetailsPatientPage(driver);
		helper=new HelperClass(driver);
		helper.launchModule(patientUrl);
		helper.login(patientUname,patientPword);
		helper.navigateToAModule(" Profile ");
		hmapPat= patientpage.personalDetails();
		ssn=hmapPat.get("patientSsn");
		Assert.assertTrue(hmapPat.size()>0,"Validating size of hashmap");
		Assert.assertTrue(ssn.length()>0,"validating size of ssn");
	}
	/*Admin Profile Page Details
	 * @param adminUrl
	 * @param adminUname
	 * @param adminPword
	 * @throws InterruptedException
	 */
	
	@Parameters({"adminUrl","adminUname","adminPword"})
	@Test(dependsOnMethods= {"profilePatientDetails"})
	public void adminPatientTab(String adminUrl,String adminUname,String adminPword) throws InterruptedException {
		helper=new HelperClass(driver);
		admPaPage=new CreateReportVisitPrescFeesAdmPage(driver);
		padepage=new PatientDetailsAdmPage(driver);
		helper.launchModule(adminUrl);
		helper.adminLogin(adminUname,adminPword);
		helper.navigateToAModule("Patients ");
		admPaPage.searchBySSN(ssn);
		hmapAdm=padepage.paDetailsAdm();
		Assert.assertTrue(hmapAdm.size()>0,"Validating size of hashmap");
	}
	/*
	 * Comparing Patient Profile Page content with admin profile page content
	 */
	
	@Test(dependsOnMethods={"adminPatientTab" })
	public void validatePaDetails() {
	    SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(hmapAdm.get("paName"),hmapPat.get("patientName")); 
		softAssert.assertEquals(hmapAdm.get("paSsn"),hmapPat.get("patientSsn"));
		softAssert.assertEquals(hmapAdm.get("paAge"),hmapPat.get("patientAge"));
		softAssert.assertTrue(hmapAdm.get("paWeight").contains(hmapPat.get("patientWeight")));
		softAssert.assertEquals(hmapAdm.get("paHeight"),hmapPat.get("patientHeight"));
		softAssert.assertEquals(hmapAdm.get("paAddress"),hmapPat.get("patientAddress"));
		softAssert.assertAll();
		
	}	
		
}
	

