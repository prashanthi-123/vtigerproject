package vtigerFinalScripts;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.IconstantPath;

public class CreateLeadTest extends BaseClass {
           
	@Test
	public void createLeadTest() {
		SoftAssert soft=new SoftAssert();
   	    home.clickLeads();
   	    soft.assertTrue(driver.getTitle().contains("Leads"));
   	    lead.clickPlusButton();
   	 soft.assertEquals(createLead.getpageHeader(),"Creating New Lead");
   	 Map<String, String> map=excel.readFromExcel("LeadsTestData", "Create lead");
   	 String lastName=map.get("Last Name")+jutil.generateRandomNum(100);
   	 excel.writeToExcel("LeadsTestData", 11, 3, lastName,IconstantPath.EXCEL_PATH);
   	
   	 createLead.setLastName(lastName);
   	 createLead.setCompanyName(map.get("Company"));
   	 createLead.clickSaveButton();
   	soft.assertTrue(newLeadInfo.getpageHeader().contains(lastName));
   	if(newLeadInfo.getpageHeader().contains(lastName))
   		 excel.writeToExcel("LeadsTestData","Create lead","Pass",IconstantPath.EXCEL_PATH);
   	 else
   		 excel.writeToExcel("LeadsTestData","Create lead","Fail",IconstantPath.EXCEL_PATH);
   	    soft.assertAll();
	}
}
