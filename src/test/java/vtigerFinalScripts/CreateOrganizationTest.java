package vtigerFinalScripts;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.IconstantPath;

public class CreateOrganizationTest extends BaseClass{
            
	     @Test
	     public void createorgTest() {
	    	 SoftAssert soft=new SoftAssert();
	    	 home.clickOrganizatons();
	    	 soft.assertTrue(driver.getTitle().contains("Orgaizations"));
	    	 org.clickPlusButton();
	    	 soft.assertTrue(createOrg.getpageHeader().equals("Creating New Organization"));
	    	 Map<String, String> map=excel.readFromExcel("OrganizationTestData", "Create Organization");
	    	 String orgName=map.get("Organization Name")+jutil.generateRandomNum(100);
	    	 createOrg.setorgName(orgName);
	    	 createOrg.clickSaveButton();
	    	 
	    	 soft.assertTrue(newOrgInfo.getpageHeader().contains(orgName));
	    	 if(newOrgInfo.getpageHeader().contains(orgName))
	    		 excel.writeToExcel("OrganizationTestData", "Create Organization", "Pass", IconstantPath.EXCEL_PATH);
	    	 else
	    		 excel.writeToExcel("OrganizationTestData", "Create Organization", "Fail",IconstantPath.EXCEL_PATH);
	    	//soft.assertAll();
	    	 
	    	 
	     }
}
