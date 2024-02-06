package pageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.PageUtilities;

public class QA_Legend_NotePage {
	WebDriver driver;
	
	@FindBy(xpath = "//a[text()=' Add note']")
	WebElement addNote_button;
	@FindBy(xpath = "//input[@id='title']")
	WebElement inputNote_Title;
	@FindBy(xpath = "//textarea[@id='description']")
	WebElement inputNote_description;
	@FindBy(xpath = "//button[text()=' Save']")
	WebElement note_Save_button;
	
	
	
	
	
	
	
	
	
	
	public QA_Legend_NotePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}










	public void clickOn_AddNote_button() {
		PageUtilities.clickOnElement(addNote_button);
	}
	public void addNote_inputTitle(String noteTitle) {
		PageUtilities.enterText(inputNote_Title, noteTitle);
	}
	public void addNote_inputDescription(String noteDescription) {
		PageUtilities.enterText(inputNote_description, noteDescription);
	}
	public void clickOnNote_Save_button() {
		PageUtilities.clickOnElement(note_Save_button);
	}
}
