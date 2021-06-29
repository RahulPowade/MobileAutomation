package TryPage;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.RegEx;
import javax.imageio.ImageIO;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.springframework.test.util.AssertionErrors;
import org.tiaa.automation.test.Hooks;
import org.tiaa.digitalLibrary.GenericFunctions;
import org.tiaa.interactions.RestAPISpecification;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.google.common.base.Function;

import config.FileUploader;
import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import net.sourceforge.htmlunit.corejs.javascript.ast.SwitchCase;
import planFocus.utility.GenericUtil;
import stepDefinitions.RunCukesTest;

public class TryPage extends GenericFunctions {

	WebDriver driver;
	GenericUtil generic;

	public TryPage() {
		this.driver = Hooks.driver;
		PageFactory.initElements(this.driver, this);
		this.generic = new GenericUtil();
	}

	public void performAction(String action, String label, String eleType) throws Exception {
		String action1 = action.toLowerCase();
		String ele = eleType.toLowerCase();

		switch (action1) {
		case "click":
			switch (ele) {
			case "button":
				clickUsingJS(this.findButton(label));
				break;
			default:
				System.out.println("Web Element provided by you is not valid.");
			}
			break;
		default:
			System.out.println("Action provided by you is not valid.");
		}
	}

	public void login(String ULabel, String User, String PLabel, String Password, String url) throws Exception {

		try {
			this.driver.navigate().to(url);
			this.driver.manage().window().maximize();

			this.findInputObj2(ULabel).sendKeys(User);
			this.findInputObj2(PLabel).sendKeys(Password);
		} catch (Exception e) {
			this.driver.manage().window().maximize();

			this.findInputObj2(ULabel).sendKeys(User);
			this.findInputObj2(PLabel).sendKeys(Password);
		}
	}

	public void clickBtn(String Blabel) throws Exception {
		clickUsingJS(this.findButton(Blabel));
	}

	public void verifyBtnIsDisplayed(String Blabel) throws Exception {
		Assert.assertTrue("The button is not displayed on the page", this.findButton(Blabel).isDisplayed());
	}

	public void clickBtnInSection(String Blabel, String section) {
		clickUsingJS(this.findButtonInSection(Blabel, section));
	}

	public void verifyBtnIsDisplayedInSection(String Blabel, String section) {
		Assert.assertTrue("The button is not displayed in the section",
				findButtonInSection(Blabel, section).isDisplayed());
	}

	public void clickRadioBtnCheckbox(String Blabel) throws Exception {
		clickUsingJS(this.findRadioButtonCheckBox(Blabel));
	}

	public void verifyRadioBtnCheckboxIsDisplayed(String Blabel) throws Exception {
		Assert.assertTrue("The button is not displayed on the page",
				this.findRadioButtonCheckBox(Blabel).isDisplayed());
	}

	public void clickRadioBtnCheckboxInSection(String Blabel, String section) throws Exception {
		clickUsingJS(this.findRadioButtonCheckBoxInSection(Blabel, section));
	}

	public void verifyRadioBtnCheckboxInSectionIsDisplayed(String Blabel, String section) throws Exception {
		Assert.assertTrue("The button is not displayed in the section",
				this.findRadioButtonCheckBoxInSection(Blabel, section).isDisplayed());
	}

	public void clickTab(String Tablabel) {
		clickUsingJS(this.findTab(Tablabel));
	}

	public void verifyTabIsDisplayed(String Tablabel) throws Exception {
		Assert.assertTrue("The Tab is not displayed on the page", this.findTab(Tablabel).isDisplayed());
	}

	public void hoverOverTab(String Tablabel) throws InterruptedException {
		this.hoverOver(this.findTab(Tablabel));
	}

	public void enterText(String text, String FieldLabel) throws Exception {
		this.findInputObj2(FieldLabel).sendKeys(text);
	}

	public void verifyTextboxIsDisplayed(String FieldLabel) throws Exception {
		Assert.assertTrue("The Text box is not displayed on the page", this.findInputObj2(FieldLabel).isDisplayed());
	}

	public WebElement enterTextInTextBox(String values, String label) throws Exception {
		List<WebElement> users = driver.findElements(By.xpath("(//*[contains(text(),'" + label
				+ "')])/../../..//input|(//*[contains(@placeholder,'" + label + "')])/../../..//input"));

		WebElement ele = null;
		boolean found = false;
		List<WebElement> users1 = driver.findElements(By.xpath(
				"(//*[text()='" + label + "'])/..//input|(//*[@placeholder='" + label + "'])/..//input|(//*[text()='"
						+ label + "'])/../..//input|(//*[@placeholder='" + label + "'])/../..//input"));

		String[] b = values.split("~");
		System.out.println(b);
		int i = 0;
		for (WebElement field1 : users1) {

			try {
				Assert.assertTrue(field1.isDisplayed());
				try {
					if (field1.isEnabled()) {
						field1.clear();
					}
				} catch (Exception exceptionTemp) {
					// TODO: handle exception
					System.out.println(exceptionTemp);
				}
				Assert.assertTrue(field1.getAttribute("value").isEmpty());
				if (field1.getAttribute("value").isEmpty()) {
					if (b.length > 1) {
						if (b[i].contentEquals("") || b[i].contentEquals(" ")) {
							i = i + 1;
						} else {
							System.out.println(b[i].trim());
							field1.sendKeys(b[i].trim());
							i = i + 1;
						}

					} else {
						field1.sendKeys(b[i].trim());
					}
				}
				ele = field1;
				found = true;
				// break;
			} catch (AssertionError e7) {
			}
			// }

			if (users.size() == 0 && users1.size() == 0)
				throw new Exception("element not found");
		}

		return ele;
	}

	public WebElement findInputObj2(String name) throws Exception {
		List<WebElement> users = driver.findElements(By.xpath(
				"(//*[contains(text(),'" + name + "')])/../../..//input|//*[contains(@placeholder,'" + name + "')]"));

		WebElement ele = null;
		boolean found = false;

		List<WebElement> users1 = driver.findElements(By.xpath("(//*[text()='" + name
				+ "'])/..//input|//*[@placeholder='" + name + "']|(//*[text()='" + name + "'])/../..//input"));
		for (WebElement field1 : users1) {
			try {
				Assert.assertTrue(field1.isDisplayed());
				// System.out.println("**********"+field1.getAttribute("value"));
				Assert.assertTrue(field1.getAttribute("value").isEmpty());
				ele = field1;
				found = true;
				break;
			} catch (AssertionError e7) {
			}
		}
		/*
		 * List<WebElement> users3 =
		 * driver.findElements(By.xpath("(//*[contains(text(),'"+name+
		 * "')])/..//input[contains(@type,'"+name+"')]|(//*[contains(text(),'"+name+
		 * "')])/../..//input[contains(@type,'"+name+"')]")); for (WebElement field1 :
		 * users3) { try { Assert.assertTrue(field1.isDisplayed()); ele = field1; found
		 * = true; break; } catch (AssertionError e7) { } }
		 * 
		 * List<WebElement> users2 =
		 * driver.findElements(By.xpath("(//*[contains(text(),'"+name+
		 * "')])/..//input[contains(@name,'"+name+"')]|(//*[contains(text(),'"+name+
		 * "')])/../..//input[contains(@name,'"+name+"')]")); for (WebElement field1 :
		 * users2) { try { Assert.assertTrue(field1.isDisplayed()); ele = field1; found
		 * = true; break; } catch (AssertionError e7) { } }
		 * 
		 * 
		 * if (users.size() == 0 && users1.size() == 0 && users2.size() == 0 &&
		 * users3.size() == 0) throw new Exception("element not found");
		 */

		if (found == false) {
			for (WebElement field : users) {
				try {
					String a = field.getAttribute("name");
					a = this.replaceDotUnderscoreWithSpace(a);
					a = this.secondWord(a);
					a = this.lowercase(a);
					Assert.assertTrue(name.contains(a));
					Assert.assertTrue(field.isDisplayed());
					ele = field;
					break;
				} catch (AssertionError e) {
					try {
						String a = field.getAttribute("name");
						a = this.replaceDotUnderscoreWithSpace(a);
						a = this.secondWord(a);
						a = this.lowercase(a);
						String b = name;
						b = this.lowercase(b);
						Assert.assertTrue(b.contains(a));
						Assert.assertTrue(field.isDisplayed());
						ele = field;
						break;
					} catch (AssertionError e1) {
						try {
							String a = field.getAttribute("name");
							a = this.replaceDotUnderscoreWithSpace(a);
							a = this.secondWord(a);
							a = this.lowercase(a);
							String b = name;
							b = this.camelCaseWithSpace(b);
							Assert.assertTrue(b.contains(a));
							Assert.assertTrue(field.isDisplayed());
							ele = field;
							break;
						} catch (AssertionError e2) {
							try {
								String a = field.getAttribute("name");
								a = this.replaceDotUnderscoreWithSpace(a);
								a = this.secondWord(a);
								a = this.lowercase(a);
								String b = name;
								b = this.camelCaseWithoutSpace(b);
								Assert.assertTrue(b.contains(a));
								Assert.assertTrue(field.isDisplayed());
								ele = field;
								break;
							} catch (AssertionError e3) {
								try {
									String a = field.getAttribute("name");
									a = this.replaceDotUnderscoreWithSpace(a);
									a = this.firstword(a);
									a = this.lowercase(a);
									String b = name;
									b = this.lowercase(b);
									Assert.assertTrue(b.contains(a));
									Assert.assertTrue(field.isDisplayed());
									ele = field;
									break;
								} catch (AssertionError e6) {

								}

							}

						}
					}
				}

				catch (ArrayIndexOutOfBoundsException e4) {
					try {
						String a = field.getAttribute("name");
						a = this.lowercase(a);
						String b = name;
						b = this.lowercase(b);
						Assert.assertTrue(b.contains(a));
						Assert.assertTrue(field.isDisplayed());
						ele = field;
						break;
					} catch (AssertionError e5) {
						try {
							String a = field.getAttribute("name");
							a = this.lowercase(a);
							String b = name;
							b = this.removeSpaces(b);
							b = this.lowercase(b);
							Assert.assertTrue(b.contains(a));
							Assert.assertTrue(field.isDisplayed());
							ele = field;
							break;
						} catch (AssertionError e3) {
						}

					}
				}
			}
		}
		if (ele == null) {
			for (WebElement field3 : users) {
				try {
					Assert.assertTrue(field3.isDisplayed());
					ele = field3;
					found = true;
					break;
				} catch (AssertionError e10) {
				}
			}
		}
		return ele;
	}

	public WebElement findButton(String name) throws Exception {

		List<WebElement> users = driver.findElements(By.xpath("//*[contains(@value,'" + name + "')]|//*[contains(@alt,'"
				+ name + "')]|//button[contains(text(),'" + name + "')]|//*[contains(text(),'" + name
				+ "')]/../..//a[contains(@role,'button')]//*[contains(text(),'" + name + "')]|//*[contains(text(),'"
				+ name + "')]/../..//a[contains(@role,'button') and contains(text(),'" + name
				+ "')]|//*[contains(text(),'" + name + "')]/../..//a[contains(@class,'btn') and contains(text(),'"
				+ name + "')]|//*[contains(text(),'" + name
				+ "')]/../..//a[contains(@class,'button') and contains(text(),'" + name + "')]|//*[contains(text(),'"
				+ name + "')]/../..//a[contains(@class,'btn')]/*[contains(text(),'" + name + "')]"));

		WebElement ele = null;
		if (users.size() == 0) {
			String x = name;
			x = this.lowercase(x);
			List<WebElement> users1 = driver.findElements(By.xpath("//*[contains(@value,'" + x
					+ "')]|//*[contains(@alt,'" + x + "')]|//button[contains(text(),'" + x + "')]|//*[contains(text(),'"
					+ x + "')]/../..//a[contains(@role,'button')]//*[contains(text(),'" + x
					+ "')]|//*[contains(text(),'" + x + "')]/../..//a[contains(@role,'button') and contains(text(),'"
					+ x + "')]|//*[contains(text(),'" + x + "')]/../..//a[contains(@class,'btn') and contains(text(),'"
					+ x + "')]|//*[contains(text(),'" + x
					+ "')]/../..//a[contains(@class,'button') and contains(text(),'" + x + "')]|//*[contains(text(),'"
					+ x + "')]/../..//a[contains(@class,'btn')]/*[contains(text(),'" + x + "')]"));
			for (WebElement field : users1) {
				try {
					Assert.assertTrue(field.isDisplayed());
					ele = field;
					break;
				} catch (AssertionError e) {

				}

			}

		}

		for (WebElement field : users) {
			try {
				Assert.assertTrue(field.isDisplayed());
				ele = field;
				break;
			} catch (AssertionError e) {

			}

		}
		if (ele == null)
			throw new Exception("Element not found");
		return ele;

	}

	/*
	 * public WebElement findButton(String name) { List<WebElement> users =
	 * driver.findElements(By.xpath("//*[contains(@value,'" + name +
	 * "')]|//*[contains(@alt,'" + name + "')]|//button[contains(text(),'" + name +
	 * "')]|//*[contains(text(),'" + name +
	 * "')]/../..//a[contains(@role,'button')]//*[contains(text(),'" + name +
	 * "')]|//*[contains(text(),'" + name +
	 * "')]/../..//a[contains(@role,'button') and contains(text(),'" + name +
	 * "')]|//*[contains(text(),'" + name +
	 * "')]/../..//a[contains(@class,'btn') and contains(text(),'" + name +
	 * "')]|//*[contains(text(),'" + name +
	 * "')]/../..//a[contains(@class,'button') and contains(text(),'" + name +
	 * "')]|//*[contains(text(),'"+name +"')]/../..//a[contains(@class,'btn')]"));
	 * WebElement ele = null;
	 * 
	 * for (WebElement field : users) { try { String a =
	 * field.getAttribute("value"); if (a == null) { a = field.getAttribute("alt");
	 * if (a == null) a = field.getText(); } a = this.firstword(a); a =
	 * this.replaceDotUnderscoreWithSpace(a); a = a + this.secondWord(a); a =
	 * this.lowercase(a); Assert.assertTrue(name.contains(a));
	 * Assert.assertTrue(field.isDisplayed()); ele = field; } catch (AssertionError
	 * e) { try { String a = field.getAttribute("name"); if (a.equals(null)) { a =
	 * field.getAttribute("alt"); if (a == null) a = field.getText(); } a =
	 * this.firstword(a); a = this.lowercase(a); String b = name; b =
	 * this.lowercase(b); Assert.assertTrue(b.contains(a));
	 * Assert.assertTrue(field.isDisplayed()); ele = field; } catch (AssertionError
	 * e1) { try { String a = field.getAttribute("name"); if (a.equals(null)) { a =
	 * field.getAttribute("alt"); if (a == null) a = field.getText(); } a =
	 * this.firstword(a); a = this.lowercase(a); String b = name; b =
	 * this.camelCaseWithSpace(b); Assert.assertTrue(b.contains(a));
	 * Assert.assertTrue(field.isDisplayed()); ele = field; } catch (AssertionError
	 * e2) { try { String a = field.getAttribute("name"); if (a.equals(null)) { a =
	 * field.getAttribute("alt"); if (a == null) a = field.getText(); } a =
	 * this.firstword(a); a = this.lowercase(a); String b = name; b =
	 * this.camelCaseWithoutSpace(b); Assert.assertTrue(b.contains(a));
	 * Assert.assertTrue(field.isDisplayed()); ele = field; } catch (AssertionError
	 * e3) {
	 * 
	 * }
	 * 
	 * } } }
	 * 
	 * catch (ArrayIndexOutOfBoundsException e4) { try { String a =
	 * field.getAttribute("name"); if (a.equals(null)) { a =
	 * field.getAttribute("alt"); if (a == null) a = field.getText(); } a =
	 * this.lowercase(a); String b = name; b = this.lowercase(b);
	 * Assert.assertTrue(b.contains(a)); Assert.assertTrue(field.isDisplayed()); ele
	 * = field; return ele; } catch (AssertionError e5) { try { String a =
	 * field.getAttribute("name"); if (a.equals(null)) { a =
	 * field.getAttribute("alt"); if (a == null) a = field.getText(); } a =
	 * this.lowercase(a); String b = name; b = this.removeSpaces(b); b =
	 * this.lowercase(b); Assert.assertTrue(b.contains(a));
	 * Assert.assertTrue(field.isDisplayed()); ele = field; return ele; } catch
	 * (AssertionError e3) { }
	 * 
	 * } } } if (ele == null) { for (WebElement field : users) {
	 * Assert.assertTrue(field.isDisplayed()); ele = field; break; } } return ele; }
	 */

