package TryPage;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.commons.lang.WordUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.tiaa.automation.test.Hooks;
import org.tiaa.digitalLibrary.GenericFunctions;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import planFocus.utility.GenericUtil;

public class New_TryPage extends GenericFunctions {
	
	WebDriver driver;
	GenericUtil generic;
	
	public New_TryPage() {
		this.driver = Hooks.driver;
		PageFactory.initElements(this.driver, this);
		this.generic = new GenericUtil();
	}
	
	
	    // We'll use a fake USER_AGENT so the web server thinks the robot is a normal web browser.
	    //private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
	    private List<String> links = new LinkedList<String>();
	    //private Document htmlDocument;

	    //String url=driver.getCurrentUrl();
	    
	    private static String[] links1 = null;
    	private static int linksCount = 0;
	    

	    /**
	     * This performs all the work. It makes an HTTP request, checks the response, and then gathers
	     * up all the links on the page. Perform a searchForWord after the successful crawl
	     * 
	     * @param url
	     *            - The URL to visit
	     * @return whether or not the crawl was successful
	     * @throws IOException 
	     * @throws InterruptedException 
	     */
	    public boolean crawl(String url) throws IOException, InterruptedException
	    {
	        
	    	try
	        {
	        	driver.get(url);
	    		List<WebElement> linksize = driver.findElements(By.tagName("a")); 
	        	linksCount = linksize.size();
	        	System.out.println("Total no of links Available: "+linksCount);
	        	links1= new String[linksCount];
	        	System.out.println("List of links Available: ");  
	        	// print all the links from webpage 
	        	for(int i=0;i<linksCount;i++)
	        	{
	        	links1[i] = linksize.get(i).getAttribute("href");
	        	System.out.println(linksize.get(i).getAttribute("href"));
	        	this.links.add(linksize.get(i).getAttribute("href"));
	        	} 
	        	// navigate to each Link on the webpage
	        	for(int i=0;i<linksCount;i++)
	        	{
	        	driver.navigate().to(links1[i]);
	        	Thread.sleep(3000);
	        	}
	        	
	        	
	        	
	        	
	        	
//	        	Connection connection = Jsoup.connect(url);//.userAgent(USER_AGENT);
//	            Document htmlDocument = Jsoup.connect(url).validateTLSCertificates(false).get();
//	            this.htmlDocument = htmlDocument;
//	            if(connection.response().statusCode() == 200) // 200 is the HTTP OK status code
//	                                                          // indicating that everything is great.
//	            {
//	                System.out.println("\n**Visiting** Received web page at " + url);
//	            }
	              
//	            if(!connection.response().contentType().contains("text/html"))
//	            {
//	                System.out.println("**Failure** Retrieved something other than HTML");
//	                return false;
//	            }
//	            Elements linksOnPage = htmlDocument.select("a[href]");
//	            System.out.println("Found (" + linksOnPage.size() + ") links");
//	            for(Element link : linksOnPage)
//	            {
//	                this.links.add(link.absUrl("href"));
//	            }
	            return true;
	        }
	        catch(Exception ioe)
	        {
	            // We were not successful in our HTTP request
	            return false;
	        }
	    }


	    /**
	     * Performs a search on the body of on the HTML document that is retrieved. This method should
	     * only be called after a successful crawl.
	     * 
	     * @param searchWord
	     *            - The word or string to look for
	     * @return whether or not the word was found
	     */
//	    public boolean searchForWord(String searchWord)
//	    {
//	        // Defensive coding. This method should only be used after a successful crawl.
//	        if(this.htmlDocument == null)
//	        {
//	            System.out.println("ERROR! Call crawl() before performing analysis on the document");
//	            return false;
//	        }
//	        System.out.println("Searching for the word " + searchWord + "...");
//	        String bodyText = this.htmlDocument.body().text();
//	        return bodyText.toLowerCase().contains(searchWord.toLowerCase());
//	    }


	    public List<String> getLinks()
	    {
	        System.out.println("All Links @@@@@@@@@@@@@@@@@ "+links);
	    	return this.links;
	    }

	

        private static final int MAX_PAGES_TO_SEARCH = 50;
        private Set<String> pagesVisited = new HashSet<String>();
        private List<String> pagesToVisit = new LinkedList<String>();
	
    
    private String nextUrl()
    {
        String nextUrl;
//        do
//        {
//            nextUrl = this.pagesToVisit.remove(0);
//        } while(this.pagesVisited.contains(nextUrl));
//        this.pagesVisited.add(nextUrl);
//        System.out.println("$$$$$$$$$$$$$$$$$$$$$$"+pagesToVisit);
//        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&"+pagesVisited);
        
        nextUrl = this.pagesToVisit.remove(0);
        
        if(this.pagesVisited.contains(nextUrl))
        	System.out.println("Already visited on "+nextUrl);
        else
        	this.pagesVisited.add(nextUrl);
        return nextUrl;
    }
    
