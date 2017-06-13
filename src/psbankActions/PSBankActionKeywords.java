package psbankActions;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PSBankActionKeywords {
	WebDriver driver;
	public PSBankActionKeywords(WebDriver driver){
		this.driver  = driver;	
	} 
	
	public void performKeywords(Properties p, String operation, String objectName, String objectType, String strvalue, String expectedResult) throws Exception{
		System.out.println("");
		Actions action = new Actions(driver);
		WebDriverWait  wait = new WebDriverWait(driver, 60);
		Robot robot = new Robot();
		switch (operation.toUpperCase()){
		case "GOTOURL":
			driver.get(p.getProperty(strvalue));
			break;
		case "MAXIMIZE":
			driver.manage().window().maximize();
			break;
		case "HOVER":
			wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectName,objectType)));
			WebElement element = driver.findElement(this.getObject(p,objectName,objectType));
			action.moveToElement(element).build().perform();
			break;	
		case "CLICK":		
			wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectName,objectType)));
			driver.findElement(this.getObject(p,objectName,objectType)).click();
			break;
		case "CLICKANDWAIT":
			wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectName,objectType)));
			driver.findElement(this.getObject(p,objectName,objectType)).click();
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			Thread.sleep(10000);
			break;
		case "SETTEXT":
			wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectName,objectType)));
			WebElement inputText = driver.findElement(this.getObject(p,objectName,objectType));
			((JavascriptExecutor)driver).executeScript("arguments[0].value= '" + strvalue + "';", inputText);
			break;
		case "APPEND":
			driver.findElement(this.getObject(p,objectName,objectType)).sendKeys(strvalue);
			break;
		case "SELECT":
		    driver.findElement(this.getObject(p, objectName, objectType)).findElement(By.xpath("//option[contains(text(),'"+ strvalue +"')]")).click();
			break;
		case "DATEPICKER":   
			wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectName,objectType)));
			WebElement fieldDate = driver.findElement(this.getObject(p,objectName,objectType)); 
			((JavascriptExecutor)driver).executeScript("arguments[0].value= '" + strvalue + "';", fieldDate);
			 break;
		case "GETTEXT":
			wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectName,objectType)));
			driver.findElement(this.getObject(p,objectName,objectType)).getText();
			break;
		case "CLEAR":
			wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectName,objectType)));
			driver.findElement(this.getObject(p,objectName,objectType)).clear();
			break;
		case "CLOSEMODAL":
			WebElement webElement = driver.findElement(this.getObject(p,objectName,objectType));
			webElement.sendKeys(Keys.TAB);
			webElement.sendKeys(Keys.ENTER);
			break;
		case "SWITCHTOMODAL": //for modals
			driver.switchTo().activeElement();
			break;
		case "SWITCHTODEFAULT": //go back to main window
			driver.switchTo().defaultContent();
			break;
		case "SWITCHTOWINDOW": //for new window
			for (String handle : driver.getWindowHandles()) {	 
			    driver.switchTo().window(handle);}
			break;
		case "SWITCHTOFRAME": //for iframes
			driver.switchTo().frame(strvalue);
			break;
		case "SCROLLDOWN":
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,250)", "");
			break;
		case "WAIT":
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			Thread.sleep(3000);
			break;
		case "VALIDATE":
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			Thread.sleep(1000);
			break;
		case "SWITCHTOPRNTSCRN":
			driver.switchTo().activeElement();
			robot.delay(10000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.delay(5000);
			robot.keyPress(KeyEvent.VK_P);
			robot.keyPress(KeyEvent.VK_R);
			robot.keyPress(KeyEvent.VK_I);
            robot.keyPress(KeyEvent.VK_N);
            robot.keyPress(KeyEvent.VK_T);
            robot.delay(5000);
            robot.keyPress(KeyEvent.VK_ENTER);
			break;
		case "ESCAPEPRNTSCRN":
			driver.switchTo().activeElement();
            robot.delay(5000);
            robot.keyPress(KeyEvent.VK_ESCAPE);
			break;
		case "TAB":
			driver.switchTo().activeElement();
			robot.delay(3000);
			robot.keyPress(KeyEvent.VK_TAB);
			break;
		case "REFRESH":
			driver.navigate().refresh();
			break;
		case "ALERT SUCCESS":
			try{
			Alert alert = driver.switchTo().alert();		
	        String alertMessage = driver.switchTo().alert().getText();		
	        System.out.println(alertMessage+" OK");			
	        alert.accept();	
			ExpectedConditions.visibilityOfElementLocated(By.className(".notifHandler.successMessage"));
			}catch(Exception e){
				throw new Exception("\n"+"Test Step: "+ operation + " " + objectName + "\n" + "Expected Result: " + expectedResult + "\n" + "Test Result : FAIL");
				}
			break;	
		case "CLOSEALL":
			driver.close();
			break;
		default:
			break;
		}
	}//end of method performKeywords
	
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
}//end of class actionKeywords