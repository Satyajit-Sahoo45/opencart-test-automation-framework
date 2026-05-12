package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends BasePage {
	
	// initialize
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	// Declare
	@FindBy(id = "input-firstname")
	private WebElement txtFirstName;
	
	@FindBy(id = "input-lastname")
	private WebElement txtLastName;
	
	@FindBy(id = "input-email")
	private WebElement txtEmail;
	
	@FindBy(id = "input-telephone")
	private WebElement txtTelephone;
	
	@FindBy(id = "input-password")
	private WebElement txtPassword;
	
	@FindBy(id = "input-confirm")
	private WebElement txtConfirmPassword;
	
	@FindBy(xpath = "//input[@name = 'agree']")
	private WebElement checkPolicy;
	
	@FindBy(xpath = "//input[@value = 'Continue']")
	private WebElement continueBtn;
	
	@FindBy(xpath = "//h1[normalize-space() = 'Your Account Has Been Created!']")
	private WebElement confirmationMsg;
	
	public void setFirstName(String first_name) {
		txtFirstName.sendKeys(first_name);
	}
	
	public void setLastName(String last_name) {
		txtLastName.sendKeys(last_name);
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setTelephone(String tel) {
		txtTelephone.sendKeys(tel);
	}
	
	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void setConfirmPassword(String password) {
		txtConfirmPassword.sendKeys(password);
	}
	
	public void 	setPrivacyPolicy() {
		checkPolicy.click();
	}
	
	public void clickContinue() {
//		solution-1
		continueBtn.click();
		
//		solution-2
//		continueBtn.submit();
		
//		solution-3
//		Actions act = new Actions(driver);
//		act.moveToElement(continueBtn).click().perform();
		
//		solution-4
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].click();", continueBtn);
		
//		solution-5
//		continueBtn.sendKeys(Keys.RETURN);
		
//		solution-6
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
	}
	
	public String getConfirmationMsg() {
		try {
			return confirmationMsg.getText();
		} catch (Exception e) {
			return e.getLocalizedMessage();
		}
	}
	
}
