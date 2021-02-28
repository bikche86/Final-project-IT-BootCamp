package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasicPage {

	public LoginPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
		
	}
	
	public WebElement getUserName() {
		return driver.findElement(By.name("username"));
	}
	
	public WebElement getPassword() {
		return driver.findElement(By.name("password"));
	}
	
	public WebElement getLoginBtn() {
		return driver.findElement(By.linkText("Login"));
	}
	
	public void LogIn(String username, String password) {
		this.getUserName().sendKeys(Keys.CONTROL + "a", Keys.DELETE);
		this.getPassword().sendKeys(Keys.CONTROL + "a", Keys.DELETE);
		
		this.getUserName().sendKeys(username);
		this.getPassword().sendKeys(password);
		this.getLoginBtn().click();
	}
}
