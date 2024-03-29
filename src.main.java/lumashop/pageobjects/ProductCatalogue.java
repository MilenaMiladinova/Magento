package lumashop.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import lumashop.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	protected WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(className = ".column")
	private List<WebElement> products;
	
	@FindBy(css="div[id='product-item-info_1202'] div[id ='option-label-size-157-item-171']")
	private WebElement sizeChoice;
	
	
	@FindBy(css="div[id='product-item-info_1202'] div[id ='option-label-color-93-item-50'")
	private WebElement colorChoice;
	
	
	@FindBy(css="div[id='product-item-info_1202'] button[type='submit']")
	private WebElement submitButton;
	
	@FindBy(css=".showcart")
	private WebElement showCart;
	
	@FindBy(css="a[class='action viewcart']")
	private WebElement viewCart;
	

	By productsBy = By.id("page-title-heading");
	By messageSuccess = By.cssSelector("div[role='alert'] div[data-ui-id='message-success']");
    
	
	public List<WebElement> getProductList() {

		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String product1Name)

	{
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector(".product-item-link")).getText().equals(product1Name))
				.findFirst().orElse(null);
		return prod;
	}

	 public void addProductToCart() throws InterruptedException
	 
	 {
		
		 sizeChoice.click();
		 colorChoice.click();
		 submitButton.click();
		 Thread.sleep(7000);
		 waitForElementToAppear(messageSuccess);
	 }
	
	
	
	public CartPage goToCartPage()
	{
		waitForWebElementToAppear(showCart);
		showCart.click();
		viewCart.click();
		CartPage cartPage = new CartPage (driver);
		return cartPage;
		
	}
	
	
	
	
	
	
	
	
}
