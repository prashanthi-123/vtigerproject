package hardcodedtest;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
//contact created 
public class CreateContactwithexistingorganizationTest {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		if(driver.getTitle().contains("vtiger"))
			System.out.println("login page is displayed");
		else
			System.out.println("login page is not displayed");
		
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("prashu@123");
		driver.findElement(By.id("submitButton")).submit();
		if(driver.getTitle().contains("Home"))
			System.out.println("home page is displayed");
		else
			System.out.println("home page is not displayed");
		
		
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		if(driver.getTitle().contains("Contacts"))
			System.out.println(" contacts page is displayed");
		else
			System.out.println("contacts page is not displayed");
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		WebElement createcon=driver.findElement(By.xpath("//span[@class='lvtHeaderText']")); 
		if(createcon.getText().equals("Creating New Contact"))
			System.out.println("create new contacts page is displayed");
		else
			System.out.println("create new contacts page not is displayed");
		Random random=new Random();
		int randomNum=random.nextInt(100);
		String conName="prash"+randomNum;
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(conName);
		Thread.sleep(1000);
driver.findElement(By.xpath("//img[contains(@onclick,'Accounts&action')]")).click();
		
		String parentID = driver.getWindowHandle();
		Set<String> ids = driver.getWindowHandles();
		for(String s : ids) {
			driver.switchTo().window(s);
		}
		
		List<WebElement> orgList = driver.findElements(By.xpath("//div[@id='ListViewContents']/descendant::table[@cellspacing='1']/descendant::tr/td[1]/a"));
		for(int i=1; i < orgList.size();i++)
		{
			if(orgList.get(i).getText().equals("TCS81"))
			{
				System.out.println(orgList.get(i).getText());
			    orgList.get(i).click();
			    break;
			}
		}
		
		driver.switchTo().window(parentID);
        driver.findElement(By.xpath("//input[normalize-space(@value)='Save']")).click();
		
		String newContactInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(newContactInfo.contains(conName))
			System.out.println("Contact Created Successfully");
		else
			System.out.println("Contact Not Created");
		
		WebElement adminIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a = new Actions(driver);
		a.moveToElement(adminIcon).perform();
		
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
		driver.quit();
		
		
		
		
		
		
		
		
		
		
	}

}
