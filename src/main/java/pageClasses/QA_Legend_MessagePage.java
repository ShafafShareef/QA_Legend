package pageClasses;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utilities.ExcelUtility;
import utilities.FakerUtilities;
import utilities.PageUtilities;
import utilities.WaitUtilities;

public class QA_Legend_MessagePage {
	WebDriver driver;
	
	@FindBy(xpath = "//span[@id='select2-chosen-3']")
	WebElement recepientDropDown;
	@FindBy(xpath = "//a[text()='Compose']")
	WebElement composeButton;
	@FindBy(xpath = "//div[text()='Saumia Alex']")
	WebElement selectRecepient;
	@FindBy(xpath = "//input[@id='subject']")
	WebElement subjectField;
	@FindBy(xpath = "//textarea[@id='message']")
	WebElement messageField;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement saveButton;
	@FindBy(xpath = "//a[text()='Sent items']")
	WebElement sentItemsButton;
	@FindBy(xpath = "(//div[@class='media-body'])[1]")
	WebElement sentMessage;
	@FindBy(xpath  ="((//div[@class='row'])[2]//child::p)[1]")
	WebElement actualSubject;
	@FindBy(xpath = "((//div[@class='row'])[2]//child::p)[2]")
	WebElement actualMessage;

	
	
	
	
	
	
	public QA_Legend_MessagePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}







	public void clickOnRecepientDropDown(){
		WaitUtilities.waitForAnElementToBeClickable(driver, recepientDropDown);
		//PageUtility.clickOnElementUsingJavaScriptExecutor(recepientDropDown, driver);
		PageUtilities.clickOnElement(recepientDropDown);
	}
	
	public void clickOnComposeButton(){
		PageUtilities.refreshPage(driver);
		WaitUtilities.waitForAnElementToBeClickable(driver, composeButton);
		//PageUtility.clickOnElementUsingJavaScriptExecutor(composeButton,driver);
		PageUtilities.clickOnElement(composeButton);
	}
	
	public void selectRecepientFromDropdown(){
		WaitUtilities.waitForAnElementToBeClickable(driver, selectRecepient);
		//PageUtility.clickOnElementUsingJavaScriptExecutor(selectRecepient,driver);
		PageUtilities.clickOnElement(selectRecepient);	
	
	}
	public String enterSubjectInComposeMessagePopUp(String excelfilePath) throws IOException{
		String subject = ExcelUtility.getString(0, 1, excelfilePath, "Note_Page")+FakerUtilities.randomNumberCreation();
		PageUtilities.enterText(subjectField, subject);
		return subject;
	}
	public String enterMessagetInComposeMessagePopUp(String excelfilePath) throws IOException{
		String message = ExcelUtility.getString(0, 0, excelfilePath, "message");
		PageUtilities.enterText(messageField, message);
		return message;
	}
	public void clickOnSaveButton(){
		PageUtilities.clickOnElement(saveButton);
	}
	
	public void clickOnSentItems(){
		PageUtilities.refreshPage(driver);
		WaitUtilities.waitForAnElementToBeClickable(driver, sentItemsButton);
		PageUtilities.clickOnElement(sentItemsButton);
	}
	public void clickOnTheMessageSentFromSentItems(){
		PageUtilities.clickOnElement(sentMessage);
	}
	public String getActualSubject(){
		String actSubject = PageUtilities.getTextFromElement(actualSubject);
		return actSubject;
	}
	public String getActualMessage(){
		String actMessage = PageUtilities.getTextFromElement(actualMessage);
		return actMessage;
	}

	
}
