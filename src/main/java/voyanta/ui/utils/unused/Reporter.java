package voyanta.ui.utils.unused;





import org.junit.Assert;

import java.util.NoSuchElementException;

public class Reporter {


	
//	Reporter Reporter=new Reporter();
	
	
	public static void ReportInfo(String message)
	{
		System.out.println("Action happening "+message);
		
	}
	
	public static void ReportAssertCondition(boolean condition)
	{
		System.out.println("Verifying the condition");
		Assert.assertTrue(condition);
	}
	
	
	public static void ReportAssertEquals(String expected, String actual)
	{
		System.out.println("Verifying the expected "+expected+" actual "+actual);
		Assert.assertEquals(expected, actual);
	}
	public void ReportVerifyCondition(boolean condition)
	{
		System.out.println("Verifying the condition");
		try
		{
			Assert.assertTrue(condition);
		}
		catch(AssertionError e)
		{
			System.out.println("Verify Failure");
		}

		
	}
	public void ReportVerifyEquals(String expected, String actual)
	{
		System.out.println("Verifying the expected "+expected+" actual "+actual);
		
		try
		{
			Assert.assertEquals(expected, actual);
			
		}		
		catch(NoSuchElementException e)
		{
			System.out.println("Element not present");
		} 
		
	}
}
