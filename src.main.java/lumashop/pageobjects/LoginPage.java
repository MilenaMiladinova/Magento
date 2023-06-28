package lumashop.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import lumashop.AbstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent {

	protected WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "email")
	private WebElement emailInput;

	@FindBy(css = "input[name='login[password]']")
	private WebElement passwordInput;

	@FindBy(xpath = "(//div[@class='primary'] //*[text()='Sign In'])[1]")
	private WebElement signInButton;
	
	@FindBy(xpath = "(//button[@class='action switch'])[1]")
	private WebElement switchMenuButton;
	
	@FindBy(xpath= "(//a[contains(text(),'My Account')])[1]")
	private WebElement myAccountButton;
	
	@FindBy(xpath="//*[text()='My Orders']")
	private WebElement myOrdersButton;

	public void logIn(String email, String password) {

		emailInput.sendKeys(email);
		passwordInput.sendKeys(password);
		signInButton.click();
	}

	
	public OrderPage goToOrdersPage()
	{
		Actions a = new Actions(driver);
		switchMenuButton.click();
		a.moveToElement(myAccountButton).build().perform();
		myAccountButton.click();
		myOrdersButton.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
