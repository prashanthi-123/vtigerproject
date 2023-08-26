package pom;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pompages.LoginPage;

public class VtigerLoginTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:8888/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //Create an object because it is a non-static method
         LoginPage login=new LoginPage(driver);
         login.loginToApp("admin", "prashu@123");
      
      if(driver.getTitle().contains("vtiger"))
    	  System.out.println("Login passed");
      else
    	  System.out.println("Login Failed");
      
      driver.quit();
	}

}
