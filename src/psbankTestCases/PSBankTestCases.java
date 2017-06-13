package psbankTestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.inflectra.spiratest.addons.testnglistener.SpiraTestCase;
import com.inflectra.spiratest.addons.testnglistener.SpiraTestConfiguration;

import psbankActions.AutomationSecurity;

@SpiraTestConfiguration(
url="http://192.168.1.158/SpiraTeam/",
login="COrobia",
password="H2scs2016",
projectId=14,
releaseId=140
)

public class PSBankTestCases {
	//public String baseUrl = "https://www.psbank.com.ph/";
    public WebDriver driver ; 
    public String WB = "PSBankCalcTestCases.xlsx";
    //public String WB = "PSBankOnlineTestCases.xlsx";
    SoftAssert sAssert = new SoftAssert();
    
    @BeforeSuite
    public void runSecurity() throws Exception{
    	AutomationSecurity.validateCredentials();
    }
    
    @BeforeTest                           
      public void launchBrowser() {
	      System.out.println("Launching Chrome Browser"); 
	      System.setProperty("webdriver.chrome.driver", "C:\\Users\\H2\\workspace\\driver path new\\chromedriver.exe");
	      driver = new ChromeDriver();
	      driver.manage().window().maximize();
      }
    
     @Test (priority = 0)
     @SpiraTestCase(testCaseId=7209)
     public void IncorrectAccountName()throws Exception{
			String excelsheet="WrongAccountName";
			PSBankGenericScript.ExecuteScript(WB, excelsheet, driver, sAssert);
			sAssert.assertAll();
     }
     
     @Test (priority = 1)
     @SpiraTestCase(testCaseId=7274)
     public void IncorrectAccountNumber()throws Exception{
			String excelsheet="WrongAccountNumber";
			PSBankGenericScript.ExecuteScript(WB, excelsheet, driver, sAssert);
     }
     
     @Test (priority = 2)
     @SpiraTestCase(testCaseId=7275)
     public void AutoLoanCalc()throws Exception{
			String excelsheet="AutoLoanCalc";
			PSBankGenericScript.ExecuteScript(WB, excelsheet, driver, sAssert);
     }
     
     @Test (priority = 3)
     @SpiraTestCase(testCaseId=7276)
     public void HomeLoanCalc()throws Exception{
			String excelsheet="HomeLoanCalc";
			PSBankGenericScript.ExecuteScript(WB, excelsheet, driver, sAssert);
     }
    
     @AfterSuite                   
      public void terminateBrowser(){
          driver.quit();
      }
}