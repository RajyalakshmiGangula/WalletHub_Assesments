package utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ReusableMethods {
	static WebDriver browser = null;
	static HashMap<String, String> hmap = new HashMap<String, String>();

	 public static void setup(){
		 /**
		  * 1. This method is allow to access the chrome browser
		  * 2. Maximize the window and 
		  * 3. providing 30 seconds implicit wait to load the elements and web pages
		  * Parameters: None
		  * Return : None
		  *
		  */
         
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/drivers/chromedriver.exe");
			browser = new ChromeDriver();  
			browser.manage().window().maximize();
	    	browser.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		}
	 
	    public static void enter_url(String url){
	    	/**
	    	 * This method allows to enter the application url
	    	 * Parameters: String
	    	 * Return : String
	    	 * 
	    	 */
	    	browser.get(url);
	    	
	    }
	 
	 public static void click_element(String elment_info){
		 /**
	    	 * This method allows to click the web element like button, check box etc..
	    	 * Parameters: String
	    	 * Return : None
	    	 * 
	    	 */
	    	
	    	WebElement element = find_element(elment_info);
	    	if(element_display_state(element))
	    		element.click();
	    		System.out.println("Clicked on the element successfully");	
	    	
	    }
		
	   

		public static WebElement find_element(String element) {
			 /**
	    	 * This method allows to find the web element from string.
	    	 * Eg: passed string is like "id__name". it splits the element by __ and stores in string array.
	    	 * Parameters: String
	    	 * Return : WebElement
	    	 * 
	    	 */
	    	String string[] = null;
	    	String identifier_type = null;
	    	String element_property = null;
	    	string = element.split("__");
	    	identifier_type = string[0];
	    	element_property = string[1];
	    	WebElement web_element = null;
	    	
	    	if(identifier_type.equalsIgnoreCase("id")){
	    		web_element=browser.findElement(By.id(element_property));
	    	}
	    	else if(identifier_type.equalsIgnoreCase("name")){
	    		web_element=browser.findElement(By.name(element_property));
	    	}
	    	else if(identifier_type.equalsIgnoreCase("className")){
	    		web_element=browser.findElement(By.className(element_property));
	    	}
	    	else if(identifier_type.equalsIgnoreCase("linkText")){
	    		web_element=browser.findElement(By.linkText(element_property));
	    	}
	    	else if(identifier_type.equalsIgnoreCase("partialLinkText")){
	    		web_element=browser.findElement(By.partialLinkText(element_property));
	    	}
	    	else if(identifier_type.equalsIgnoreCase("xpath")){
	    		web_element=browser.findElement(By.xpath(element_property));
	    	}
	    	else if(identifier_type.equalsIgnoreCase("cssSelector")){
	    		web_element=browser.findElement(By.cssSelector(element_property));
	    	}
	    	
	 
			return web_element;
			
		}
	    
	    public static void set_text(String webelemnt, String elemnt_text){
	    	 /**
	    	 * This method allows to enter text/data in the text field.
	    	 * Parameters: String
	    	 * Return : None
	    	 * 
	    	 */
	    	WebElement web_element = find_element(webelemnt);
	    	if(element_display_state(web_element))
	    		web_element.sendKeys(elemnt_text);
	    	
	    }
	    
	    public static boolean element_display_state(WebElement element) {
	    	 /**
	    	 * This method allows to check the web element is present or not on the page
	    	 * Parameters: String
	    	 * Return : None
	    	 * 
	    	 */
	    	if(element.isDisplayed() && element.isEnabled()){
	    		return true;
	    	}

			return false;
		}
	    
	    public static HashMap<String, String> readdata_excel() throws Exception{
	    	 /**
	    	 * This method allows to read data from excel file and return data in key and value pairs.
	    	 * Parameters: None
	    	 * Return : HashMap
	    	 * 
	    	 */
			FileInputStream inputStream = new FileInputStream(new File(System.getProperty("user.dir")+"/src/testdata/wallethub_assesment_Data.xlsx"));
			XSSFWorkbook wbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = wbook.getSheet("Assesment1");
			int last_row = sheet.getLastRowNum();

			for(int i=0; i<last_row+1;i++){
				XSSFRow row = sheet.getRow(i);
				for(int j=0;j<row.getLastCellNum();j++){
					String key_cell = row.getCell(0).getStringCellValue();
					String value_cell = row.getCell(1).getStringCellValue();
					System.out.println("Keys from excel "+ key_cell + "Values from excel "+ value_cell);
					hmap.put(key_cell, value_cell);
				}
				
			}
			return hmap;
			
		}
	    
	    public static void mouse_hover_element(WebElement star_element){
	    	 /**
	    	 * This method allows to mouse hover on the web element
	    	 * Parameters: WebElement
	    	 * Return : None
	    	 * 
	    	 */
	    	Actions action = new Actions(browser);
	    	action.moveToElement(star_element).build().perform();
	    }
	   
	    public static List<WebElement> find_listelements(String element) {
	    	 /**
	    	 * This method allows to find the list of web elements .
	    	 * Eg: passed string is like "id__name". it splits the element by __ and stores in string array.
	    	 * Parameters: String
	    	 * Return : WebElement
	    	 * 
	    	 */
	    	String string[] = null;
	    	String identifier_type = null;
	    	String element_property = null;
	    	string = element.split("__");
	    	identifier_type = string[0];
	    	element_property = string[1];
	    	List<WebElement> web_element = null;
	    	
	    	if(identifier_type.equalsIgnoreCase("id")){
	    		web_element=browser.findElements(By.id(element_property));
	    	}
	    	else if(identifier_type.equalsIgnoreCase("name")){
	    		web_element=browser.findElements(By.name(element_property));
	    	}
	    	else if(identifier_type.equalsIgnoreCase("className")){
	    		web_element=browser.findElements(By.className(element_property));
	    	}
	    	else if(identifier_type.equalsIgnoreCase("linkText")){
	    		web_element=browser.findElements(By.linkText(element_property));
	    	}
	    	else if(identifier_type.equalsIgnoreCase("partialLinkText")){
	    		web_element=browser.findElements(By.partialLinkText(element_property));
	    	}
	    	else if(identifier_type.equalsIgnoreCase("xpath")){
	    		web_element=browser.findElements(By.xpath(element_property));
	    	}
	    	else if(identifier_type.equalsIgnoreCase("cssSelector")){
	    		web_element=browser.findElements(By.cssSelector(element_property));
	    	}
	    	
	 
			return web_element;
			
		}
	    
	    
	  
	    
	    
}
