package lumashop.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
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

public class SubmitOrderTest extends BaseTest {

	
	@Test
	public void submitOrder() throws IOException, InterruptedException {
	
	
	
		String firstName = "George";
		String lastName = "King";
		String email = "ma408of555@gmail.com";
		String address = "162 Blue Spring Ave.";
		String city = "Deltona";
		String postCode = "32725";
		String country = "United States";
		String state = "Florida";
		String telephone = "407-555-0113";
		String password = "Password123";
		String product1 = "Eos V-Neck Hoodie";

		
       
		AccountCreationPage accountCreationPage = homePage.goCreateAccount();
		accountCreationPage.createAccount("George", "King", "ma408of555@gmail.com", "Password123");
		Assert.assertEquals(driver.findElement(By.cssSelector("div[data-ui-id='message-success']")).getText(),
				"Thank you for registering with Main Website Store.");
		ProductCatalogue productCatalogue = accountCreationPage.goToProductCatalogue();
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.getProductByName(product1);
		productCatalogue.addProductToCart();
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProduct(product1);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.addAddress(address);
		checkoutPage.selectCountry(country);
		checkoutPage.selectState(state);
		checkoutPage.addCity(city);
		checkoutPage.addPostCode(postCode);
		checkoutPage.addTelephoneNumber(telephone);
		checkoutPage.selectShippingRate();
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String successMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(successMessage.equalsIgnoreCase("Thank you for your purchase!"));
	    

	}

}
