package utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageUtilities {
	public static void closeBrowser(WebDriver driver) {
		driver.close();
		// doubt
	}
	public static void clickOnElement(WebElement element){
		element.click();
	}
	public static void enterText(WebElement element, String value) {
		element.sendKeys(value);
	}
	public static String getTextFromElement(WebElement element) {
		return element.getText();
	}
	public static void clearText(WebElement element) {
		element.clear();
	}
	public static void navigateBack(WebDriver driver) {
		driver.navigate().back();
	}
	public static void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	public static void dismissAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	public static void uploadFileWithRobot(String filepath) throws AWTException {
		Robot robot =new Robot();
		StringSelection stringSelection = new StringSelection(filepath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		
		 robot.keyPress(KeyEvent.VK_CONTROL);
         robot.keyPress(KeyEvent.VK_V);
         robot.keyRelease(KeyEvent.VK_V);
         robot.keyRelease(KeyEvent.VK_CONTROL);
         robot.delay(1000);
         
         robot.keyPress(KeyEvent.VK_ENTER);
         robot.keyRelease(KeyEvent.VK_ENTER);
         robot.delay(1000);
         

	}
	
}
