package vtigerFinalScripts;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.IconstantPath;

public class CreateEventTest  extends BaseClass{
            @Test
	public void createEventTest()  {
	      
		    SoftAssert soft=new SoftAssert();
		     Map<String, String> map=excel.readFromExcel("EventsTestData", "Create New Event");
		     home.selectFromQuickCreate(web,map.get("Quick Create"));
		     soft.assertEquals(createEvent.getPageHeader(),"Create To Do");
		     String subject=map.get("subject")+jutil.generateRandomNum(100);
		     createEvent.setSubject(subject);
		     createEvent.clickonstartDate();
		     createEvent.chooserequiredDate(web, map.get("Start Date"), jutil);
		     createEvent.clickonDueDate();
		     createEvent.chooserequiredDate(web, map.get("Due Date"), jutil);
		     createEvent.clicksaveButton();
		     soft.assertTrue(newEventInfo.getpageHeader().contains(subject));
		     if(newEventInfo.getpageHeader().contains(subject))
		    	 excel.writeToExcel("EventsTestData", "Create New Event", "Pass", IconstantPath.EXCEL_PATH);
		     else
		    	 excel.writeToExcel("EventsTestData", "Create New Event", "Fail" ,IconstantPath.EXCEL_PATH);
		     
		    soft.assertAll();
            }
}

		     

	