	public WebElement findRadioButtonCheckBox(String name) throws Exception {
		List<WebElement> users = driver.findElements(By
				.xpath("//*[contains(text(),'" + name + "')]/../..//*[contains(@type,'checkbox')]|//*[contains(text(),'"
						+ name + "')]/../..//*[contains(@type,'radio')]/following-sibling::*[contains(text(),'" + name
						+ "')]|//*[contains(text(),'" + name
						+ "')]/../../..//*[contains(@input-type,'checkbox')]|//*[contains(text(),'" + name
						+ "')]/../../..//*[contains(@input-type,'radio')]//*[contains(text(),'" + name
						+ "')]|//*[contains(@type,'radio')][contains(@value , '" + name
						+ "')]|//*[contains(@type,'radio')][contains(@id , '" + name + "')]"));
		if (users.size() == 0)
			throw new Exception("element not found");
		WebElement ele = null;

		for (WebElement field : users) {
			try {
				Assert.assertTrue(field.isDisplayed());
				ele = field;
				break;
			} catch (AssertionError e) {

			}

		}
		if (ele == null)
			throw new Exception("Element not found");
		return ele;
	}

	public WebElement findRadioButtonCheckBoxInSection(String name, String section) throws Exception {
		List<WebElement> users = driver.findElements(By.xpath("//*[contains(text(),'" + section
				+ "')]/../..//*[contains(text(),'" + name
				+ "')]/../..//*[contains(@type,'checkbox')]|//*[contains(text(),'" + section
				+ "')]/../..//*[contains(text(),'" + name
				+ "')]/../..//*[contains(@type,'radio')]/following-sibling::*[contains(text(),'" + name + "')]"));
		if (users.size() == 0)
			throw new Exception("element not found");
		WebElement ele = null;

		for (WebElement field : users) {
			try {
				Assert.assertTrue(field.isDisplayed());
				ele = field;
				break;
			} catch (AssertionError e) {

			}

		}
		if (ele == null)
			throw new Exception("Element not found");
		return ele;
	}

	public void selectDropdownValue(String value, String name) throws InterruptedException {
		boolean found = false;
		try {
			Select dropdown2 = new Select(driver.findElement(By.xpath("(//*[text()='" + name + "'])/../..//select")));
			if (dropdown2 != null) {
				found = true;
				for (int i = 1; i <= 50; i++) {
					try {
						Select dropdown = new Select(
								driver.findElement(By.xpath("(//*[text()='" + name + "'])/../..//select")));

						String a = driver
								.findElement(By.xpath("(//*[text()='" + name + "'])/../..//select/option[" + i + "]"))
								.getText().trim();

						String[] b = value.split(" ");
						for (String arg : b) {
							Assert.assertTrue(a.contains(arg));
						}
						dropdown.selectByIndex(i - 1);
						break;
					} catch (AssertionError e) {
					}
				}
			}

			if (found == false) {

				Select dropdown1 = new Select(
						driver.findElement(By.xpath("(//*[contains(text(),'" + name + "')])/../..//select")));

				for (int i = 1; i <= 50; i++) {
					try {
						Select dropdown = new Select(
								driver.findElement(By.xpath("(//*[contains(text(),'" + name + "')])/../..//select")));

						String a = driver
								.findElement(By.xpath(
										"(//*[contains(text(),'" + name + "')])/../..//select/option[" + i + "]"))
								.getText().trim();

						String[] b = value.split(" ");
						for (String arg : b) {
							Assert.assertTrue(a.contains(arg));
						}
						dropdown.selectByIndex(i - 1);
						break;
					} catch (AssertionError e) {
					}
				}
			}
		}

		catch (org.openqa.selenium.NoSuchElementException e1) {
			WebElement overlay = driver
					.findElement(By.xpath("(//*[contains(text(),'" + name + "')])/../..//mat-select"));
			clickUsingJS(overlay);
			Thread.sleep(2000);

			WebElement option = driver.findElement(By.xpath(
					"//*[contains(text(),'" + value + "')]/../..//mat-option//*[contains(text(),'" + value + "')]"));
			clickUsingJS(option);
		}

	}

	public void verifyDropdownIsDisplayed(String name) throws Exception {
		boolean found = false;
		try {
			Select dropdown2 = new Select(driver.findElement(By.xpath("(//*[text()='" + name + "'])/../..//select")));
			if (dropdown2 != null) {
				found = true;
				try {
					Select dropdown = new Select(
							driver.findElement(By.xpath("(//*[text()='" + name + "'])/../..//select")));
				} catch (Exception e) {
					throw new Exception("The dropdown is not displayed in the page");
				}
			}

			if (found == false) {

				Select dropdown1 = new Select(
						driver.findElement(By.xpath("(//*[contains(text(),'" + name + "')])/../..//select")));

				try {
					Select dropdown = new Select(
							driver.findElement(By.xpath("(//*[contains(text(),'" + name + "')])/../..//select")));
				} catch (Exception e) {
					throw new Exception("The dropdown is not displayed in the page");
				}
			}
		}

		catch (org.openqa.selenium.NoSuchElementException e1) {
			WebElement overlay = driver
					.findElement(By.xpath("(//*[contains(text(),'" + name + "')])/../..//mat-select"));
			Assert.assertTrue("The dropdown is not displayed in the page", overlay.isDisplayed());
			Thread.sleep(2000);
		}

	}

	public void clickOnExpandList(String name) throws InterruptedException {
		try {
			List<WebElement> users = driver.findElements(By.xpath("(//*[contains(text(),'" + name
					+ "')])/../..//button[contains(text(),'" + name + "')]|(//*[contains(text(),'" + name
					+ "')])/../..//a/*[contains(text(),'" + name + "')]"));

			for (WebElement field : users) {
				try {
					String p = driver.getCurrentUrl();
					Assert.assertTrue(field.isDisplayed());

					String j = field.getText().trim();

					if (j.equals(name)) {
						String a = field.getAttribute("class").trim();
						System.out.println("@@@@@@@@@@" + field.getText());
						clickUsingJS(field);
						Thread.sleep(4000);
						String q = driver.getCurrentUrl();

						if (!p.equals(q)) {
							this.driver.navigate().back();
						}

						String b = field.getAttribute("class").trim();
						System.out.println("@@@@@@@@@@" + field.getAttribute("class").trim());

						if (!a.equals(b)) {
							break;
						}
					}

					else {
						String[] k = name.split(" ");
						for (String arg : k) {
							Assert.assertTrue(j.contains(arg));
						}

						String a = field.getAttribute("class").trim();
						clickUsingJS(field);
						Thread.sleep(4000);
						String q = driver.getCurrentUrl();

						if (!p.equals(q)) {
							this.driver.navigate().back();
						}

						String b = field.getAttribute("class").trim();

						if (!a.equals(b)) {
							break;
						}
					}
				} catch (AssertionError e) {
				}
			}
		} catch (ElementNotFoundException e2) {
			String x = name;
			x = this.lowercase(x);
			List<WebElement> users = driver
					.findElements(By.xpath("(//*[contains(text(),'" + x + "')])/../..//button[contains(text(),'" + x
							+ "')]|(//*[contains(text(),'" + x + "')])/../..//a/*[contains(text(),'" + x + "')]"));

			for (WebElement field : users) {
				try {
					String p = driver.getCurrentUrl();
					Assert.assertTrue(field.isDisplayed());

					String j = field.getText().trim();

					if (j.equals(name)) {
						String a = field.getAttribute("class").trim();
						clickUsingJS(field);
						Thread.sleep(4000);
						String q = driver.getCurrentUrl();

						if (!p.equals(q)) {
							this.driver.navigate().back();
						}

						String b = field.getAttribute("class").trim();

						if (!a.equals(b)) {
							break;
						}
					}

					else {
						String[] k = name.split(" ");
						for (String arg : k) {
							Assert.assertTrue(j.contains(arg));
						}

						String a = field.getAttribute("class").trim();
						clickUsingJS(field);
						Thread.sleep(4000);
						String q = driver.getCurrentUrl();

						if (!p.equals(q)) {
							this.driver.navigate().back();
						}

						String b = field.getAttribute("class").trim();

						if (!a.equals(b)) {
							break;
						}
					}
				} catch (ElementNotFoundException e) {
					String y = name;
					y = this.camelCaseWithSpace(y);
					List<WebElement> users1 = driver.findElements(By.xpath("(//*[contains(text(),'" + y
							+ "')])/../..//button[contains(text(),'" + y + "')]|(//*[contains(text(),'" + y
							+ "')])/../..//a/*[contains(text(),'" + y + "')]"));

					for (WebElement field1 : users1) {
						try {
							String p = driver.getCurrentUrl();
							Assert.assertTrue(field1.isDisplayed());

							String j = field1.getText().trim();

							if (j.equals(name)) {
								String a = field1.getAttribute("class").trim();
								clickUsingJS(field1);
								Thread.sleep(4000);
								String q = driver.getCurrentUrl();

								if (!p.equals(q)) {
									this.driver.navigate().back();
								}

								String b = field1.getAttribute("class").trim();

								if (!a.equals(b)) {
									break;
								}
							}

							else {
								String[] k = name.split(" ");
								for (String arg : k) {
									Assert.assertTrue(j.contains(arg));
								}

								String a = field1.getAttribute("class").trim();
								clickUsingJS(field1);
								Thread.sleep(4000);
								String q = driver.getCurrentUrl();

								if (!p.equals(q)) {
									this.driver.navigate().back();
								}

								String b = field1.getAttribute("class").trim();

								if (!a.equals(b)) {
									break;
								}
							}
						} catch (AssertionError e1) {
						}
					}
				}
			}
		}
	}

	public void clickOnExpandListInSection(String name, String section) throws InterruptedException {
		try {
			List<WebElement> users = driver.findElements(By.xpath("(//*[contains(text(),'" + section
					+ "')]/../..//*[contains(text(),'" + name + "')])/../..//button[contains(text(),'" + name
					+ "')]|(//*[contains(text(),'" + section + "')]/../..//*[contains(text(),'" + name
					+ "')])/../..//a/*[contains(text(),'" + name + "')]"));

			for (WebElement field : users) {
				try {
					String p = driver.getCurrentUrl();
					Assert.assertTrue(field.isDisplayed());

					String j = field.getText().trim();

					if (j.equals(name)) {
						String a = field.getAttribute("class").trim();
						System.out.println("@@@@@@@@@@" + field.getText());
						clickUsingJS(field);
						Thread.sleep(4000);
						String q = driver.getCurrentUrl();

						if (!p.equals(q)) {
							this.driver.navigate().back();
						}

						String b = field.getAttribute("class").trim();
						System.out.println("@@@@@@@@@@" + field.getAttribute("class").trim());

						if (!a.equals(b)) {
							break;
						}
					}

					else {
						String[] k = name.split(" ");
						for (String arg : k) {
							Assert.assertTrue(j.contains(arg));
						}

						String a = field.getAttribute("class").trim();
						clickUsingJS(field);
						Thread.sleep(4000);
						String q = driver.getCurrentUrl();

						if (!p.equals(q)) {
							this.driver.navigate().back();
						}

						String b = field.getAttribute("class").trim();

						if (!a.equals(b)) {
							break;
						}
					}
				} catch (AssertionError e) {
				}
			}
		} catch (ElementNotFoundException e2) {
			String x = name;
			x = this.lowercase(x);
			List<WebElement> users = driver
					.findElements(By.xpath("(//*[contains(text(),'" + section + "')]/../..//*[contains(text(),'" + x
							+ "')])/../..//button[contains(text(),'" + x + "')]|(//*[contains(text(),'" + section
							+ "')]/../..//*[contains(text(),'" + x + "')])/../..//a/*[contains(text(),'" + x + "')]"));

			for (WebElement field : users) {
				try {
					String p = driver.getCurrentUrl();
					Assert.assertTrue(field.isDisplayed());

					String j = field.getText().trim();

					if (j.equals(name)) {
						String a = field.getAttribute("class").trim();
						clickUsingJS(field);
						Thread.sleep(4000);
						String q = driver.getCurrentUrl();

						if (!p.equals(q)) {
							this.driver.navigate().back();
						}

						String b = field.getAttribute("class").trim();

						if (!a.equals(b)) {
							break;
						}
					}

					else {
						String[] k = name.split(" ");
						for (String arg : k) {
							Assert.assertTrue(j.contains(arg));
						}

						String a = field.getAttribute("class").trim();
						clickUsingJS(field);
						Thread.sleep(4000);
						String q = driver.getCurrentUrl();

						if (!p.equals(q)) {
							this.driver.navigate().back();
						}

						String b = field.getAttribute("class").trim();

						if (!a.equals(b)) {
							break;
						}
					}
				} catch (ElementNotFoundException e) {
					String y = name;
					y = this.camelCaseWithSpace(y);
					List<WebElement> users1 = driver.findElements(By.xpath("(//*[contains(text(),'" + section
							+ "')]/../..//*[contains(text(),'" + y + "')])/../..//button[contains(text(),'" + y
							+ "')]|(//*[contains(text(),'" + section + "')]/../..//*[contains(text(),'" + y
							+ "')])/../..//a/*[contains(text(),'" + y + "')]"));

					for (WebElement field1 : users1) {
						try {
							String p = driver.getCurrentUrl();
							Assert.assertTrue(field1.isDisplayed());

							String j = field1.getText().trim();

							if (j.equals(name)) {
								String a = field1.getAttribute("class").trim();
								clickUsingJS(field1);
								Thread.sleep(4000);
								String q = driver.getCurrentUrl();

								if (!p.equals(q)) {
									this.driver.navigate().back();
								}

								String b = field1.getAttribute("class").trim();

								if (!a.equals(b)) {
									break;
								}
							}

							else {
								String[] k = name.split(" ");
								for (String arg : k) {
									Assert.assertTrue(j.contains(arg));
								}

								String a = field1.getAttribute("class").trim();
								clickUsingJS(field1);
								Thread.sleep(4000);
								String q = driver.getCurrentUrl();

								if (!p.equals(q)) {
									this.driver.navigate().back();
								}

								String b = field1.getAttribute("class").trim();

								if (!a.equals(b)) {
									break;
								}
							}
						} catch (AssertionError e1) {
						}
					}
				}
			}
		}
	}

	/*
	 * public void clicklink(String name) { List<WebElement> users =
	 * driver.findElements(By.linkText(name));
	 * 
	 * if (users.size() == 0) { List<WebElement> users1 =
	 * driver.findElements(By.partialLinkText(name)); try { for (WebElement field :
	 * users1) { Assert.assertTrue(field.isDisplayed()); clickUsingJS(field); break;
	 * } } catch(AssertionError e) { } }
	 * 
	 * try { for (WebElement field : users) {
	 * Assert.assertTrue(field.isDisplayed()); clickUsingJS(field); break; } }
	 * catch(AssertionError e) { }
	 * 
	 * }
	 */

