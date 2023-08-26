package vtigerFinalScripts;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.IconstantPath;

public class CreateOrgWithTypeAndIndustryTest extends BaseClass{
	
         @Test
	 public void createOrgWithTypeAndIndustryTest() {
    	 SoftAssert soft=new SoftAssert();
    	 home.clickOrganizatons();
    	 soft.assertTrue(driver.getTitle().contains("orgaizations"));
    	 org.clickPlusButton();
    	 soft.assertTrue(createOrg.getpageHeader().equals("Creating New Organization"));
    	 Map<String, String> map=excel.readFromExcel("OrganizationTestData", "Create Organization");
    	 String orgName=map.get("Organization Name")+jutil.generateRandomNum(100);
    	 createOrg.setorgName(orgName);
    	 createOrg.selectIndustry(web, map.get("Industry"));
    	 createOrg.selectType(web, map.get("Type"));
    	 createOrg.clickSaveButton();
    	 
    	 soft.assertTrue(newOrgInfo.getpageHeader().contains(orgName));
    	 if(newOrgInfo.getpageHeader().contains(orgName))
    		 excel.writeToExcel("OrganizationTestData", "Create Organization with Industry And Type", "pass", IconstantPath.EXCEL_PATH);
    	 else
    		 excel.writeToExcel("OrganizationTestData", "Create Organization with Industry And Type", "Fail",IconstantPath.EXCEL_PATH);
    	 soft.assertAll();
    	 

}
}
