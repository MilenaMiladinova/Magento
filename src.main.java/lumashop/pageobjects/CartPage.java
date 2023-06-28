package lumashop.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import lumashop.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	protected WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "tbody[class='cart item'] tr td div strong")
    private List<WebElement> cartProducts;

	@FindBy(css = "div[class='cart-summary _sticky'] ul li button :nth-child(1)")
	private WebElement proceedToCheckoutButton;

	public Boolean VerifyProduct(String product1Name) {

		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(product1Name));
		return match;
	}
	

	public CheckoutPage goToCheckout() throws InterruptedException

	{
		scroll();
		Thread.sleep(3000);
		waitForWebElementToAppear(proceedToCheckoutButton);
		proceedToCheckoutButton.click();
		return new CheckoutPage(driver);
	
	}

}
