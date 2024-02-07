 package testCases;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;

import org.apache.poi.util.SystemOutLogger;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import automationCore.BaseClass;
import dev.failsafe.internal.util.Assert;
import pageClasses.QA_Legend_AnnouncementPage;
import pageClasses.QA_Legend_ClientPage;
import pageClasses.QA_Legend_EventPage;
import pageClasses.QA_Legend_HomePage;
import pageClasses.QA_Legend_ItemPage;
import pageClasses.QA_Legend_LeavePage;
import pageClasses.QA_Legend_LoginPage;
import pageClasses.QA_Legend_MessagePage;
import pageClasses.QA_Legend_NotePage;
import utilities.DateUtilities;
import utilities.ExcelUtility;
import utilities.FakerUtilities;
import utilities.PageUtilities;
import utilities.WaitUtilities;

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
	QA_Legend_AnnouncementPage announcementPage;
	QA_Legend_LeavePage leavePage;
	
	
	
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
		announcementPage = new QA_Legend_AnnouncementPage(driver);
		leavePage = new QA_Legend_LeavePage(driver);
		
		
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
	public void add_Notes() throws IOException, AWTException, InterruptedException {
		homePage.clickOnDashboardNotes();
//		String pageTitle=PageUtilities.getTitleOfThePage(driver);
//		//System.out.println(pageTitle);
//		String expecPageTitle = "Demo CRM";
		notePage.clickOn_AddNote_button();
		String noteTitle=ExcelUtility.getString(1, 0, excelfilePath, "Note_Page")+FakerUtilities.randomNumberCreation();
		notePage.addNote_inputTitle(noteTitle);
		String noteDescription=ExcelUtility.getString(1, 1, excelfilePath, "Note_Page");
		notePage.addNote_inputDescription(noteDescription);
		
		// file upload image
		//notePage.clickOn_UploadFile();
		String path = "C:\\Users\\LENOVO\\eclipse-workspace\\QA_Legend\\src\\main\\java\\testData\\TestImgs\\Screenshot (1).jpg";
		notePage.upload_image(path);
		Thread.sleep(3000);
		
		notePage.clickOnNote_Save_button();
		
		
		String actualNoteTitle = notePage.toGetActualNoteTitle();
		org.testng.Assert.assertEquals(actualNoteTitle, noteTitle);
		
//		String actualImageTitle = notePage.toGetUploadedImageTitle();
//		System.out.println(actualImageTitle);
//		String expectedImageTitle = "Screenshot--1-.jpg";
//		org.testng.Assert.assertEquals(actualImageTitle, expectedImageTitle);
		
	}
	@Test
	public void message_ComposeandSend(){
		homePage.clickOnDashboardMessage();
		messagePage.clickOnCompose();
		messagePage.clickOnTo();
//		String to_address = "Saumia Alex";
//		messagePage.inputRecipiantName(to_address);
		
		
		
		
		
		
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
		System.out.println("Trail Git");
	}
	
	@Test
	public void add_Announcements() throws IOException, AWTException, InterruptedException {
		//PageUtilities.scrollToBottom(driver, 1000);
		homePage.scrollSidepanel();
		homePage.clickOnDashboardAnnouncements();
		announcementPage.clickOn_AddAnnouncement();
		String announcment_title=ExcelUtility.getString(1, 0, excelfilePath, "Announcement")+FakerUtilities.randomNumberCreation();
		announcementPage.inputTitle(announcment_title);
		PageUtilities.enter_TAB_Key();
		String announcment=ExcelUtility.getString(1, 1, excelfilePath, "Announcement");
		announcementPage.input_Announcement(announcment);
		String s_date = DateUtilities.getCurrentDate();
		announcementPage.input_AnnouncementStartDate(s_date);
		//LocalDate tdate= DateUtilities.getTommarowDate();
		String e_date = s_date;
		announcementPage.input_AnnouncementEndDate(e_date);
		announcementPage.scrolluptoSave();
		
		
		//String path = "C:\\Users\\LENOVO\\eclipse-workspace\\QA_Legend\\src\\main\\java\\testData\\TestImgs\\Screenshot (1).jpg";
		//notePage.upload_image(path);
		//Thread.sleep(3000);
	
		//announcementPage.announcement_Savebuton();
		//Thread.sleep(2000);
		//PageUtilities.scrollToBottom(driver, 1000);
		
		announcementPage.announcement_Savebuton();
//		WebElement view_Button = driver.findElement(By.xpath("//a[@title='View']"));
//		Assert.assertEquals(true, view_Button.isDisplayed());
		String actual_ViewButton_Title = announcementPage.toGetActual_View_button_Title();
		String expected_ViewButton_Title = "View";
		org.testng.Assert.assertEquals(actual_ViewButton_Title, expected_ViewButton_Title);
		

		
	}
	@Test
	public void applyAndAssignLeave() throws IOException 
	{	
		homePage.scrollSidepanel();
		homePage.clickOnDashboardLeave();
		leavePage.clickOnApplyLeaveButton();
		leavePage.clickOnLeaveTypeDropDown();
		leavePage.clickOnCasualLeave();
		String l_date = DateUtilities.getCurrentDate();
		leavePage.input_LeaveDate(l_date);
//		leavePage.enterReasonForLeave(excelFilePath);
//		leavePage.clickOnDateApplyLeaveInPopUp();
	}

	
	
	

}
