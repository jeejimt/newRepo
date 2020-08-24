package org.iit.mmp.adminmodule.tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.io.IOException;
import java.util.HashMap;
import org.iit.mmp.adminmodule.pages.CreateReportVisitPrescFeesAdmPage;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.ViewReportHistoryPatientPage;
import org.iit.util.TestBase;

public class ReportHistoryTest extends TestBase{
	ViewReportHistoryPatientPage viewReportPage;
	HelperClass helper;
	CreateReportVisitPrescFeesAdmPage admPapage;
	public  HashMap<String,String> haVisit,haPresc,haReport;
	public  HashMap<String,String> hpApp,hpPresc,hpReport;

	/*Admin Page--> Patient Tab -->Create Visit Button--creating Appointment
	 * @param adminUrl
	 * @param ssnNo
	 * @param adminUname
	 * @param adminPword
	 * @param drName
	 * @throws InterruptedException
	 */
	@Parameters({"adminUrl","ssnNo","adminUname","adminPword","drName"})
	@Test
	public  void  createVisit(String adminUrl,String ssnNo,String adminUname,String adminPword,String drName) throws InterruptedException{

		admPapage=new CreateReportVisitPrescFeesAdmPage(driver);
		helper=new HelperClass(driver);
		helper.launchModule(adminUrl);
		helper.adminLogin(adminUname,adminPword);
		helper.navigateToAModule("Patients ");
		admPapage.searchBySSN(ssnNo);
		helper.clickButtons("Create Visit");
		haVisit=admPapage.visitDetails(drName);
		Assert.assertTrue(haVisit.size()>0,"Validating size of hashmap");
	}

	/*
	 * Admin Page--> Patient Tab -->Add Prescription  Button--Adding Prescription details
	 */
	@Parameters({"ssnNo"})
	@Test(dependsOnMethods= {"createVisit"})
	public void addPrescription(String ssnNo) throws InterruptedException, IOException {
		admPapage.searchBySSN(ssnNo);
		helper.clickButtons("Add Precription");
		haPresc=admPapage.prescriptonDetails();
		Assert.assertTrue(haPresc.size()>0,"Validating size of hashmap");
	}
	/*
	 * Admin Page--> Patient Tab -->Create Fee Button--Adding Fee Details
	 */
	@Parameters({"ssnNo"})
	@Test(dependsOnMethods= {"addPrescription"})
	public void createFee(String ssnNo) throws InterruptedException{
		helper.navigateToAModule("Patients ");
		admPapage.searchBySSN(ssnNo);
		helper.clickButtons("Create Fee");	
		String alertText=admPapage.feeDetails();
		Assert.assertTrue(alertText.contains("Fee Successfully Entered."));
	}
	/*
	 * Admin Page-->Patient Tab-->Reports Button
	 */

	@Test(dependsOnMethods= {"createFee"})
	public void creatReport() throws Throwable  {	
		helper.clickButtons("Reports");
		haReport= admPapage.reportDetails();
		Assert.assertTrue(haReport.size()>0,"Validating size of hashmap");
	} 
	/*Patient Page -->Profile Tab-->View Report Button --View Reports page details displayed
	 * @param  patientUrl
	 * @param patientUname
	 * @param patientPword
	 * @throws InterruptedException
	 */
	// patient module
	@Parameters({"patientUrl","patientUname","patientPword"})
	@Test(dependsOnMethods={"creatReport"})
	public void viewReport(String patientUrl,String patientUname,String patientPword) throws InterruptedException {
		viewReportPage=new ViewReportHistoryPatientPage(driver);
		helper=new HelperClass(driver);
		helper.launchModule(patientUrl);
		helper.login(patientUname,patientPword);
		helper.navigateToAModule(" Profile ");
		hpReport=viewReportPage.viewReport();
		Assert.assertTrue(hpReport.size()>0,"Validating size of hashmap");
	}
	/*
	 * Patient Page -->Profile Tab-->View History Button --View History Page  opens with 4 buttons 
	 */

	@Test(dependsOnMethods={"viewReport"})
	public void viewHistory() throws InterruptedException {
		viewReportPage.viewHistory();
	}
	/*
	 * Past Appointment Button--clicking -shows  past Appointment details
	 */

	@Test(dependsOnMethods={"viewHistory"})
	public void viewPastAppointment() throws InterruptedException{
		hpApp=viewReportPage.pastAppointment();
		Assert.assertTrue(hpApp.size()>0,"Validating size of hashmap");
	}
	/*
	 * Past Prescription--clicking -- shows  Prescription Details
	 */

	@Test(dependsOnMethods={"viewPastAppointment"})
	public void viewPastPrescription() throws InterruptedException {
		hpPresc=viewReportPage.pastPrescription();
		Assert.assertTrue(hpPresc.size()>0,"Validating size of hashmap");
	}
	/*
	 * Comparing  Create Visit  details in Admin Page with Past appointment details in Patient Page   
	 */

	//validation 
	@Test(dependsOnMethods= {"viewPastPrescription"})
	public void validateAppointment() {	
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(haVisit.get("dateofAppointment").contains(hpApp.get("dateApp")));
		softAssert.assertTrue(haVisit.get("timeOfAppointment").contains(hpApp.get("timeApp"))); 		 
		softAssert.assertTrue(haVisit.get("symptoms").contains(hpApp.get("symp"))); 
		softAssert.assertTrue(haVisit.get("doctorName").contains(hpApp.get("DrName")));
		softAssert.assertAll();	
		/*
		 * Comparing  Add Prescription  details in Admin Page with Past  Prescription details in Patient Page
		 */
	}
	@Test(dependsOnMethods= {"validateAppointment"})
	public void validatePrescription(){	
		SoftAssert softAssert = new SoftAssert();	
		softAssert.assertTrue(haPresc.get("prescDate").contains(hpPresc.get("DateP"))); 
		softAssert.assertTrue(haPresc.get("medName").contains(hpPresc.get("NameP"))); 
		softAssert.assertTrue(haPresc.get("medDesc").contains(hpPresc.get("DescP")));
		softAssert.assertAll();
	}
	/*
	 * Comparing   Reports  details in Admin Page with View Report details in Patient Page
	 */

	@Test(dependsOnMethods= {"validatePrescription"})
	public void validateReports(){
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(hpReport.get("nameRepo").contains(haReport.get("reportName")));
		softAssert.assertTrue(hpReport.get("descRepo").contains(haReport.get("reportDesc")));
		softAssert.assertAll();	

	}

}

