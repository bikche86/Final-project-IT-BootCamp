package Pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage {

	public ProfilePage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
		
	}
	
	public WebElement getFirstName() {
		return driver.findElement(By.name("user_first_name"));
	}
	
	public WebElement getLastName() {
		return driver.findElement(By.name("user_last_name"));
	}
	
	public WebElement getAddress() {
		return driver.findElement(By.name("user_address"));
	}
	
	public WebElement getPhoneNo() {
		return driver.findElement(By.name("user_phone"));
	}
	
	public WebElement getZipCode() {
		return driver.findElement(By.name("user_zip"));
	}
	
	public void getCountry(String country) throws InterruptedException {
		WebElement countryElement = driver.findElement(By.id("user_country_id"));
		Select countrySelect = new Select(countryElement);
		countrySelect.selectByVisibleText(country);
		Thread.sleep(5000);
	}
	
	public void getState(String state) throws InterruptedException {
		WebElement stateElement = driver.findElement(By.id("user_state_id"));
		Select stateSelect = new Select(stateElement);
		stateSelect.selectByVisibleText(state);
		Thread.sleep(5000);
	}
	
	public void getCity(String city) throws InterruptedException {
		WebElement cityElement = driver.findElement(By.id("user_city"));
		Select citySelect = new Select(cityElement);
		citySelect.selectByVisibleText(city);
		Thread.sleep(5000);
	}
	
	public WebElement getSaveBtn() {
		return driver.findElement(By.name("btn_submit"));
	}
	
	public WebElement getUploadImage() {
		return driver.findElement(By.className("uploadFile-Js"));
	}
	
	public void uploadImage(String image) throws IOException {
		this.getUploadImage();
		String imgPath = new File(image).getCanonicalPath();
		this.driver.findElement(By.xpath("//*[@id='form-upload']/input")).sendKeys(imgPath);
	}
	
	public void deleteImage() {
		WebElement delete = driver.findElement(By.className("remove"));
		js.executeScript("arguments[0].click();", delete);
	}
	
	public void updateProfile(String firstName, String lastName, String address, String phoneNo, String zipCode,
			String country, String state, String city) throws InterruptedException {

		this.getFirstName().sendKeys(Keys.CONTROL + "a", Keys.DELETE);
		this.getLastName().sendKeys(Keys.CONTROL + "a", Keys.DELETE);
		this.getAddress().sendKeys(Keys.CONTROL + "a", Keys.DELETE);
		this.getPhoneNo().sendKeys(Keys.CONTROL + "a", Keys.DELETE);
		this.getZipCode().sendKeys(Keys.CONTROL + "a", Keys.DELETE);

		this.getFirstName().sendKeys(firstName);
		this.getLastName().sendKeys(lastName);
		this.getAddress().sendKeys(address);
		this.getPhoneNo().sendKeys(phoneNo);
		this.getZipCode().sendKeys(zipCode);
		this.getCountry(country);
		this.getState(state);
		this.getCity(city);

		this.getSaveBtn().click();
	}
	}

