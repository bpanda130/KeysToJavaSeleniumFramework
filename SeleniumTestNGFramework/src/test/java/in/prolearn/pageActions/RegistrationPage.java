package in.prolearn.pageActions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
	public WebDriver driver = null;
	
	public RegistrationPage(WebDriver driver){
		this.driver = driver;
	}
	
	public void setFirstName(String fn){
		driver.findElement(By.name("firstname")).sendKeys(fn);
	}
	public void setLastName(String fn){
		driver.findElement(By.name("lastname")).sendKeys(fn);
	}

}
