package pageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.PageUtilities;

public class QA_Legend_ClientPage {
	WebDriver driver;
	@FindBy(xpath = "//a[text()=' Add client']")
	WebElement button_AddClient;
	@FindBy(xpath = "//input[@id='company_name']")
	WebElement input_CompanyName;
	@FindBy(xpath = "//textarea[@id='address']")
	WebElement input_Address;
	@FindBy(xpath = "//input[@id='city']")
	WebElement input_City;
	@FindBy(xpath = "//input[@id='state']")
	WebElement input_state;
	@FindBy(xpath = "//input[@id='country']")
	WebElement input_country;
	@FindBy(xpath = "//button[text()=' Save']")
	WebElement client_Save_button;
	
	
	
	
	
	
	
	
	
	public QA_Legend_ClientPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}









	public void clickOnAddClient() {
		PageUtilities.clickOnElement(button_AddClient);
	}
	public void inputCompanyName(String companyName) {
		PageUtilities.enterText(input_CompanyName, companyName);
	}
	public void inputAddress(String address) {
		PageUtilities.enterText(input_Address, address);
	}
	public void inputCity(String city) {
		PageUtilities.enterText(input_City, city);
	}
	public void inputState(String state) {
		PageUtilities.enterText(input_state, state);
	}
	public void inputCountry(String country) {
		PageUtilities.enterText(input_country, country);
	}
	public void clickOnClientSave_button() {
		PageUtilities.clickOnElement(client_Save_button);
	}
}
