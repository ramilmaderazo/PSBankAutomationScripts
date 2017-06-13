package psbankActions;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class PSBankExpectedResult {
	WebDriver driver;
	public PSBankExpectedResult(WebDriver driver){
		this.driver  = driver;	
	} 
	public void VerifyExpectedResult(SoftAssert sAssert,WebDriver driver, Properties p, String expectedAction, String expectedObject, String expectedOType, String expectedValue) throws Exception{
		WebDriverWait  wait = new WebDriverWait(driver, 60);
		System.out.println("");
		switch (expectedAction.toUpperCase()){
		case "ASSERTEQUALS":
			String aqActualText = driver.findElement(this.getObject(p,expectedObject,expectedOType)).getText();
			Assert.assertEquals(aqActualText, expectedValue);
			break;
		case "SOFTASSERTEQUALS":
			String sActualText = driver.findElement(this.getObject(p,expectedObject,expectedOType)).getText();
			sAssert.assertEquals(sActualText, expectedValue);
			break;
		case "ASSERTTRUECONTAINS":
			String atActualText = driver.findElement(this.getObject(p,expectedObject,expectedOType)).getText();
			Assert.assertTrue(atActualText.contains(expectedValue));
			break;
		case "FINDELEMENT":
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,expectedObject,expectedOType)));
		        driver.findElement(this.getObject(p,expectedObject,expectedOType));
				} catch (Exception e) {
		    	throw new Exception("\n"+ expectedObject + " element is missing");}
			break;	
		case "VERIFYTRUECONTAINS":
			String vtActualText = driver.findElement(this.getObject(p,expectedObject,expectedOType)).getText();
			try {
				Assert.assertTrue(vtActualText.contains(expectedValue));
				} catch (Error e) {
					throw new Exception("Failed " +"\n" + "Actual Result: " + vtActualText + "\n" + "Expected Result: " + expectedValue);
				}
			break;
		}//end of switch
}
	
	private By getObject(Properties p, String objectName, String objectType) throws Exception{
		if(objectType.equalsIgnoreCase("XPATH")){
			return By.xpath(p.getProperty(objectName));
		}//if
		else if(objectType.equalsIgnoreCase("CLASSNAME")){
			return By.className(p.getProperty(objectName));
		}//end else if
		else if(objectType.equalsIgnoreCase("CSS")){
			return By.cssSelector(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("LINK")){
			return By.linkText(p.getProperty(objectName));
		}	
		else if(objectType.equalsIgnoreCase("PARTIALLINK")){
			return By.partialLinkText(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("ID")){
			return By.id(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("TAGNAME")){
			return By.tagName(p.getProperty(objectName));
		}
		else{
			throw new Exception("Wrong object type");
		}
	}// end of method getObject
}

