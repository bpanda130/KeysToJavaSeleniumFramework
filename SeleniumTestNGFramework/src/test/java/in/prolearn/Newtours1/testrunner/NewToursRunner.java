package in.prolearn.Newtours1.testrunner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import in.prolearn.pageActions.HomePage;
import in.prolearn.pageActions.RegistrationPage;
import in.prolearn.utility.BrowserSelection;
import in.prolearn.utility.ExcelReader;
import in.prolearn.utility.NewToursListners;

@Listeners(NewToursListners.class)//linking the listner class to testcase.

public class NewToursRunner {
	
	WebDriver driver = null;
	HomePage hp = null;
	RegistrationPage rp = null;
	
	@Parameters({"bn", "appurl"})
	@BeforeTest
	public void init(@Optional("ff")String browsername, 
			@Optional("http://newtours.demoaut.com/")String url){
		driver = BrowserSelection.getWebBrowser(browsername);
		BrowserSelection.openURL(url);
		hp = PageFactory.initElements(driver, HomePage.class);
		rp = PageFactory.initElements(driver, RegistrationPage.class);
	}
	
	@Test(dataProvider="NewTours", dataProviderClass=ExcelReader.class)
	public void VerifyHomePage(String pageTitle, String rowNum) throws Throwable{
		String testresult = "fail";
		String errormesg = "";
		try{
			String actPageTitle = HomePage.getTitle();
			System.out.println(actPageTitle);
			System.out.println(pageTitle);
			System.out.println(rowNum);
			
			Assert.assertEquals(actPageTitle, pageTitle);
			Assert.assertTrue(actPageTitle.equals(pageTitle));
			HomePage.clickRegisterLink();
			testresult = "pass";
		}catch(Throwable e){
			errormesg = e.getMessage();
			throw e;
		}//Added Throwable class to handle Assert functionality.
		finally{
			ExcelReader.WriteExcel(testresult, Integer.parseInt(rowNum), 16);
		}
	}
	
	@Test(dataProvider="NewTours", dataProviderClass=ExcelReader.class)
	public void verifyRegisterPage(String data1, String data2, String data3, String data4,
			String data5, String data6, String data7, String data8,
			String data9, String data10, String data11, String data12, String data13, String data14){
		// TODO call all the action from RegistrationPage.java
	}
}
