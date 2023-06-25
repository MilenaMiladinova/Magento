package lumashop.pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lumashop.AbstractComponents.AbstractComponent;

public class HomePage extends AbstractComponent{
	
	WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(css="div[class='panel header'] ul li:nth-child(3)")
	WebElement createAccountButton;
	
	@FindBy(xpath="//*[text()='Create New Customer']")
	WebElement createNewCustomerButton;
	
	@FindBy(xpath="(//a[contains(text(),'Sign In')])[1]")
	WebElement loginButton;
	
	
	
	
	
	By welcomeMsg = By.cssSelector("div[class='panel header'] ul li span[class='not-logged-in']");
	
	
	
	public void landOn()
	
	{
		
		driver.get("https://www.google.com/");
		driver.navigate().to("https://magento2-b2b.magebit.com/");
		waitForElementToAppear(welcomeMsg);
		
		
	}
	
	public AccountCreationPage goCreateAccount() throws InterruptedException
	{
		
		createAccountButton.click();
		Thread.sleep(5000);
		createNewCustomerButton.click();
		AccountCreationPage accountCreationPage = new AccountCreationPage (driver);
		return accountCreationPage;
	}
	
	public LoginPage goLogin()
	{
		loginButton.click();
		LoginPage loginPage = new LoginPage(driver);
		return loginPage;
	}
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
