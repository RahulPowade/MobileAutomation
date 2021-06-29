package stepDefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.lang.annotation.Repeatable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.tiaa.digitalLibrary.GenericFunctions;

import com.google.common.io.Files;

import CCCommon.allCCMethods;
import TryPage.TryPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import junit.framework.Assert;
import page.commonPages.LogonPage;
import page.userManagement.AddOrCopyUser;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class CC_StepDefinition extends GenericFunctions {

	TryPage cc = new TryPage();

	/** This method used to login to the application **/

	@Given("^login \"([^\"]*)\"\"([^\"]*)\" and \"([^\"]*)\"\"([^\"]*)\" and \"([^\"]*)\"$")
	public void loginWithAndAnd(String userNameLabel, String userNameValue, String passwordLabel, String passwordValue,
			String URL) throws Throwable {
		waitForLoad(driver);
		cc.login(userNameLabel, userNameValue, passwordLabel, passwordValue, URL);
		waitForLoad(driver);
	}

	/** This method used to click on a button **/

	@And("^click \"([^\"]*)\" button$")
	public void click(String label) throws Throwable {
		cc.clickBtn(label);
	}

	/** This method is used to enter value in a field **/

	@And("^enter \"([^\"]*)\" in \"([^\"]*)\"$")
	public void enterIn(String value, String label) throws Throwable {
//		waitForLoad(driver);
		cc.enterTextInTextBox(value, label);
//		waitForLoad(driver);
	}

	/**
	 * This method used to wait for certain seconds before the next step executed
	 **/

	@And("^wait \"([^\"]*)\" ms$")
	public void waitForMs(String value) throws Throwable {
		int n = Integer.parseInt(value);
		Thread.sleep(n);
	}

	/** This method is used to navigate to a URL **/

	@And("^navigate \"([^\"]*)\" url$")
	public void navigateURL(String value) throws Throwable {
		cc.navigateToURL(value);
	}

	/** This method is used to click a link **/

	@And("^click \"([^\"]*)\" link$")
	public void clickTheLink(String label) throws Throwable {
		cc.clickOnLink(label);
	}

	/** This method is used to select a value from the dropdowm **/

	@And("^select \"([^\"]*)\" from \"([^\"]*)\" dropdown$")
	public void selectValueFromDropdown(String value, String label) throws Throwable {
		cc.selectDropdownValue(value, label);
	}

	/** This method is used to click on a tab **/

	@And("^click \"([^\"]*)\" tab$")
	public void clickOnTab(String label) throws Throwable {
		cc.clickTab(label);
	}

	/** This method is used click on a menu **/

	@And("^click \"([^\"]*)\" menu$")
	public void clickOnMenu(String label) throws Throwable {
		cc.clickTab(label);
	}

	/** This method is used to click on a text **/

	@And("^click \"([^\"]*)\" text$")
	public void clickText(String label) throws Throwable {
		cc.clickOnText(label);
	}

	/** This method is used to expand a list **/

	@And("^click \"([^\"]*)\" expandList$")
	public void clickOnExpandList(String label) throws Throwable {
		cc.clickOnExpandList(label);
	}

	/** This method is used to click a checkbox **/

	@And("^click \"([^\"]*)\" checkbox$")
	public void clickOnCheckbox(String label) throws Throwable {
		cc.clickRadioBtnCheckbox(label);
	}

	/** This method is used to hover over **/

	@And("^hoverover \"([^\"]*)\" tab$")
	public void moverHoverOnTab(String label) throws Throwable {
		cc.hoverOverTab(label);
	}

	/** This method is used to hover over **/

	@And("^hoverover \"([^\"]*)\" menu$")
	public void moverHoverOnMenu(String label) throws Throwable {
		cc.hoverOverTab(label);
	}

	/** This method is used to verify the current URL **/

	@And("^verify \"([^\"]*)\" currenturl$")
	public void verifyTheCurrent(String value) throws Throwable {
		cc.verifyCurrentURL(value);
	}

	/** This method is used to verify any message **/

	@And("^verify \"([^\"]*)\" message$")
	public void verifyMessageDisplayed(String value) throws Throwable {
		cc.verifyTextIsDisplayed(value);
	}

	/** This method is used to verify any text **/

	@And("^verify \"([^\"]*)\" text$")
	public void verifyTextDisplayed(String value) throws Throwable {
		cc.verifyTextIsDisplayed(value);
	}

	/** This method is used to click on a radiobutton **/

	@And("^click \"([^\"]*)\" radiobutton$")
	public void clickOnRadioButton(String label) throws Throwable {
		cc.clickRadioBtnCheckbox(label);
	}

	/** This method is used to scroll down with pixels **/

	@And("^scroll \"([^\"]*)\" pixels$")
	public void scrollPixels(String value) throws Throwable {
		cc.scrollByPixels(value);
	}

	/** This method is used to click on radiobutton in a section **/

	@And("^click \"([^\"]*)\" radiobutton in \"([^\"]*)\"$")
	public void clickRadioButtonOf(String label, String section) throws Throwable {
		cc.clickRadioBtnCheckboxInSection(label, section);
	}

	/** This method is used to press keyboard **/

	@And("^press \"([^\"]*)\" key$")
	public void pressKey(String value) throws Throwable {
		cc.pressKeyboardKey(value);
	}

	/** This method is used to select a date from the date picker / calendar **/

	@Given("^select \"([^\"]*)\" as \"([^\"]*)\" datepicker$")
	public void select_as_datepicker(String label, String value) throws Throwable {
		cc.dateSelection(label, value);
	}

	/** This method is used to verify if a button is enabled or disabled **/

	@Then("^verify \"([^\"]*)\" button \"([^\"]*)\"$")
	public void verifyButtonEnabledorDisabled(String value, String status) throws Throwable {

		cc.verifyBtnEnabledOrDisabled(value, status);
	}

	/** This method is used to repeat the step n times **/

	@And("^repeat \"([^\"]*)\" times$")
	public void repeatTimes(String value, DataTable stepsToRepeat) throws Throwable {
		int n = Integer.parseInt(value);
		cc.repeatSteps(n, stepsToRepeat);
	}

	/** This method is used to verify if the value is present in the table **/

	@And("^verify \"([^\"]*)\" table$")
	public void verifyTable(String tableName, DataTable datatable) throws Throwable {
		cc.verifyTableData(tableName, datatable);
	}

	/** This method is used to verify the page header **/

	@And("^verify \"([^\"]*)\" pageheading$")
	public void verifyPageHeading(String label) throws Throwable {
		cc.verifyPageHeading(label);
	}

	/** This method is used to verify if the button is displayed **/

	@And("^verify \"([^\"]*)\" button$")
	public void verifyButtonIsDisplayed(String label) throws Throwable {
		cc.verifyBtnIsDisplayed(label);
	}

	/** This method is used to verify if the button is displayed in a section **/

	@And("^verify \"([^\"]*)\" button in \"([^\"]*)\"$")
	public void verifyButtonOfIsDisplayed(String label, String section) throws Throwable {
		cc.verifyBtnIsDisplayedInSection(label, section);
	}

	/**
	 * This method is used to verify if the radio button is displayed in a section
	 **/

	@And("^verify \"([^\"]*)\" radiobutton in \"([^\"]*)\"$")
	public void verifyRadiobuttonOfIsDisplayed(String label, String section) throws Throwable {
		cc.verifyRadioBtnCheckboxInSectionIsDisplayed(label, section);
	}

	/** This method is used to verify if radio button is displayed **/

	@And("^verify \"([^\"]*)\" radiobutton$")
	public void verifyRadiobuttonIsDisplayed(String label) throws Throwable {
		cc.verifyRadioBtnCheckboxIsDisplayed(label);
	}

	/** This method is used to verify if the tab is displayed **/

	@And("^verify \"([^\"]*)\" tab$")
	public void verifyTabIsDisplayed(String label) throws Throwable {
		cc.verifyTabIsDisplayed(label);
	}

	/** This method is used to verify if the textbox is displayed **/

	@And("^verify \"([^\"]*)\" textbox$")
	public void verifyTextboxIsDisplayed(String label) throws Throwable {
		cc.verifyTextboxIsDisplayed(label);
	}

	/** This method is used to verify if the checkbox is displayed **/

	@And("^verify \"([^\"]*)\" checkbox$")
	public void verifyCheckboxIsDisplayed(String label) throws Throwable {
		cc.verifyRadioBtnCheckboxIsDisplayed(label);
	}

	/** This method is used to verify if the checkbox is displayed in a section **/

	@And("^verify \"([^\"]*)\" checkbox in \"([^\"]*)\"$")
	public void verifyCheckboxOfIsDisplayed(String label, String section) throws Throwable {
		cc.verifyRadioBtnCheckboxInSectionIsDisplayed(label, section);
	}

	/** This method is used to verify if the section heading is displayed **/

	@And("^verify \"([^\"]*)\" sectionheading$")
	public void verifySectionHeading(String label) throws Throwable {
		cc.verifySectionHeading(label);
	}

	/** This method is used to verify if the link is displayed **/

	@And("^verify \"([^\"]*)\" link$")
	public void verifyLinkIsDisplayed(String label) throws Throwable {
		cc.verifyLinkIsDisplayed(label);
	}

	/** This method is used to verify if the dropdown is displayed **/

	@And("^verify \"([^\"]*)\" dropdown$")
	public void verifyDropdownIsDisplayed(String label) throws Throwable {
		cc.verifyDropdownIsDisplayed(label);
	}

	/**
	 * This method is used to enter random alphabets concatenated to the suffix of
	 * the input value in a text box
	 **/

	@And("^enter \"([^\"]*)\" in \"([^\"]*)\" with randomalphabet$")
	public void enterRandomAlphabet(String value, String label) throws Throwable {
		cc.enterAlphaRandom(value, label);
	}

	/**
	 * This method is used to enter random alphabets concatenated to the suffix of
	 * the input value in a text box in a section
	 **/

	@And("^enter \"([^\"]*)\" in \"([^\"]*)\" with randomalphabet in \"([^\"]*)\"$")
	public void enterRandomAlphabetInSection(String value, String label, String section) throws Throwable {
		waitForLoad(driver);
		cc.enterAlphaRandomInSection(value, label, section);
		waitForLoad(driver);
	}

	/**
	 * This method is used to enter random numbers concatenated to the suffix of the
	 * input value in a text box
	 **/

	@And("^enter \"([^\"]*)\" in \"([^\"]*)\" with randomnumber$")
	public void enterRandomNumeric(String value, String label) throws Throwable {
		cc.enterNumericRandom(value, label);
	}

	/**
	 * This method is used to enter random numbers concatenated to the suffix of the
	 * input value in a text box in a section
	 **/

	@And("^Enter \"([^\"]*)\" in \"([^\"]*)\" with randomNumber in \"([^\"]*)\"$")
	public void enterRandomNumericInsection(String value, String label, String section) throws Throwable {
		waitForLoad(driver);
		cc.enterNumericRandomInSection(value, label, section);
		waitForLoad(driver);
	}

	/** This method is used to enter data in the table **/

	@And("^enter \"([^\"]*)\" table$")
	public void enterTable(String value, DataTable dataTable) throws Throwable {
		cc.enterTableData(value, dataTable);
	}

	/** This method is used to click on an accordion **/

	@And("^click \"([^\"]*)\" accordion$")
	public void clickAccordion(String label) throws Throwable {
		cc.clickOnText(label);
	}

	/** This method is used to edit data in the table **/
	@And("^edit \"([^\"]*)\" table$")
	public void editTable(String value, DataTable dataTable) throws Throwable {
		cc.editTableData(value, dataTable);
	}

	/**
	 * This method is used to verify if data is present in the table for a specific
	 * user from Examples
	 **/

	@And("^verify \"([^\"]*)\" table for \"([^\"]*)\"$")
	public void verifyTable(String value, String user, DataTable dataTable) throws Throwable {
		cc.verifyTableDataForMultipleUsers(value, user, dataTable);
	}

	/** This method is used to click a button in a section **/

	@And("^click \"([^\"]*)\" button in \"([^\"]*)\"$")
	public void clickButtonInSection(String label, String section) throws Throwable {
		cc.clickBtnInSection(label, section);
	}

	/** This method is used to click a menu in a section **/

	@And("^click \"([^\"]*)\" menu in \"([^\"]*)\"$")
	public void clickOnMenuInSection(String label, String section) throws Throwable {
		cc.clickTabInSection(label, section);
	}

	/** This method is used to click a link in a section **/

	@And("^click \"([^\"]*)\" link in \"([^\"]*)\"$")
	public void clickOnLinkInSection(String label, String section) throws Throwable {
		waitForLoad(driver);
		cc.clickOnLinkInSection(label, section);
		waitForLoad(driver);
	}

	/** This method is used to click a checkbox in a section **/

	@And("^click \"([^\"]*)\" checkbox in \"([^\"]*)\"$")
	public void clickCheckBoxInSectionOf(String label, String section) throws Throwable {
		cc.clickRadioBtnCheckboxInSection(label, section);
	}

	/** This method is used to enter value in a field in a section **/

	@And("^enter \"([^\"]*)\" in \"([^\"]*)\" in \"([^\"]*)\"$")
	public void enterTextInSection(String value, String label, String section) throws Throwable {
		waitForLoad(driver);
		// cc.enterTextInSection(value,label, section);
		cc.enterTextInTextBoxInSection(value, label, section);
		waitForLoad(driver);
	}

	/** This method is used to select a value from the dropdown in a section **/

	@And("^select \"([^\"]*)\" from \"([^\"]*)\" dropdown in \"([^\"]*)\"$")
	public void selectValueFromDropdownInSection(String value, String label, String section) throws Throwable {
		waitForLoad(driver);
		cc.selectDropdownValueInSection(value, label, section);
		waitForLoad(driver);
	}

	/** This method is used to hover over tab in a section **/

	@And("^hoverover \"([^\"]*)\" tab in \"([^\"]*)\"$")
	public void HoverOnTabInSection(String label, String section) throws Throwable {
		waitForLoad(driver);
		cc.hoverOverTabInSection(label, section);
		waitForLoad(driver);
	}

	/** This method is used to hover over a link **/ // 0826 - - new method

	@And("^hoverover \"([^\"]*)\" link$")
	public void moverHoverOnLink(String label) throws Throwable {
		waitForLoad(driver);
		cc.hoverOverLink(label);
		waitForLoad(driver);
	}

	/** This method is used to hover over link in a section **/ // 0826 - new method
	@And("^hoverover \"([^\"]*)\" link in \"([^\"]*)\"$")
	public void hoverOverLinkInSection(String Label, String section) throws InterruptedException {
		waitForLoad(driver);
		cc.hoverOverLinkInSection(Label, section);
		waitForLoad(driver);
	}

	/**
	 * This method is used to verify the section heading is displayed in a section
	 **/

	@And("^verify \"([^\"]*)\" sectionheading in \"([^\"]*)\"$")
	public void verifySectionHeadingInSection(String value, String section) throws Throwable {
		cc.verifySectionHeadingInSection(value, section);
	}

	/** This method is used to verify the tab is displayed in a section **/

	@And("^verify \"([^\"]*)\" tab in \"([^\"]*)\"$")
	public void verifyTabIsDisplayedInSection(String label, String section) throws Throwable {
		cc.verifyTabIsDisplayedInSection(label, section);
	}

	/** This method is used to verify the textbox is displayed in a section **/

	@And("^verify \"([^\"]*)\" textbox in \"([^\"]*)\"$")
	public void verifyTextboxIsDisplayedInSection(String label, String section) throws Throwable {
		cc.verifyTextboxIsDisplayedInSection(label, section);
	}

	/** This method is used to verify the link is displayed in a section **/

	@And("^verify \"([^\"]*)\" link in \"([^\"]*)\"$")
	public void verifyLinkIsDisplayedInSection(String value, String section) throws Throwable {
		cc.verifyLinkIsDisplayedInSection(value, section);
	}

	/** This method is used to verify the dropdown is displayed in a section **/

	@And("^verify \"([^\"]*)\" dropdown in \"([^\"]*)\"$")
	public void verifyDropdownIsDisplayedInSection(String value, String section) throws Throwable {
		cc.verifyDropdownIsDisplayedInSection(value, section);
	}

	/** This method is used to verify the message is displayed in a section **/

	@And("^verify \"([^\"]*)\" message in \"([^\"]*)\"$")
	public void verifyMessageDisplayedInSection(String value, String section) throws Throwable {
		cc.verifyTextIsDisplayedInSection(value, section);
	}

	/** This method is used to verify the text is displayed in a section **/

	@And("^verify \"([^\"]*)\" text in \"([^\"]*)\"$")
	public void verifyTextDisplayedInSection(String value, String section) throws Throwable {
		cc.verifyTextIsDisplayedInSection(value, section);
	}

	/**
	 * This method is used to verify the button is enable or disable in a section
	 **/

	@Then("^verify \"([^\"]*)\" button \"([^\"]*)\" in \"([^\"]*)\"$")
	public void verifyButtonEnabledOrDisabledInSection(String label, String status, String section) throws Throwable {
		cc.verifyBtnEnabledOrDisabledInSection(label, status, section);
	}

	/**
	 * This method is used to select value from the datepicker or calendar in a
	 * section
	 **/

	@Given("^select \"([^\"]*)\" as \"([^\"]*)\" datepicker in \"([^\"]*)\"$")
	public void selectDatePickerInSection(String label, String value, String section) throws Throwable {
		cc.datePickerInSection(label, value, section);
	}

	@And("^enter \"([^\"]*)\" table for \"([^\"]*)\"$")
	public void enterTableFor(String value, String user, DataTable dataTable) throws Throwable {
		cc.enterTableDataForMultipleUsers(value, user, dataTable);
	}

	@And("^edit \"([^\"]*)\" table for \"([^\"]*)\"$")
	public void editTableFor(String value, String user, DataTable dataTable) throws Throwable {
		cc.editTableDataForMultipleUsers(value, user, dataTable);
	}

	@Given("^upload \"([^\"]*)\" file$")
	public void upload_file(String filepath) throws Throwable {
		String exe = "C:\\Users\\sontil\\git\\PlanSponserSite\\src\\test\\resources\\FileUpload.exe";
		cc.uploadFile(filepath, exe);
	}

	@Then("^verify \"([^\"]*)\" checkbox \"([^\"]*)\" in \"([^\"]*)\"$")
	public void verifycheckboxEnabledorDisabledorNotDisplayed(String label, String status, String section)
			throws Throwable {
		cc.verifyCheckBoxEnabledOrDisabledInSection(label, status, section);
	}

	@Then("^verify \"([^\"]*)\" radiobutton \"([^\"]*)\" in \"([^\"]*)\"$")
	public void verifyradiobuttonEnabledorDisabledorNotDisplayed(String label, String status, String section)
			throws Throwable {
		cc.verifyRadioBtnEnabledOrDisabledInSection(label, status, section);
	}

	@Given("^verify \"([^\"]*)\" checkbox \"([^\"]*)\"$")
	public void VerifycheckboxEnabledorDisabledorNotDisplayed(String label, String status) throws Throwable {
		cc.verifyCheckBoxEnabledOrDisabled(label, status);
	}

	@Given("^verify \"([^\"]*)\" radiobutton \"([^\"]*)\"$")
	public void VerifyradiobuttonEnabledorDisabledorNotDisplayed(String label, String status) throws Throwable {
		cc.verifyRadioBtnEnabledOrDisabled(label, status);
	}

	@Given("^verify \"([^\"]*)\" dropdown \"([^\"]*)\"$")
	public void VerifyDropdownEnabledorDisabledorNotDisplayed(String label, String status) throws Throwable {
		cc.verifyDropdownEnabledOrDisabled(label, status);
	}

	@Then("^verify \"([^\"]*)\" dropdown \"([^\"]*)\" in \"([^\"]*)\"$")
	public void VerifyDropdownEnabledorDisabledorNotDisplayedInsection(String value, String status, String section)
			throws Throwable {
		cc.verifyDropdownEnabledOrDisabledInSection(value, status, section);
	}

	@Given("^verify \"([^\"]*)\" link \"([^\"]*)\"$")
	public void VerifyLinkEnabledorDisabledorNotDisplayed(String label, String status) throws Throwable {
		cc.verifyLinkEnabledOrDisabled(label, status);
	}

	@Then("^verify \"([^\"]*)\" link \"([^\"]*)\" in \"([^\"]*)\"$")
	public void VerifyLinkEnabledorDisabledorNotDisplayedInsection(String link, String status, String section)
			throws Throwable {
		cc.verifyLinkEnabledOrDisabledInSection(link, status, section);
	}

	@Given("^verify \"([^\"]*)\" tab \"([^\"]*)\"$")
	public void VerifyTabEnabledorDisabledorNotDisplayed(String label, String status) throws Throwable {
		cc.verifyTabEnabledOrDisabled(label, status);
	}

	@Then("^verify \"([^\"]*)\" tab \"([^\"]*)\" in \"([^\"]*)\"$")
	public void VerifyTabEnabledorDisabledorNotDisplayedInsection(String label, String status, String section)
			throws Throwable {
		cc.verifyTabEnabledOrDisabledInSection(label, status, section);
	}

	@Given("^verify \"([^\"]*)\" textbox \"([^\"]*)\"$")
	public void VerifyTextboxEnabledorDisabledorNotDisplayed(String label, String status) throws Throwable {
		cc.verifyTextboxEnabledOrDisabled(label, status);
	}

	@Then("^verify \"([^\"]*)\" textbox \"([^\"]*)\" in \"([^\"]*)\"$")
	public void VerifyTextboxEnabledorDisabledorNotDisplayedInsection(String label, String status, String section)
			throws Throwable {
		cc.verifyTextboxEnabledOrDisabledInSection(label, status, section);
	}
	
	
	/** This method is used to expand a list in a section **/

	@And("^click \"([^\"]*)\" expandlist in \"([^\"]*)\"$")
	public void clickOnExpandListInSection(String label, String section) throws Throwable {
		waitForLoad(driver);
		cc.clickOnExpandListInSection(label, section);
		waitForLoad(driver);
	}
	
	@And("^click Income link$") public void clickIncomeLink() throws Throwable {
		  this.driver.findElement(By.xpath("//*[@id='incomePanelHeader']/h3/a")).click(
		  ); }
		  
		 @And("^click Add Another Row link of Social Security Income$") public void
		  clickAddAnotherRowLinkOfSocialSecurityIncome() throws Throwable {
		  this.driver.findElement(By.xpath(
		  "//*[@id='incomeEditform']/div[4]/div[6]/div[3]/a")).click();
		  Thread.sleep(3000);
		  this.driver.findElement(By.xpath("(//*[@id='ssincome_yes1'])[1]")).click();
		  Thread.sleep(3000);
		  System.out.println("%%%%%%%%%%%%%%%%%%"+this.driver.findElement(By.xpath(
		  "//*[@id='ssIncomes0']/td[3]/input[1]")).getAttribute("value")); }

		@And("^wait for page load$")
		public void waitForPageLoad() throws Throwable {
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		}

		@And("^Click Manage Firms sub tab$")
		public void clickManageFirmsSubTab() throws Throwable {
			this.driver.findElement(By.xpath("//a/*[text()='Manage Firms']")).click();
		}

	// ****To be deleted methods******
	/*
	 * @And("^Hoverover multivendor tab and click Run a Report$") public void
	 * hoveroverMultivendorTabAndClickRunAReport() throws Throwable {
	 * cc.hoverOverTab("Multi-Vendor"); cc.clickOnLink("Run a Report"); }
	 * 
	 * @And("^click contributions detail report$") public void
	 * clickContributionsDetailReport() throws Throwable { waitForLoad(driver);
	 * cc.clickOnLink("View Contributions Report");
	 * cc.clickOnLink("Contributions Detail Report"); }
	 * 
	 * @Then("^verify At a Glance SPV page is displayed$") public void
	 * verifyAtAGlanceSPVPageIsDisplayed() throws Throwable { waitForLoad(driver);
	 * cc.verifyPageHeading("Participant Account: "); }
	 * 
	 * @And("^click Income link$") public void clickIncomeLink() throws Throwable {
	 * this.driver.findElement(By.xpath("//*[@id='incomePanelHeader']/h3/a")).click(
	 * ); }
	 * 
	 * @And("^click Add Another Row link of Social Security Income$") public void
	 * clickAddAnotherRowLinkOfSocialSecurityIncome() throws Throwable {
	 * this.driver.findElement(By.xpath(
	 * "//*[@id='incomeEditform']/div[4]/div[6]/div[3]/a")).click();
	 * Thread.sleep(3000);
	 * this.driver.findElement(By.xpath("(//*[@id='ssincome_yes1'])[1]")).click();
	 * Thread.sleep(3000);
	 * System.out.println("%%%%%%%%%%%%%%%%%%"+this.driver.findElement(By.xpath(
	 * "//*[@id='ssIncomes0']/td[3]/input[1]")).getAttribute("value")); }
	 * 
	 * @Then("^add in first row$")
	 * 
	 * @And("^Add in first row$") public void addInFirstRow() throws Throwable {
	 * System.out.println("%%%%%%%%%%%%%%%%%%"+this.driver.findElement(By.xpath(
	 * "//*[@id='ssGrossIncomeTable']/thead/tr/th[2]")).getText()); }
	 * 
	 * @And("^Get value$") public void getValue() throws Throwable {
	 * Thread.sleep(3000);
	 * System.out.println("%%%%%%%%%%%%%%%%%%"+this.driver.findElement(By.xpath(
	 * "//*[@id='username']")).getAttribute("value")); }
	 * 
	 * @Given("^Navigate \"([^\"]*)\" URLA$") public void navigateURL1(String arg1)
	 * throws Throwable { apm.navigateToURL(arg1); }
	 * 
	 * @And("^click \"([^\"]*)\" buttonA$") public void clickButton1(String arg1)
	 * throws Throwable { apm.clickBtn(arg1); }
	 */

}
