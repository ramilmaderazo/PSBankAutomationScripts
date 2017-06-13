package psbankTestCases;

import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import psbankActions.PSBankActionKeywords;
import psbankActions.PSBankExpectedResult;
import psbankActions.PSBankObjects;
import psbankExcelTestCase.PSBankExcelTestCases;

public class PSBankGenericScript {
	public static void ExecuteScript(String wb, String excelSheet, WebDriver webdriver, SoftAssert sAssert) throws Exception{
		PSBankExcelTestCases file = new  PSBankExcelTestCases();
		PSBankObjects object = new PSBankObjects();
		Properties allObjects = object.getObjectRepository();
		PSBankActionKeywords action = new PSBankActionKeywords(webdriver);
		PSBankExpectedResult expectedOperation = new PSBankExpectedResult(webdriver);
		//Read keyword sheet
		Sheet sheet = file.readExcel(System.getProperty("user.dir")+"\\", wb ,excelSheet);
		//Find number of rows in excel file
		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
		//Loop to read excel file rows
		for(int i = 1; i < rowCount+1; i++){
			//loop over all the rows s
			Row row = sheet.getRow(i);
				//Check if the first cell contain a value, if yes, that means it is a new test case name
				if(row.getCell(0).toString().length()==0){
					//Print test case detail on console
					System.out.println(row.getCell(1).toString()+"---"+row.getCell(2).toString()+"---"+row.getCell(3).toString()+"---"+row.getCell(4).toString());
					//Call perform function to perform operation on UI
					action.performKeywords(allObjects, row.getCell(1).toString(),row.getCell(2).toString(),row.getCell(3).toString(), row.getCell(4).toString(), row.getCell(5).toString());
					//Check if test step has expected result
					if(row.getCell(5).toString().length()!=0){
						expectedOperation.VerifyExpectedResult(sAssert, webdriver, allObjects, row.getCell(5).toString(), row.getCell(6).toString(), row.getCell(7).toString(), row.getCell(8).toString());
					}
				}//end if
				else{
					//Print the new test case name when it started
					System.out.println("New Test Case -> "+row.getCell(0).toString()+" started");
				}//end of else
		}//end of for
	}//end of method Execute
}