	public void clicklink(String name) {
		try {
			List<WebElement> users = driver.findElements(
					By.xpath("//a[contains(text(),'" + name + "')]|//a/*[contains(text(),'" + name + "')]"));
			if (users.size() == 0) {
				Assert.assertTrue(driver
						.findElement(
								By.xpath("//a[contains(text(),'" + name + "')]|//a/*[contains(text(),'" + name + "')]"))
						.isDisplayed());
			}

			for (WebElement field : users) {
				if (field.isDisplayed()) {
					String a = field.getText().trim();
					int m = a.length();
					String b = name;
					int n = b.length();
					Assert.assertTrue(n == m);
					Assert.assertTrue(field.isDisplayed());
					clickUsingJS(field);
					break;
				}
			}

		} catch (ElementNotFoundException | AssertionError | org.openqa.selenium.NoSuchElementException e2) {
			try {
				String x = name;
				x = this.camelCaseWithSingleCharacterInLower(x);
				List<WebElement> users = driver.findElements(
						By.xpath("//a[contains(text(),'" + x + "')]|//a/*[contains(text(),'" + x + "')]"));
				if (users.size() == 0) {
					Assert.assertTrue(driver
							.findElement(
									By.xpath("//a[contains(text(),'" + x + "')]|//a/*[contains(text(),'" + x + "')]"))
							.isDisplayed());
				}

				for (WebElement field : users) {

					String a = field.getText().trim();
					int m = a.length();
					int n = name.length();
					Assert.assertTrue(n == m);
					Assert.assertTrue(field.isDisplayed());
					clickUsingJS(field);
					break;
				}
			} catch (ElementNotFoundException | AssertionError | org.openqa.selenium.NoSuchElementException e) {
				try {
					String y = name;
					y = this.camelCaseWithSpace(y);
					List<WebElement> users1 = driver.findElements(
							By.xpath("//a[contains(text(),'" + y + "')]|//a/*[contains(text(),'" + y + "')]"));
					if (users1.size() == 0) {
						Assert.assertTrue(driver
								.findElement(By
										.xpath("//a[contains(text(),'" + y + "')]|//a/*[contains(text(),'" + y + "')]"))
								.isDisplayed());
					}

					for (WebElement field1 : users1) {

						String a = field1.getText().trim();
						int m = a.length();
						int n = name.length();
						Assert.assertTrue(n == m);
						Assert.assertTrue(field1.isDisplayed());
						clickUsingJS(field1);
						break;
					}
				} catch (ElementNotFoundException | AssertionError | org.openqa.selenium.NoSuchElementException e1) {
					try {
						String u = name;
						u = this.lowercase(u);
						List<WebElement> users2 = driver.findElements(
								By.xpath("//a[contains(text(),'" + u + "')]|//a/*[contains(text(),'" + u + "')]"));
						if (users2.size() == 0) {
							Assert.assertTrue(driver
									.findElement(By.xpath(
											"//a[contains(text(),'" + u + "')]|//a/*[contains(text(),'" + u + "')]"))
									.isDisplayed());
						}

						for (WebElement field2 : users2) {

							String a = field2.getText().trim();
							int m = a.length();
							int n = name.length();
							Assert.assertTrue(n == m);
							Assert.assertTrue(field2.isDisplayed());
							clickUsingJS(field2);
							break;
						}
					} catch (ElementNotFoundException | AssertionError
							| org.openqa.selenium.NoSuchElementException e3) {
						try {
							List<WebElement> users3 = driver.findElements(By.xpath(
									"//a[contains(text(),'" + name + "')]|//a/*[contains(text(),'" + name + "')]"));
							if (users3.size() == 0) {
								Assert.assertTrue(driver.findElement(By.xpath(
										"//a[contains(text(),'" + name + "')]|//a/*[contains(text(),'" + name + "')]"))
										.isDisplayed());
							}

							for (WebElement field2 : users3) {

								String a = field2.getText().trim();
								Assert.assertTrue(field2.isDisplayed());
								clickUsingJS(field2);
								break;
							}
						} catch (ElementNotFoundException | AssertionError
								| org.openqa.selenium.NoSuchElementException e4) {
							try {
								List<WebElement> users = driver.findElements(
										By.xpath("//a/*[text()='" + name + "']|//a[text(),'" + name + "']"));
								if (users.size() == 0) {
									Assert.assertTrue(driver
											.findElement(
													By.xpath("//a/*[text()='" + name + "']|//a[text()='" + name + "']"))
											.isDisplayed());
								}

								for (WebElement field : users) {
									Assert.assertTrue(field.isDisplayed());
									clickUsingJS(field);
									break;
								}

							} catch (AssertionError e6) {
							}
						}
					}
				}
			}
		}
	}

	public WebElement verifyLinkIsDisplayed(String name) {
		WebElement ele = null;
		try {
			List<WebElement> users = driver.findElements(
					By.xpath("//a[contains(text(),'" + name + "')]|//a/*[contains(text(),'" + name + "')]"));
			if (users.size() == 0) {
				Assert.assertTrue(driver
						.findElement(
								By.xpath("//a[contains(text(),'" + name + "')]|//a/*[contains(text(),'" + name + "')]"))
						.isDisplayed());
			}

			for (WebElement field : users) {
				String a = field.getText().trim();
				int m = a.length();
				String b = name;
				int n = b.length();
				Assert.assertTrue(n == m);
				Assert.assertTrue(field.isDisplayed());
				ele = field;
				break;
			}

		} catch (ElementNotFoundException | AssertionError | org.openqa.selenium.NoSuchElementException e2) {
			try {
				String x = name;
				x = this.camelCaseWithSingleCharacterInLower(x);
				List<WebElement> users = driver.findElements(
						By.xpath("//a[contains(text(),'" + x + "')]|//a/*[contains(text(),'" + x + "')]"));
				if (users.size() == 0) {
					Assert.assertTrue(driver
							.findElement(
									By.xpath("//a[contains(text(),'" + x + "')]|//a/*[contains(text(),'" + x + "')]"))
							.isDisplayed());
				}

				for (WebElement field : users) {

					String a = field.getText().trim();
					int m = a.length();
					int n = name.length();
					Assert.assertTrue(n == m);
					Assert.assertTrue(field.isDisplayed());
					ele = field;
					break;
				}
			} catch (ElementNotFoundException | AssertionError | org.openqa.selenium.NoSuchElementException e) {
				try {
					String y = name;
					y = this.camelCaseWithSpace(y);
					List<WebElement> users1 = driver.findElements(
							By.xpath("//a[contains(text(),'" + y + "')]|//a/*[contains(text(),'" + y + "')]"));
					if (users1.size() == 0) {
						Assert.assertTrue(driver
								.findElement(By
										.xpath("//a[contains(text(),'" + y + "')]|//a/*[contains(text(),'" + y + "')]"))
								.isDisplayed());
					}

					for (WebElement field1 : users1) {

						String a = field1.getText().trim();
						int m = a.length();
						int n = name.length();
						Assert.assertTrue(n == m);
						Assert.assertTrue(field1.isDisplayed());
						ele = field1;
						break;
					}
				} catch (ElementNotFoundException | AssertionError | org.openqa.selenium.NoSuchElementException e1) {
					try {
						String u = name;
						u = this.lowercase(u);
						List<WebElement> users2 = driver.findElements(
								By.xpath("//a[contains(text(),'" + u + "')]|//a/*[contains(text(),'" + u + "')]"));
						if (users2.size() == 0) {
							Assert.assertTrue(driver
									.findElement(By.xpath(
											"//a[contains(text(),'" + u + "')]|//a/*[contains(text(),'" + u + "')]"))
									.isDisplayed());
						}

						for (WebElement field2 : users2) {

							String a = field2.getText().trim();
							int m = a.length();
							int n = name.length();
							Assert.assertTrue(n == m);
							Assert.assertTrue(field2.isDisplayed());
							ele = field2;
							break;
						}
					} catch (ElementNotFoundException | AssertionError
							| org.openqa.selenium.NoSuchElementException e3) {
						try {
							List<WebElement> users3 = driver.findElements(By.xpath(
									"//a[contains(text(),'" + name + "')]|//a/*[contains(text(),'" + name + "')]"));
							if (users3.size() == 0) {
								Assert.assertTrue(driver.findElement(By.xpath(
										"//a[contains(text(),'" + name + "')]|//a/*[contains(text(),'" + name + "')]"))
										.isDisplayed());
							}

							for (WebElement field2 : users3) {

								String a = field2.getText().trim();
								Assert.assertTrue(field2.isDisplayed());
								ele = field2;
								break;
							}
						} catch (ElementNotFoundException | AssertionError
								| org.openqa.selenium.NoSuchElementException e4) {
							try {
								List<WebElement> users = driver.findElements(
										By.xpath("//a/*[text()='" + name + "']|//a[text(),'" + name + "']"));
								if (users.size() == 0) {
									Assert.assertTrue(driver
											.findElement(
													By.xpath("//a/*[text(),'" + name + "']|//a[text(),'" + name + "']"))
											.isDisplayed());
								}

								for (WebElement field : users) {
									Assert.assertTrue(field.isDisplayed());
									ele = field;
									break;
								}

							} catch (AssertionError e6) {
							}
						}
					}
				}
			}
		}
		return ele;
	}

	public WebElement findTab(String name) {
		WebElement ele = null;
		try {
			List<WebElement> users = driver.findElements(By.xpath("(//*[contains(text(),'" + name
					+ "')])/../..//a[contains(text(),'" + name + "') and contains(@tabindex,'')]|(//*[contains(text(),'"
					+ name + "')])/../..//a[@href]/span[contains(text(),'" + name + "')]|(//*[contains(text(),'" + name
					+ "')])/../..//a[contains(@title,'" + name + "') and contains(@href,'')]|(//*[contains(text(),'"
					+ name + "')])/parent::*[contains(@role,'tab')]//*[contains(text(),'" + name + "')]"));
			if (users.size() == 0) {
				this.driver.findElement(By.xpath("(//*[contains(text(),'" + name + "')])/../..//a[contains(text(),'"
						+ name + "') and contains(@tabindex,'')]|(//*[contains(text(),'" + name
						+ "')])/../..//a[@href]/span[contains(text(),'" + name + "')]|(//*[contains(text(),'" + name
						+ "')])/../..//a[contains(@title,'" + name + "') and contains(@href,'')]|(//*[contains(text(),'"
						+ name + "')])/parent::*[contains(@role,'tab')]//*[contains(text(),'" + name + "')]"))
						.isDisplayed();
			}

			for (WebElement field : users) {
				try {
					Assert.assertTrue(field.isDisplayed());
					ele = field;
					break;
				} catch (AssertionError e) {
				}
			}
		} catch (ElementNotFoundException | AssertionError | org.openqa.selenium.NoSuchElementException e2) {
			try {
				String x = name;
				x = this.camelCaseWithSpace(x);
				List<WebElement> users = driver.findElements(By.xpath("(//*[contains(text(),'" + x
						+ "')])/../..//a[contains(text(),'" + x
						+ "') and contains(@tabindex,'')]|(//*[contains(text(),'" + x
						+ "')])/../..//a[@href]/span[contains(text(),'" + x + "')]|(//*[contains(text(),'" + x
						+ "')])/../..//a[contains(@title,'" + x + "') and contains(@href,'')]|(//*[contains(text(),'"
						+ x + "')])/parent::*[contains(@role,'tab')]"));
				if (users.size() == 0) {
					Assert.assertTrue(
							driver.findElement(By.xpath("(//*[contains(text(),'" + x + "')])/../..//a[contains(text(),'"
									+ x + "') and contains(@tabindex,'')]|(//*[contains(text(),'" + x
									+ "')])/../..//a[@href]/span[contains(text(),'" + x + "')]|(//*[contains(text(),'"
									+ x + "')])/../..//a[contains(@title,'" + x
									+ "') and contains(@href,'')]|(//*[contains(text(),'" + x
									+ "')])/parent::*[contains(@role,'tab')]")).isDisplayed());
				}

				for (WebElement field : users) {

					Assert.assertTrue(field.isDisplayed());
					ele = field;
					break;
				}
			} catch (ElementNotFoundException | AssertionError | org.openqa.selenium.NoSuchElementException e) {
				String y = name;
				y = this.lowercase(y);
				List<WebElement> users1 = driver.findElements(By.xpath("(//*[contains(text(),'" + y
						+ "')])/../..//a[contains(text(),'" + y
						+ "') and contains(@tabindex,'')]|(//*[contains(text(),'" + y
						+ "')])/../..//a[@href]/span[contains(text(),'" + y + "')]|(//*[contains(text(),'" + y
						+ "')])/../..//a[contains(@title,'" + y + "') and contains(@href,'')]|(//*[contains(text(),'"
						+ y + "')])/parent::*[contains(@role,'tab')]"));
				if (users1.size() == 0) {
					Assert.assertTrue(
							driver.findElement(By.xpath("(//*[contains(text(),'" + y + "')])/../..//a[contains(text(),'"
									+ y + "') and contains(@tabindex,'')]|(//*[contains(text(),'" + y
									+ "')])/../..//a[@href]/span[contains(text(),'" + y + "')]|(//*[contains(text(),'"
									+ y + "')])/../..//a[contains(@title,'" + y
									+ "') and contains(@href,'')]|(//*[contains(text(),'" + y
									+ "')])/parent::*[contains(@role,'tab')]")).isDisplayed());
				}

				for (WebElement field1 : users1) {
					try {
						Assert.assertTrue(field1.isDisplayed());
						ele = field1;
						break;
					} catch (AssertionError e1) {
					}
				}
			}
		}
		return ele;
	}

