package Tests;

import org.testng.annotations.BeforeClass;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import Pages.AuthPage;
import Pages.CartSummaryPage;
import Pages.LocationPopupPage;
import Pages.LoginPage;
import Pages.MealPage;
import Pages.NotificationSystemPage;
import Pages.ProfilePage;

public abstract class BasicTest {
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected JavascriptExecutor js;
	protected LocationPopupPage locationPopupPage;
	protected LoginPage loginPage;
	protected NotificationSystemPage notificationSistemPage;
	protected ProfilePage profilePage;
	protected AuthPage authPage;
	protected MealPage mealPage;
	protected CartSummaryPage cartSummaryPage;
	protected String BaseUrl;
	protected String username;
	protected String password;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");

		this.driver = new ChromeDriver();
		this.wait = new WebDriverWait(driver, 30);
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		this.js = (JavascriptExecutor) driver;
		
		this.locationPopupPage = new LocationPopupPage(driver, wait, js);
		this.loginPage = new LoginPage(driver, wait, js);
		this.notificationSistemPage = new NotificationSystemPage(driver, wait, js);
		this.profilePage = new ProfilePage(driver, wait, js);
		this.authPage = new AuthPage(driver, wait, js);
		this.mealPage = new MealPage(driver, wait, js);
		this.cartSummaryPage = new CartSummaryPage(driver, wait, js);
		
		this.BaseUrl = "http://demo.yo-meals.com";
		this.username = "customer@dummyid.com";
		this.password = "12345678a";
	}
	
	@AfterMethod
	public void afterTest(ITestResult testResult) throws IOException {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	
		}
		driver.manage().deleteAllCookies();
	}
	
	@AfterClass
	public void quit() {
		this.driver.quit();
}
}
