package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthPage extends BasicPage {

	public AuthPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
		
	}
	
	public WebElement getUser() {
		return driver.findElement(By.xpath("//*[@id='header']/div[2]/div/div[2]/div[2]/ul/li/a"));
	}

	public WebElement getMyAccount() {
		return this.driver.findElement(By.className("after-arrow"));
	}
	
	public WebElement getLogout() {
		return this.driver.findElement(By.linkText("Logout"));
	}
	
	public void Logout() {
		this.getUser().click();
		this.getLogout().click();
	}
}
