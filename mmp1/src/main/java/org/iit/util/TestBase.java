package org.iit.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TestBase {
	
 protected WebDriver driver;
 

 @BeforeTest
 public void instantiateDriver() {
		WebDriverManager.chromedriver().setup();
		 driver= new ChromeDriver();
	
	}
 }
 
 

