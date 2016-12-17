package in.prolearn.pageActions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	private static WebDriver driver = null;
	
	public HomePage(WebDriver driver){
		this.driver = driver;
	}
	
	public static String getTitle(){
		return driver.getTitle();
	}
	public static void clickRegisterLink(){
		WebDriverWait ww = new WebDriverWait(driver, 50);
		WebElement regLink = ww.until(ExpectedConditions.
				presenceOfElementLocated(By.xpath("//a[contains(text(),'REGISTER')]")));
		regLink.click();
	}
	//Also find through WebElement.findElement()
	//Also find using Fluentwait
	//Also find using FindBy()
	
	@FindBy(how = How.XPATH, using="//a[contains(text(),'REGISTER')]")
	private WebElement regLink2;
	public void clickRegisterLink5(){
		regLink2.click();
	}
}
