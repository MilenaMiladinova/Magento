package lumashop.tests;

import java.io.IOException;
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
import lumashop.pageobjects.LoginPage;
import lumashop.pageobjects.OrderPage;
import lumashop.pageobjects.ProductCatalogue;
import rahulshettyacademy.TestComponents.Retry;

public class SubmitOrderTest extends BaseTest {

	@Test(dataProvider = "getData", groups = { "Purchase" }, retryAnalyzer = lumashop.TestComponents.Retry.class)
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		

		AccountCreationPage accountCreationPage = homePage.goCreateAccount();
		accountCreationPage.createAccount(input.get("firstName"), input.get("lastName"), input.get("email"), input.get("password"));
		Assert.assertEquals(driver.findElement(By.cssSelector("div[data-ui-id='message-success']")).getText(),
				"Thank you for registering with Main Website Store.");
		ProductCatalogue productCatalogue = accountCreationPage.goToProductCatalogue();
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.getProductByName(input.get("product1Name"));
		productCatalogue.addProductToCart();
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProduct(input.get("product1Name"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.addAddress(input.get("address"));
		checkoutPage.selectCountry(input.get("country"));
		checkoutPage.selectState(input.get("state"));
		checkoutPage.addCity(input.get("city"));
		checkoutPage.addPostCode(input.get("postCode"));
		checkoutPage.addTelephoneNumber(input.get("telephone"));
		checkoutPage.selectShippingRate();
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String successMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(successMessage.equalsIgnoreCase("Thank you for your purchase!"));

	}

	@Test(dependsOnMethods = {"submitOrder"},dataProvider= "getData") 
	public void orderHistoryTest(HashMap<String, String> input)

	{
		LoginPage loginPage = homePage.goLogin();
		loginPage.logIn(input.get("email"), input.get("password"));
		OrderPage orderPage = loginPage.goToOrdersPage();
		Assert.assertTrue(orderPage.VerifyOrder(input.get("product1Name")));

	}

	@DataProvider
	public Object[][] getData() throws IOException 
	{

		List<HashMap<String, String>> data = getJasonDataToMap(System.getProperty("user.dir")+"\\src.test.java\\lumashop\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, {data.get(1)} };

	}


}
