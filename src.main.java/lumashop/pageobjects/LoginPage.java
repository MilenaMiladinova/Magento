package lumashop.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import lumashop.AbstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "email")
	WebElement emailInput;

	@FindBy(css = "input[name='login[password]']")
	WebElement passwordInput;

	@FindBy(xpath = "(//div[@class='primary'] //*[text()='Sign In'])[1]")
	WebElement signInButton;
	
	@FindBy(xpath = "(//button[@class='action switch'])[1]")
	WebElement switchMenuButton;
	
	@FindBy(xpath= "(//a[contains(text(),'My Account')])[1]")
	WebElement myAccountButton;
	
	@FindBy(xpath="//*[text()='My Orders']")
	WebElement myOrdersButton;

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
