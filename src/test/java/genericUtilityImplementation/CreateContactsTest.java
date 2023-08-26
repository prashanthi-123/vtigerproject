package genericUtilityImplementation;


import java.util.Map;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import genericLibraries.ExcelUtility;
import genericLibraries.IconstantPath;
import genericLibraries.JavaUtility;
import genericLibraries.PropertiesUtility;
import genericLibraries.WebDriverUtility;

public class CreateContactsTest {

	public static void main(String[] args) throws InterruptedException {
		PropertiesUtility property=new PropertiesUtility();
		ExcelUtility excel=new ExcelUtility();
		JavaUtility jutil=new JavaUtility();
		WebDriverUtility webutil=new WebDriverUtility();
		
		property.propertiesInitilalization(IconstantPath.PROPERTIES_PATH);
		excel.excelInitialization(IconstantPath.EXCEL_PATH);
		
		
		//WebDriver driver=new ChromeDriver();
		//driver.manage().window().maximize();
		//driver.get("http://localhost:8888/");
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebDriver driver=webutil.launchBrowser(property.readFromProperties("browser"));
		webutil.maximizeBrowser();
		webutil.navigateToApp(property.readFromProperties("url"));
		webutil.waitTillElementFound(Long.parseLong(property.readFromProperties("time")));
		
		if(driver.getTitle().contains("vtiger"))
			System.out.println("login page is displayed");
		else
			System.out.println("login page is not displayed");
		
		
		driver.findElement(By.name("user_name")).sendKeys(property.readFromProperties("username"));
		driver.findElement(By.name("user_password")).sendKeys(property.readFromProperties("password"));
		driver.findElement(By.id("submitButton")).submit();
		if(driver.getTitle().contains("Home"))
			System.out.println("home page is displayed");
		else
			System.out.println("home page is not displayed");
		
		
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		if(driver.getTitle().contains("Contacts"))
			System.out.println(" contacts page is displayed");
		else
			System.out.println("contacts page is displayed");
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		WebElement createcon=driver.findElement(By.xpath("//span[@class='lvtHeaderText']")); 
		if(createcon.getText().equals("Creating New Contact"))
			System.out.println("create new contacts page is displayed");
		else
			System.out.println("create new contacts page not is displayed");
		
		//Random random=new Random();
		//int randomNum=random.nextInt(100);
		Map<String,String> map=excel.readFromExcel("ContactTestData", "Create Contact");
		 String contactName=map.get("Last Name")+jutil.generateRandomNum(100);
		driver.findElement(By.name("lastname")).sendKeys(contactName);
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(contactName);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[normalize-space(@value)='Save']")).click();
		String newconInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(newconInfo.contains(contactName)) {
			System.out.println("contact is created succesfully");
		excel.writeToExcel("ContactTestData", "Create Contact", "Pass", IconstantPath.EXCEL_PATH);
		}
		else {
			System.out.println("contact is not created succesfully");
		excel.writeToExcel("ContactTestData", "Create Contact", "Fail", IconstantPath.EXCEL_PATH);
	}
		
		
		WebElement adminIcon=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		//Actions a=new Actions(driver);
		//a.moveToElement(adminIcon).perform();
		webutil.mouseHover(adminIcon);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
		//driver.quit();
		webutil.quitAllWindows();
		excel.closeExcel();
		
		
	}

}
