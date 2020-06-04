package resonancia.bigbasket.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resonancia.bigbasket.customeutils.WaitUntil;
import resonancia.bigbasket.maindata.TestBase;

public class LoginPage extends TestBase {
	@CacheLookup
	@FindBy(xpath="//div[@id='headerControllerId']//a[3]")
	WebElement loginbtn;
	@CacheLookup
	@FindBy(css="a[qa=\"bbLogo\"]")
	WebElement bblogo;
	@CacheLookup
	@FindBy(css="a[class='gplusBtn smGlobalBtn']")
	WebElement gmailloginbtn;
	@CacheLookup
	@FindBy(css="div[class=\"login-main-content\"]")
	WebElement loginpanel;
	@CacheLookup
	@FindBy(xpath="//div[contains(text(),'Use another account')]")
	WebElement otheraccountbtn;
	@CacheLookup
	@FindBy(css="input[id=\"identifierId\"]")
	WebElement emailinput;
	@CacheLookup
	@FindBy(css="input[name=\"password\"]")
	WebElement passwordinput;
	@FindBy(xpath="//span[contains(text(),'Next')]")
	WebElement nextbtn;
	@CacheLookup
	@FindBy(css="span[qa=\"supportNumber\"]")
	WebElement supportnumber;
	@CacheLookup
	@FindBy(xpath="//div[@class='dropdown user-dropdown hvr-drop']//span[@class='ng-binding']")
	WebElement fname;
	@CacheLookup
	@FindBy(xpath="//div[@class='modal-content']//div[@class='ng-scope']//button[@type='button']")
	WebElement popUpCloseBtn;
	

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	
	public Boolean validateBBLogoIsVisible() {
		return bblogo.isDisplayed();
	}
	
	public void clickOnLoginButton() {
		loginbtn.click();
		WaitUntil.timeInSeconds(2);
		switchToPopupWindow();
		popUpCloseBtn.click();
		switchToMainWindow();
		loginbtn.click();
		WaitUntil.timeInSeconds(2);
		switchToPopupWindow();
	}
	
	public HomePage loginUsingGoogleAccount() {
		gmailloginbtn.click();
		switchToPopupWindow("Sign In");
		emailinput.clear();
		emailinput.sendKeys(prop.getProperty("username"));
		nextbtn.click();
		WaitUntil.timeInSeconds(2);
		passwordinput.clear();
		passwordinput.sendKeys(prop.getProperty("password"));
		nextbtn.click();
		//driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		switchToMainWindow();
		//popUpCloseBtn.click();
		WaitUntil.timeInSeconds(5);
		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		return new HomePage();
	}
	
	public HomePage loginUsingSavedGoogleAccount() {
		gmailloginbtn.click();
		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		//switchToMainWindow();
		//popUpCloseBtn.click();
		//WaitUntil.timeInSeconds(5);
		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		return new HomePage();
	}
	
	public Boolean isSupportNumberIsVisible() {
		return supportnumber.isDisplayed();
	}
	
	public String getCustomerFirstName() {
		return fname.getText();
	}
	
	
}