    public void search(String url) throws IOException, InterruptedException
    {
        while(this.pagesVisited.size() < MAX_PAGES_TO_SEARCH)
        {
            String currentUrl;
            TryPage leg = new TryPage();
            if(this.pagesToVisit.isEmpty())
            {
                currentUrl = url;
                this.pagesVisited.add(url);
            }
            else
            {
                currentUrl = this.nextUrl();
            }
           // leg.crawl(currentUrl); // Lots of stuff happening here. Look at the crawl method in
                                   // SpiderLeg
//            boolean success = leg.searchForWord(searchWord);
//            if(success)
//            {
//                System.out.println(String.format("**Success** Word %s found at %s", searchWord, currentUrl));
//                break;
//            }
            //this.pagesToVisit.addAll(leg.getLinks());
        }
        System.out.println(String.format("**Done** Visited %s web page(s)", this.pagesVisited.size()));
    }
    
    
    public void login(String ULabel, String User, String PLabel, String Password, String url) throws Exception {
    	
    	this.driver.navigate().to(url);
		this.driver.manage().window().maximize();
		
		this.findInputObj2(ULabel).sendKeys(User);
		this.findInputObj2(PLabel).sendKeys(Password);
    }
    
public void clickBtn(String Blabel) {
	clickUsingJS(this.findButton(Blabel));
    }

public void clickRadioBtnCheckbox(String Blabel) throws Exception {
	clickUsingJS(this.findRadioButtonCheckBox(Blabel));
    }

public void clickRadioBtnCheckboxInSection(String Blabel, String section) throws Exception {
	clickUsingJS(this.findRadioButtonCheckBoxInSection(Blabel,section));
    }

public void clickTab(String Tablabel) {
	clickUsingJS(this.findTab(Tablabel));
    }

public void hoverOverTab(String Tablabel) throws InterruptedException {
	this.hoverOver(this.findTab(Tablabel));
    }

public void enterText(String text, String FieldLabel) throws Exception {
	this.findInputObj2(FieldLabel).sendKeys(text);
    }
    
//    public WebElement findInputObj(String name) {
//    	List<WebElement> users=driver.findElements(By.xpath("(//*[contains(text(),'"+name+"')])/../../../child::*/child::input|(//*[contains(text(),'"+name+"')])/../..//input|(//*[contains(text(),'"+name+"')])/../../child::*/child::input"));
//    	WebElement ele=null;
////    	WebElement ele=this.driver.findElement(By.xpath("(//*[contains(text(),'"+name+"')])/../..//input"));
//    	
//    	for(WebElement field:users) {
//    		try {
//    			Assert.assertTrue(field.isDisplayed());
//    			ele=field;
//    		}
//    		catch(AssertionError e) {
//    			ele=driver.findElement(By.xpath("(//*[contains(text(),'"+name+"')])/../../child::*/child::input"));
//    		}
//    		catch(NullPointerException ne) {
//    			ele=driver.findElement(By.xpath("(//*[contains(text(),'"+name+"')])/../../../child::*/child::input"));
//    		}
//    		
//    	}
//		return ele;
//    }
//    
//    public WebElement findInputObj1(String name) {
//    	WebElement ele=null;
//    	try {
//    		String p=this.firstword(name);
//    		String q=this.lowercase(p);
//    	List<WebElement> users=driver.findElements(By.xpath("(//*[contains(text(),'"+name+"')])/../../..//input[contains(@name,'"+q+"')]"));
////    	WebElement ele=this.driver.findElement(By.xpath("(//*[contains(text(),'"+name+"')])/../../..//input[contains(@name,'user')]"));
//    	
//    	for(WebElement field:users) {
//    			Assert.assertTrue(field.isDisplayed());
//    			ele=field;
//    		}
//    	}
//    		catch(AssertionError e) {
//    			String p=this.firstword(name);
//        		String q=this.camelCaseWithoutSpace(p);
//        	List<WebElement> users=driver.findElements(By.xpath("(//*[contains(text(),'"+name+"')])/../../..//input[contains(@name,'"+q+"')]"));
////        	WebElement ele=this.driver.findElement(By.xpath("(//*[contains(text(),'"+name+"')])/../../..//input[contains(@name,'user')]"));
//        	
//        	for(WebElement field:users) {
//        			Assert.assertTrue(field.isDisplayed());
//        			ele=field;
//        		}
//    		}
//    		catch(NullPointerException e1) {
//        		String q=this.camelCaseWithoutSpace(name);
//        	List<WebElement> users=driver.findElements(By.xpath("(//*[contains(text(),'"+name+"')])/../../..//input[contains(@name,'"+q+"')]"));
////        	WebElement ele=this.driver.findElement(By.xpath("(//*[contains(text(),'"+name+"')])/../../..//input[contains(@name,'user')]"));
//        	
//        	for(WebElement field:users) {
//        			Assert.assertTrue(field.isDisplayed());
//        			ele=field;
//        		}
//    		}
//    		
//    	
//		return ele;
//    }
    
    
    
