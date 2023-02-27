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

public class ErrorValidationsTest extends BaseTest {

	
	@Test(groups= {"ErrorHandling"})
	public void AccountCreationErrorValidation() throws IOException, InterruptedException {
	
       
		AccountCreationPage accountCreationPage = homePage.goCreateAccount();
		accountCreationPage.createAccount("George", "King", "ma408of555@gmail.com", "Password123");
		accountCreationPage.getErrorMessage();
		Assert.assertEquals("There is already an account with this email address. If you are sure that it is your email address, click here to get your password and access your account.", accountCreationPage.getErrorMessage());
		
		
	}

	
	@Test(groups= {"ErrorHandling"})
	public void ProductErrorValidation() throws IOException, InterruptedException {
		
	String product1 = "Eos V-Neck Hoodie";
	AccountCreationPage accountCreationPage = homePage.goCreateAccount();
	accountCreationPage.createAccount("George", "King", "ma408of556@gmail.com", "Password123");
	Assert.assertEquals(driver.findElement(By.cssSelector("div[data-ui-id='message-success']")).getText(),
			"Thank you for registering with Main Website Store.");
	ProductCatalogue productCatalogue = accountCreationPage.goToProductCatalogue();
	List<WebElement> products = productCatalogue.getProductList();
	productCatalogue.getProductByName(product1);
	productCatalogue.addProductToCart();
	CartPage cartPage = productCatalogue.goToCartPage();
	Boolean match = cartPage.VerifyProduct("Eoss V-Neck Hoodie");
	Assert.assertFalse(match);
	}
}
