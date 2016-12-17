package in.prolearn.utility;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class BrowserSelection {
	private static WebDriver driver =null;
	
	public static WebDriver getGridWebBrowser(String browsername) throws MalformedURLException{
		
		if(browsername.equalsIgnoreCase("Chrome")){
			ChromeOptions options = new ChromeOptions();
			options.addArguments("user-data-dir=C:\\Users\\Bishnu\\AppData\\Local\\Google\\Chrome\\User Data\\Default");
			options.addArguments("--start-maximized");
			
			DesiredCapabilities dc = DesiredCapabilities.chrome();
			dc.setBrowserName("ch");
			dc.setPlatform(Platform.WINDOWS);
			
			//will execute in ur local machine.
			//driver = new RemoteWebDriver(dc);
			
			driver = new RemoteWebDriver(new URL("http://localhost:5567/wd/hub"), dc);
			
		}
		else{
			ProfilesIni profile = new ProfilesIni();
			FirefoxProfile ffProfile = profile.getProfile("BishnuSeleniumProfile");
			
			//code to setup driver for Grid execution
			DesiredCapabilities dc = DesiredCapabilities.firefox();
			dc.setBrowserName("ff");
			dc.setPlatform(Platform.XP);
			
			//Will execute in mentioned client location.
			driver = new RemoteWebDriver(new URL("http://localhost:5566/wd/hub"), dc);
		}
		
		return driver;
	}
	
	public static WebDriver getWebBrowser(String browsername){
		
		if(browsername.equalsIgnoreCase("Chrome")){
			ChromeOptions options = new ChromeOptions();
			options.addArguments("user-data-dir=C:\\Users\\Bishnu\\AppData\\Local\\Google\\Chrome\\User Data\\Default");
			options.addArguments("--start-maximized");
			driver = new ChromeDriver(options);
		}
		else{
			ProfilesIni profile = new ProfilesIni();
			FirefoxProfile ffProfile = profile.getProfile("BishnuSeleniumProfile");
			driver = new FirefoxDriver(ffProfile);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	public static void openURL(String URL){
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	public static String getCurrentDate(){
		DateFormat dateFormat = new SimpleDateFormat("MM_dd_YYYY_hh_mm_ss");
		Date date = new Date();
		String sDate = dateFormat.format(date);
		return sDate;
	}
	
	public static void getScreenShot() throws IOException{
		String destination  = "C:\\Temp\\"+getCurrentDate()+" "
				+driver.getTitle().replace(":", "_").replace("\\", "_")+".png";
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(destination));
	}
}