    public WebElement findInputObj2(String name) throws Exception {
    	List<WebElement> users=driver.findElements(By.xpath("(//*[contains(text(),'"+name+"')])/../../..//input|(//*[contains(@placeholder,'"+name+"')])/../../..//input"));
    	
    	WebElement ele=null;
    	boolean found=false;
//    	WebElement ele=this.driver.findElement(By.xpath("(//*[contains(text(),'"+name+"')])/../..//input")).getAttribute(name);
//    	String a=this.driver.findElement(By.xpath("(//*[contains(text(),'"+name+"')])/../..//input")).getAttribute("name");
    	List<WebElement> users1=driver.findElements(By.xpath("(//*[text()='"+name+"'])/..//input|(//*[@placeholder='"+name+"'])/..//input|(//*[text()='"+name+"'])/../..//input|(//*[@placeholder='"+name+"'])/../..//input"));
    	for(WebElement field1:users1) {
    		try {
    			Assert.assertTrue(field1.isDisplayed());
    			ele=field1;
    			found = true;
    			break;
				}
				catch(AssertionError e7) {
				}
		}
    	
		if(users.size()==0 && users1.size()==0)
    		throw new Exception("element not found");
		
//		List<WebElement> users2=driver.findElements(By.xpath("(//*[contains(text(),'"+name+"')])/..//input|(//*[contains(@placeholder,'"+name+"')])/..//input"));
//		for(WebElement field2:users2) {
//    		try {
//    			Assert.assertTrue(field2.isDisplayed());
//    			ele=field2;
//    			found = true;
//    			break;
//				}
//				catch(AssertionError e7) {
//				}
//		}
		
		
    if(found==false) {
    	for(WebElement field:users) {
    		try {
    			String a=field.getAttribute("name");
    			a=this.replaceDotUnderscoreWithSpace(a);
    			a=this.secondWord(a);
    			a=this.lowercase(a);
    			Assert.assertTrue(name.contains(a));
    			Assert.assertTrue(field.isDisplayed());
    			ele=field;
    			break;
    		}
    		catch(AssertionError e) {
    			try {
        			String a=field.getAttribute("name");
        			a=this.replaceDotUnderscoreWithSpace(a);
        			a=this.secondWord(a);
        			a=this.lowercase(a);
        			String b=name;
        			b=this.lowercase(b);
        			Assert.assertTrue(b.contains(a));
        			Assert.assertTrue(field.isDisplayed());
        			ele=field;
        			break;
        		}
        		catch(AssertionError e1) {
        			try {
            			String a=field.getAttribute("name");
            			a=this.replaceDotUnderscoreWithSpace(a);
            			a=this.secondWord(a);
            			a=this.lowercase(a);
            			String b=name;
            			b=this.camelCaseWithSpace(b);
            			Assert.assertTrue(b.contains(a));
            			Assert.assertTrue(field.isDisplayed());
            			ele=field;
            			break;
            		}
            		catch(AssertionError e2) {
            			try {
                			String a=field.getAttribute("name");
                			a=this.replaceDotUnderscoreWithSpace(a);
                			a=this.secondWord(a);
                			a=this.lowercase(a);
                			String b=name;
                			b=this.camelCaseWithoutSpace(b);
                			Assert.assertTrue(b.contains(a));
                			Assert.assertTrue(field.isDisplayed());
                			ele=field;
                			break;
                		}
                		catch(AssertionError e3) {
                			try {
                    			String a=field.getAttribute("name");
                    			a=this.replaceDotUnderscoreWithSpace(a);
                    			a=this.firstword(a);
                    			a=this.lowercase(a);
                    			String b=name;
                    			b=this.lowercase(b);
                    			Assert.assertTrue(b.contains(a));
                    			Assert.assertTrue(field.isDisplayed());
                    			ele=field;
                    			break;
                    		}
                    		catch(AssertionError e6) {
                    			
                    		}
                			
                		}
                		
            		}
        		}
    		}
    		
    		catch(ArrayIndexOutOfBoundsException e4) {
    			try {
    			String a=field.getAttribute("name");
    			a=this.lowercase(a);
    			String b=name;
    			b=this.lowercase(b);
    			Assert.assertTrue(b.contains(a));
    			Assert.assertTrue(field.isDisplayed());
    			ele=field;
    			break;
    			}
    			catch (AssertionError e5) {
    				try {
    				String a=field.getAttribute("name");
        			a=this.lowercase(a);
        			String b=name;
        			b=this.removeSpaces(b);
        			b=this.lowercase(b);
        			Assert.assertTrue(b.contains(a));
        			Assert.assertTrue(field.isDisplayed());
        			ele=field;
        			break;
    				}
    				catch(AssertionError e3) {
            		}
    				
    			}
    		}
    	}
    }
		if(ele==null) {
			for(WebElement field3:users) {
	    		try {
	    			Assert.assertTrue(field3.isDisplayed());
	    			ele=field3;
	    			found = true;
	    			break;
					}
					catch(AssertionError e10) {
					}
			}
		}
		return ele;
    }
    
    
    public WebElement findButton(String name) {
    	List<WebElement> users=driver.findElements(By.xpath("//*[contains(@value,'"+name+"')]|//*[contains(@alt,'"+name+"')]|//button[contains(text(),'"+name+"')]|//*[contains(text(),'"+name+"')]/../..//a[contains(@role,'button')]//*[contains(text(),'"+name+"')]|//*[contains(text(),'"+name+"')]/../..//a[contains(@role,'button') and contains(text(),'"+name+"')]|//*[contains(text(),'"+name+"')]/../..//a[contains(@class,'btn') and contains(text(),'"+name+"')]|//*[contains(text(),'"+name+"')]/../..//a[contains(@class,'button') and contains(text(),'"+name+"')]"));
    	WebElement ele=null;
//    	WebElement ele=this.driver.findElement(By.xpath("(//*[contains(text(),'"+name+"')])/../..//input")).getAttribute(name);
//    	String a=this.driver.findElement(By.xpath("(//*[contains(text(),'"+name+"')])/../..//input")).getAttribute("name");
    	
//    	for(WebElement field:users) {
////    		try {
////    		Assert.assertTrue(field.isDisplayed());
////			ele=field;
////    		}
////    		catch(AssertionError e6) {
////    		}
////    		return ele;
//    	}
    	
    	for(WebElement field:users) {
    		try {
    			String a=field.getAttribute("value");
    			if(a==null) {
    				a=field.getAttribute("alt");
    				if(a==null)
    					a=field.getText();
    			}
    			a=this.firstword(a);
    			a=this.replaceDotUnderscoreWithSpace(a);
    			a=a+this.secondWord(a);
    			a=this.lowercase(a);
    			Assert.assertTrue(name.contains(a));
    			Assert.assertTrue(field.isDisplayed());
    			ele=field;
    		}
    		catch(AssertionError e) {
    			try {
        			String a=field.getAttribute("name");
        			if(a.equals(null)) {
        				a=field.getAttribute("alt");
        				if(a==null)
        					a=field.getText();
        			}
        			a=this.firstword(a);
        			a=this.lowercase(a);
        			String b=name;
        			b=this.lowercase(b);
        			Assert.assertTrue(b.contains(a));
        			Assert.assertTrue(field.isDisplayed());
        			ele=field;
        		}
        		catch(AssertionError e1) {
        			try {
            			String a=field.getAttribute("name");
            			if(a.equals(null)) {
            				a=field.getAttribute("alt");
            				if(a==null)
            					a=field.getText();
            			}
            			a=this.firstword(a);
            			a=this.lowercase(a);
            			String b=name;
            			b=this.camelCaseWithSpace(b);
            			Assert.assertTrue(b.contains(a));
            			Assert.assertTrue(field.isDisplayed());
            			ele=field;
            		}
            		catch(AssertionError e2) {
            			try {
                			String a=field.getAttribute("name");
                			if(a.equals(null)) {
                				a=field.getAttribute("alt");
                				if(a==null)
                					a=field.getText();
                			}
                			a=this.firstword(a);
                			a=this.lowercase(a);
                			String b=name;
                			b=this.camelCaseWithoutSpace(b);
                			Assert.assertTrue(b.contains(a));
                			Assert.assertTrue(field.isDisplayed());
                			ele=field;
                		}
                		catch(AssertionError e3) {
                			
                		}
                		
            		}
        		}
    		}
    		
    		catch(ArrayIndexOutOfBoundsException e4) {
    			try {
    			String a=field.getAttribute("name");
    			if(a.equals(null)) {
    				a=field.getAttribute("alt");
    				if(a==null)
    					a=field.getText();
    			}
    			a=this.lowercase(a);
    			String b=name;
    			b=this.lowercase(b);
    			Assert.assertTrue(b.contains(a));
    			Assert.assertTrue(field.isDisplayed());
    			ele=field;
    			return ele;
    			}
    			catch (AssertionError e5) {
    				try {
    				String a=field.getAttribute("name");
    				if(a.equals(null)) {
        				a=field.getAttribute("alt");
        				if(a==null)
        					a=field.getText();
        			}
        			a=this.lowercase(a);
        			String b=name;
        			b=this.removeSpaces(b);
        			b=this.lowercase(b);
        			Assert.assertTrue(b.contains(a));
        			Assert.assertTrue(field.isDisplayed());
        			ele=field;
        			return ele;
    				}
    				catch(AssertionError e3) {
            		}
    				
    			}
    		}
    	}
    	if(ele==null) {
		for(WebElement field:users) {
    		Assert.assertTrue(field.isDisplayed());
			ele=field;
			break;
    	}
    	}
    	return ele;
    }
    
    
    
    
    public WebElement findRadioButtonCheckBox(String name) throws Exception {
    	List<WebElement> users=driver.findElements(By.xpath("//*[contains(text(),'"+name+"')]/../..//*[contains(@type,'checkbox')]|//*[contains(text(),'"+name+"')]/../..//*[contains(@type,'radio')]/following-sibling::*[contains(text(),'"+name+"')]|//*[contains(text(),'"+name+"')]/../../..//*[contains(@input-type,'checkbox')]|//*[contains(text(),'"+name+"')]/../../..//*[contains(@input-type,'radio')]//*[contains(text(),'"+name+"')]"));
    	if(users.size()==0)
    		throw new Exception("element not found");
    	WebElement ele=null;
//    	WebElement ele=this.driver.findElement(By.xpath("(//*[contains(text(),'"+name+"')])/../..//input")).getAttribute(name);
//    	String a=this.driver.findElement(By.xpath("(//*[contains(text(),'"+name+"')])/../..//input")).getAttribute("name");
    	
    	for(WebElement field:users) {
    		try {
    			Assert.assertTrue(field.isDisplayed());
    			ele=field;
    			break;
    		}
    		catch(AssertionError e) {
    			
    		}
    			
    	}
    	if(ele==null)
    		throw new Exception("Element not found");
		return ele;
    }
    
