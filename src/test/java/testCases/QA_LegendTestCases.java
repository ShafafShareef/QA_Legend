package testCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import automationCore.BaseClass;
import dev.failsafe.internal.util.Assert;
import pageClasses.QA_Legend_ClientPage;
import pageClasses.QA_Legend_EventPage;
import pageClasses.QA_Legend_HomePage;
import pageClasses.QA_Legend_ItemPage;
import pageClasses.QA_Legend_LoginPage;
import pageClasses.QA_Legend_MessagePage;
import pageClasses.QA_Legend_NotePage;
import utilities.DateUtilities;
import utilities.ExcelUtility;
import utilities.FakerUtilities;

public class QA_LegendTestCases extends BaseClass{
	public WebDriver driver;
	FileInputStream fis;
	Properties prop;
	String excelfilePath = "/src/main/java/testData/testdata_Excel.xlsx";
	
	
	QA_Legend_LoginPage loginPage;
	QA_Legend_HomePage homePage;
	QA_Legend_EventPage eventPage;
	QA_Legend_NotePage notePage;
	QA_Legend_MessagePage messagePage;
	QA_Legend_ClientPage clientPage;
	QA_Legend_ItemPage itemPage;
	
	
	
	@BeforeMethod
	@Parameters({"browser"})
	public void initialization(String browser) throws Exception {
		driver = browserInitization(browser);
		driver.manage().window().maximize();
		fis = new FileInputStream("C:\\Users\\LENOVO\\eclipse-workspace\\QA_Legend\\src\\main\\java\\testData\\testData.properties");
		prop = new Properties();
		prop.load(fis);
		driver.get(prop.getProperty("url"));
		
		loginPage = new QA_Legend_LoginPage(driver);
		homePage = new QA_Legend_HomePage(driver);
		eventPage = new QA_Legend_EventPage(driver);
		notePage = new QA_Legend_NotePage(driver);
		messagePage = new QA_Legend_MessagePage(driver);
		clientPage = new QA_Legend_ClientPage(driver);
		itemPage = new QA_Legend_ItemPage(driver);
		
		
		loginPage.enterUserName(prop.getProperty("username"));
		loginPage.enterPassword(prop.getProperty("password"));
		loginPage.clickLoginButton();
		
		}
	
	
	@Test(priority = 1)
	
	public void loginCRM() { 
		homePage.clickOnUserProfileName();
		homePage.clickOnLogoutButton();
		loginPage.enterUserName(prop.getProperty("username"));
		loginPage.enterPassword(prop.getProperty("password"));
		loginPage.clickLoginButton();
		String expectedUserName = prop.getProperty("profileName");
		String actualUserName = homePage.getProfileName();
		org.testng.Assert.assertEquals(actualUserName, expectedUserName);
		
		
		
	}
	@Test(priority = 2)
	public void logoutCRM() {
//		loginPage.enterUserName(prop.getProperty("username"));
//		loginPage.enterPassword(prop.getProperty("password"));
//		loginPage.clickLoginButton();
		homePage.clickOnUserProfileName();
		homePage.clickOnLogoutButton();
	}
	@Test
	public void addEvent() throws IOException {
		homePage.clickonDashboardEvents();
		eventPage.clickonAddEvents();
		//System.out.println(excelfilePath);
		//eventPage.clickonTitleField();
		String event_Title=ExcelUtility.getString(1, 0, excelfilePath, "Sheet1")+FakerUtilities.randomNumberCreation();
		eventPage.inputTitle(event_Title);
		String event_Description_=ExcelUtility.getString(1, 1, excelfilePath, "Sheet1");
		eventPage.inputEventDescription(event_Description_);
		//System.out.println(DateUtilities.getCurrentDate());
		String startDate = DateUtilities.getCurrentDate();
		String startTime = DateUtilities.getCurrenttime();
		eventPage.inputEventStartDate(startDate);
		//eventPage.inputEventStartTime(startTime);
		String endDate = startDate;
		eventPage.inputEventEndDate(endDate);
		String event_Location=ExcelUtility.getString(1, 2, excelfilePath, "Sheet1");
		eventPage.inputEventLocation(event_Location);
		eventPage.clickon_Event_Save();
	}
	@Test
	public void add_Notes() throws IOException {
		homePage.clickOnDashboardNotes();
		notePage.clickOn_AddNote_button();
		String noteTitle=ExcelUtility.getString(1, 0, excelfilePath, "Note_Page")+FakerUtilities.randomNumberCreation();
		notePage.addNote_inputTitle(noteTitle);
		String noteDescription=ExcelUtility.getString(1, 1, excelfilePath, "Note_Page");
		notePage.addNote_inputDescription(noteDescription);
		notePage.clickOnNote_Save_button();
		
		// file upload image
		
	}
	@Test
	public void message_ComposeandSend(){
		homePage.clickOnDashboardMessage();
		messagePage.clickOnCompose();
		messagePage.selectRecipiant();
		
		
		
		
		// to complete
		
	}
	
	@Test
	public void add_Client() throws IOException {
		homePage.clickOnDashboardClients();
		clientPage.clickOnAddClient();
		String companyName=ExcelUtility.getString(1, 0, excelfilePath, "Sheet2");
		clientPage.inputCompanyName(companyName);
		String address=ExcelUtility.getString(1, 1, excelfilePath, "Sheet2");
		clientPage.inputAddress(address);
		String city=ExcelUtility.getString(1, 2, excelfilePath, "Sheet2");
		clientPage.inputCity(city);
		String state=ExcelUtility.getString(1, 3, excelfilePath, "Sheet2");
		clientPage.inputState(state);
		String country=ExcelUtility.getString(1, 4, excelfilePath, "Sheet2");
		clientPage.inputCountry(country);
		clientPage.clickOnClientSave_button();
		
		
		
	}
	
	@Test(dataProvider = "test_login_credentials")
	public void loginScnrios(String userName, String password) {
		homePage.clickOnUserProfileName();
		homePage.clickOnLogoutButton();
		loginPage.enterUserName(userName);
		loginPage.enterPassword(password);
		loginPage.clickLoginButton();
		
		
		
	}
	@DataProvider(name = "test_login_credentials")
	public Object[][] testData(){
		Object [][] login_data = new Object[3][2];
		login_data[0][0] = "admin@admin.com";
		login_data[0][1] = "12345678";
		login_data[1][0] = "admin@adm.com";
		login_data[1][1] = "12345678";
		login_data[2][0] = "admin@admin.com";
		login_data[2][1] = "123456789";
		return login_data;
		
	}
	
	
	@Test
	public void add_Item() throws IOException {
		homePage.clickOnDashboardItem();
		itemPage.clickOnAddItem();
		String event_Title=ExcelUtility.getString(1, 0, excelfilePath, "Note_Page")+FakerUtilities.randomNumberCreation();
		itemPage.inputTitle(event_Title);
		String item_Description=ExcelUtility.getString(1, 1, excelfilePath, "Note_Page");
		itemPage.inputItemDescription(item_Description);
		String item_Rate=ExcelUtility.getString(1, 1, excelfilePath, "Note_Page");
		itemPage.inputItemRate(item_Rate);
		itemPage.clickon_item_Save();
	}
	

}
