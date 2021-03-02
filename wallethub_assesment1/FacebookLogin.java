package wallethub_assesment1;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.WebElement;

import utility.ReusableMethods;

public class FacebookLogin {
	
	static String username = "id__email";
	static String password = "id__pass";
	static String btn_login = "name__login";
	static String searchField = "xpath__//input[@placeholder='Search Facebook']";
	static String url = "https://www.facebook.com/";
	static HashMap<String, String> hash_map = new HashMap<>();
	
	
	public static void main(String args[]) throws Exception{
		
		System.out.println(System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
//		ReusableMethods.setup();
//		ReusableMethods.enter_url(url);
//		enter_credentials();
//		ReusableMethods.click_element(btn_login);
//		verify_postlogin();		
	}
    
    public static void enter_credentials() throws Exception{
    	hash_map = ReusableMethods.readdata_excel();
    	ReusableMethods.set_text(username,hash_map.get("Username"));
    	ReusableMethods.set_text(password,hash_map.get("Password"));
    	System.out.println("Entered username and password successfully");
    	
    }
    
    public static void verify_postlogin(){
    	WebElement searchElement = ReusableMethods.find_element(searchField);
    	if(ReusableMethods.element_display_state(searchElement)){
    		System.out.println("Search field is visible");
    		System.out.println("Post login status message --Hello World");
    	}
    }
    
    


	

}