    public WebElement findRadioButtonCheckBoxInSection(String name, String section) throws Exception {
    	List<WebElement> users=driver.findElements(By.xpath("//*[contains(text(),'"+section+"')]/../..//*[contains(text(),'"+name+"')]/../..//*[contains(@type,'checkbox')]|//*[contains(text(),'"+section+"')]/../..//*[contains(text(),'"+name+"')]/../..//*[contains(@type,'radio')]/following-sibling::*[contains(text(),'"+name+"')]"));
    	if(users.size()==0)
    		throw new Exception("element not found");
    	WebElement ele=null;
//    	WebElement ele=this.driver.findElement(By.xpath("(//*[contains(text(),'"+name+"')])/../..//input")).getAttribute(name);
//    	String a=this.driver.findElement(By.xpath("(//*[contains(text(),'"+name+"')])/../..//input")).getAttribute("name");
    	
    	for(WebElement field:users) {
    		try {
    			Assert.assertTrue(field.isDisplayed());
    			ele=field;
    			break;
    		}
    		catch(AssertionError e) {
    			
    		}
    			
    	}
    	if(ele==null)
    		throw new Exception("Element not found");
		return ele;
    }
    
    
    
    
    
    public void selectDropdownValue(String value, String name) throws InterruptedException {
    	boolean found=false;
    	try {
    	Select dropdown2=new Select(driver.findElement(By.xpath("(//*[text()='"+name+"'])/../..//select")));
    	if(dropdown2!=null) {
    		found=true;
    		for(int i = 1; i <= 50; i++) {
        		try {
        			Select dropdown=new Select(driver.findElement(By.xpath("(//*[text()='"+name+"'])/../..//select")));
        			
        			String a = driver.findElement(By.xpath("(//*[text()='"+name+"'])/../..//select/option["+i+"]")).getText().trim();
        			
        			String[] b=value.split(" ");
        			for(String arg:b) {
        				Assert.assertTrue(a.contains(arg));
        			}
        			dropdown.selectByIndex(i-1);
        			break;
        		}
        		catch(AssertionError e) {
        		}
    	}
    	}
    	
    if(found==false) {
    	
    		Select dropdown1=new Select(driver.findElement(By.xpath("(//*[contains(text(),'"+name+"')])/../..//select")));
    	
    	for(int i = 1; i <= 50; i++) {
    		try {
    			Select dropdown=new Select(driver.findElement(By.xpath("(//*[contains(text(),'"+name+"')])/../..//select")));
    			
    			String a = driver.findElement(By.xpath("(//*[contains(text(),'"+name+"')])/../..//select/option["+i+"]")).getText().trim();
    			
    			String[] b=value.split(" ");
    			for(String arg:b) {
    				Assert.assertTrue(a.contains(arg));
    			}
    			dropdown.selectByIndex(i-1);
    			break;
    		}
    		catch(AssertionError e) {
    		}
    	}
    }
    	}
    	
    	catch(org.openqa.selenium.NoSuchElementException e1) {
			WebElement overlay = driver.findElement(By.xpath("(//*[contains(text(),'"+name+"')])/../..//mat-select"));
			clickUsingJS(overlay);
			Thread.sleep(2000);
			
			WebElement option = driver.findElement(By.xpath("//*[contains(text(),'"+value+"')]/../..//mat-option//*[contains(text(),'"+value+"')]"));
			clickUsingJS(option);
		}
    
    	
    }
    
    
    
    
    
