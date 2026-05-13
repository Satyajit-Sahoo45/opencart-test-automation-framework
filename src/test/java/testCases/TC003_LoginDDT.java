package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*
 
  Data is valid - login success - test pass - logout
  Data is valid - login failed - test fail
  
  Data is invalid - login success - test fail - logout
  Data is invalid - login failed - test pass
  
 */

public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "Datadriven") // getting data provider from different class
	public void verify_loginDDT(String email, String password, String expectedResult) throws InterruptedException {
		
		try {
				
			logger.info("***** Starting TC003_LoginDDT *****");
			
			//HomePage
			HomePage hp = new HomePage(getDriver());
			hp.clickMyAccount();
			hp.clickLogin();
			
			//Login
			LoginPage lp = new LoginPage(getDriver());
			lp.setEmail(email);
			lp.setPassword(password);
			lp.clickLogin();
			
			//MyAccount
			MyAccountPage map = new MyAccountPage(getDriver());
			boolean targetPage = map.isMyAccountPageExists();
			
			 /*
			  		Data is valid - login success - test pass - logout
	  				Data is valid - login failed - test fail
			 */
			if(expectedResult.equalsIgnoreCase("Valid")) {
				if(targetPage == true) {
					map.clickLogout();
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}
			}
			
			
			 /*
			 		Data is invalid - login success - test fail - logout
	  				Data is invalid - login failed - test pass
			 */
			if(expectedResult.equalsIgnoreCase("Invalid")) {
				if(targetPage == true) {
					map.clickLogout();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}
			
		} catch(Exception e) {
			Assert.fail();
		}
 		
		Thread.sleep(3000);
		logger.info("***** Finished TC003_LoginDDT *****");
		
	}
	
}
