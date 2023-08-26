package vtigerFinalScripts;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.IconstantPath;

public class CreateAndDuplicateLeadTest extends BaseClass {
	     
	@Test
	public void createAndDuplicateLeadTest() {
		SoftAssert soft=new SoftAssert();
   	    home.clickLeads();
   	    soft.assertTrue(driver.getTitle().contains("Leads"));
   	    lead.clickPlusButton();
   	 soft.assertEquals(createLead.getpageHeader(),"Creating New Lead");
   	 Map<String, String> map=excel.readFromExcel("LeadsTestData", "Create and Duplicate Lead");
   	 String lastName=map.get("Last Name")+jutil.generateRandomNum(100);
   	 createLead.setLastName(lastName);
   	 createLead.setCompanyName(map.get("Company"));
   	 createLead.clickSaveButton();
    	soft.assertTrue(newLeadInfo.getpageHeader().contains(lastName));
    	newLeadInfo.clickDuplicateButton();
    	soft.assertTrue(duplicatingLead.getpageHeader().contains(lastName));
    	String newLastName=map.get("New Last Name")+jutil.generateRandomNum(100);
    	duplicatingLead.setLastName(newLastName);
    	duplicatingLead.clicksaveButton();
   	if(newLeadInfo.getpageHeader().contains(newLastName))
   		 excel.writeToExcel("LeadsTestData", "Create and Duplicate Lead", "Pass", IconstantPath.EXCEL_PATH);
   	 else
   		 excel.writeToExcel("LeadsTestData", "Create and Duplicate Lead","Fail" ,IconstantPath.EXCEL_PATH);
   	    soft.assertAll();
	}
         
}