    public void clickDroprightDropdown(String name) throws InterruptedException {
    	try {
    	List<WebElement> users=driver.findElements(By.xpath("(//*[contains(text(),'"+name+"')])/../..//button[contains(text(),'"+name+"')]|(//*[contains(text(),'"+name+"')])/../..//a/*[contains(text(),'"+name+"')]"));
    	
    	for(WebElement field:users) {
    		try {
    			String p=driver.getCurrentUrl();
    			Assert.assertTrue(field.isDisplayed());
    			
    			String j=field.getText().trim();
    			
    			if(j.equals(name)) {
    				String a=field.getAttribute("class").trim();
    				System.out.println("@@@@@@@@@@"+field.getText());
        			clickUsingJS(field);
        			Thread.sleep(4000);
        			String q=driver.getCurrentUrl();
        			
        			if(!p.equals(q)) {
        				this.driver.navigate().back();
        			}
        			
        			String b=field.getAttribute("class").trim();
        			System.out.println("@@@@@@@@@@"+field.getAttribute("class").trim());
        			
        			if(!a.equals(b)) {
        				break;
        			}
    			}
    			
    			else {
    			String[] k=name.split(" ");
    			for(String arg:k) {
    				Assert.assertTrue(j.contains(arg));
    			}
    			
    			String a=field.getAttribute("class").trim();
    			clickUsingJS(field);
    			Thread.sleep(4000);
    			String q=driver.getCurrentUrl();
    			
    			if(!p.equals(q)) {
    				this.driver.navigate().back();
    			}
    			
    			String b=field.getAttribute("class").trim();
    			
    			if(!a.equals(b)) {
    				break;
    			}
    			}
    		}
    		catch(AssertionError e) {
    		}
    	}
    	}
    	catch(ElementNotFoundException e2) {
    		String x=name;
    		x=this.lowercase(x);
    		List<WebElement> users=driver.findElements(By.xpath("(//*[contains(text(),'"+x+"')])/../..//button[contains(text(),'"+x+"')]|(//*[contains(text(),'"+x+"')])/../..//a/*[contains(text(),'"+x+"')]"));
        	
        	for(WebElement field:users) {
        		try {
        			String p=driver.getCurrentUrl();
        			Assert.assertTrue(field.isDisplayed());
        			
        			String j=field.getText().trim();
        			
        			if(j.equals(name)) {
        				String a=field.getAttribute("class").trim();
            			clickUsingJS(field);
            			Thread.sleep(4000);
            			String q=driver.getCurrentUrl();
            			
            			if(!p.equals(q)) {
            				this.driver.navigate().back();
            			}
            			
            			String b=field.getAttribute("class").trim();
            			
            			if(!a.equals(b)) {
            				break;
            			}
        			}
        			
        			else {
        			String[] k=name.split(" ");
        			for(String arg:k) {
        				Assert.assertTrue(j.contains(arg));
        			}
        			
        			String a=field.getAttribute("class").trim();
        			clickUsingJS(field);
        			Thread.sleep(4000);
        			String q=driver.getCurrentUrl();
        			
        			if(!p.equals(q)) {
        				this.driver.navigate().back();
        			}
        			
        			String b=field.getAttribute("class").trim();
        			
        			if(!a.equals(b)) {
        				break;
        			}
        			}
        		}
        		catch(ElementNotFoundException e) {
        			String y=name;
            		y=this.camelCaseWithSpace(y);
            		List<WebElement> users1=driver.findElements(By.xpath("(//*[contains(text(),'"+y+"')])/../..//button[contains(text(),'"+y+"')]|(//*[contains(text(),'"+y+"')])/../..//a/*[contains(text(),'"+y+"')]"));
                	
                	for(WebElement field1:users1) {
                		try {
                			String p=driver.getCurrentUrl();
                			Assert.assertTrue(field1.isDisplayed());
                			
                			String j=field1.getText().trim();
                			
                			if(j.equals(name)) {
                				String a=field1.getAttribute("class").trim();
                    			clickUsingJS(field1);
                    			Thread.sleep(4000);
                    			String q=driver.getCurrentUrl();
                    			
                    			if(!p.equals(q)) {
                    				this.driver.navigate().back();
                    			}
                    			
                    			String b=field1.getAttribute("class").trim();
                    			
                    			if(!a.equals(b)) {
                    				break;
                    			}
                			}
                			
                			else {
                			String[] k=name.split(" ");
                			for(String arg:k) {
                				Assert.assertTrue(j.contains(arg));
                			}
                			
                			String a=field1.getAttribute("class").trim();
                			clickUsingJS(field1);
                			Thread.sleep(4000);
                			String q=driver.getCurrentUrl();
                			
                			if(!p.equals(q)) {
                				this.driver.navigate().back();
                			}
                			
                			String b=field1.getAttribute("class").trim();
                			
                			if(!a.equals(b)) {
                				break;
                			}
                			}
                		}
                		catch(AssertionError e1) {
                		}
                	}
        		}
        	}
    	}
    }
    
    
    
    
    
    
    public void clicklink(String name) {
    	try {
    	List<WebElement> users=driver.findElements(By.xpath("//a[contains(text(),'"+name+"')]|//a/*[contains(text(),'"+name+"')]"));
    	if(users.size()==0) {
    		Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'"+name+"')]|//a/*[contains(text(),'"+name+"')]")).isDisplayed());
    	}
    	
