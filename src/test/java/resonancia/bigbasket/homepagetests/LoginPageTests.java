package resonancia.bigbasket.homepagetests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import resonancia.bigbasket.maindata.TestBase;
import resonancia.bigbasket.pages.LoginPage;

public class LoginPageTests extends TestBase {

	LoginPage loginpage;

	public LoginPageTests() {
		super();
	}

	@BeforeClass
	public void setUp() throws Exception {
		initBrowser();
		loginpage = new LoginPage();
	}

	@Test
	public void validateLoginPageLogoIsVisible() {
		Assert.assertTrue(loginpage.validateBBLogoIsVisible(), "Logo is not Visble");
	}

	@Test
	public void loginUsingGoogleAccount() {
		loginpage.clickOnLoginButton();
		//loginpage.loginUsingGoogleAccount();
		loginpage.loginUsingSavedGoogleAccount();
		Assert.assertEquals(loginpage.getCustomerFirstName(), prop.getProperty("fname"));
	}

	@Test
	public void validateSupportNumberIsDisplayed() {
		Assert.assertTrue(loginpage.isSupportNumberIsVisible(), "Support number is not visible");
	}

	//@AfterClass
	public void clearUp() {
		driver.quit();
	}

}
