 package testCases;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;

import org.apache.poi.util.SystemOutLogger;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import pageClasses.QA_Legend_Quick_AddNote;
import utilities.DateUtilities;
import utilities.ExcelUtility;
import utilities.FakerUtilities;
import utilities.PageUtilities;
import utilities.WaitUtilities;

public class QA_LegendTestCases extends BaseClass{
	WebDriver driver;
	
	FileInputStream fis;
	 Properties prop;
	 final String excelfilePath = "/src/main/java/testData/testdata_Excel.xlsx";
	
	
	 QA_Legend_LoginPage loginPage;
	 QA_Legend_HomePage homePage;
	 QA_Legend_EventPage eventPage;
	 QA_Legend_NotePage notePage;
	 QA_Legend_MessagePage messagePage;
	 QA_Legend_ClientPage clientPage;
	 QA_Legend_ItemPage itemPage;
	 QA_Legend_AnnouncementPage announcementPage;
	 QA_Legend_LeavePage leavePage;
	 QA_Legend_Quick_AddNote quickaddNotePage;
	
	
	
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
		quickaddNotePage = new QA_Legend_Quick_AddNote(driver);
		
		
		loginPage.enterUserName(prop.getProperty("username"));
		loginPage.enterPassword(prop.getProperty("password"));
		loginPage.clickLoginButton();
		
		}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	
		
	
	@Test(groups = {"LoginScnarions"})
	
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
	@Test(priority = 2,groups = {"LoginScnarions"})
	public void logoutCRM() {
		homePage.clickOnUserProfileName();
		homePage.clickOnLogoutButton();
		boolean login_Status = homePage.checkPresenceOfLogin_button();
		//System.out.println(login_Status);
		boolean expec_Status = true;
		org.testng.Assert.assertEquals(login_Status, expec_Status);
	}
	@Test(dataProvider = "test_login_credentials" , priority = 3, groups = {"LoginScnarions"})
	public void login_Negative_Scnrios(String userName, String password) throws InterruptedException {
		homePage.clickOnUserProfileName();
		homePage.clickOnLogoutButton();
		loginPage.enterUserName(userName);
		loginPage.enterPassword(password);
		loginPage.clickLoginButton();
		Thread.sleep(3000);
		boolean auth_fail_popup = homePage.checkPresenceOfauthentication_failed_popup();
		boolean expec_popup = true;
		org.testng.Assert.assertEquals(auth_fail_popup, expec_popup);
		
	}
	@DataProvider(name = "test_login_credentials")
	public Object[][] testData(){
		Object [][] login_data = new Object[3][2];
		login_data[0][0] = "admin@admin.comm";
		login_data[0][1] = "123456789";
		login_data[1][0] = "admin@adm.com";
		login_data[1][1] = "12345678";
		login_data[2][0] = "admin@admin.com";
		login_data[2][1] = "123456789";
		return login_data;
		
	}
	
	
	
	@Test(priority = 4)
	public void addEvent () throws IOException {
		homePage.clickonDashboardEvents();
		eventPage.clickonAddEvents();
		String event_Title=ExcelUtility.getString(1, 0, excelfilePath, "Sheet1")+FakerUtilities.randomNumberCreation();
		eventPage.inputTitle(event_Title);
		String event_Description_=ExcelUtility.getString(1, 1, excelfilePath, "Sheet1");
		eventPage.inputEventDescription(event_Description_);
		//System.out.println(DateUtilities.getCurrentDate());
		String startDate = DateUtilities.getCurrentDate();
		String startTime = DateUtilities.getCurrenttime();
		eventPage.inputEventStartDate(startDate);
		eventPage.inputEventStartTime(startTime);
		String endDate = startDate;
		eventPage.inputEventEndDate(endDate);
		String event_Location=ExcelUtility.getString(1, 2, excelfilePath, "Sheet1");
		eventPage.inputEventLocation(event_Location);
		eventPage.clickon_Event_Save(); 
//		String expec_url = prop.getProperty("addEvent_url");
//		org.testng.Assert.assertEquals(driver.getCurrentUrl(), expec_url);
		String pageTitle = driver.getTitle();
		String expecPageTitle = prop.getProperty("page_Title");
		org.testng.Assert.assertEquals(expecPageTitle, pageTitle);
	}
	
	@Test(priority = 5)
	public void addEventByQuickIcon() throws IOException {
		quickaddNotePage.clickOnQuickAddIcon();
		quickaddNotePage.clickOnAddEvent();
		String event_Title=ExcelUtility.getString(1, 0, excelfilePath, "Sheet1")+FakerUtilities.randomNumberCreation();
		eventPage.inputTitle(event_Title);
		String event_Description_=ExcelUtility.getString(1, 1, excelfilePath, "Sheet1");
		eventPage.inputEventDescription(event_Description_);
		String startDate = DateUtilities.getCurrentDate();
		String startTime = DateUtilities.getCurrenttime();
		eventPage.inputEventStartDate(startDate);
		eventPage.inputEventStartTime(startTime);
		String endDate = startDate;
		eventPage.inputEventEndDate(endDate);
		String event_Location=ExcelUtility.getString(1, 2, excelfilePath, "Sheet1");
		eventPage.inputEventLocation(event_Location);
		eventPage.clickon_Event_Save();
//		String expec_url = prop.getProperty("addEvent_url");
//		org.testng.Assert.assertEquals(driver.getCurrentUrl(), expec_url);
		String pageTitle = driver.getTitle();
		String expecPageTitle = prop.getProperty("page_Title");
		org.testng.Assert.assertEquals(expecPageTitle, pageTitle);


	}


	
	@Test(priority = 6)
	public void add_Notes() throws IOException, AWTException, InterruptedException {
		homePage.clickOnDashboardNotes();
		notePage.clickOn_AddNote_button();
		String noteTitle=ExcelUtility.getString(1, 0, excelfilePath, "Note_Page")+FakerUtilities.randomNumberCreation();
		notePage.addNote_inputTitle(noteTitle);
		String noteDescription=ExcelUtility.getString(1, 1, excelfilePath, "Note_Page");
		notePage.addNote_inputDescription(noteDescription);		
		String path = prop.getProperty("screenshot_Path");
		notePage.upload_image(path);
		Thread.sleep(3000);
		notePage.clickOnNote_Save_button();
		String actualNoteTitle = notePage.toGetActualNoteTitle();
		org.testng.Assert.assertEquals(actualNoteTitle, noteTitle);		
	}
	
	@Test(priority = 7)
	public void addNoteusingQuickIcon() throws IOException, AWTException, InterruptedException {
		quickaddNotePage.clickOnQuickAddIcon();
		quickaddNotePage.clickOnAddNote();
		String noteTitle=ExcelUtility.getString(1, 0, excelfilePath, "Note_Page")+FakerUtilities.randomNumberCreation();
		notePage.addNote_inputTitle(noteTitle);
		String noteDescription=ExcelUtility.getString(1, 1, excelfilePath, "Note_Page");
		notePage.addNote_inputDescription(noteDescription);		
		String path = prop.getProperty("screenshot_Path");
		notePage.upload_image(path);
		Thread.sleep(3000);
		notePage.clickOnNote_Save_button();
		Thread.sleep(2000);
		homePage.clickOnDashboardNotes();  
		String actualNoteTitle = notePage.toGetActualNoteTitle();
		org.testng.Assert.assertEquals(actualNoteTitle, noteTitle);	
	}

	
	
	@Test(priority = 8)
	public void message_ComposeandSend() throws InterruptedException, IOException{
		homePage.clickOnDashboardMessage();
		messagePage.clickOnComposeButton();
		messagePage.clickOnRecepientDropDown();
		messagePage.selectRecepientFromDropdown();
		String expSubject = messagePage.enterSubjectInComposeMessagePopUp(excelfilePath);
		String expMessage = messagePage.enterMessagetInComposeMessagePopUp(excelfilePath);
		messagePage.clickOnSaveButton();
		messagePage.clickOnSentItems();
		messagePage.clickOnTheMessageSentFromSentItems();
		String actualSubject = messagePage.getActualSubject();
		String actualMessage = messagePage.getActualMessage();
		System.out.println(actualMessage);
		System.out.println(actualSubject);
		org.testng.Assert.assertTrue(actualMessage.contains(expMessage)&&actualSubject.contains(expSubject));	
	}
	
	@Test(priority = 9)
	public void add_Client() throws IOException, InterruptedException {
		homePage.clickOnDashboardClients();
		clientPage.clickOnAddClient();
		String companyName=ExcelUtility.getString(1, 0, excelfilePath, "Sheet2")+FakerUtilities.randomNumberCreation();
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
		Thread.sleep(2000 );
		clientPage.search_added_company(companyName);
		String addedCmpany = clientPage.getPre_added_Client();
		org.testng.Assert.assertEquals(companyName, addedCmpany);
		
		
		
	}
	
	
	
	
	
	
	
	
	@Test(priority = 10)
	public void add_Item() throws IOException, InterruptedException {
		homePage.clickOnDashboardItem();
		itemPage.clickOnAddItem();
		String event_Title=ExcelUtility.getString(1, 0, excelfilePath, "Note_Page")+FakerUtilities.randomNumberCreation();
		itemPage.inputTitle(event_Title);
		String item_Description=ExcelUtility.getString(1, 1, excelfilePath, "Note_Page");
		itemPage.inputItemDescription(item_Description);
		String item_Rate=ExcelUtility.getString(1, 1, excelfilePath, "Note_Page");
		itemPage.inputItemRate(item_Rate);
		itemPage.clickon_item_Save();
		Thread.sleep(2000);
		String item = event_Title;
		itemPage.search_added_item(item);
		String adde_item = itemPage.getPre_added_Item();
		org.testng.Assert.assertEquals(event_Title, adde_item);
		
	}
	
		@Test(priority = 12)
	public void applyAndAssignLeave() throws IOException, InterruptedException 
	{	
		homePage.scrollSidepanel();
		homePage.clickOnDashboardLeave();
		String title_ofLeave_Page=PageUtilities.getTitleOfThePage(driver);
		leavePage.clickOnApplyLeaveButton();
		leavePage.clickOnLeaveTypeDropDown();
		leavePage.clickOnCasualLeave();
		String l_date = DateUtilities.getCurrentDate();
		leavePage.input_LeaveDate(l_date);
		String leave_Reason=ExcelUtility.getString(1, 0, excelfilePath, "Leave");
		Thread.sleep(2000);
		leavePage.enterReasonForLeave(leave_Reason);
		leavePage.clickOnApply_Leave_button();
		String title_ofthe_Page = PageUtilities.getTitleOfThePage(driver);
		org.testng.Assert.assertEquals(title_ofLeave_Page, title_ofthe_Page);
		 
	}
		@Test(priority = 11)
		public void add_Announcements() throws IOException, AWTException, InterruptedException {
			homePage.scrollSidepanel();
			homePage.clickOnDashboardAnnouncements();
			announcementPage.clickOn_AddAnnouncement();
			String announcment_title=ExcelUtility.getString(1, 0, excelfilePath, "Announcement")+FakerUtilities.randomNumberCreation();
			announcementPage.inputTitle(announcment_title);
			String announcment=ExcelUtility.getString(1, 1, excelfilePath, "Announcement");
			announcementPage.input_Announcement(announcment);
			String s_date = DateUtilities.getCurrentDate();
			announcementPage.input_AnnouncementStartDate(s_date);
			//LocalDate tdate= DateUtilities.getTommarowDate();
			String e_date = s_date;
			announcementPage.input_AnnouncementEndDate(e_date);
			announcementPage.scrolluptoSave();	
			announcementPage.announcement_Savebuton();
			String actual_ViewButton_Title = announcementPage.toGetActual_View_button_Title();
			String expected_ViewButton_Title = prop.getProperty("viewButton_Title");
			org.testng.Assert.assertEquals(actual_ViewButton_Title, expected_ViewButton_Title);
			

			
		}
		
		
	
	

}
