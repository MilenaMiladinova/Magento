package lumashop.pageobjects;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import lumashop.AbstractComponents.AbstractComponent;

public class AccountCreationPage extends AbstractComponent {

	protected WebDriver driver;

	public AccountCreationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "firstname")
	private WebElement firstNameInput;

	@FindBy(id = "lastname")
	private WebElement lastNameInput;

	@FindBy(id = "is_subscribed")
	private WebElement isSubscribed;

	@FindBy(id = "email_address")
	private WebElement emailAddressInput;

	@FindBy(id = "password")
	private WebElement passwordInput;

	@FindBy(id = "password-confirmation")
	private WebElement passwordConfirmationInput;

	@FindBy(css = "button[title='Create an Account']")
	private WebElement submitInput;

	@FindBy(xpath = "//nav/ul/li[2]")
	private WebElement womenDepartment;

	@FindBy(id = "ui-id-13")
	private WebElement productCategory;

	@FindBy(css = ".message-error")
	private WebElement errorMessage;

	

	public void createAccount(String firstName, String lastName, String email, String password)
			throws InterruptedException {

		firstNameInput.sendKeys(firstName);
		lastNameInput.sendKeys(lastName);
		isSubscribed.click();
		emailAddressInput.sendKeys(email);
		passwordInput.sendKeys(password);
		passwordConfirmationInput.sendKeys(password);
		submitInput.click();
	}
	
	
	

	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

	public ProductCatalogue goToProductCatalogue() throws InterruptedException {
	    
		waitForWebElementToAppear(womenDepartment);
		Actions a = new Actions(driver);
		a.moveToElement(womenDepartment).build().perform();
		Thread.sleep(2000);
		a.sendKeys(Keys.ARROW_DOWN).build().perform();
		a.sendKeys(Keys.ARROW_RIGHT).build().perform();
		productCategory.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}

	
}
