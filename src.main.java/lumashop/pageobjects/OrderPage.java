package lumashop.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import lumashop.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
}
	
	@FindBy(xpath="//*[text()='View Order']")
	WebElement viewOrderButton;
	
	@FindBy(css = "tr td:nth-child(1)")
    private List<WebElement> orderedProductName;
	
	
	public Boolean VerifyOrder(String product1Name) {
		
		viewOrderButton.click();
		Boolean match = orderedProductName.stream().anyMatch(cartProduct -> cartProduct.getText().contains(product1Name));
		return match;
	}
	
	
	
	
	
	
	
	
	
	
	
}
