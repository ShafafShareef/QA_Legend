package pageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.PageUtilities;

public class QA_Legend_ItemPage {
	WebDriver driver;
	
	@FindBy(xpath = "//a[text()=' Add item']")
	WebElement button_AddItem;
	@FindBy(xpath="//input[@id='title']")
	WebElement additem_Title;
	@FindBy(xpath = "//textarea[@id='description']")
	WebElement additem_Description;
	@FindBy(xpath="//button[text()=' Save']")
	WebElement additem_Save_button;
	@FindBy(xpath = "//input[@id='item_rate']")
	WebElement additem_Rate;
	
	
	
	
	
	
	
	
	public QA_Legend_ItemPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	
	
	
	public void clickOnAddItem() {
		PageUtilities.clickOnElement(button_AddItem);
	}
	public void inputTitle(String event_Title) {
		PageUtilities.enterText(additem_Title, event_Title);
	}
	public void inputItemDescription(String item_Description) {
		PageUtilities.enterText(additem_Description, item_Description);
	}
	public void inputItemRate(String item_Rate) {
		PageUtilities.enterText(additem_Rate, item_Rate);
	}
	public void clickon_item_Save() {
		PageUtilities.clickOnElement(additem_Save_button);
	}
	
}
