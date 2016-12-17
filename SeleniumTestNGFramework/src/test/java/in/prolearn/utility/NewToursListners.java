package in.prolearn.utility;

import java.io.IOException;

import org.testng.IClass;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class NewToursListners extends TestListenerAdapter implements ISuiteListener{
	static int passcount=0;
	static int failcount=0;
	static int skipcount=0;
	
	@Override
	  public void onTestStart(ITestResult tr) {
	    System.out.println(tr.getMethod()+"..........started");
	    passcount++;
	  }
	
	
	
	@Override
	  public void onTestSuccess(ITestResult tr) {
		
	    passcount++;
	  }

	  @Override
	  public void onTestFailure(ITestResult tr) {
		  log("Test '"+tr.getName() +"' FAILED");
		  log(tr.getTestClass());
		  System.out.println(".......");
		  try{
			  BrowserSelection.getScreenShot();
		  }catch(IOException e){
			  e.printStackTrace();
		  }
		  failcount++;
	  }

	  @Override
	  public void onTestSkipped(ITestResult tr) {
		  skipcount++;
	  }
	  
	  //user define method
	  private void log(String methodname){
		  System.out.println(methodname);
	  }
	  private void log(IClass testclass){
		  System.out.println(testclass);
	  }


//Isuite lister methods

	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
	}



	public void onFinish(ISuite suite) {
		System.out.println("Total Testcase passed: "+passcount);
		System.out.println("Total Testcase passed: "+failcount);
		System.out.println("Total Testcase passed: "+skipcount);
		
	}
}
