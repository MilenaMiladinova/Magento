package lumashop.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lumashop.pageobjects.AccountCreationPage;
import lumashop.pageobjects.CartPage;
import lumashop.pageobjects.CheckoutPage;
import lumashop.pageobjects.ConfirmationPage;
import lumashop.pageobjects.HomePage;
import lumashop.pageobjects.LoginPage;
import lumashop.pageobjects.ProductCatalogue;
import lumashop.AbstractComponents.AbstractComponent;
import lumashop.TestComponents.BaseTest;

public class StepDefinitionImpl extends BaseTest{

	public HomePage homePage;
	public AccountCreationPage accountCreationPage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	static CheckoutPage checkoutPage;
	public ConfirmationPage confirmationPage;
	

    @Given("^I landed on HomePage$")
	public void I_landed_on_Ecommerce_Page() throws IOException, InterruptedException
	{
		homePage = launchApplication();
		
		
	}

    @Given("^I moved to AccountCreationPage$")
	public void I_moved_on_AccountCreationPage() throws InterruptedException 
	{
		accountCreationPage = homePage.goCreateAccount();
		
	}
	
	
	
    @Given("^Registered with firstName (.+) and lastName (.+) and email(.+) and password (.+)$")
	public void register_with_username_and_password(String firstName, String lastName, String email, String password) throws InterruptedException
	{
		
		accountCreationPage.createAccount(firstName,lastName, email, password);
		
	}
	
   
    @When("^I add product (.+) to Cart and submit the order$")
	public void i_add_product_to_cart(String productName) throws InterruptedException
	{
    
    	productCatalogue = accountCreationPage.goToProductCatalogue();
		List<WebElement> products = productCatalogue.getProductList();
	    productCatalogue.getProductByName(productName);
		productCatalogue.addProductToCart();
		CartPage cartPage = productCatalogue.goToCartPage();
	    Boolean match = cartPage.VerifyProduct(productName);
		Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
	    checkoutPage.addAddress("162 Blue Spring Ave.");
		checkoutPage.selectCountry("United States");
		checkoutPage.selectState("Florida");
		checkoutPage.addCity("Deltona");
		checkoutPage.addPostCode("32725");
		checkoutPage.addTelephoneNumber("407-555-0113");
		checkoutPage.selectShippingRate();
		confirmationPage = checkoutPage.submitOrder();
	}
	
	

    @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_confirmationPage(String string)
    {
    	String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
    }
    
    
 
}
