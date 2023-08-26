package vtigerFinalScripts;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.IconstantPath;

public class DeleteLeadTest extends BaseClass {
	            
	@Test
	public void deleteLeadTest() {
		SoftAssert soft=new SoftAssert();
		home.clickLeads();
		soft.assertTrue(driver.getTitle().contains("Leads"));
		Map<String, String> map=excel.readFromExcel("LeadsTestData", "Delete lead");
		lead.traverseToRequiredLead(web, map.get("Lead Name"));
		lead.clickDelete();
		
		web.handleAlert("ok");
		List<WebElement> leadNameList=lead.getLeadsNamesList();
		boolean status=false;
		for(WebElement leads:leadNameList) {
			if(!(leads.getText().equals("null79"))) {
				status=true;
			}
		}
		soft.assertTrue(status);
		if(status) 
			excel.writeToExcel("LeadsTestData", "Delete lead", "Pass", IconstantPath.EXCEL_PATH);
		else
			excel.writeToExcel("LeadsTestData", "Delete lead", "Fail", IconstantPath.EXCEL_PATH);
	
		soft.assertAll();
		
		
	}
          
}
