package lumashop.tests;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.auto.common.Visibility;

import io.github.bonigarcia.wdm.WebDriverManager;
import lumashop.TestComponents.BaseTest;
import lumashop.pageobjects.AccountCreationPage;
import lumashop.pageobjects.CartPage;
import lumashop.pageobjects.CheckoutPage;
import lumashop.pageobjects.ConfirmationPage;
import lumashop.pageobjects.HomePage;
import lumashop.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(dataProvider = "getData", groups = { "ErrorHandling" })
	public void AccountCreationErrorValidation(HashMap<String, String> input) throws IOException, InterruptedException {

		AccountCreationPage accountCreationPage = homePage.goCreateAccount();
		accountCreationPage.createAccount(input.get("firstName"), input.get("lastName"), input.get("email"), input.get("password"));
		accountCreationPage.getErrorMessage();
		Assert.assertEquals(
				"There is already an account with this email address. If you are sure that it is your email address, click here to get your password and access your account.",
				accountCreationPage.getErrorMessage());

}
	
	


	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJasonDataToMap(
				System.getProperty("user.dir") + "\\src.test.java\\lumashop\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0)}, {data.get(1)}};

	}
}
