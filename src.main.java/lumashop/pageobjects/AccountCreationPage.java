package lumashop.pageobjects;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import lumashop.AbstractComponents.AbstractComponent;

public class AccountCreationPage extends AbstractComponent {

	WebDriver driver;

	public AccountCreationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "firstname")
	WebElement firstNameInput;

	@FindBy(id = "lastname")
	WebElement lastNameInput;

	@FindBy(id = "is_subscribed")
	WebElement isSubscribed;

	@FindBy(id = "email_address")
	WebElement emailAddressInput;

	@FindBy(id = "password")
	WebElement passwordInput;

	@FindBy(id = "password-confirmation")
	WebElement passwordConfirmationInput;

	@FindBy(css = "button[title='Create an Account']")
	WebElement submitInput;

	@FindBy(xpath = "//nav/ul/li[2]")
	WebElement womenDepartment;

	@FindBy(id = "ui-id-13")
	WebElement productCategory;

	@FindBy(css = ".message-error")
	WebElement errorMessage;

	

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