	public void hoverOver(WebElement ele) throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(ele).build().perform();
		Thread.sleep(3000);
	}

	public WebElement findButtonInSection(String name, String section) {
		List<WebElement> users = driver
				.findElements(By.xpath("//*[contains(text(),'" + section + "')]/../..//*[contains(@value,'" + name
						+ "')]|//*[contains(text(),'" + section + "')]/../..//*[contains(@alt,'" + name
						+ "')]|//*[contains(text(),'" + section + "')]/../..//button[contains(text(),'" + name
						+ "')]|//*[contains(text(),'" + section + "')]/../..//*[contains(text(),'" + name
						+ "')]/../..//a[contains(@role,'button')]//*[contains(text(),'" + name
						+ "')]|//*[contains(text(),'" + section + "')]/../..//*[contains(text(),'" + name
						+ "')]/../..//a[contains(@role,'button') and contains(text(),'" + name
						+ "')]|//*[contains(text(),'" + section + "')]/../..//*[contains(text(),'" + name
						+ "')]/../..//a[contains(@class,'btn') and contains(text(),'" + name
						+ "')]|//*[contains(text(),'" + section + "')]/../..//*[contains(text(),'" + name
						+ "')]/../..//a[contains(@class,'button') and contains(text(),'" + name
						+ "')]|//*[contains(text(),'" + section + "')]/../..//*[contains(text(),'" + name
						+ "')]/../..//a[contains(@class,'btn')]/*[contains(text(),'" + name + "')]"));
		WebElement ele = null;

		for (WebElement field : users) {
			try {
				String a = field.getAttribute("value");
				if (a == null) {
					a = field.getAttribute("alt");
					if (a == null)
						a = field.getText();
				}
				a = this.firstword(a);
				a = this.replaceDotUnderscoreWithSpace(a);
				a = a + this.secondWord(a);
				a = this.lowercase(a);
				Assert.assertTrue(name.contains(a));
				Assert.assertTrue(field.isDisplayed());
				ele = field;
			} catch (AssertionError e) {
				try {
					String a = field.getAttribute("name");
					if (a.equals(null)) {
						a = field.getAttribute("alt");
						if (a == null)
							a = field.getText();
					}
					a = this.firstword(a);
					a = this.lowercase(a);
					String b = name;
					b = this.lowercase(b);
					Assert.assertTrue(b.contains(a));
					Assert.assertTrue(field.isDisplayed());
					ele = field;
				} catch (AssertionError e1) {
					try {
						String a = field.getAttribute("name");
						if (a.equals(null)) {
							a = field.getAttribute("alt");
							if (a == null)
								a = field.getText();
						}
						a = this.firstword(a);
						a = this.lowercase(a);
						String b = name;
						b = this.camelCaseWithSpace(b);
						Assert.assertTrue(b.contains(a));
						Assert.assertTrue(field.isDisplayed());
						ele = field;
					} catch (AssertionError e2) {
						try {
							String a = field.getAttribute("name");
							if (a.equals(null)) {
								a = field.getAttribute("alt");
								if (a == null)
									a = field.getText();
							}
							a = this.firstword(a);
							a = this.lowercase(a);
							String b = name;
							b = this.camelCaseWithoutSpace(b);
							Assert.assertTrue(b.contains(a));
							Assert.assertTrue(field.isDisplayed());
							ele = field;
						} catch (AssertionError e3) {

						}

					}
				}
			}

			catch (ArrayIndexOutOfBoundsException e4) {
				try {
					String a = field.getAttribute("name");
					if (a.equals(null)) {
						a = field.getAttribute("alt");
						if (a == null)
							a = field.getText();
					}
					a = this.lowercase(a);
					String b = name;
					b = this.lowercase(b);
					Assert.assertTrue(b.contains(a));
					Assert.assertTrue(field.isDisplayed());
					ele = field;
					return ele;
				} catch (AssertionError e5) {
					try {
						String a = field.getAttribute("name");
						if (a.equals(null)) {
							a = field.getAttribute("alt");
							if (a == null)
								a = field.getText();
						}
						a = this.lowercase(a);
						String b = name;
						b = this.removeSpaces(b);
						b = this.lowercase(b);
						Assert.assertTrue(b.contains(a));
						Assert.assertTrue(field.isDisplayed());
						ele = field;
						return ele;
					} catch (AssertionError e3) {
					}

				}
			}
		}
		if (ele == null) {
			for (WebElement field : users) {
				Assert.assertTrue(field.isDisplayed());
				ele = field;
				break;
			}
		}
		return ele;
	}

	@FindBy(xpath = "//*[text()='Not right now']/..")
	WebElement popClose;

	public void close_Popup() {
		try {
			// driver.navigate().refresh();
			if (popClose.isDisplayed()) {
				popClose.click();
			}
		} catch (Exception e) {

		}

		// this.driver.switchTo().alert();
		// Actions action = new Actions(driver);
		// action.sendKeys(Keys.ESCAPE).build().perform();

	}

	public void compareImage(String image1, String image2) throws IOException {
		BufferedImage imgA = ImageIO.read(new File("F:\\temp\\" + image1 + ".png"));
		BufferedImage imgB = ImageIO.read(new File("F:\\temp\\" + image2 + ".png"));
		// subtractImages(imgA,imgB);
		subtractImages1(imgA, imgB);
		// boolean same=bufferedImagesEqual(imgA,imgB);
		// if(same==true)
		// System.out.println("both images are same *****************");
		// else
		// System.out.println("both images are different *****************");
	}

	boolean bufferedImagesEqual(BufferedImage img1, BufferedImage img2) throws IOException {
		if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) {
			for (int x = 0; x < img1.getWidth(); x++) {
				for (int y = 0; y < img1.getHeight(); y++) {
					if (img1.getRGB(x, y) != img2.getRGB(x, y))
						return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}

	private static void subtractImages(BufferedImage image1, BufferedImage image2) throws IOException {
		BufferedImage image3 = new BufferedImage(image1.getWidth(), image1.getHeight(), image1.getType());
		int color;
		for (int x = 0; x < image1.getWidth(); x++)
			for (int y = 0; y < image1.getHeight(); y++) {
				color = Math.abs(image2.getRGB(x, y) - image1.getRGB(x, y));
				image3.setRGB(x, y, color);
			}
		ImageIO.write(image3, "png", new File("F:\\temp\\image.png"));
	}

	private static void subtractImages1(BufferedImage image1, BufferedImage image2) throws IOException {
		BufferedImage image3 = new BufferedImage(image1.getWidth(), image1.getHeight(), image1.getType());
		int color;
		for (int x = 0; x < image1.getWidth(); x++)
			for (int y = 0; y < image1.getHeight(); y++) {
				if (image1.getRGB(x, y) != image2.getRGB(x, y)) {
					image3.setRGB(x, y, new Color(0, 255, 0).getRGB());
				} else {
					color = image2.getRGB(x, y);
					int color1 = image1.getRGB(x, y);
					image3.setRGB(x, y, color);
					image3.setRGB(x, y, color1);
				}
			}
		ImageIO.write(image3, "png", new File("F:\\temp\\output.png"));
	}

	public void setBrowser(String browser) {
		switch (browser.toLowerCase().trim()) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver",
					"C:/Users/Powade/git/PlanSponserSite/src/funcTest/resources/drivers/chromedriver.exe");
			this.driver = new ChromeDriver();
			break;

		case "firefox":
			System.setProperty("webdriver.gecko.driver",
					"C:/Users/Powade/git/PlanSponserSite/src/funcTest/resources/drivers/geckodriver.exe");
			this.driver = new FirefoxDriver();
			break;

		case "ie":
		case "internet explorer":
			System.setProperty("webdriver.ie.driver",
					"C:/Users/Powade/git/PlanSponserSite/src/funcTest/resources/drivers/IEDriverServer.exe");
			this.driver = new InternetExplorerDriver();
			break;

		default:
			throw new RuntimeException("Invalid Internal Simulation Provided...");
		}

	}

	public void isSelected() {
		WebElement e = driver.findElement(By.xpath("//*[@id='main']/label[2]/div"));
		System.out.println("***********" + e.isSelected());
	}

	public String firstword(String words) {
		String a = words;
		String[] cnt = a.split(" ");
		return cnt[0];
	}

	public String secondWord(String words) {
		String a = words;
		String[] cnt = a.split(" ");
		return cnt[1];
	}

	public String lowercase(String words) {
		String a = words;
		String b = a.toLowerCase().trim();
		return b;
	}

	public String uppercase(String words) {
		String a = words;
		String b = a.toUpperCase().trim();
		return b;
	}

	public String removeSpaces(String words) {
		String a = words;
		String b = a.replaceAll("\\ ", "");
		return b;
	}

	public String replaceDotUnderscoreWithSpace(String words) {
		String a = words;
		String b = a.replaceAll("\\.|\\_", " ");
		return b;
	}

	public String camelCaseWithSpace(String words) {
		String a = words;
		final char[] delimiters = { ' ', '_' };
		String b = WordUtils.capitalizeFully(a, delimiters);
		return b;
	}

	public String camelCaseWithoutSpace(String words) {
		String a = words;
		final char[] delimiters = { ' ', '_' };
		String b = WordUtils.capitalizeFully(a, delimiters);
		String c = b.replaceAll("\\ ", "");
		return c;
	}

	public String firstLowerSceondCamel(String words) {
		String a = words;
		final char[] delimiters = { ' ', '_' };
		String b = WordUtils.capitalizeFully(a, delimiters);
		String c = b.replaceAll("\\ ", "");
		return c;
	}

	public String camelCaseWithSingleCharacterInLower(String words) {
		String a = words;
		final char[] delimiters = { ' ', '_' };
		String b = WordUtils.capitalizeFully(a, delimiters);
		String[] cnt = b.split(" ");
		String r = "";
		for (String x : cnt) {
			if (x.length() <= 2)
				x = x.toLowerCase();
			r = r + " " + x;
		}
		r = r.trim();
		System.out.println(r);
		return r;
	}

	public List<String> findArguments(String line) {
		ArrayList<String> b = new ArrayList<String>();
		Pattern p = Pattern.compile("\"([^\"]*)\"");
		Matcher m = p.matcher(line);
		while (m.find()) {
			// System.out.println(m.group(1));
			b.add(m.group(1));
		}
		return b;
	}

	public String findStep(String line) {
		String b = line.replaceAll("\"(.*?)\"", "*");
		return b;
	}

	// code for slider-sneha
	public void Slider_select(int step) {

		WebElement slider = driver.findElement(By.xpath("//div[@id='slider']/a"));
		Actions move = new Actions(driver);
		Action action = (Action) move.dragAndDropBy(slider, step, 0).build();
		((Actions) action).perform();

	}

	public void verifyBtnEnabledOrDisabled(String name, String value) throws Exception {
		WebElement btn_value = null;

		btn_value = findButton(name);
		if (value.contains("not displayed")) {
			ElementNotDisplayed(btn_value);
		} else {
			Elementenableddisabled(btn_value, value);
		}

	}

	public void repeatSteps(int arg1, DataTable arg2) throws Throwable {
		List<String> toDo = arg2.asList(String.class);

		for (int i = 0; i < arg1; i++) {
			for (int j = 0; j <= toDo.size() - 1; j++) {
				String step = this.findStep(toDo.get(j));
				switch (step) {
				case "Navigate * URL":
					this.navigateToURL(this.findArguments(toDo.get(j)).get(0));
					break;
				case "Click * button":
					this.clickBtn(this.findArguments(toDo.get(j)).get(0));
					break;
				case "Click * link":
					this.clickOnLink(this.findArguments(toDo.get(j)).get(0));
					break;
					
				case "click * link in *":
					this.clickOnLinkInSection(this.findArguments(toDo.get(j)).get(0),this.findArguments(toDo.get(j)).get(1));
					break;
				case "Select * from * dropdown":
					this.selectDropdownValue(this.findArguments(toDo.get(j)).get(0),
							this.findArguments(toDo.get(j)).get(1));
					break;
				case "Hoverover * tab":
					this.hoverOverTab(this.findArguments(toDo.get(j)).get(0));
					break;
				case "Click * tab":
					this.clickTab(this.findArguments(toDo.get(j)).get(0));
					break;
				case "Verify * currentURL":
					this.verifyCurrentURL(this.findArguments(toDo.get(j)).get(0));
					break;
				case "Click * radio button of *":
					this.clickRadioBtnCheckboxInSection(this.findArguments(toDo.get(j)).get(0),
							this.findArguments(toDo.get(j)).get(1));
					break;
				case "Enter * in *":
					this.enterText(this.findArguments(toDo.get(j)).get(0), this.findArguments(toDo.get(j)).get(1));
					break;
				case "Verify * text":
					this.verifyTextIsDisplayed(this.findArguments(toDo.get(j)).get(0));
					break;
				case "Login ** and ** and *":
					this.login(this.findArguments(toDo.get(j)).get(0), this.findArguments(toDo.get(j)).get(1),
							this.findArguments(toDo.get(j)).get(2), this.findArguments(toDo.get(j)).get(3),
							this.findArguments(toDo.get(j)).get(4));
					break;
				case "Scroll * pixels":
					this.scrollByPixels(this.findArguments(toDo.get(j)).get(0));
					break;
				case "Click * expandlist":
					this.clickOnExpandList(this.findArguments(toDo.get(j)).get(0));
					break;

				default:
					System.err.println("Your Action is not valid");
				}
			}

		}
	}

	public void pressKeyboardKey(String arg1) {
		Actions action = new Actions(driver);
		// action.sendKeys(Keys.ENTER).build().perform();

		switch (arg1.toLowerCase().trim()) {
		case "enter":
			// Actions action1 = new Actions(driver);
			action.sendKeys(Keys.ENTER).build().perform();
			break;
		case "tab":
			action.sendKeys(Keys.TAB).build().perform();
			break;
		case "escape":
		case "esc":
			action.sendKeys(Keys.ESCAPE).build().perform();
			break;
		case "up":
		case "up arrow":
		case "arrow up":
			action.sendKeys(Keys.ARROW_UP).build().perform();
			break;
		case "down":
		case "down arrow":
		case "arrow down":
			action.sendKeys(Keys.ARROW_DOWN).build().perform();
			break;
		case "right":
		case "right arrow":
		case "arrow right":
			action.sendKeys(Keys.ARROW_RIGHT).build().perform();
			break;
		case "left":
		case "left arrow":
		case "arrow left":
			action.sendKeys(Keys.ARROW_LEFT).build().perform();
			break;

		default:
			throw new RuntimeException("Invalid Key Provided...");
		}
	}

	public void scrollByPixels(String arg1) {
		int n = Integer.parseInt(arg1);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + n + ")");

		// ((JavascriptExecutor) driver)
		// .executeScript(
		// "arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});",
		// element);
	}

	public void verifyTextIsDisplayed(String arg1) {
		waitForLoad(driver);
		try {
			WebElement text = this.driver.findElement(By.xpath("//*[text()='" + arg1 + "']"));
			Assert.assertTrue(text.isDisplayed());
		} catch (NoSuchElementException | org.openqa.selenium.NoSuchElementException | AssertionError e) {
			WebElement text = this.driver.findElement(By.xpath("//*[contains(text(),'" + arg1 + "')]"));
			Assert.assertTrue(text.isDisplayed());
		}
		waitForLoad(driver);
	}

	public void clickOnText(String arg1) {
		waitForLoad(driver);
		try {
			WebElement text = this.driver.findElement(By.xpath("//*[text()='" + arg1 + "']"));
			Assert.assertTrue(text.isDisplayed());
			text.click();
		} catch (NoSuchElementException | org.openqa.selenium.NoSuchElementException | AssertionError e) {
			this.driver.findElement(By.xpath("//*[contains(text(),'" + arg1 + "')]|//a[contains(.,'" + arg1 + "')]"))
					.click();
		}
		waitForLoad(driver);
	}

	public void clickOnLink(String arg1) throws InterruptedException {
		waitForLoad(driver);
		String parent = this.driver.getWindowHandle();
		this.clicklink(arg1);
		Thread.sleep(3000);

		Set<String> winodws = this.driver.getWindowHandles();

		for (String str : winodws) {
			System.out.println(str);
		}

		for (String str : winodws) {
			if (str != parent) {
				System.out.println("Switching to window" + str);
				this.driver.switchTo().window(str);
			}
		}
		// Thread.sleep(3000);
	}

	public void navigateToURL(String arg1) {
		try {
			waitForLoad(this.driver);
			this.driver.navigate().to(arg1);
			this.driver.manage().window().maximize();
			waitForLoad(driver);
		} catch (Exception e) {
			this.driver.manage().window().maximize();
		}
	}

	public void verifyCurrentURL(String arg1) throws InterruptedException {
		waitForLoad(driver);
		Thread.sleep(5000);
		Assert.assertTrue(this.driver.getCurrentUrl().contains(arg1));
		waitForLoad(driver);
	}

	public void verifyPageHeading(String arg1) {
		waitForLoad(driver);
		try {
			WebElement text = this.driver.findElement(By.xpath("//h1[text()='" + arg1 + "']"));
			Assert.assertTrue(text.isDisplayed());
		} catch (NoSuchElementException | org.openqa.selenium.NoSuchElementException | AssertionError e) {
			try {
				WebElement text = this.driver.findElement(By.xpath("//h1[contains(text(),'" + arg1 + "')]"));
				Assert.assertTrue(text.isDisplayed());
			} catch (NoSuchElementException | org.openqa.selenium.NoSuchElementException | AssertionError e1) {
				try {
					WebElement text = this.driver.findElement(By.xpath("//h2[text()='" + arg1 + "']"));
					Assert.assertTrue(text.isDisplayed());
				} catch (NoSuchElementException | org.openqa.selenium.NoSuchElementException | AssertionError e2) {
					WebElement text = this.driver.findElement(By.xpath("//h2[contains(text(),'" + arg1 + "')]"));
					Assert.assertTrue(text.isDisplayed());
				}
			}
		}
		waitForLoad(driver);
	}

	public void verifySectionHeading(String arg1) {
		waitForLoad(driver);
		try {
			WebElement text = this.driver.findElement(
					By.xpath("//h3[text()='" + arg1 + "']|//h2[text()='" + arg1 + "']|//h1[text()='" + arg1 + "']"));
			Assert.assertTrue(text.isDisplayed());
		} catch (NoSuchElementException | org.openqa.selenium.NoSuchElementException | AssertionError e) {
			WebElement text = this.driver.findElement(By.xpath("//h3[contains(text(),'" + arg1
					+ "')]|//h2[contains(text(),'" + arg1 + "')]|//h1[contains(text(),'" + arg1 + "')]"));
			Assert.assertTrue(text.isDisplayed());
		}
		waitForLoad(driver);
	}

	public void enterAlphaRandom(String text, String FieldLabel) throws Exception {

		String alpha = RandomStringUtils.randomAlphabetic(5);
		String rdmAplha = text + alpha;
		System.out.print(rdmAplha);
		this.findInputObj2(FieldLabel).sendKeys(rdmAplha);
	}

	public void enterAlphaRandomInSection(String text, String FieldLabel, String section) throws Exception {

		String alpha = RandomStringUtils.randomAlphabetic(5);
		String rdmAplha = text + alpha;
		System.out.print(rdmAplha);
		// this.findInputObj2(FieldLabel).sendKeys(rdmAplha);
		this.enterTextInTextBoxInSection(rdmAplha, FieldLabel, section);
	}

	public WebElement enterTextInTextBoxInSection(String values, String label, String section) throws Exception {
		List<WebElement> users = driver.findElements(By.xpath("(//*[contains(text(),'" + section
				+ "')]/../..//*[contains(text(),'" + label + "')])/../../..//input|(//*[contains(text(),'" + section
				+ "')]/../..//*[contains(@placeholder,'" + label + "')])/../../..//input"));

		WebElement ele = null;
		boolean found = false;
		List<WebElement> users1 = driver.findElements(By.xpath("(//*[contains(text(),'" + section
				+ "')]/../..//*[text()='" + label + "'])/..//input|(//*[contains(text(),'" + section
				+ "')]/../..//*[@placeholder='" + label + "'])/..//input|(//*[contains(text(),'" + section
				+ "')]/../..//*[text()='" + label + "'])/../..//input|(//*[contains(text(),'" + section
				+ "')]/../..//*[@placeholder='" + label + "'])/../..//input"));

		String[] s = values.split("~");
		System.out.println(s);
		int i = 0;
		for (WebElement field1 : users1) {

			try {
				Assert.assertTrue(field1.isDisplayed());
				try {
					if (field1.isEnabled()) {
						field1.clear();
					}
				} catch (Exception exceptionTemp) {
					// TODO: handle exception
					System.out.println(exceptionTemp);
				}
				Assert.assertTrue(field1.getAttribute("value").isEmpty());
				if (field1.getAttribute("value").isEmpty()) {
					if (s.length > 1) {
						if (s[i].contentEquals("") || s[i].contentEquals(" ")) {
							i = i + 1;
						} else {
							System.out.println(s[i].trim());
							field1.sendKeys(s[i].trim());
							i = i + 1;
						}

					} else {
						field1.sendKeys(s[i].trim());
					}
				}
				ele = field1;
				found = true;
				// break;
			} catch (AssertionError e7) {
			}
			// }

			if (users.size() == 0 && users1.size() == 0)
				throw new Exception("element not found");

		}

		return ele;
	}

	public void enterNumericRandom(String text, String FieldLabel) throws Exception {
		String numeric = RandomStringUtils.randomNumeric(5);
		String rdmNumeric = text + numeric;
		System.out.print(rdmNumeric);
		this.findInputObj2(FieldLabel).sendKeys(rdmNumeric);
	}

	public void enterNumericRandomInSection(String text, String FieldLabel, String section) throws Exception {
		String numeric = RandomStringUtils.randomNumeric(5);
		String rdmNumeric = text + numeric;
		System.out.print(rdmNumeric);
		// this.findInputObj2(FieldLabel).sendKeys(rdmNumeric);
		this.enterTextInTextBoxInSection(rdmNumeric, FieldLabel, section);
	}

	public void hoverOverLink(String linkLabel) throws InterruptedException {
		this.hoverOver(this.verifyLinkIsDisplayed(linkLabel));
	}

	public void hoverOverLinkInSection(String name, String section) throws InterruptedException {
		this.hoverOver(this.verifyLinkIsDisplayedInSection(name, section));
	}

	public void ClickButtonbyID(String name) {
		WebElement el = driver.findElement(By.id(name));
		clickUsingJS(el);

	}

	public void verifyTableDataForMultipleUsers(String arg1, String arg2, DataTable arg3) throws Exception {
		List<List<String>> list = arg3.asLists(String.class);
		colSize = list.get(0);
		lists.clear();

		if (list.get(0).get(0).equalsIgnoreCase("&RowNumber")) {
			for (int i = 1; i < list.size(); i++) { // i starts from 1 because i=0 represents the header
				for (int j = 0; j < colSize.size(); j++) {
					// System.out.println("_______________________"+RunCukesTest.getCurrentUser());
					this.findTable2(arg1, list.get(i).get(0), list.get(0).get(j), list.get(i).get(j));
				}
				if (this.getCommonElements(lists).isEmpty()) {
					System.err.println(
							"Input record at row no. " + i + " is not matching at row no. " + i + " in the table");
				}
				lists.clear();
			}
		} else if (list.get(0).get(0).equalsIgnoreCase("&UserID")) {
			for (int i = 1; i < list.size(); i++) { // i starts from 1 because i=0 represents the header
				for (int j = 0; j < colSize.size(); j++) {
					if (list.get(i).get(0).equals(arg2))
						this.findTable(arg1, list.get(0).get(j), list.get(i).get(j));
				}
				if (this.getCommonElements(lists).isEmpty()) {
					if (list.get(i).get(0).equals(arg2))
						System.err.println("Input record at row no. " + i + " for user " + arg2 + " is not matching");
				}
				lists.clear();
			}
		} else if (list.get(0).get(0).equalsIgnoreCase("&UserID")
				&& list.get(0).get(1).equalsIgnoreCase("&RowNumber")) {
			for (int i = 1; i < list.size(); i++) { // i starts from 1 because i=0 represents the header
				for (int j = 0; j < colSize.size(); j++) {
					if (list.get(i).get(0).equals(arg2))
						this.findTable2(arg1, list.get(i).get(1), list.get(0).get(j), list.get(i).get(j));
				}
				if (this.getCommonElements(lists).isEmpty()) {
					System.err.println(
							"Input record at row no. " + i + " is not matching at row no. " + i + " in the table");
				}
				lists.clear();
			}
		} else if (list.get(0).get(1).equalsIgnoreCase("&RowNumber")
				&& list.get(0).get(1).equalsIgnoreCase("&UserID")) {
			for (int i = 1; i < list.size(); i++) { // i starts from 1 because i=0 represents the header
				for (int j = 0; j < colSize.size(); j++) {
					if (list.get(i).get(1).equals(arg2))
						this.findTable2(arg1, list.get(i).get(0), list.get(0).get(j), list.get(i).get(j));
				}
				if (this.getCommonElements(lists).isEmpty()) {
					System.err.println(
							"Input record at row no. " + i + " is not matching at row no. " + i + " in the table");
				}
				lists.clear();
			}
		} else {
			for (int i = 1; i < list.size(); i++) { // i starts from 1 because i=0 represents the header
				for (int j = 0; j < colSize.size(); j++) {
					this.findTable(arg1, list.get(0).get(j), list.get(i).get(j));
				}
				// System.out.println("@@@@@@@@@@@@@@@@@@"+lists);
				// System.out.println("$$$$$$$$$$$$$$$$$$"+this.getCommonElements(lists));
				if (this.getCommonElements(lists).isEmpty()) {
					System.err.println("Record at row no. " + i + " is not present");
				}
				lists.clear();
			}
		}
	}

	List<String> colSize = new ArrayList<>();

	public void verifyTableData(String arg1, DataTable arg2) throws Exception {
		List<List<String>> list = arg2.asLists(String.class);
		colSize = list.get(0);
		lists.clear();

		if (list.get(0).get(0).equalsIgnoreCase("&RowNumber")) {
			for (int i = 1; i < list.size(); i++) { // i starts from 1 because i=0 represents the header
				for (int j = 0; j < colSize.size(); j++) {
					this.findTable2(arg1, list.get(i).get(0), list.get(0).get(j), list.get(i).get(j));
				}
				if (this.getCommonElements(lists).isEmpty()) {
					System.err.println(
							"Record at row no. " + i + " is not present at row no. " + i + " in the application");
				}
				lists.clear();
			}
		} else {
			for (int i = 1; i < list.size(); i++) { // i starts from 1 because i=0 represents the header
				for (int j = 0; j < colSize.size(); j++) {
					this.findTable(arg1, list.get(0).get(j), list.get(i).get(j));
				}
				// System.out.println("@@@@@@@@@@@@@@@@@@"+lists);
				// System.out.println("$$$$$$$$$$$$$$$$$$"+this.getCommonElements(lists));
				if (this.getCommonElements(lists).isEmpty()) {
					System.err.println("Record at row no. " + i + " is not present");
				}
				lists.clear();
			}
		}
	}

	List<List<Integer>> lists = new ArrayList<List<Integer>>();

	public void findTable(String name, String colHead, String colValue) throws Exception {
		List<Integer> tempList = new ArrayList<>();
		tempList.clear();

		List<WebElement> table = driver
				.findElements(By.xpath("//*[contains(text(),'" + name + "')]/following::table/thead/tr//th"));

		if (table.size() == 0)
			throw new Exception("Table not found");
		WebElement ele = null;

		try {
			for (int i = 0; i <= table.size() - 1; i++) {
				// for(WebElement field:table) {
				// System.out.println("%%%%%%%%%%%%%%%%"+table.get(i).getText().toLowerCase());
				Assert.assertTrue(table.get(i).isDisplayed());
				if (table.get(i).getText().toLowerCase().contains(colHead.toLowerCase())) {
					// if(Pattern.compile(Pattern.quote(table.get(i).getText()),
					// Pattern.CASE_INSENSITIVE).matcher(colHead).find()) {
					List<WebElement> column = driver.findElements(By.xpath(
							"//*[contains(text(),'" + name + "')]/following::table/tbody/tr//td[" + (i + 1) + "]"));

					for (int j = 0; j <= column.size() - 1; j++) {
						// System.out.println("$$$$$$$$$$$$$$$$$$$$"+column.get(j).getText());
						if (column.get(j).isDisplayed()) {
							if (column.get(j).getText().toLowerCase().contains(colValue.toLowerCase())) {
								// if(Pattern.compile(Pattern.quote(column.get(j).getText()),
								// Pattern.CASE_INSENSITIVE).matcher(colValue).find()) {
								tempList.add(j);
							}
						}
					}
					lists.add(tempList);
					break;
				}
			}
		}

		catch (ElementNotFoundException | AssertionError | org.openqa.selenium.NoSuchElementException e2) {

		}
	}

	public void findTable2(String name, String rowNo, String colHead, String colValue) throws Exception {
		List<Integer> tempList = new ArrayList<>();
		tempList.clear();

		List<WebElement> table = driver
				.findElements(By.xpath("//*[contains(text(),'" + name + "')]/following::table/thead/tr//th"));

		if (table.size() == 0)
			throw new Exception("Table not found");
		WebElement ele = null;

		try {
			for (int i = 0; i <= table.size() - 1; i++) {
				Assert.assertTrue(table.get(i).isDisplayed());
				if (table.get(i).getText().toLowerCase().contains(colHead.toLowerCase())) {
					List<WebElement> column = driver.findElements(By.xpath("//*[contains(text(),'" + name
							+ "')]/following::table/tbody/tr[" + rowNo + "]//td[" + (i + 1) + "]"));

					for (int j = 0; j <= column.size() - 1; j++) {
						if (column.get(j).isDisplayed()) {
							if (column.get(j).getText().toLowerCase().contains(colValue.toLowerCase())) {
								tempList.add(j);
							}
						}
					}
					lists.add(tempList);
					break;
				}
			}
		}

		catch (ElementNotFoundException | AssertionError | org.openqa.selenium.NoSuchElementException e2) {

		}
	}

	public void enterTableData(String arg1, DataTable arg2) throws Exception {
		List<List<String>> list = arg2.asLists(String.class);
		colSize = list.get(0);

		if (list.get(0).get(0).equalsIgnoreCase("&RowNumber")) {
			for (int i = 1; i < list.size(); i++) { // i starts from 1 because i=0 represents the header
				for (int j = 0; j < colSize.size(); j++) {
					this.enterTable2(arg1, list.get(i).get(0), list.get(0).get(j), list.get(i).get(j));
				}
			}
		} else {
			for (int i = 1; i < list.size(); i++) { // i starts from 1 because i=0 represents the header
				for (int j = 0; j < colSize.size(); j++) {
					this.enterTable(arg1, list.get(0).get(j), list.get(i).get(j));
				}
			}
		}
	}

	public void enterTableDataForMultipleUsers(String arg1, String arg2, DataTable arg3) throws Exception {
		List<List<String>> list = arg3.asLists(String.class);
		colSize = list.get(0);

		if (list.get(0).get(0).equalsIgnoreCase("&RowNumber")) {
			for (int i = 1; i < list.size(); i++) { // i starts from 1 because i=0 represents the header
				for (int j = 0; j < colSize.size(); j++) {
					this.enterTable2(arg1, list.get(i).get(0), list.get(0).get(j), list.get(i).get(j));
				}
			}
		} else if (list.get(0).get(0).equalsIgnoreCase("&UserID")) {
			for (int i = 1; i < list.size(); i++) { // i starts from 1 because i=0 represents the header
				for (int j = 0; j < colSize.size(); j++) {
					if (list.get(i).get(0).equals(arg2))
						this.enterTable(arg1, list.get(0).get(j), list.get(i).get(j));
				}
			}
		} else if (list.get(0).get(0).equalsIgnoreCase("&UserID")
				&& list.get(0).get(1).equalsIgnoreCase("&RowNumber")) {
			for (int i = 1; i < list.size(); i++) { // i starts from 1 because i=0 represents the header
				for (int j = 0; j < colSize.size(); j++) {
					if (list.get(i).get(0).equals(arg2))
						this.enterTable2(arg1, list.get(i).get(1), list.get(0).get(j), list.get(i).get(j));
				}
			}
		} else if (list.get(0).get(0).equalsIgnoreCase("&RowNumber")
				&& list.get(0).get(1).equalsIgnoreCase("&UserID")) {
			for (int i = 1; i < list.size(); i++) { // i starts from 1 because i=0 represents the header
				for (int j = 0; j < colSize.size(); j++) {
					if (list.get(i).get(1).equals(arg2))
						this.enterTable2(arg1, list.get(i).get(0), list.get(0).get(j), list.get(i).get(j));
				}
			}
		} else {
			for (int i = 1; i < list.size(); i++) { // i starts from 1 because i=0 represents the header
				for (int j = 0; j < colSize.size(); j++) {
					this.enterTable(arg1, list.get(0).get(j), list.get(i).get(j));
				}
			}
		}
	}

	public void enterTable(String name, String colHead, String colValue) throws Exception {

		List<WebElement> table = driver.findElements(By.xpath("//*[contains(text(),'" + name + "')]/following::table/thead/tr//th"));

		if (table.size() == 0)
			throw new Exception("Table not found");
		WebElement ele = null;

		try {
			for (int i = 0; i <= table.size() - 1; i++) {
				String a = table.get(i).getText();
				Assert.assertTrue(table.get(i).isDisplayed());
				if (table.get(i).getText().toLowerCase().contains(colHead.toLowerCase())) {
					List<WebElement> column = driver.findElements(
							By.xpath("//*[contains(text(),'" + name + "')]/following-sibling::table/tbody/tr//td["
									+ (i + 1) + "]/textarea|//*[contains(text(),'" + name
									+ "')]/following-sibling::table/tbody/tr//td[" + (i + 1) + "]/input"));

					for (int j = 0; j <= column.size() - 1; j++) {
						if (column.get(j).isDisplayed()) {
							if (column.get(j).getAttribute("value").isEmpty()) {
								// column.get(j).clear();
								column.get(j).sendKeys(colValue);
								break;
							}
						}
					}
					break;
				}
			}
		}

		catch (ElementNotFoundException | AssertionError | org.openqa.selenium.NoSuchElementException e2) {

		}
	}

	public void enterTable2(String name, String rowNo, String colHead, String colValue) throws Exception {

		List<WebElement> table = driver.findElements(By.xpath("//*[contains(text(),'" + name + "')]/following-sibling::table/thead/tr//th"));

		if (table.size() == 0)
			throw new Exception("Table not found");
		WebElement ele = null;

		try {
			for (int i = 0; i <= table.size() - 1; i++) {
				Assert.assertTrue(table.get(i).isDisplayed());
				if (table.get(i).getText().toLowerCase().contains(colHead.toLowerCase())) {
					// List<WebElement> column =
					// driver.findElements(By.xpath("//*[contains(text(),'"+name+"')]/following::table/tbody/tr["+rowNo+"]//td["+(i+1)+"]"));
					List<WebElement> column = driver.findElements(By.xpath("//*[contains(text(),'" + name
							+ "')]/following-sibling::table/tbody/tr[" + rowNo + "]//td[" + (i + 1)
							+ "]/textarea|//*[contains(text(),'" + name + "')]/following-sibling::table/tbody/tr["
							+ rowNo + "]//td[" + (i + 1) + "]/input"));

					for (int j = 0; j <= column.size() - 1; j++) {
						if (column.get(j).isDisplayed()) {
							if (column.get(j).getAttribute("value").isEmpty()) {
								column.get(j).sendKeys(colValue);
								break;
							}
						}
					}
					break;
				}
			}
		}

		catch (ElementNotFoundException | AssertionError | org.openqa.selenium.NoSuchElementException e2) {

		}
	}

	public void editTableData(String arg1, DataTable arg2) throws Exception {
		List<List<String>> list = arg2.asLists(String.class);
		colSize = list.get(0);

		if (list.get(0).get(0).equalsIgnoreCase("&RowNumber")) {
			for (int i = 1; i < list.size(); i++) { // i starts from 1 because i=0 represents the header
				for (int j = 0; j < colSize.size(); j++) {
					this.editTable2(arg1, list.get(i).get(0), list.get(0).get(j), list.get(i).get(j));
				}
			}
		} else {
			for (int i = 1; i < list.size(); i++) { // i starts from 1 because i=0 represents the header
				for (int j = 0; j < colSize.size(); j++) {
					this.enterTable(arg1, list.get(0).get(j), list.get(i).get(j));
				}
			}
		}
	}

	public void editTableDataForMultipleUsers(String arg1, String arg2, DataTable arg3) throws Exception {
		List<List<String>> list = arg3.asLists(String.class);
		colSize = list.get(0);

		if (list.get(0).get(0).equalsIgnoreCase("&RowNumber")) {
			for (int i = 1; i < list.size(); i++) { // i starts from 1 because i=0 represents the header
				for (int j = 0; j < colSize.size(); j++) {
					this.editTable2(arg1, list.get(i).get(0), list.get(0).get(j), list.get(i).get(j));
				}
			}
		} else if (list.get(0).get(0).equalsIgnoreCase("&UserID")
				&& list.get(0).get(1).equalsIgnoreCase("&RowNumber")) {
			for (int i = 1; i < list.size(); i++) { // i starts from 1 because i=0 represents the header
				for (int j = 0; j < colSize.size(); j++) {
					if (list.get(i).get(0).equals(arg2))
						this.editTable2(arg1, list.get(i).get(1), list.get(0).get(j), list.get(i).get(j));
				}
			}
		} else if (list.get(0).get(0).equalsIgnoreCase("&RowNumber")
				&& list.get(0).get(1).equalsIgnoreCase("&UserID")) {
			for (int i = 1; i < list.size(); i++) { // i starts from 1 because i=0 represents the header
				for (int j = 0; j < colSize.size(); j++) {
					if (list.get(i).get(1).equals(arg2))
						this.editTable2(arg1, list.get(i).get(0), list.get(0).get(j), list.get(i).get(j));
				}
			}
		} else {
			for (int i = 1; i < list.size(); i++) { // i starts from 1 because i=0 represents the header
				for (int j = 0; j < colSize.size(); j++) {
					this.enterTable(arg1, list.get(0).get(j), list.get(i).get(j));
				}
			}
		}
	}

	public void editTable2(String name, String rowNo, String colHead, String colValue) throws Exception {

		List<WebElement> table = driver
				.findElements(By.xpath("//*[contains(text(),'" + name + "')]/following::table/thead/tr//th"));

		if (table.size() == 0)
			throw new Exception("Table not found");

		String step = this.findStep(colValue);
		switch (step) {
		case "Enter *":
			try {
				for (int i = 0; i <= table.size() - 1; i++) {
					Assert.assertTrue(table.get(i).isDisplayed());
					if (table.get(i).getText().toLowerCase().contains(colHead.toLowerCase())) {
						List<WebElement> column = driver.findElements(By.xpath("//*[contains(text(),'" + name
								+ "')]/following-sibling::table/tbody/tr[" + rowNo + "]//td[" + (i + 1)
								+ "]//textarea|//*[contains(text(),'" + name + "')]/following-sibling::table/tbody/tr["
								+ rowNo + "]//td[" + (i + 1) + "]//input"));

						for (int j = 0; j <= column.size() - 1; j++) {
							if (column.get(j).isDisplayed()) {
								if (column.get(j).getAttribute("value").isEmpty()) {
									List<String> arg = this.findArguments(colValue);
									column.get(j).sendKeys(arg.get(0));
									break;
								}
							}
						}
						break;
					}
				}
			}

			catch (ElementNotFoundException | AssertionError | org.openqa.selenium.NoSuchElementException e2) {
			}

			break;

		case "Click *":
		case "Click":
			List<String> arg = this.findArguments(colValue);
			if (arg.isEmpty()) {
				System.out.println("$$$$$$$$$$$1" + arg);
				try {
					for (int i = 0; i <= table.size() - 1; i++) {
						Assert.assertTrue(table.get(i).isDisplayed());
						System.out.println("$$$$$$$$$$$123456 " + table.get(i).getText());
						if (table.get(i).getText().toLowerCase().contains(colHead.toLowerCase())) {
							List<WebElement> column = driver.findElements(
									By.xpath("//*[contains(text(),'" + name + "')]/following-sibling::table/tbody/tr["
											+ rowNo + "]//td[" + (i + 1) + "]//input"));

							for (int j = 0; j <= column.size() - 1; j++) {
								if (column.get(j).isDisplayed()) {
									clickUsingJS(column.get(j));
									break;
								}
							}
						}
					}
				}

				catch (ElementNotFoundException | AssertionError | org.openqa.selenium.NoSuchElementException e2) {
				}
			} else {
				try {
					for (int i = 0; i <= table.size() - 1; i++) {
						Assert.assertTrue(table.get(i).isDisplayed());
						if (table.get(i).getText().toLowerCase().contains(colHead.toLowerCase())) {
							// List<WebElement> column =
							// driver.findElements(By.xpath("//*[contains(text(),'"+name+"')]/following-sibling::table/tbody/tr["+rowNo+"]//td["+(i+1)+"]/*[contains(text(),'"+arg.get(0)+"')]|//*[contains(text(),'"+name+"')]/following-sibling::table/tbody/tr["+rowNo+"]//td["+(i+1)+"]//*[contains(text(),'"+arg.get(0)+"')]"));
							List<WebElement> column = driver.findElements(By.xpath("//*[contains(text(),'" + name
									+ "')]/following-sibling::table/tbody/tr[" + rowNo + "]//td[" + (i + 1)
									+ "]/*[contains(text(),'" + arg.get(0) + "')]/..//input|//*[contains(text(),'"
									+ name + "')]/following-sibling::table/tbody/tr[" + rowNo + "]//td[" + (i + 1)
									+ "]//*[contains(text(),'" + arg.get(0) + "')]/..//input"));
							for (int j = 0; j <= column.size() - 1; j++) {
								if (column.get(j).isDisplayed()) {
									clickUsingJS(column.get(j));
									break;
								}
							}
						}
					}
				} catch (ElementNotFoundException | AssertionError | org.openqa.selenium.NoSuchElementException e2) {
				}
			}
			break;

		case "Select *":
			try {
				for (int i = 0; i <= table.size() - 1; i++) {
					Assert.assertTrue(table.get(i).isDisplayed());
					if (table.get(i).getText().toLowerCase().contains(colHead.toLowerCase())) {
						List<WebElement> column = driver.findElements(By.xpath("//*[contains(text(),'" + name
								+ "')]/following-sibling::table/tbody/tr[" + rowNo + "]//td[" + (i + 1) + "]//select"));

						for (int j = 0; j <= column.size() - 1; j++) {
							if (column.get(j).isDisplayed()) {
								for (int k = 1; k <= 50; k++) {
									try {
										Select dropdown = new Select(driver.findElement(By.xpath("//*[contains(text(),'"
												+ name + "')]/following-sibling::table/tbody/tr[" + rowNo + "]//td["
												+ (i + 1) + "]//select")));

										String a = driver.findElement(By.xpath("//*[contains(text(),'" + name
												+ "')]/following-sibling::table/tbody/tr[" + rowNo + "]//td[" + (i + 1)
												+ "]//select/option[" + k + "]")).getText().trim();

										String[] b = this.findArguments(colValue).get(0).split(" ");
										for (String opt : b) {
											Assert.assertTrue(a.contains(opt));
										}
										dropdown.selectByIndex(k - 1);
										break;
									} catch (AssertionError e) {
									}
								}
							}
						}
						break;
					}
				}
			}

			catch (ElementNotFoundException | AssertionError | org.openqa.selenium.NoSuchElementException e2) {
			}
			break;

		default:
			System.err.println("Your Action is not valid");
		}
	}

	public void uploadFile(String path, String exe) throws AWTException, InterruptedException {
		this.generic.waitForWhile(2000);

		// BaseConfig.setDefaultProperties();
		String x = path;
		String y = exe;
		this.generic.waitForWhile(2000);
		FileUploader.autoItUpload(x, y);

		Thread.sleep(2000);

		this.driver.switchTo().defaultContent();
	}

	// sneha

	public void verifyCheckBoxEnabledOrDisabledInSection(String name, String value, String section) throws Exception {
		WebElement checkbox_value = null;
		checkbox_value = findRadioButtonCheckBoxInSection(name, section);

		if (value.contains("not displayed")) {
			ElementNotDisplayed(checkbox_value);
		} else {
			Elementenableddisabled(checkbox_value, value);
		}

	}

	public void verifyRadioBtnEnabledOrDisabledInSection(String name, String value, String section) throws Exception {
		WebElement radiobutton = null;
		radiobutton = findRadioButtonCheckBoxInSection(name, section);

		if (value.contains("not displayed")) {
			ElementNotDisplayed(radiobutton);
		} else {
			Elementenableddisabled(radiobutton, value);
		}
	}

	public void verifyDropdownEnabledOrDisabled(String name, String value) throws Exception {
		WebElement dropdwn_value = null;

		dropdwn_value = findInputObj2(name);
		if (value.contains("not displayed")) {
			ElementNotDisplayed(dropdwn_value);
		} else {
			Elementenableddisabled(dropdwn_value, value);
		}
	}

	public void verifyDropdownEnabledOrDisabledInSection(String name, String value, String section) throws Exception {
		WebElement drpdwn = null;
		drpdwn = findInputObjInSection(name, section);

		if (value.contains("not displayed")) {
			ElementNotDisplayed(drpdwn);
		} else {
			Elementenableddisabled(drpdwn, value);
		}
	}

	public void verifyLinkEnabledOrDisabled(String name, String value) {
		WebElement link_value = null;

		link_value = FindLink(name);
		if (value.contains("not displayed")) {
			ElementNotDisplayed(link_value);
		} else {
			Elementenableddisabled(link_value, value);
		}

	}

	public void verifyLinkEnabledOrDisabledInSection(String name, String value, String section) throws Exception {
		WebElement radiobutton = null;
		radiobutton = verifyLinkIsDisplayedInSection(name, section);

		if (value.contains("not displayed")) {
			ElementNotDisplayed(radiobutton);
		} else {
			Elementenableddisabled(radiobutton, value);
		}
	}

	public void verifyCheckBoxEnabledOrDisabled(String name, String value) throws Exception {

		WebElement checkbx = null;
		checkbx = findRadioButtonCheckBox(name);

		if (value.contains("not displayed")) {
			ElementNotDisplayed(checkbx);
		} else {
			Elementenableddisabled(checkbx, value);
		}
	}

	public void verifyRadioBtnEnabledOrDisabled(String name, String value) throws Exception {

		WebElement radiobtn = null;
		radiobtn = findRadioButtonCheckBox(name);

		if (value.contains("not displayed")) {
			ElementNotDisplayed(radiobtn);
		} else {
			Elementenableddisabled(radiobtn, value);
		}
	}

	// sneha

	public void verifyTabEnabledOrDisabled(String name, String value) {

		WebElement tab_value = null;

		tab_value = findTab(name);
		if (value.contains("not displayed")) {
			ElementNotDisplayed(tab_value);
		} else {
			Elementenableddisabled(tab_value, value);
		}

	}

	public void verifyTabEnabledOrDisabledInSection(String name, String value, String section) throws Exception {
		WebElement tab_ele = null;
		tab_ele = findTabInSection(name, section);

		if (value.contains("not displayed")) {
			ElementNotDisplayed(tab_ele);
		} else {
			Elementenableddisabled(tab_ele, value);
		}
	}

	public void verifyTextboxEnabledOrDisabled(String name, String value) throws Exception {

		WebElement txtbox_value = null;

		txtbox_value = findInputObj2(name);
		if (value.contains("not displayed")) {
			ElementNotDisplayed(txtbox_value);
		} else {
			Elementenableddisabled(txtbox_value, value);
		}
	}

	public void verifyTextboxEnabledOrDisabledInSection(String name, String value, String section) throws Exception {
		WebElement tab_ele = null;
		tab_ele = findInputObjInSection(name, section);

		if (value.contains("not displayed")) {
			ElementNotDisplayed(tab_ele);
		} else {
			Elementenableddisabled(tab_ele, value);
		}
	}

	public void Buttonenableddisabled(String name, String value) throws Exception {
		WebElement button_value = findButton(name);

		Elementenableddisabled(button_value, value);
	}

	public void Elementenableddisabled(WebElement ele, String value) {
		if (value.contains("enable")) {
			Assert.assertTrue(ele.isEnabled());
		}

		else if (value.contains("disable")) {
			Assert.assertTrue(!(ele.isEnabled()));
		}
	}

	// sneha
	public WebElement FindLink(String name) {
		WebElement ele = null;

		try {
			List<WebElement> users = driver.findElements(By.xpath("//a[contains(text(),'" + name
					+ "')]|//a/*[contains(text(),'" + name + "')]|//a[contains(@title,'" + name + "')]"));
			if (users.size() == 0) {
				Assert.assertTrue(
						driver.findElement(By.xpath("//a[contains(text(),'" + name + "')]|//a/*[contains(text(),'"
								+ name + "')]|//a[contains(@title,'" + name + "')]")).isDisplayed());
			}

			for (WebElement field : users) {
				String a = field.getText().trim();
				int m = a.length();
				String b = name;
				int n = b.length();
				Assert.assertTrue(n == m);
				Assert.assertTrue(field.isDisplayed());
				ele = field;

				break;
			}

		} catch (ElementNotFoundException | AssertionError | org.openqa.selenium.NoSuchElementException e2) {
			try {
				String x = name;
				x = this.camelCaseWithSingleCharacterInLower(x);
				List<WebElement> users = driver.findElements(
						By.xpath("//a[contains(text(),'" + x + "')]|//a/*[contains(text(),'" + x + "')]"));
				if (users.size() == 0) {
					Assert.assertTrue(driver
							.findElement(
									By.xpath("//a[contains(text(),'" + x + "')]|//a/*[contains(text(),'" + x + "')]"))
							.isDisplayed());
				}

				for (WebElement field : users) {

					String a = field.getText().trim();
					int m = a.length();
					int n = name.length();
					Assert.assertTrue(n == m);
					Assert.assertTrue(field.isDisplayed());
					ele = field;
					break;
				}
			} catch (ElementNotFoundException | AssertionError | org.openqa.selenium.NoSuchElementException e) {
				try {
					String y = name;
					y = this.camelCaseWithSpace(y);
					List<WebElement> users1 = driver.findElements(
							By.xpath("//a[contains(text(),'" + y + "')]|//a/*[contains(text(),'" + y + "')]"));
					if (users1.size() == 0) {
						Assert.assertTrue(driver
								.findElement(By
										.xpath("//a[contains(text(),'" + y + "')]|//a/*[contains(text(),'" + y + "')]"))
								.isDisplayed());
					}

					for (WebElement field1 : users1) {

						String a = field1.getText().trim();
						int m = a.length();
						int n = name.length();
						Assert.assertTrue(n == m);
						Assert.assertTrue(field1.isDisplayed());
						ele = field1;
						break;
					}
				} catch (ElementNotFoundException | AssertionError | org.openqa.selenium.NoSuchElementException e1) {
					try {
						String u = name;
						u = this.lowercase(u);
						List<WebElement> users2 = driver.findElements(
								By.xpath("//a[contains(text(),'" + u + "')]|//a/*[contains(text(),'" + u + "')]"));
						if (users2.size() == 0) {
							Assert.assertTrue(driver
									.findElement(By.xpath(
											"//a[contains(text(),'" + u + "')]|//a/*[contains(text(),'" + u + "')]"))
									.isDisplayed());
						}

						for (WebElement field2 : users2) {

							String a = field2.getText().trim();
							int m = a.length();
							int n = name.length();
							Assert.assertTrue(n == m);
							Assert.assertTrue(field2.isDisplayed());
							ele = field2;
							break;
						}
					} catch (ElementNotFoundException | AssertionError
							| org.openqa.selenium.NoSuchElementException e3) {
						try {
							List<WebElement> users3 = driver.findElements(By.xpath(
									"//a[contains(text(),'" + name + "')]|//a/*[contains(text(),'" + name + "')]"));
							if (users3.size() == 0) {
								Assert.assertTrue(driver.findElement(By.xpath(
										"//a[contains(text(),'" + name + "')]|//a/*[contains(text(),'" + name + "')]"))
										.isDisplayed());
							}

							for (WebElement field2 : users3) {

								String a = field2.getText().trim();
								Assert.assertTrue(field2.isDisplayed());
								ele = field2;
								break;
							}
						} catch (ElementNotFoundException | AssertionError
								| org.openqa.selenium.NoSuchElementException e4) {
							try {
								List<WebElement> users = driver.findElements(
										By.xpath("//a/*[text()='" + name + "']|//a[text(),'" + name + "']"));
								if (users.size() == 0) {
									Assert.assertTrue(driver
											.findElement(
													By.xpath("//a/*[text(),'" + name + "']|//a[text(),'" + name + "']"))
											.isDisplayed());
								}

								for (WebElement field : users) {
									Assert.assertTrue(field.isDisplayed());
									ele = field;
									break;
								}

							} catch (AssertionError e6) {
							}
						}
					}
				}
			}
		}

		return ele;
	}

	public void Linkstatus(String name, String value) {
		WebElement link_value = null;

		link_value = FindLink(name);
		if (value.contains("not displayed")) {
			ElementNotDisplayed(link_value);
		} else {
			Elementenableddisabled(link_value, value);
		}

	}

	// sneha
	public void ButtonNotDisplayed(String name) throws Exception {
		WebElement button_value = null;
		button_value = findButton(name);
		ElementNotDisplayed(button_value);

	}

	public void ElementNotDisplayed(WebElement e) {
		Assert.assertTrue(e == null);

		if (e == null) {
			System.out.println("Element is not displayed");
		}
	}

	public void Tabenableddisablednotdisplayed(String name, String value) {

		WebElement tab_value = null;

		tab_value = findTab(name);
		if (value.contains("not displayed")) {
			ElementNotDisplayed(tab_value);
		} else {
			Elementenableddisabled(tab_value, value);
		}

	}

	// ****Vivian*******

	public void clickTabInSection(String name, String section) {
		clickUsingJS(this.findTabInSection(name, section));
	}

	public WebElement findTabInSection(String name, String section) {
		WebElement ele = null;
		try {
			List<WebElement> users = driver.findElements(By.xpath("(//*[contains(text(),'" + section
					+ "')]/../..//*[contains(text(),'" + name + "')])/../..//a[contains(text(),'" + name
					+ "') and contains(@tabindex,'')]|(//*[contains(text(),'" + section
					+ "')]/../..//*[contains(text(),'" + name + "')])/../..//a[@href]/span[contains(text(),'" + name
					+ "')]|(//*[contains(text(),'" + section + "')]/../..//*[contains(text(),'" + name
					+ "')])/../..//a[contains(@title,'" + name + "') and contains(@href,'')]|(//*[contains(text(),'"
					+ section + "')]/../..//*[contains(text(),'" + name
					+ "')])/parent::*[contains(@role,'tab')]//*[contains(text(),'" + name + "')]"));
			if (users.size() == 0) {
				this.driver.findElement(By.xpath("(//*[contains(text(),'" + section + "')]/../..//*[contains(text(),'"
						+ name + "')])/../..//a[contains(text(),'" + name
						+ "') and contains(@tabindex,'')]|(//*[contains(text(),'" + section
						+ "')]/../..//*[contains(text(),'" + name + "')])/../..//a[@href]/span[contains(text(),'" + name
						+ "')]|(//*[contains(text(),'" + section + "')]/../..//*[contains(text(),'" + name
						+ "')])/../..//a[contains(@title,'" + name + "') and contains(@href,'')]|(//*[contains(text(),'"
						+ section + "')]/../..//*[contains(text(),'" + name
						+ "')])/parent::*[contains(@role,'tab')]//*[contains(text(),'" + name + "')]")).isDisplayed();
			}

			for (WebElement field : users) {
				try {
					Assert.assertTrue(field.isDisplayed());
					ele = field;
					break;
				} catch (AssertionError e) {
				}
			}
		} catch (ElementNotFoundException | AssertionError | org.openqa.selenium.NoSuchElementException e2) {
			try {
				String x = name;
				x = this.camelCaseWithSpace(x);
				List<WebElement> users = driver.findElements(By.xpath("(//*[contains(text(),'" + section
						+ "')]/../..//*[contains(text(),'" + x + "')])/../..//a[contains(text(),'" + x
						+ "') and contains(@tabindex,'')]|(//*[contains(text(),'" + section
						+ "')]/../..//*[contains(text(),'" + x + "')])/../..//a[@href]/span[contains(text(),'" + x
						+ "')]|(//*[contains(text(),'" + section + "')]/../..//*[contains(text(),'" + x
						+ "')])/../..//a[contains(@title,'" + x + "') and contains(@href,'')]|(//*[contains(text(),'"
						+ section + "')]/../..//*[contains(text(),'" + x + "')])/parent::*[contains(@role,'tab')]"));
				if (users.size() == 0) {
					Assert.assertTrue(driver
							.findElement(By.xpath("(//*[contains(text(),'" + section + "')]/../..//*[contains(text(),'"
									+ x + "')])/../..//a[contains(text(),'" + x
									+ "') and contains(@tabindex,'')]|(//*[contains(text(),'" + section
									+ "')]/../..//*[contains(text(),'" + x
									+ "')])/../..//a[@href]/span[contains(text(),'" + x + "')]|(//*[contains(text(),'"
									+ section + "')]/../..//*[contains(text(),'" + x + "')])/../..//a[contains(@title,'"
									+ x + "') and contains(@href,'')]|(//*[contains(text(),'" + section
									+ "')]/../..//*[contains(text(),'" + x + "')])/parent::*[contains(@role,'tab')]"))
							.isDisplayed());
				}

				for (WebElement field : users) {

					Assert.assertTrue(field.isDisplayed());
					ele = field;
					break;
				}
			} catch (ElementNotFoundException | AssertionError | org.openqa.selenium.NoSuchElementException e) {
				String y = name;
				y = this.lowercase(y);
				List<WebElement> users1 = driver.findElements(By.xpath("(//*[contains(text(),'" + section
						+ "')]/../..//*[contains(text(),'" + y + "')])/../..//a[contains(text(),'" + y
						+ "') and contains(@tabindex,'')]|(//*[contains(text(),'" + section
						+ "')]/../..//*[contains(text(),'" + y + "')])/../..//a[@href]/span[contains(text(),'" + y
						+ "')]|(//*[contains(text(),'" + section + "')]/../..//*[contains(text(),'" + y
						+ "')])/../..//a[contains(@title,'" + y + "') and contains(@href,'')]|(//*[contains(text(),'"
						+ section + "')]/../..//*[contains(text(),'" + y + "')])/parent::*[contains(@role,'tab')]"));
				if (users1.size() == 0) {
					Assert.assertTrue(driver
							.findElement(By.xpath("(//*[contains(text(),'" + section + "')]/../..//*[contains(text(),'"
									+ y + "')])/../..//a[contains(text(),'" + y
									+ "') and contains(@tabindex,'')]|(//*[contains(text(),'" + section
									+ "')]/../..//*[contains(text(),'" + y
									+ "')])/../..//a[@href]/span[contains(text(),'" + y + "')]|(//*[contains(text(),'"
									+ section + "')]/../..//*[contains(text(),'" + y + "')])/../..//a[contains(@title,'"
									+ y + "') and contains(@href,'')]|(//*[contains(text(),'" + section
									+ "')]/../..//*[contains(text(),'" + y + "')])/parent::*[contains(@role,'tab')]"))
							.isDisplayed());
				}

				for (WebElement field1 : users1) {
					try {
						Assert.assertTrue(field1.isDisplayed());
						ele = field1;
						break;
					} catch (AssertionError e1) {
					}
				}
			}
		}
		return ele;
	}

	public void clickOnLinkInSection(String name, String section) throws InterruptedException {
		waitForLoad(driver);
		String parent = this.driver.getWindowHandle();
		this.clicklinkInSection(name, section);
		Thread.sleep(3000);

		Set<String> winodws = this.driver.getWindowHandles();

		for (String str : winodws) {
			System.out.println(str);
		}

		for (String str : winodws) {
			if (str != parent) {
				System.out.println("Switching to window" + str);
				this.driver.switchTo().window(str);
			}
		}
		// Thread.sleep(3000);
	}

	public void clicklinkInSection(String name, String section) {
		try {
			List<WebElement> users = driver.findElements(By.xpath("//*[contains(text(),'" + section
					+ "')]/../..//a[contains(text(),'" + name + "')]|//*[contains(text(),'" + section
					+ "')]/../..//a/*[contains(text(),'" + name + "')]"));
			if (users.size() == 0) {
				Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'" + section
						+ "')]/../..//a[contains(text(),'" + name + "')]|//*[contains(text(),'" + section
						+ "')]/../..//a/*[contains(text(),'" + name + "')]")).isDisplayed());
			}

			for (WebElement field : users) {
				String a = field.getText().trim();
				int m = a.length();
				String b = name;
				int n = b.length();
				Assert.assertTrue(n == m);
				Assert.assertTrue(field.isDisplayed());
				clickUsingJS(field);
				break;
			}

		} catch (ElementNotFoundException | AssertionError | org.openqa.selenium.NoSuchElementException e2) {
			try {
				String x = name;
				x = this.camelCaseWithSingleCharacterInLower(x);
				List<WebElement> users = driver.findElements(By.xpath("//*[contains(text(),'" + section
						+ "')]/../..//a[contains(text(),'" + x + "')]|//*[contains(text(),'" + section
						+ "')]/../..//a/*[contains(text(),'" + x + "')]"));
				if (users.size() == 0) {
					Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'" + section
							+ "')]/../..//a[contains(text(),'" + x + "')]|//*[contains(text(),'" + section
							+ "')]/../..//a/*[contains(text(),'" + x + "')]")).isDisplayed());
				}

				for (WebElement field : users) {

					String a = field.getText().trim();
					int m = a.length();
					int n = name.length();
					Assert.assertTrue(n == m);
					Assert.assertTrue(field.isDisplayed());
					clickUsingJS(field);
					break;
				}
			} catch (ElementNotFoundException | AssertionError | org.openqa.selenium.NoSuchElementException e) {
				try {
					String y = name;
					y = this.camelCaseWithSpace(y);
					List<WebElement> users1 = driver.findElements(By.xpath("//*[contains(text(),'" + section
							+ "')]/../..//a[contains(text(),'" + y + "')]|//*[contains(text(),'" + section
							+ "')]/../..//a/*[contains(text(),'" + y + "')]"));
					if (users1.size() == 0) {
						Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'" + section
								+ "')]/../..//a[contains(text(),'" + y + "')]|//*[contains(text(),'" + section
								+ "')]/../..//a/*[contains(text(),'" + y + "')]")).isDisplayed());
					}

					for (WebElement field1 : users1) {

						String a = field1.getText().trim();
						int m = a.length();
						int n = name.length();
						Assert.assertTrue(n == m);
						Assert.assertTrue(field1.isDisplayed());
						clickUsingJS(field1);
						break;
					}
				} catch (ElementNotFoundException | AssertionError | org.openqa.selenium.NoSuchElementException e1) {
					try {
						String u = name;
						u = this.lowercase(u);
						List<WebElement> users2 = driver.findElements(By.xpath("//*[contains(text(),'" + section
								+ "')]/../..//a[contains(text(),'" + u + "')]|//*[contains(text(),'" + section
								+ "')]/../..//a/*[contains(text(),'" + u + "')]"));
						if (users2.size() == 0) {
							Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'" + section
									+ "')]/../..//a[contains(text(),'" + u + "')]|//*[contains(text(),'" + section
									+ "')]/../..//a/*[contains(text(),'" + u + "')]")).isDisplayed());
						}

						for (WebElement field2 : users2) {

							String a = field2.getText().trim();
							int m = a.length();
							int n = name.length();
							Assert.assertTrue(n == m);
							Assert.assertTrue(field2.isDisplayed());
							clickUsingJS(field2);
							break;
						}
					} catch (ElementNotFoundException | AssertionError
							| org.openqa.selenium.NoSuchElementException e3) {
						try {
							List<WebElement> users3 = driver.findElements(By.xpath("//*[contains(text(),'" + section
									+ "')]/../..//a[contains(text(),'" + name + "')]|//*[contains(text(),'" + section
									+ "')]/../..//a/*[contains(text(),'" + name + "')]"));
							if (users3.size() == 0) {
								Assert.assertTrue(driver
										.findElement(By.xpath("//*[contains(text(),'" + section
												+ "')]/../..//a[contains(text(),'" + name + "')]|//*[contains(text(),'"
												+ section + "')]/../..//a/*[contains(text(),'" + name + "')]"))
										.isDisplayed());
							}

							for (WebElement field2 : users3) {

								String a = field2.getText().trim();
								Assert.assertTrue(field2.isDisplayed());
								clickUsingJS(field2);
								break;
							}
						} catch (ElementNotFoundException | AssertionError
								| org.openqa.selenium.NoSuchElementException e4) {
							try {
								List<WebElement> users = driver.findElements(By.xpath("//*[contains(text(),'" + section
										+ "')]/../..//a/*[text()='" + name + "']|//*[contains(text(),'" + section
										+ "')]/../..//a[text(),'" + name + "']"));
								if (users.size() == 0) {
									Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'" + section
											+ "')]/../..//a/*[text(),'" + name + "']|//*[contains(text(),'" + section
											+ "')]/../..//a[text(),'" + name + "']")).isDisplayed());
								}

								for (WebElement field : users) {
									Assert.assertTrue(field.isDisplayed());
									clickUsingJS(field);
									break;
								}

							} catch (AssertionError e6) {
							}
						}
					}
				}
			}
		}
	}

	public void enterTextInSection(String text, String name, String section) throws Exception {
		this.findInputObjInSection(name, section).sendKeys(text);
	}

	public WebElement findInputObjInSection(String name, String section) throws Exception {
		List<WebElement> users = driver.findElements(By.xpath("(//*[contains(text(),'" + section
				+ "')]/../..//*[contains(text(),'" + name + "')])/../../..//input|(//*[contains(text(),'" + section
				+ "')]/../..//*[contains(@placeholder,'" + name + "')])/../../..//input"));

		WebElement ele = null;
		boolean found = false;
		List<WebElement> users1 = driver
				.findElements(By.xpath("(//*[contains(text(),'" + section + "')]/../..//*[text()='" + name
						+ "'])/..//input|(//*[contains(text(),'" + section + "')]/../..//*[@placeholder='" + name
						+ "'])/..//input|(//*[text()='" + name + "'])/../..//input|(//*[contains(text(),'" + section
						+ "')]/../..//*[@placeholder='" + name + "'])/../..//input"));
		for (WebElement field1 : users1) {
			try {
				Assert.assertTrue(field1.isDisplayed());
				ele = field1;
				found = true;
				break;
			} catch (AssertionError e7) {
			}
		}

		if (users.size() == 0 && users1.size() == 0)
			throw new Exception("element not found");

		if (found == false) {
			for (WebElement field : users) {
				try {
					String a = field.getAttribute("name");
					a = this.replaceDotUnderscoreWithSpace(a);
					a = this.secondWord(a);
					a = this.lowercase(a);
					Assert.assertTrue(name.contains(a));
					Assert.assertTrue(field.isDisplayed());
					ele = field;
					break;
				} catch (AssertionError e) {
					try {
						String a = field.getAttribute("name");
						a = this.replaceDotUnderscoreWithSpace(a);
						a = this.secondWord(a);
						a = this.lowercase(a);
						String b = name;
						b = this.lowercase(b);
						Assert.assertTrue(b.contains(a));
						Assert.assertTrue(field.isDisplayed());
						ele = field;
						break;
					} catch (AssertionError e1) {
						try {
							String a = field.getAttribute("name");
							a = this.replaceDotUnderscoreWithSpace(a);
							a = this.secondWord(a);
							a = this.lowercase(a);
							String b = name;
							b = this.camelCaseWithSpace(b);
							Assert.assertTrue(b.contains(a));
							Assert.assertTrue(field.isDisplayed());
							ele = field;
							break;
						} catch (AssertionError e2) {
							try {
								String a = field.getAttribute("name");
								a = this.replaceDotUnderscoreWithSpace(a);
								a = this.secondWord(a);
								a = this.lowercase(a);
								String b = name;
								b = this.camelCaseWithoutSpace(b);
								Assert.assertTrue(b.contains(a));
								Assert.assertTrue(field.isDisplayed());
								ele = field;
								break;
							} catch (AssertionError e3) {
								try {
									String a = field.getAttribute("name");
									a = this.replaceDotUnderscoreWithSpace(a);
									a = this.firstword(a);
									a = this.lowercase(a);
									String b = name;
									b = this.lowercase(b);
									Assert.assertTrue(b.contains(a));
									Assert.assertTrue(field.isDisplayed());
									ele = field;
									break;
								} catch (AssertionError e6) {

								}

							}

						}
					}
				}

				catch (ArrayIndexOutOfBoundsException e4) {
					try {
						String a = field.getAttribute("name");
						a = this.lowercase(a);
						String b = name;
						b = this.lowercase(b);
						Assert.assertTrue(b.contains(a));
						Assert.assertTrue(field.isDisplayed());
						ele = field;
						break;
					} catch (AssertionError e5) {
						try {
							String a = field.getAttribute("name");
							a = this.lowercase(a);
							String b = name;
							b = this.removeSpaces(b);
							b = this.lowercase(b);
							Assert.assertTrue(b.contains(a));
							Assert.assertTrue(field.isDisplayed());
							ele = field;
							break;
						} catch (AssertionError e3) {
						}

					}
				}
			}
		}
		if (ele == null) {
			for (WebElement field3 : users) {
				try {
					Assert.assertTrue(field3.isDisplayed());
					ele = field3;
					found = true;
					break;
				} catch (AssertionError e10) {
				}
			}
		}
		return ele;
	}

	public void selectDropdownValueInSection(String value, String name, String section) throws InterruptedException {
		boolean found = false;
		try {
			Select dropdown2 = new Select(driver.findElement(By
					.xpath("(//*[contains(text(),'" + section + "')]/../..//*[text()='" + name + "'])/../..//select")));
			if (dropdown2 != null) {
				found = true;
				for (int i = 1; i <= 50; i++) {
					try {
						Select dropdown = new Select(driver.findElement(By.xpath("(//*[contains(text(),'" + section
								+ "')]/../..//*[text()='" + name + "'])/../..//select")));

						String a = driver.findElement(By.xpath("(//*[contains(text(),'" + section
								+ "')]/../..//*[text()='" + name + "'])/../..//select/option[" + i + "]")).getText()
								.trim();

						String[] b = value.split(" ");
						for (String arg : b) {
							Assert.assertTrue(a.contains(arg));
						}
						dropdown.selectByIndex(i - 1);
						break;
					} catch (AssertionError e) {
					}
				}
			}

			if (found == false) {

				Select dropdown1 = new Select(driver.findElement(By.xpath("(//*[contains(text(),'" + section
						+ "')]/../..//*[contains(text(),'" + name + "')])/../..//select")));

				for (int i = 1; i <= 50; i++) {
					try {
						Select dropdown = new Select(driver.findElement(By.xpath("(//*[contains(text(),'" + section
								+ "')]/../..//*[contains(text(),'" + name + "')])/../..//select")));

						String a = driver.findElement(By.xpath("(//*[contains(text(),'" + section
								+ "')]/../..//*[contains(text(),'" + name + "')])/../..//select/option[" + i + "]"))
								.getText().trim();

						String[] b = value.split(" ");
						for (String arg : b) {
							Assert.assertTrue(a.contains(arg));
						}
						dropdown.selectByIndex(i - 1);
						break;
					} catch (AssertionError e) {
					}
				}
			}
		}

		catch (org.openqa.selenium.NoSuchElementException e1) {
			WebElement overlay = driver.findElement(By.xpath("(//*[contains(text(),'" + section
					+ "')]/../..//*[contains(text(),'" + name + "')])/../..//mat-select"));
			clickUsingJS(overlay);
			Thread.sleep(2000);

			WebElement option = driver.findElement(By.xpath(
					"//*[contains(text(),'" + value + "')]/../..//mat-option//*[contains(text(),'" + value + "')]"));
			clickUsingJS(option);
		}

	}

	public void hoverOverTabInSection(String name, String section) throws InterruptedException {
		this.hoverOver(this.findTabInSection(name, section));
	}

	public void verifyPageHeadingInSection(String name, String section) {
		waitForLoad(driver);
		try {
			WebElement text = this.driver
					.findElement(By.xpath("//*[contains(text(),'" + section + "')]/../..//h1[text()='" + name + "']"));
			Assert.assertTrue(text.isDisplayed());
		} catch (NoSuchElementException | org.openqa.selenium.NoSuchElementException | AssertionError e) {
			WebElement text = this.driver.findElement(
					By.xpath("//*[contains(text(),'" + section + "')]/../..//h1[contains(text(),'" + name + "')]"));
			Assert.assertTrue(text.isDisplayed());
		}
		waitForLoad(driver);
	}

	public void verifySectionHeadingInSection(String name, String section) {
		waitForLoad(driver);
		try {
			WebElement text = this.driver.findElement(By.xpath("//*[contains(text(),'" + section
					+ "')]/../..//h3[text()='" + name + "']|//*[contains(text(),'" + section + "')]/../..//h2[text()='"
					+ name + "']|//*[contains(text(),'" + section + "')]/../..//h1[text()='" + name + "']"));
			Assert.assertTrue(text.isDisplayed());
		} catch (NoSuchElementException | org.openqa.selenium.NoSuchElementException | AssertionError e) {
			WebElement text = this.driver.findElement(By.xpath("//*[contains(text(),'" + section
					+ "')]/../..//h3[contains(text(),'" + name + "')]|//*[contains(text(),'" + section
					+ "')]/../..//h2[contains(text(),'" + name + "')]|//*[contains(text(),'" + section
					+ "')]/../..//h1[contains(text(),'" + name + "')]"));
			Assert.assertTrue(text.isDisplayed());
		}
		waitForLoad(driver);
	}

	public void verifyTabIsDisplayedInSection(String name, String section) throws Exception {
		Assert.assertTrue("The Tab is not displayed on the page", this.findTabInSection(name, section).isDisplayed());
	}

	public void verifyTextboxIsDisplayedInSection(String name, String section) throws Exception {
		Assert.assertTrue("The Text box is not displayed on the page",
				this.textboxIsDisplayedInSection(name, section).isDisplayed());
	}

	public WebElement textboxIsDisplayedInSection(String name, String section) throws Exception {
		List<WebElement> users = driver.findElements(By.xpath("(//*[contains(text(),'" + section
				+ "')]/../..//*[contains(text(),'" + name + "')])/../../..//input|(//*[contains(text(),'" + section
				+ "')]/../..//*[contains(@placeholder,'" + name + "')])/../../..//input"));

		WebElement ele = null;
		boolean found = false;
		List<WebElement> users1 = driver.findElements(By.xpath("(//*[contains(text(),'" + section
				+ "')]/../..//*[text()='" + name + "'])/..//input|(//*[contains(text(),'" + section
				+ "')]/../..//*[@placeholder='" + name + "'])/..//input|(//*[contains(text(),'" + section
				+ "')]/../..//*[text()='" + name + "'])/../..//input|(//*[contains(text(),'" + section
				+ "')]/../..//*[@placeholder='" + name + "'])/../..//input"));
		for (WebElement field1 : users1) {
			try {
				Assert.assertTrue(field1.isDisplayed());
				ele = field1;
				found = true;
				break;
			} catch (AssertionError e7) {
			}
		}

		if (users.size() == 0 && users1.size() == 0)
			throw new Exception("element not found");

		if (found == false) {
			for (WebElement field : users) {
				try {
					String a = field.getAttribute("name");
					a = this.replaceDotUnderscoreWithSpace(a);
					a = this.secondWord(a);
					a = this.lowercase(a);
					Assert.assertTrue(name.contains(a));
					Assert.assertTrue(field.isDisplayed());
					ele = field;
					break;
				} catch (AssertionError e) {
					try {
						String a = field.getAttribute("name");
						a = this.replaceDotUnderscoreWithSpace(a);
						a = this.secondWord(a);
						a = this.lowercase(a);
						String b = name;
						b = this.lowercase(b);
						Assert.assertTrue(b.contains(a));
						Assert.assertTrue(field.isDisplayed());
						ele = field;
						break;
					} catch (AssertionError e1) {
						try {
							String a = field.getAttribute("name");
							a = this.replaceDotUnderscoreWithSpace(a);
							a = this.secondWord(a);
							a = this.lowercase(a);
							String b = name;
							b = this.camelCaseWithSpace(b);
							Assert.assertTrue(b.contains(a));
							Assert.assertTrue(field.isDisplayed());
							ele = field;
							break;
						} catch (AssertionError e2) {
							try {
								String a = field.getAttribute("name");
								a = this.replaceDotUnderscoreWithSpace(a);
								a = this.secondWord(a);
								a = this.lowercase(a);
								String b = name;
								b = this.camelCaseWithoutSpace(b);
								Assert.assertTrue(b.contains(a));
								Assert.assertTrue(field.isDisplayed());
								ele = field;
								break;
							} catch (AssertionError e3) {
								try {
									String a = field.getAttribute("name");
									a = this.replaceDotUnderscoreWithSpace(a);
									a = this.firstword(a);
									a = this.lowercase(a);
									String b = name;
									b = this.lowercase(b);
									Assert.assertTrue(b.contains(a));
									Assert.assertTrue(field.isDisplayed());
									ele = field;
									break;
								} catch (AssertionError e6) {

								}

							}

						}
					}
				}

				catch (ArrayIndexOutOfBoundsException e4) {
					try {
						String a = field.getAttribute("name");
						a = this.lowercase(a);
						String b = name;
						b = this.lowercase(b);
						Assert.assertTrue(b.contains(a));
						Assert.assertTrue(field.isDisplayed());
						ele = field;
						break;
					} catch (AssertionError e5) {
						try {
							String a = field.getAttribute("name");
							a = this.lowercase(a);
							String b = name;
							b = this.removeSpaces(b);
							b = this.lowercase(b);
							Assert.assertTrue(b.contains(a));
							Assert.assertTrue(field.isDisplayed());
							ele = field;
							break;
						} catch (AssertionError e3) {
						}

					}
				}
			}
		}
		if (ele == null) {
			for (WebElement field3 : users) {
				try {
					Assert.assertTrue(field3.isDisplayed());
					ele = field3;
					found = true;
					break;
				} catch (AssertionError e10) {
				}
			}
		}
		return ele;
	}

	public WebElement verifyLinkIsDisplayedInSection(String name, String section) {
		WebElement ele = null;
		try {
			List<WebElement> users = driver.findElements(By.xpath("//*[contains(text(),'" + section
					+ "')]/../..//a[contains(text(),'" + name + "')]|//*[contains(text(),'" + section
					+ "')]/../..//a/*[contains(text(),'" + name + "')]"));
			if (users.size() == 0) {
				Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'" + section
						+ "')]/../..//a[contains(text(),'" + name + "')]|//*[contains(text(),'" + section
						+ "')]/../..//a/*[contains(text(),'" + name + "')]")).isDisplayed());
			}

			for (WebElement field : users) {
				String a = field.getText().trim();
				int m = a.length();
				String b = name;
				int n = b.length();
				Assert.assertTrue(n == m);
				Assert.assertTrue(field.isDisplayed());
				ele = field;
				break;
			}

		} catch (ElementNotFoundException | AssertionError | org.openqa.selenium.NoSuchElementException e2) {
			try {
				String x = name;
				x = this.camelCaseWithSingleCharacterInLower(x);
				List<WebElement> users = driver.findElements(By.xpath("//*[contains(text(),'" + section
						+ "')]/../..//a[contains(text(),'" + x + "')]|//*[contains(text(),'" + section
						+ "')]/../..//a/*[contains(text(),'" + x + "')]"));
				if (users.size() == 0) {
					Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'" + section
							+ "')]/../..//a[contains(text(),'" + x + "')]|//*[contains(text(),'" + section
							+ "')]/../..//a/*[contains(text(),'" + x + "')]")).isDisplayed());
				}

				for (WebElement field : users) {

					String a = field.getText().trim();
					int m = a.length();
					int n = name.length();
					Assert.assertTrue(n == m);
					Assert.assertTrue(field.isDisplayed());
					ele = field;
					break;
				}
			} catch (ElementNotFoundException | AssertionError | org.openqa.selenium.NoSuchElementException e) {
				try {
					String y = name;
					y = this.camelCaseWithSpace(y);
					List<WebElement> users1 = driver.findElements(By.xpath("//*[contains(text(),'" + section
							+ "')]/../..//a[contains(text(),'" + y + "')]|//*[contains(text(),'" + section
							+ "')]/../..//a/*[contains(text(),'" + y + "')]"));
					if (users1.size() == 0) {
						Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'" + section
								+ "')]/../..//a[contains(text(),'" + y + "')]|//*[contains(text(),'" + section
								+ "')]/../..//a/*[contains(text(),'" + y + "')]")).isDisplayed());
					}

					for (WebElement field1 : users1) {

						String a = field1.getText().trim();
						int m = a.length();
						int n = name.length();
						Assert.assertTrue(n == m);
						Assert.assertTrue(field1.isDisplayed());
						ele = field1;
						break;
					}
				} catch (ElementNotFoundException | AssertionError | org.openqa.selenium.NoSuchElementException e1) {
					try {
						String u = name;
						u = this.lowercase(u);
						List<WebElement> users2 = driver.findElements(By.xpath("//*[contains(text(),'" + section
								+ "')]/../..//a[contains(text(),'" + u + "')]|//*[contains(text(),'" + section
								+ "')]/../..//a/*[contains(text(),'" + u + "')]"));
						if (users2.size() == 0) {
							Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'" + section
									+ "')]/../..//a[contains(text(),'" + u + "')]|//*[contains(text(),'" + section
									+ "')]/../..//a/*[contains(text(),'" + u + "')]")).isDisplayed());
						}

						for (WebElement field2 : users2) {

							String a = field2.getText().trim();
							int m = a.length();
							int n = name.length();
							Assert.assertTrue(n == m);
							Assert.assertTrue(field2.isDisplayed());
							ele = field2;
							break;
						}
					} catch (ElementNotFoundException | AssertionError
							| org.openqa.selenium.NoSuchElementException e3) {
						try {
							List<WebElement> users3 = driver.findElements(By.xpath("//*[contains(text(),'" + section
									+ "')]/../..//a[contains(text(),'" + name + "')]|//*[contains(text(),'" + section
									+ "')]/../..//a/*[contains(text(),'" + name + "')]"));
							if (users3.size() == 0) {
								Assert.assertTrue(driver
										.findElement(By.xpath("//*[contains(text(),'" + section
												+ "')]/../..//a[contains(text(),'" + name + "')]|//*[contains(text(),'"
												+ section + "')]/../..//a/*[contains(text(),'" + name + "')]"))
										.isDisplayed());
							}

							for (WebElement field2 : users3) {

								String a = field2.getText().trim();
								Assert.assertTrue(field2.isDisplayed());
								ele = field2;
								break;
							}
						} catch (ElementNotFoundException | AssertionError
								| org.openqa.selenium.NoSuchElementException e4) {
							try {
								List<WebElement> users = driver.findElements(By.xpath("//*[contains(text(),'" + section
										+ "')]/../..//a/*[text()='" + name + "']|//*[contains(text(),'" + section
										+ "')]/../..//a[text(),'" + name + "']"));
								if (users.size() == 0) {
									Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'" + section
											+ "')]/../..//a/*[text(),'" + name + "']|//*[contains(text(),'" + section
											+ "')]/../..//a[text(),'" + name + "']")).isDisplayed());
								}

								for (WebElement field : users) {
									Assert.assertTrue(field.isDisplayed());
									ele = field;
									break;
								}

							} catch (AssertionError e6) {
							}
						}
					}
				}
			}
		}
		return ele;
	}

	public void verifyDropdownIsDisplayedInSection(String name, String section) throws Exception {
		boolean found = false;
		try {
			Select dropdown2 = new Select(driver.findElement(By
					.xpath("(//*[contains(text(),'" + section + "')]/../..//*[text()='" + name + "'])/../..//select")));
			if (dropdown2 != null) {
				found = true;
				try {
					Select dropdown = new Select(driver.findElement(By.xpath("(//*[contains(text(),'" + section
							+ "')]/../..//*[text()='" + name + "'])/../..//select")));
				} catch (Exception e) {
					throw new Exception("The dropdown is not displayed in the page");
				}
			}

			if (found == false) {

				Select dropdown1 = new Select(driver.findElement(By.xpath("(//*[contains(text(),'" + section
						+ "')]/../..//*[contains(text(),'" + name + "')])/../..//select")));

				try {
					Select dropdown = new Select(driver.findElement(By.xpath("(//*[contains(text(),'" + section
							+ "')]/../..//*[contains(text(),'" + name + "')])/../..//select")));
				} catch (Exception e) {
					throw new Exception("The dropdown is not displayed in the page");
				}
			}
		}

		catch (org.openqa.selenium.NoSuchElementException e1) {
			WebElement overlay = driver.findElement(By.xpath("(//*[contains(text(),'" + section
					+ "')]/../..//*[contains(text(),'" + name + "')])/../..//mat-select"));
			Assert.assertTrue("The dropdown is not displayed in the page", overlay.isDisplayed());
			Thread.sleep(2000);
		}

	}

	public void verifyTextIsDisplayedInSection(String name, String section) {
		waitForLoad(driver);
		try {
			WebElement text = this.driver
					.findElement(By.xpath("//*[contains(text(),'" + section + "')]/../..//*[text()='" + name + "']"));
			Assert.assertTrue(text.isDisplayed());
		} catch (NoSuchElementException | org.openqa.selenium.NoSuchElementException | AssertionError e) {
			WebElement text = this.driver.findElement(
					By.xpath("//*[contains(text(),'" + section + "')]/../..//*[contains(text(),'" + name + "')]"));
			Assert.assertTrue(text.isDisplayed());
		}
		waitForLoad(driver);
	}

	public void verifyBtnEnabledOrDisabledInSection(String name, String value, String section) throws Exception {
		WebElement btn = null;
		btn = findButtonInSection(name, section);

		if (value.contains("not displayed")) {
			ElementNotDisplayed(btn);
		} else {
			Elementenableddisabled(btn, value);
		}
	}

	// *****sneha******

	public void Selectdatepickerdropdown(String value, String name) {
		WebElement ele = null;
		try {
			ele = driver.findElement(By.xpath("//*[contains(@data-handler,'" + name + "')]"));
			if (ele != null) {
				Select dropdown = new Select(ele);
				dropdown.selectByVisibleText(value);
			}
		} catch (Exception e1) {
			if (ele == null) {
				WebElement val = null;
				val = Select_datahandler(value);

				if (val == null) {
					System.out.println("Element not found");
				}

				else {
					if (val.getText().contains(value)) {
						System.out.println("Value-selected");
					}
				}
			}

		}

	}

	public