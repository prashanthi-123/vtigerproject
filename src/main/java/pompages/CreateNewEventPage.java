package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.JavaUtility;
import genericLibraries.WebDriverUtility;

public class CreateNewEventPage {

	//Declaration
	
	private String commonpath="//div[@class='calendar' and contains(@style,'block')]"+"/descendant::td[%s]";
	@FindBy(xpath = "//b[text()='Create To Do']")
	private WebElement pageHeader;
	@FindBy (name="subject")
	private WebElement subjectTF;
	@FindBy(id="jscal_trigger_date_start")
	private WebElement startDateCalenderImage;
	@FindBy(id="jscal_trigger_due_date")
	private WebElement dueDateCalenderImage;
	@FindBy(xpath = "//input[normalize-space(@value)='Save']")
	private WebElement saveButton;
	
	
	//Initialization
	public CreateNewEventPage(WebDriver driver) {
		PageFactory.initElements( driver,this);
	}
		
	//Utilization
	public String getPageHeader() {
		return pageHeader.getText();
	}
	
	public void setSubject(String subject) {
		subjectTF.sendKeys(subject);
	}
	
	public void clickonstartDate() {
		startDateCalenderImage.click();
	}
	
	public void chooserequiredDate(WebDriverUtility web,String reqDateofYear, JavaUtility jutil) {
		String[]s=reqDateofYear.split("-");
		int reqYear=Integer.parseInt(s[0]);
		int reqMonth=Integer.parseInt(s[1]);
		String reqDate=s[2];
		
		
		String actMonthYear=web.convertStringToDynamicXpath(commonpath, "@class='title'").getText();
		
		String []str=actMonthYear.split(",");
		int actYear=Integer.parseInt(str[1]);
		
		while (actYear<reqYear) {
			web.convertStringToDynamicXpath(commonpath,"text()='>>'").click();
			actMonthYear=web.convertStringToDynamicXpath(commonpath, "@class='title'").getText();
			str=actMonthYear.split(",");
			actYear=Integer.parseInt(str[1]);
		}
		
		int actMonth=jutil.convertMonthToInt(str[0]);
		while(actMonth<reqMonth) {
			web.convertStringToDynamicXpath(reqDate, "text()='>'").click();
			actMonthYear=web.convertStringToDynamicXpath(commonpath, "@class='title'").getText();
			
			str=actMonthYear.split(",");
			actMonth=jutil.convertMonthToInt(str[0]);
		}
		
		while(actYear>reqYear) {
			web.convertStringToDynamicXpath(reqDate, "text()='title'").click();
			actMonthYear=web.convertStringToDynamicXpath(commonpath, "@class='title'").getText();
			str=actMonthYear.split(",");
			actYear=Integer.parseInt(str[0]);
		}
		web.convertStringToDynamicXpath(reqDate, "text()='"+reqDate+"'").click();
	}
	
	public void clickonDueDate() {
		dueDateCalenderImage.click();
	}
	public void clicksaveButton() {
		saveButton.click();
		
	}
	
	
}
