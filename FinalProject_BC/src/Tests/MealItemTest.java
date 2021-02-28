package Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.CartSummaryPage;
import Pages.LocationPopupPage;
import Pages.LoginPage;
import Pages.MealPage;
import Pages.NotificationSystemPage;

public class MealItemTest extends BasicTest {
	
	@Test(priority = 1)
	public void addMealToCartTest() {
		this.driver.navigate().to(this.BaseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		
		LocationPopupPage locationPopup = new LocationPopupPage(driver, wait, js);
		NotificationSystemPage notificationSystem = new NotificationSystemPage(driver, wait, js);
		MealPage meal = new MealPage(driver, wait, js);
		
		locationPopup.ClosePopUp();
		meal.addMealToCart("3");
		
		Assert.assertTrue(notificationSystem.getMessage().contains("The Following Errors Occurred:"),
				"[ERROR]: Message not shown.");
		Assert.assertTrue(notificationSystem.getMessage().contains("Please Select Location"),
				"[ERROR]: Message not shown.");

		notificationSystem.Wait();
		
		locationPopup.OpenPopUp();
		locationPopup.setLocation("City Center - Albany");
		
		meal.addMealToCart("3");

		Assert.assertTrue(notificationSystem.getMessage().contains("Meal Added To Cart"),
				"[ERROR]: Message not shownd.");
	}
	
	@Test(priority = 5)
	public void addMealToFavoriteTest() throws InterruptedException {
		this.driver.navigate().to(this.BaseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		
		LocationPopupPage locationPopup = new LocationPopupPage(driver, wait, js);
		LoginPage login = new LoginPage(driver, wait, js);
		NotificationSystemPage notificationSystem = new NotificationSystemPage(driver, wait, js);
		MealPage meal = new MealPage(driver, wait, js);
		
		locationPopup.ClosePopUp();
		Thread.sleep(5000);
		meal.addMealToFavorite();

		Assert.assertTrue(notificationSystem.getMessage().contains("Please login first!"),
				"[ERROR]:Message not shown.");
		notificationSystem.Wait();
		this.driver.navigate().to(this.BaseUrl + "/guest-user/login-form");
		
		login.LogIn(username, password);

		this.driver.navigate().to(this.BaseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");

		meal.addMealToFavorite();
		
		Assert.assertTrue(notificationSystem.getMessage().contains("Product has been added to your favorites."),
				"[ERROR]:Message not shown.");
	}
	
	@Test(priority = 10)
	public void clearCartTest() throws IOException, InterruptedException {
		this.driver.navigate().to(this.BaseUrl + "/meals");
		
		LocationPopupPage locationPopup = new LocationPopupPage(driver, wait, js);
		NotificationSystemPage notificationSystem = new NotificationSystemPage(driver, wait, js);
		MealPage meal = new MealPage(driver, wait, js);
		CartSummaryPage cartSummary = new CartSummaryPage(driver, wait, js);

		locationPopup.setLocation("City Center - Albany");
		
		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Meals");
		
		for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
			XSSFRow row = sheet.getRow(i);

			XSSFCell meals = row.getCell(0);
			String links = meals.getStringCellValue();

			this.driver.navigate().to(links);
			Thread.sleep(3000);
			meal.addMealToCart("1");

			Assert.assertEquals(this.notificationSistemPage, ("Meal Added To Cart"),
					"[ERROR]: Message not shown.");
			notificationSystem.Wait();
		}
		
		cartSummary.clearAll();
		notificationSystem.Wait();

		Assert.assertEquals(this.notificationSistemPage.getMessage(), ("Meal Added To Cart"),
				"[ERROR]: Message not shown.");

		workbook.close();
		fis.close();
	}

}