    	for(WebElement field:users) {
    			String a=field.getText().trim();
    			int m=a.length();
    			String b=name;
    			int n=b.length();
    			Assert.assertTrue(n==m);
    			Assert.assertTrue(field.isDisplayed());
    			clickUsingJS(field);
    			break;
    		}
    	
    	}
    	catch(ElementNotFoundException|AssertionError|org.openqa.selenium.NoSuchElementException e2) {
    		try {
    		String x=name;
    		x=this.camelCaseWithSingleCharacterInLower(x);
    		List<WebElement> users=driver.findElements(By.xpath("//a[contains(text(),'"+x+"')]|//a/*[contains(text(),'"+x+"')]"));
    		if(users.size()==0) {
        		Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'"+x+"')]|//a/*[contains(text(),'"+x+"')]")).isDisplayed());
    		}
        	
        	for(WebElement field:users) {
        		
        			String a=field.getText().trim();
        			int m=a.length();
        			int n=name.length();
        			Assert.assertTrue(n==m);
        			Assert.assertTrue(field.isDisplayed());
        			clickUsingJS(field);
        			break;
        		}
    		}
        		catch(ElementNotFoundException|AssertionError|org.openqa.selenium.NoSuchElementException e) {
        			try {
        			String y=name;
            		y=this.camelCaseWithSpace(y);
            		List<WebElement> users1=driver.findElements(By.xpath("//a[contains(text(),'"+y+"')]|//a/*[contains(text(),'"+y+"')]"));
            		if(users1.size()==0) {
                		Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'"+y+"')]|//a/*[contains(text(),'"+y+"')]")).isDisplayed());
            		}
                	
                	for(WebElement field1:users1) {
                		
                			String a=field1.getText().trim();
                			int m=a.length();
                			int n=name.length();
                			Assert.assertTrue(n==m);
                			Assert.assertTrue(field1.isDisplayed());
                			clickUsingJS(field1);
                			break;
                		}
                	}
                		catch(ElementNotFoundException|AssertionError|org.openqa.selenium.NoSuchElementException e1) {
                			try {
                			String u=name;
                    		u=this.lowercase(u);
                    		List<WebElement> users2=driver.findElements(By.xpath("//a[contains(text(),'"+u+"')]|//a/*[contains(text(),'"+u+"')]"));
                    		if(users2.size()==0) {
                        		Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'"+u+"')]|//a/*[contains(text(),'"+u+"')]")).isDisplayed());
                    		}
                        	
                        	for(WebElement field2:users2) {
                        		
                        			String a=field2.getText().trim();
                        			int m=a.length();
                        			int n=name.length();
                        			Assert.assertTrue(n==m);
                        			Assert.assertTrue(field2.isDisplayed());
                        			clickUsingJS(field2);
                        			break;
                        		}
                        	}
                        		catch(ElementNotFoundException|AssertionError|org.openqa.selenium.NoSuchElementException e3) {
                        			try {
                                		List<WebElement> users3=driver.findElements(By.xpath("//a[contains(text(),'"+name+"')]|//a/*[contains(text(),'"+name+"')]"));
                                		if(users3.size()==0) {
                                    		Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'"+name+"')]|//a/*[contains(text(),'"+name+"')]")).isDisplayed());
                                		}
                                    	
                                    	for(WebElement field2:users3) {
                                    		
                                    			String a=field2.getText().trim();
                                    			Assert.assertTrue(field2.isDisplayed());
                                    			clickUsingJS(field2);
                                    			break;
                                    		}
                                    	}
                                    		catch(ElementNotFoundException|AssertionError|org.openqa.selenium.NoSuchElementException e4) {
                                    			try {
                                    	        	List<WebElement> users=driver.findElements(By.xpath("//a/*[text()='"+name+"']|//a[text(),'"+name+"']"));
                                    	        	if(users.size()==0) {
                                    	        		Assert.assertTrue(driver.findElement(By.xpath("//a/*[text(),'"+name+"']|//a[text(),'"+name+"']")).isDisplayed());
                                    	        	}
                                    	        	
                                    	        	for(WebElement field:users) {
                                    	        			Assert.assertTrue(field.isDisplayed());
                                    	        			clickUsingJS(field);
                                    	        			break;
                                    	        		}
                                    	        	
                                    	        	}
                                    	    	catch(AssertionError e6) {
                                    			}
                                    		}
                        		}
                		}
                	}
        		}
        	}
    	    
    
    
    
    
    
    public WebElement findTab(String name) {
    	WebElement ele=null;
    	try {
//    		List<WebElement> users=driver.findElements(By.xpath("(//*[contains(text(),'"+name+"')])/parent::*[contains(@role,'tab')]//*[contains(text(),'"+name+"')]"));
    	List<WebElement> users=driver.findElements(By.xpath("(//*[contains(text(),'"+name+"')])/../..//a[contains(text(),'"+name+"') and contains(@tabindex,'')]|(//*[contains(text(),'"+name+"')])/../..//a[@href]/span[contains(text(),'"+name+"')]|(//*[contains(text(),'"+name+"')])/../..//a[contains(@title,'"+name+"') and contains(@href,'')]|(//*[contains(text(),'"+name+"')])/parent::*[contains(@role,'tab')]//*[contains(text(),'"+name+"')]"));
    	if(users.size()==0) {
    		this.driver.findElement(By.xpath("(//*[contains(text(),'"+name+"')])/../..//a[contains(text(),'"+name+"') and contains(@tabindex,'')]|(//*[contains(text(),'"+name+"')])/../..//a[@href]/span[contains(text(),'"+name+"')]|(//*[contains(text(),'"+name+"')])/../..//a[contains(@title,'"+name+"') and contains(@href,'')]|(//*[contains(text(),'"+name+"')])/parent::*[contains(@role,'tab')]//*[contains(text(),'"+name+"')]")).isDisplayed();
    	}
    		
    	for(WebElement field:users) {
    		try {
    			Assert.assertTrue(field.isDisplayed());
    			ele=field;
    			break;
    		}
    		catch(AssertionError e) {
    		}
    	}
    	}
    	catch(ElementNotFoundException|AssertionError|org.openqa.selenium.NoSuchElementException e2) {
    		try {
    		String x=name;
    		x=this.camelCaseWithSpace(x);
    		List<WebElement> users=driver.findElements(By.xpath("(//*[contains(text(),'"+x+"')])/../..//a[contains(text(),'"+x+"') and contains(@tabindex,'')]|(//*[contains(text(),'"+x+"')])/../..//a[@href]/span[contains(text(),'"+x+"')]|(//*[contains(text(),'"+x+"')])/../..//a[contains(@title,'"+x+"') and contains(@href,'')]|(//*[contains(text(),'"+x+"')])/parent::*[contains(@role,'tab')]"));
    		if(users.size()==0) {
        		Assert.assertTrue(driver.findElement(By.xpath("(//*[contains(text(),'"+x+"')])/../..//a[contains(text(),'"+x+"') and contains(@tabindex,'')]|(//*[contains(text(),'"+x+"')])/../..//a[@href]/span[contains(text(),'"+x+"')]|(//*[contains(text(),'"+x+"')])/../..//a[contains(@title,'"+x+"') and contains(@href,'')]|(//*[contains(text(),'"+x+"')])/parent::*[contains(@role,'tab')]")).isDisplayed());
    		}
        		
        	for(WebElement field:users) {
        		
        			Assert.assertTrue(field.isDisplayed());
        			ele=field;
        			break;
        		}
    		}
        		catch(ElementNotFoundException|AssertionError|org.openqa.selenium.NoSuchElementException e) {
        			String y=name;
            		y=this.lowercase(y);
            		List<WebElement> users1=driver.findElements(By.xpath("(//*[contains(text(),'"+y+"')])/../..//a[contains(text(),'"+y+"') and contains(@tabindex,'')]|(//*[contains(text(),'"+y+"')])/../..//a[@href]/span[contains(text(),'"+y+"')]|(//*[contains(text(),'"+y+"')])/../..//a[contains(@title,'"+y+"') and contains(@href,'')]|(//*[contains(text(),'"+y+"')])/parent::*[contains(@role,'tab')]"));
            		if(users1.size()==0) {
                		Assert.assertTrue(driver.findElement(By.xpath("(//*[contains(text(),'"+y+"')])/../..//a[contains(text(),'"+y+"') and contains(@tabindex,'')]|(//*[contains(text(),'"+y+"')])/../..//a[@href]/span[contains(text(),'"+y+"')]|(//*[contains(text(),'"+y+"')])/../..//a[contains(@title,'"+y+"') and contains(@href,'')]|(//*[contains(text(),'"+y+"')])/parent::*[contains(@role,'tab')]")).isDisplayed());
            		}
                		
                	for(WebElement field1:users1) {
                		try {
                			Assert.assertTrue(field1.isDisplayed());
                			ele=field1;
                			break;
                		}
                		catch(AssertionError e1) {
                		}
                	}
        		}
        	}
		return ele;
    }
    
    
    
    
    
    public void hoverOver(WebElement ele) throws InterruptedException {
    	Actions action=new Actions(driver);
    	action.moveToElement(ele).build().perform();
    	Thread.sleep(3000);
    }
    
    
    
    
    
    @FindBy(xpath = "//h4[text()='PLANFOCUS NOTICE']/../../../../../../../..//button")
	WebElement popClose;
	
	public void close_Popup() {
		try {
//		driver.navigate().refresh();
		if(popClose.isDisplayed()) {
			popClose.click();
		}
		}catch(Exception e) {
			
		}
		
//		this.driver.switchTo().alert();
//		Actions action = new Actions(driver);
//		action.sendKeys(Keys.ESCAPE).build().perform();
		
	}
	
	
	
	public void compareImage(String image1, String image2) throws IOException {
		BufferedImage imgA = ImageIO.read(new File("F:\\temp\\"+image1+".png")); 
	    BufferedImage imgB = ImageIO.read(new File("F:\\temp\\"+image2+".png")); 
//	    subtractImages(imgA,imgB);
	    subtractImages1(imgA,imgB);
//	    boolean same=bufferedImagesEqual(imgA,imgB);
//	    if(same==true)
//	    	System.out.println("both images are same    *****************");
//	    else
//	    	System.out.println("both images are different    *****************");
	}
	    boolean bufferedImagesEqual(BufferedImage img1, BufferedImage img2) throws IOException {
//	    	BufferedImage image3 = new BufferedImage(img1.getWidth(), img1.getHeight(), img1.getType());
	    if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) {
	     for (int x = 0; x < img1.getWidth(); x++) {
	      for (int y = 0; y < img1.getHeight(); y++) {
	       if (img1.getRGB(x, y) != img2.getRGB(x, y))
//	    	   image3.setRGB(x, y, 250);
	        return false;
	       }
	      }
//	     ImageIO.write(image3, "png",  new File("F:\\temp\\image.png"));
	     } else {
	        return false;
	     }
	     return true;
	    }
    
	    
	    private static void subtractImages(BufferedImage image1, BufferedImage image2) throws IOException {
	        BufferedImage image3 = new BufferedImage(image1.getWidth(), image1.getHeight(), image1.getType());
	        int color;
	        for(int x = 0; x < image1.getWidth(); x++)
	            for(int y = 0; y < image1.getHeight(); y++) {
	                color = Math.abs(image2.getRGB(x, y) - image1.getRGB(x, y));                
	                image3.setRGB(x, y, color);
	            }
	        ImageIO.write(image3, "png",  new File("F:\\temp\\image.png"));
	     }
	    
	    private static void subtractImages1(BufferedImage image1, BufferedImage image2) throws IOException {
	        BufferedImage image3 = new BufferedImage(image1.getWidth(), image1.getHeight(), image1.getType());
	        int color;
	        for(int x = 0; x < image1.getWidth(); x++)
	            for(int y = 0; y < image1.getHeight(); y++) {
	            	if (image1.getRGB(x, y) != image2.getRGB(x, y)) {
//	            		image3.getGraphics().drawImage(0, 0, null);
	            		image3.setRGB(x, y, new Color(0,255,0).getRGB() );
	            	}
	            	else {
	            		color=image2.getRGB(x, y);
	            		int color1 = image1.getRGB(x, y);
//	            		System.out.println("@@@@@@@@@@@@@@"+color);
	            		image3.setRGB(x, y, color);
	            		image3.setRGB(x, y, color1);
	            	}
//	                color = Math.abs(image1.getRGB(x, y) - image2.getRGB(x, y));                
//	                image3.setRGB(x, y, color);
	            }
	        ImageIO.write(image3, "png",  new File("F:\\temp\\output.png"));
	     }
    
    
    
    public void setBrowser(String browser) {
    	switch(browser.toLowerCase().trim()) {
    	case "chrome":
    		System.setProperty("webdriver.chrome.driver", "C:/Users/Powade/git/PlanSponserSite/src/funcTest/resources/drivers/chromedriver.exe");
        	this.driver = new ChromeDriver();
        	break;
        	
    	case "firefox":
    		System.setProperty("webdriver.gecko.driver", "C:/Users/Powade/git/PlanSponserSite/src/funcTest/resources/drivers/geckodriver.exe");
    		this.driver = new FirefoxDriver();
    		break;
    		
    	case "ie":
    	case "internet explorer":
    		System.setProperty("webdriver.ie.driver", "C:/Users/Powade/git/PlanSponserSite/src/funcTest/resources/drivers/IEDriverServer.exe");
    		this.driver = new InternetExplorerDriver();
    		break;
    		
    	default:
			throw new RuntimeException("Invalid Internal Simulation Provided...");
    	}
    	
    }
    
    public String firstword(String words) {
    	String a=words;
      String[] cnt=a.split(" ");
      return cnt[0];
    }
    
    public String secondWord(String words) {
    	String a=words;
      String[] cnt=a.split(" ");
      return cnt[1];
    }
    
    public String lowercase(String words) {
    	String a=words;
    	String b=a.toLowerCase().trim();
      return b;
    }
    
    public String uppercase(String words) {
    	String a=words;
    	String b=a.toUpperCase().trim();
      return b;
    }
    
    public String removeSpaces(String words) {
    	String a=words;
    	String b=a.replaceAll("\\ ", "");
      return b;
    }
    
    public String replaceDotUnderscoreWithSpace(String words) {
    	String a=words;
    	String b=a.replaceAll("\\.|\\_", " ");
      return b;
    }
    
    public String camelCaseWithSpace(String words) {
    	String a=words;
    	final char[] delimiters = { ' ', '_' };
    	String b=WordUtils.capitalizeFully(a, delimiters);
      return b;
    }
    
    public String camelCaseWithoutSpace(String words) {
    	String a=words;
    	final char[] delimiters = { ' ', '_' };
    	String b=WordUtils.capitalizeFully(a, delimiters);
    	String c=b.replaceAll("\\ ", "");
      return c;
    }
    
    public String firstLowerSceondCamel(String words) {
    	String a=words;
    	final char[] delimiters = { ' ', '_' };
    	String b=WordUtils.capitalizeFully(a, delimiters);
    	String c=b.replaceAll("\\ ", "");
      return c;
    }
    
    public String camelCaseWithSingleCharacterInLower(String words) {
    	String a=words;
    	final char[] delimiters = { ' ', '_' };
    	String b=WordUtils.capitalizeFully(a, delimiters);
    	String[] cnt=b.split(" ");
    	String r="";
    	for(String x:cnt) {
    		if(x.length()<=2)
    			x=x.toLowerCase();
    	r=r+" "+x;	
    	}
    	r=r.trim();
    	System.out.println(r);
    	return r;
    }
    
    
    public static void main(String[] args) {
    	 int[] arr = {-2,2,-1,1,-3,3}; 
    	 List<Integer> list = new ArrayList<Integer>();
    	 List<Integer> list1 = new ArrayList<Integer>();
    	 int temp;
         for(int i=0;i<arr.length;i++) {
        	 if(arr[i]<0) {
//        		 temp=arr[i];
        		 list.add(arr[i]);
        	 }
        	 else {
        		 list1.add(arr[i]);
        	 }
        		
         }
         System.out.println(list);
         System.out.println(list1);
    }
    


}
