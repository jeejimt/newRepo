package org.iit.mmp.patientmodule.pages;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.iit.mmp.adminmodule.pages.CreateReportVisitPrescFeesAdmPage;
import org.iit.mmp.helper.HelperClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ViewReportHistoryPatientPage {

	By viewReportBT=By.xpath("//button[contains(text(),'View Reports')]");
	By viewHistoryBT=By.xpath("//button[contains(text(),'View History')]");
	By dateTB=By.xpath("//table/tbody/tr[1]/td[1]");
	By timeLB=By.xpath("//table/tbody/tr[1]/td[2]");
	By symTB=By.xpath("//table/tbody/tr[1]/td[3]");
	By DrName=By.xpath("//table/tbody/tr[1]/td[4]");
	By prescTB=By.xpath("//div[@class='col-md-6']/div/div[2]/h2");
	By prescDescTB=By.xpath("//div[@class='col-md-6']/div/div[2]/p");		
	By tableselector=By.xpath("//table[@class='table']");
	HashMap<String,String> hmapApp,hmapPresc,hmapReport;
	WebDriver driver;
	HelperClass helperObj;
	ScheduleAppointmentPage sap;
	CreateReportVisitPrescFeesAdmPage pPage;
	
	public ViewReportHistoryPatientPage(WebDriver driver){
	 this.driver=driver;	
	 helperObj=new  HelperClass(driver);
	}
	            
	public HashMap<String,String> viewReport() throws InterruptedException{	
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;	
		hmapReport=new HashMap<String,String>();
		driver.findElement(viewReportBT).click();
		int[] myArray=findTableSize();
		int rowindex=myArray[0];
		int colindex=myArray[1];
		String nameR=driver.findElement(By.xpath("//table[@class='table']/tbody/tr["+(rowindex)+"]/td["+(colindex)+"]/a/ul/li/h4")).getText();
		String descR=driver.findElement(By.xpath("//table[@class='table']/tbody/tr["+(rowindex)+"]/td["+(colindex)+"]/a/ul/li/div/p")).getText();
		driver.findElement(By.xpath("//table[@class='table']/tbody/tr["+(rowindex)+"]/td["+(colindex)+"]/a/ul/li/h4")).click();
		hmapReport.put("nameRepo", nameR);
		hmapReport.put("descRepo", descR);
		driver.navigate().back();
		return hmapReport;
	}
		
	public void viewHistory() throws InterruptedException {
		
		driver.findElement(viewHistoryBT).click();	 	 
	}
		
	public  HashMap<String,String> pastAppointment() throws InterruptedException {
		Thread.sleep(1000);
		hmapApp=new HashMap<String,String>();
		helperObj.clickButtons("Past Appointments");
		String date=driver.findElement(dateTB).getText().trim();
		String time=driver.findElement(timeLB).getText().trim();
		String symptoms=driver.findElement(symTB).getText().trim();
		String docName=driver.findElement(DrName).getText().trim();
		hmapApp.put("dateApp", date);
		hmapApp.put("timeApp", time);
		hmapApp.put("symp", symptoms);
		hmapApp.put("DrName",docName );
		driver.navigate().back();
		return hmapApp;	
		}
	public HashMap<String,String> pastPrescription() throws InterruptedException {
			
	 // driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ; 
	 Thread.sleep(1000);
		helperObj.clickButtons("Past Prescription");
		hmapPresc=new HashMap<String,String>();
		int[] myArray=findTableSize();
		int rowindex=myArray[0];
		int colindex=myArray[1];
		String date=driver.findElement(By.xpath("//table[@class='table']/tbody/tr["+(rowindex)+"]/td["+(colindex)+"]/a/ul/li/div/p")).getText();
		String[] dateArray=date.split(":");
		String datePresc=dateArray[1];
		driver.findElement(By.xpath("//table[@class='table']/tbody/tr["+(rowindex)+"]/td["+(colindex)+"]")).click();
		Thread.sleep(1000);
		helperObj.waitMethod(prescTB);
		String prescName=driver.findElement(prescTB).getText();
		String prescDesc=driver.findElement(prescDescTB).getText();	
		hmapPresc.put("DateP", datePresc);
		hmapPresc.put("NameP",prescName );
		hmapPresc.put("DescP",prescDesc );
		return hmapPresc;
		}
		
	public int[] findTableSize() {
			
		WebElement tabledriver=driver.findElement(tableselector);
		List<WebElement>  rowList=tabledriver.findElements(By.tagName("tr"));
		int rows=rowList.size();
		int[] myArray=new int[2];
		//number of columns
		WebElement rowdriver=driver.findElement(By.xpath("//table[@class='table']/tbody/tr["+(rows)+"]"));
		List<WebElement> colList=rowdriver.findElements(By.tagName("td"));
		int cols=colList.size();
		if (cols==0) {
			rows=rows-1;
			cols=4;
		 }
		myArray[0]=rows;
		myArray[1]=cols;
		return myArray;	
		}
		
		
	

		



		
		
}

	


