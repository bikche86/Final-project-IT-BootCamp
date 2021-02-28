package Tests;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.AuthPage;
import Pages.LocationPopupPage;
import Pages.LoginPage;
import Pages.NotificationSystemPage;
import Pages.ProfilePage;

public class ProfileTest extends BasicTest {
	
	@Test(priority = 0)
	public void editProfileTest() throws InterruptedException {
		this.driver.navigate().to(this.BaseUrl + "/guest-user/login-form");
		
		LocationPopupPage locationPopup = new LocationPopupPage(driver, wait, js);
		LoginPage login = new LoginPage(driver, wait, js);
		NotificationSystemPage notificationSystem = new NotificationSystemPage(driver, wait, js);
		ProfilePage profile = new ProfilePage(driver, wait, js);
		AuthPage auth = new AuthPage(driver, wait, js);
		
		locationPopup.ClosePopUp();
		login.LogIn(username, password);
		Assert.assertEquals(notificationSystem.getMessage().contains("Login Successfull"),
				"[ERROR]: Message not shown.");
		
		this.driver.navigate().to(this.BaseUrl + "/member/profile");
		profile.updateProfile("Michael", "M", "Serbia", "056482456", "18000", "Yugoslavia", "Nis", "Belgrade");
		Assert.assertEquals(notificationSystem.getMessage().contains("Setup Successful"),
				"[ERROR]: Message not shown..");
		notificationSystem.Wait();
		
		auth.getLogout();
		Assert.assertEquals(notificationSystem.getMessage().contains("Logout Successfull!"),
				"[ERROR]: Message not shown..");

	}

	@Test(priority = 5)
	public void changeProfileImageTest() throws InterruptedException, IOException {
		this.driver.navigate().to(this.BaseUrl + "/guest-user/login-form");
		
		LocationPopupPage locationPopup = new LocationPopupPage(driver, wait, js);
		LoginPage login = new LoginPage(driver, wait, js);
		NotificationSystemPage notificationSystem = new NotificationSystemPage(driver, wait, js);
		ProfilePage profile = new ProfilePage(driver, wait, js);
		AuthPage auth = new AuthPage(driver, wait, js);
		
		locationPopup.ClosePopUp();
		login.LogIn(username, password);
		Assert.assertEquals(notificationSystem.getMessage().contains("Login Successfull"),
				"[ERROR]: Message not shown.");
		
		this.driver.navigate().to(this.BaseUrl + "/member/profile");
		Thread.sleep(3000);
		String imgPath = new File("images/Untitled.png").getCanonicalPath();

		profile.uploadImage(imgPath);
		Assert.assertEquals(notificationSystem.getMessage().contains("Profile Image Uploaded Successfully"),
				"[ERROR]: Message not shown.");
		notificationSystem.Wait();
		
		profile.deleteImage();
		Assert.assertEquals(notificationSystem.getMessage().contains("Profile Image Deleted Successfully"),
				"[ERROR]: Message not shown.");

		notificationSystem.Wait();
		
		auth.getLogout();
		Assert.assertEquals(notificationSystem.getMessage().contains("Logout Successfull!"),
				"[ERROR]: Message not shown.");
	}

}
