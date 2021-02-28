package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationSystemPage extends BasicPage {

	public NotificationSystemPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
		
	}
	
	public List<WebElement> getElementMessage() {
		return driver.findElements(By.xpath(
				"//*[contains(@class, 'alert--success') "
				+ "or contains(@class, 'alert--danger')][contains(@style,'display: block')]"));
	}
	
	public String getMessage() {
		return ((WebElement) this.getElementMessage()).getText();
	}
	
	public void Wait() {
		this.wait.until(ExpectedConditions.attributeToBe(By.xpath("//*[contains(@class, 'system_message')]"),
				"style",	"display: none;"));
	}

}
