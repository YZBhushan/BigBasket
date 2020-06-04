package resonancia.bigbasket.maindata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import resonancia.bigbasket.customeutils.WebEventListner;

public class TestBase {
	public static WebDriver driver;

	public static Properties prop;
	public static String parentwindowhandle;
	public static Set<String> allindowhandles;
	public static EventFiringWebDriver edriver;
	public static WebEventListner eventlistener;
	public Logger log = Logger.getLogger(this.getClass());

	public TestBase() {
		prop = new Properties();
		String basepath = System.getProperty("user.dir");
		try {
			FileInputStream fis = new FileInputStream(basepath + "\\src\\main\\resources\\config.properties");
			prop.load(fis);
			log.info("Configuration file loaded");
		} catch (FileNotFoundException e) {
			System.out.println("object repository not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("file not found");
			e.printStackTrace();
		}

	}

	public void initBrowser() {
		String browsername = prop.getProperty("browser");
		switch (browsername) {
		case "Chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("user-data-dir=C://Users//nikks//AppData//Local//Google//Chrome//User Data//Default");
			driver = new ChromeDriver(options);
			break;
		case "Firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "IE":
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			break;
		case "HtmlUnit":
			driver = new HtmlUnitDriver();
			break;
		default:
			System.err.println("Invalid Browser");
			break;
		}

		log.info(browsername + " launched");
		driver.get(prop.getProperty("url"));
		log.info("Url " + prop.getProperty("url") + " opened");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		ChromeOptions options = new ChromeOptions();
		options.addArguments("user-data-dir=C:/Users/mention user name/AppData/Local/Google/Chrome/User Data/Default");
		edriver = new EventFiringWebDriver(driver);
		eventlistener = new WebEventListner();
		edriver.register(eventlistener);
		driver = edriver;

	}

	public void switchToPopupWindow() {
		parentwindowhandle = driver.getWindowHandle();
		allindowhandles = driver.getWindowHandles();
		for (String window : allindowhandles) {
			if (!driver.switchTo().window(window).getWindowHandle().equals(parentwindowhandle)) {
				log.info("Switched to " + driver.getTitle() + " window");
				break;
			}
		}
	}

	public void switchToPopupWindow(String windowtitle) {
		parentwindowhandle = driver.getWindowHandle();
		allindowhandles = driver.getWindowHandles();
		for (String window : allindowhandles) {
			if (driver.switchTo().window(window).getTitle().contains(windowtitle)) {
				log.info("Switched to " + driver.getTitle() + " window");
				break;
			}
		}
	}

	public void switchToMainWindow() {
		driver.switchTo().window(parentwindowhandle);
		log.info("Switching to main window");
	}

	public static void main(String[] args) {
		TestBase tb = new TestBase();
		tb.initBrowser();
	}

}
