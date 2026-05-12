package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
//	initialize
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
//	Declare
	@FindBy(xpath = "//span[normalize-space() = 'My Account']")
	private WebElement lnkMyAccount;
	
	@FindBy(xpath = "//a[normalize-space() = 'Register']")
	private WebElement lnkRegister;
	
	@FindBy(linkText = "Login")
	private WebElement linkLogin;
	
	@FindBy(name = "search")
	private WebElement searchInputTxt;
	
	@FindBy(xpath = "//input[@name='search']/following-sibling::span//button[@type='button']")
	private WebElement searchBtn;
	
//	utilize
	public void clickMyAccount() {
		lnkMyAccount.click();
	}
	
	public void clickRegister() {
		lnkRegister.click();
	}
	
	public void clickLogin() {
		linkLogin.click();
	}
	
	public void setSearchText(String value) {
		searchInputTxt.sendKeys(value);
	}
	
	public void clickSearchBtn() {
		searchBtn.click();
	}

}
