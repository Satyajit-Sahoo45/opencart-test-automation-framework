package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC004_SearchTest extends BaseClass {
	
	@Test
	public void searchTest() {
		logger.info("***** Starting TC004_SearchTest *****");
		
		try {
			HomePage hp = new HomePage(getDriver());
			
			hp.clickMyAccount();
			hp.clickLogin();
			
			logger.info("Performing Login action...");
			// Login
			LoginPage lp = new LoginPage(getDriver());
			lp.setEmail(p.getProperty("email"));
			lp.setPassword(p.getProperty("password"));
			lp.clickLogin();
			
			logger.info("Validating MyAccount Page");
			// MyAccount
			MyAccountPage ap = new MyAccountPage(getDriver());
			boolean targetPage = ap.isMyAccountPageExists();
			
			Assert.assertEquals(targetPage, true, "Login Failed!");
			
			hp.setSearchText(p.getProperty("searchProductName"));
			Thread.sleep(3000);
			
			logger.info("Passed the search value to search input...");
			
			hp.clickSearchBtn();
			logger.info("Clicked on search button...");
			Thread.sleep(3000);
			
		} catch (Exception e) {
			Assert.fail();
		}
		
		logger.info("***** Finished TC004_SearchTest *****");
	}
}
